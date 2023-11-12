package br.com.wkbloodbank.controller;

import br.com.wkbloodbank.dto.*;
import br.com.wkbloodbank.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/blood-bank/pessoas")
public class BloodBankController {

    private final PessoaService pessoaService;

    public BloodBankController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("salvar-lista")
    public ResponseEntity<List<PessoaResponseDTO>> salvarLista(@RequestBody List<PessoaRequestDTO> pessoaRequestDTOList){
        List<PessoaResponseDTO> pessoaResponseDTOS = this.pessoaService.salvarPessoasPorList(pessoaRequestDTOList);
        return new ResponseEntity<>(pessoaResponseDTOS, HttpStatus.CREATED);
    }

    @PostMapping("buscar-por-filtros-page")
    public ResponseEntity<Page<PessoaResponseDTO>> buscarPorFiltros(@RequestBody PessoaRequestDTO pessoaRequestDTO, Pageable pageable){
        Page<PessoaResponseDTO> pessoaResponseDTOS = this.pessoaService.buscarPessoasPorFiltrosPage(pageable, pessoaRequestDTO);
        return new ResponseEntity<>(pessoaResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("buscar-quantidade-por-estado")
    public ResponseEntity<List<ContagemPorEstadoDTO>> buscarQuantidadePorEstado(){
        List<ContagemPorEstadoDTO> contagemPorEstadoDTOList = this.pessoaService.buscarContagemPorEstado();
        return new ResponseEntity<>(contagemPorEstadoDTOList, HttpStatus.OK);
    }

    @GetMapping("imc-medio-por-faixa-idade")
    public ResponseEntity<List<ImcMedioPorFaixaIdadeDTO>> buscarImcMedioPorFaixaIdade(){
        List<ImcMedioPorFaixaIdadeDTO> imcMedioPorFaixaIdadeDTOList = this.pessoaService.buscarImcMedioPorFaixaIdade();
        return new ResponseEntity<>(imcMedioPorFaixaIdadeDTOList, HttpStatus.OK);
    }

    @GetMapping("percentual-obesos-por-sexo")
    public ResponseEntity<List<PercentualObesosPorSexoDTO>> buscarPercentualObesosPorSexo(){
        List<PercentualObesosPorSexoDTO> percentualObesosPorSexoDTOList = this.pessoaService.buscarPercentualObesosPorSexo();
        return new ResponseEntity<>(percentualObesosPorSexoDTOList, HttpStatus.OK);
    }

    @GetMapping("media-idade-por-tipo-sangue")
    public ResponseEntity<List<MediaIdadePorTipoSangueDTO>> buscarMediaIdadePorTipoSangue(){
        List<MediaIdadePorTipoSangueDTO> mediaIdadePorTipoSangueDTOS = this.pessoaService.buscarMediaIdadePorTipoSangue();
        return new ResponseEntity<>(mediaIdadePorTipoSangueDTOS, HttpStatus.OK);
    }

    @GetMapping("quantidade-possiveis-doadores-por-tipo-sangue")
    public ResponseEntity<List<QuantidadeDePossiveisDoadoresPorTipoSangueDTO>> buscarQuantidadePossiveisDoadoresPorTipoSangue(){
        List<QuantidadeDePossiveisDoadoresPorTipoSangueDTO> quantidadeDePossiveisDoadoresPorTipoSangueDTOS = this.pessoaService.buscarQuantidadeDePossiveisDoadoresPorTipoSangue();
        return new ResponseEntity<>(quantidadeDePossiveisDoadoresPorTipoSangueDTOS, HttpStatus.OK);
    }
}
