package br.com.wkbloodbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContagemPorEstadoDTO {
    private String estado;
    private Long quantidade;
}
