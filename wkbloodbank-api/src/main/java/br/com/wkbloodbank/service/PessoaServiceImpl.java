package br.com.wkbloodbank.service;

import br.com.wkbloodbank.dto.ContagemPorEstadoDTO;
import br.com.wkbloodbank.dto.ImcMedioPorFaixaIdadeDTO;
import br.com.wkbloodbank.dto.PessoaRequestDTO;
import br.com.wkbloodbank.dto.PessoaResponseDTO;
import br.com.wkbloodbank.handlers.exceptions.BadRequestException;
import br.com.wkbloodbank.handlers.exceptions.NotFoundEntityException;
import br.com.wkbloodbank.handlers.exceptions.ServerSideException;
import br.com.wkbloodbank.model.PessoaModel;
import br.com.wkbloodbank.repository.PessoaRepository;
import br.com.wkbloodbank.repository.PessoaSpecification;
import br.com.wkbloodbank.utils.DataUtil;
import br.com.wkbloodbank.utils.UtilImc;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService{

    private final PessoaRepository pessoaRepository;

    private final ModelMapper modelMapper;

    public PessoaServiceImpl(PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
    }

    //TODO - Validar por arquivo
    @Override
    public List<PessoaResponseDTO> salvarPessoasPorArquivoJson(MultipartFile fileJson) {
        try {
            String json = new String(fileJson.getBytes());

            List<PessoaModel> listPessoas = this.modelMapper.map(json, new TypeToken<List<PessoaModel>>() {}.getType());

            return this.pessoaRepository.saveAll(listPessoas)
                    .stream()
                    .map(pessoaModel -> this.modelMapper.map(pessoaModel, PessoaResponseDTO.class))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new BadRequestException("Não foi possível salver o arquivo JSON {} " + e.getMessage());
        }
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

            //TODO - Log com quantidade
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
                            Collectors.averagingDouble(pessoaModel -> UtilImc.cacularImc(pessoaModel.getPeso(), pessoaModel.getAltura()))
                    ));

            return imcMedioPorFaixaDeIdade.entrySet()
                    .stream()
                    .map(entry -> new ImcMedioPorFaixaIdadeDTO(entry.getValue(), entry.getKey()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServerSideException("Não foi possível calcular o IMC médio por faixa de idade: " + e.getMessage());
        }

    }


}
