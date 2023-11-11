package br.com.wkbloodbank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    private String rg;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private String sexo;

    @NotNull
    private String mae;

    @NotNull
    private String pai;

    @NotNull
    private String email;

    @NotNull
    private String cep;

    @NotNull
    private String endereco;

    @NotNull
    private Integer numero;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String estado;

    @NotNull
    private String telefoneFixo;

    @NotNull
    private String celular;

    @NotNull
    private Double altura;

    @NotNull
    private Double peso;

    @NotNull
    private String tipoSanguineo;


}
