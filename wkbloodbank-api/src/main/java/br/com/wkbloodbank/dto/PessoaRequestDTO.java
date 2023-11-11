package br.com.wkbloodbank.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaRequestDTO {
    private String nome;
    private String cpf;
    private String rg;
    private LocalDate dataNascimento;
    private String sexo;
    private String mae;
    private String pai;
    private String email;
    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefoneFixo;
    private String celular;
    private Double altura;
    private Double peso;
    private String tipoSanguineo;
}
