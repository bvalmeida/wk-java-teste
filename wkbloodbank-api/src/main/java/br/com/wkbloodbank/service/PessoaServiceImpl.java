package br.com.wkbloodbank.service;

import br.com.wkbloodbank.dto.ContagemPorEstadoDTO;
import br.com.wkbloodbank.dto.PessoaRequestDTO;
import br.com.wkbloodbank.dto.PessoaResponseDTO;
import br.com.wkbloodbank.handlers.exceptions.BadRequestException;
import br.com.wkbloodbank.handlers.exceptions.NotFoundEntityException;
import br.com.wkbloodbank.model.PessoaModel;
import br.com.wkbloodbank.repository.PessoaRepository;
import br.com.wkbloodbank.repository.PessoaSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            List<PessoaModel> pessoaModels = this.pessoaRepository.saveAll(pessoaRequestDTOList
                    .stream()
                    .map(pessoaRequestDTO -> this.modelMapper.map(pessoaRequestDTO, PessoaModel.class))
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


}
