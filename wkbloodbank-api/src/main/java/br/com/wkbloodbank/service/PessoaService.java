package br.com.wkbloodbank.service;

import br.com.wkbloodbank.dto.ContagemPorEstadoDTO;
import br.com.wkbloodbank.dto.ImcMedioPorFaixaIdadeDTO;
import br.com.wkbloodbank.dto.PessoaRequestDTO;
import br.com.wkbloodbank.dto.PessoaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PessoaService {

    List<PessoaResponseDTO> salvarPessoasPorArquivoJson(MultipartFile fileJson);

    List<PessoaResponseDTO> salvarPessoasPorList(List<PessoaRequestDTO> pessoaRequestDTOList);

    Page<PessoaResponseDTO> buscarPessoasPorFiltrosPage(Pageable pageable, PessoaRequestDTO pessoaRequestDTO);

    List<ContagemPorEstadoDTO> buscarContagemPorEstado();

    List<ImcMedioPorFaixaIdadeDTO> buscarImcMedioPorFaixaIdade();

}
