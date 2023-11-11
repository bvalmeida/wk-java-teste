package br.com.wkbloodbank.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaRequestDTO {
    private String nome;
    private String cpf;
    private String rg;

    @JsonProperty("data_nasc")
    private String dataNascimento;

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

    @JsonProperty("telefone_fixo")
    private String telefoneFixo;

    private String celular;
    private Double altura;
    private Double peso;

    @JsonProperty("tipo_sanguineo")
    private String tipoSanguineo;
}
