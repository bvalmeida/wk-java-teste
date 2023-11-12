package br.com.wkbloodbank.service;

import br.com.wkbloodbank.dto.*;
import br.com.wkbloodbank.handlers.exceptions.BadRequestException;
import br.com.wkbloodbank.handlers.exceptions.NotFoundEntityException;
import br.com.wkbloodbank.handlers.exceptions.ServerSideException;
import br.com.wkbloodbank.model.PessoaModel;
import br.com.wkbloodbank.repository.PessoaRepository;
import br.com.wkbloodbank.repository.PessoaSpecification;
import br.com.wkbloodbank.utils.DataUtil;
import br.com.wkbloodbank.utils.ImcUtil;
import br.com.wkbloodbank.utils.PessoaUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService{

    private final PessoaRepository pessoaRepository;

    private final ModelMapper modelMapper;

    public PessoaServiceImpl(PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PessoaResponseDTO> salvarPessoasPorList(List<PessoaRequestDTO> pessoaRequestDTOList) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            List<PessoaModel> pessoaModels = this.pessoaRepository.saveAll(pessoaRequestDTOList
                    .stream()
                    .map(pessoaRequestDTO -> {
                        PessoaModel pessoaModel = this.modelMapper.map(pessoaRequestDTO, PessoaModel.class);
                        pessoaModel.setDataNascimento(LocalDate.parse(pessoaRequestDTO.getDataNascimento().replace("\\", ""), formatter));
                        return pessoaModel;
                    })
                    .collect(Collectors.toList()));

            return pessoaModels.stream().map(pessoaModel -> this.modelMapper
                    .map(pessoaModel, PessoaResponseDTO.class)).collect(Collectors.toList());
        }catch (Exception e){
            throw new BadRequestException("Não foi possível salvar a lista de pessoas {} " + e.getMessage());
        }
    }

    @Override
    public Page<PessoaResponseDTO> buscarPessoasPorFiltrosPage(Pageable pageable, PessoaRequestDTO pessoaRequestDTO) {
        try{
            PessoaModel pessoaModelParaFiltro = this.modelMapper.map(pessoaRequestDTO, PessoaModel.class);

            Page<PessoaModel> pessoasByPage = this.pessoaRepository.findAll(PessoaSpecification.getByFilters(pessoaModelParaFiltro),pageable);
            return pessoasByPage.map(pessoaModel -> this.modelMapper.map(pessoaModel, PessoaResponseDTO.class));
        }catch (Exception e){
            throw new NotFoundEntityException("Não foi possível buscar as pessoas por page, verifique e tente novamente");
        }
    }

    @Override
    public List<ContagemPorEstadoDTO> buscarContagemPorEstado() {
        List<String> distinctEstados = this.pessoaRepository.findDistinctEstados();
        List<ContagemPorEstadoDTO> contagemPorEstadoDTOList = new ArrayList<>();

        distinctEstados.stream().forEach(estado -> {
            Long quantidadePorEstado = this.pessoaRepository.countByEstado(estado);
            ContagemPorEstadoDTO contagemPorEstadoDTO = new ContagemPorEstadoDTO(estado, quantidadePorEstado);
            contagemPorEstadoDTOList.add(contagemPorEstadoDTO);
        });

        return contagemPorEstadoDTOList;
    }

    @Override
    public List<ImcMedioPorFaixaIdadeDTO> buscarImcMedioPorFaixaIdade() {
        List<PessoaModel> pessoaModelList = this.pessoaRepository.findAll();

        try {
            Map<String, Double> imcMedioPorFaixaDeIdade = pessoaModelList
                    .stream()
                    .collect(Collectors.groupingBy(
                            pessoaModel -> {
                                int idade = DataUtil.calcularIdade(pessoaModel.getDataNascimento());
                                int faixaInicial = (idade / 10) * 10;
                                int faixaFinal = faixaInicial + 9;
                                return String.format("%d a %d anos", faixaInicial, faixaFinal);
                            },
                            Collectors.averagingDouble(pessoaModel -> ImcUtil.cacularImc(pessoaModel.getPeso(), pessoaModel.getAltura()))
                    ));

            return imcMedioPorFaixaDeIdade.entrySet()
                    .stream()
                    .map(entry -> new ImcMedioPorFaixaIdadeDTO(entry.getValue(), entry.getKey()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServerSideException("Não foi possível calcular o IMC médio por faixa de idade: " + e.getMessage());
        }

    }

    @Override
    public List<PercentualObesosPorSexoDTO> buscarPercentualObesosPorSexo() {
        try{
            List<PessoaModel> pessoasSexoFeminino = this.pessoaRepository.findBySexo("Feminino");
            List<PessoaModel> pessoasSexoMasculino = this.pessoaRepository.findBySexo("Masculino");
            List<PercentualObesosPorSexoDTO> percentualObesosPorSexoDTOList = new ArrayList<>();

            Double porcentagemObesosPorSexoFeminino = PessoaUtil.calcularPorcentagemObesosPorSexo(pessoasSexoFeminino);
            Double porcentagemObesosPorSexoMasculino = PessoaUtil.calcularPorcentagemObesosPorSexo(pessoasSexoMasculino);

            percentualObesosPorSexoDTOList.add(new PercentualObesosPorSexoDTO(porcentagemObesosPorSexoFeminino.toString(), "Feminino"));
            percentualObesosPorSexoDTOList.add(new PercentualObesosPorSexoDTO(porcentagemObesosPorSexoMasculino.toString(), "Masculino"));

            return percentualObesosPorSexoDTOList;
        }catch (Exception e){
            throw new ServerSideException("Não foi possível buscar o percentual de obesos por sexo: " + e.getMessage());
        }
    }

    @Override
    public List<MediaIdadePorTipoSangueDTO> buscarMediaIdadePorTipoSangue() {
        List<String> byDistinctTipoSanguineo = this.pessoaRepository.findByDistinctTipoSanguineo();
        List<MediaIdadePorTipoSangueDTO> mediaIdadePorTipoSangueDTOList = new ArrayList<>();

        byDistinctTipoSanguineo
                .stream()
                .forEach(tipoSanguineo -> {
                    List<PessoaModel> pessoaModelbyTipoSanguineo = this.pessoaRepository.findByTipoSanguineo(tipoSanguineo);
                    String mediaIdade = PessoaUtil.calcularMediaIdade(pessoaModelbyTipoSanguineo);
                    mediaIdadePorTipoSangueDTOList.add(new MediaIdadePorTipoSangueDTO(mediaIdade, tipoSanguineo));
                });

        return mediaIdadePorTipoSangueDTOList;
    }

    @Override
    public List<QuantidadeDePossiveisDoadoresPorTipoSangueDTO> buscarQuantidadeDePossiveisDoadoresPorTipoSangue() {
        List<String> tipoSanguineoList = this.pessoaRepository.findByDistinctTipoSanguineo();
        List<QuantidadeDePossiveisDoadoresPorTipoSangueDTO> quantidadeDePossiveisDoadoresPorTipoSangueDTOList = new ArrayList<>();

        tipoSanguineoList.stream().forEach(tipoSanguineo -> {
            List<PessoaModel> pessoaListByTipoSanguineo = this.pessoaRepository.findByTipoSanguineo(tipoSanguineo);

            List<PessoaModel> pessoaModelPodeDoarList = PessoaUtil.validarPessoasQuePodemDoar(pessoaListByTipoSanguineo);

            QuantidadeDePossiveisDoadoresPorTipoSangueDTO quantidadeDePossiveisDoadoresPorTipoSangueDTO = new QuantidadeDePossiveisDoadoresPorTipoSangueDTO();
            quantidadeDePossiveisDoadoresPorTipoSangueDTO.setTipoSanguineo(tipoSanguineo);

            pessoaModelPodeDoarList.stream().forEach(pessoaModel -> {
                List<String> podeReceberDoacao = PessoaUtil.validarDoacaoPorTipoSangue(pessoaModel.getTipoSanguineo());

                List<PessoaModel> pessoasQuePodemDoarParaTipoSanquineo = this.pessoaRepository.findByTipoSanguineoList(podeReceberDoacao);
                Integer quantidade = pessoasQuePodemDoarParaTipoSanquineo.size();
                quantidadeDePossiveisDoadoresPorTipoSangueDTO.setQuantidade(quantidade.toString());

            });
            quantidadeDePossiveisDoadoresPorTipoSangueDTOList.add(quantidadeDePossiveisDoadoresPorTipoSangueDTO);

        });

        return quantidadeDePossiveisDoadoresPorTipoSangueDTOList;
    }


}
