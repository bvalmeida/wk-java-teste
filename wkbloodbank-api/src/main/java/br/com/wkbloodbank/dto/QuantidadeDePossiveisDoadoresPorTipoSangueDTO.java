package br.com.wkbloodbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantidadeDePossiveisDoadoresPorTipoSangueDTO {

    private String quantidade;
    private String tipoSanguineo;

}
