package br.com.wkbloodbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PercentualObesosPorSexoDTO {

    private String porcentagem;
    private String sexo;
}
