package br.com.wkbloodbank.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ImcUtil {

    public static Double cacularImc(Double peso, Double altura){
        double resultadoImc = peso / Math.pow(altura, 2);

        BigDecimal resultadoImcFormatado = new BigDecimal(resultadoImc).setScale(2, RoundingMode.HALF_UP);
        return resultadoImcFormatado.doubleValue();
    }



}
