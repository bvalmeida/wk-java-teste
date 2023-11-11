package br.com.wkbloodbank.utils;

import java.time.LocalDate;
import java.time.Period;

public class DataUtil {

    public static Integer calcularIdade(LocalDate dataNascimento){
        Period periodo = Period.between(dataNascimento, LocalDate.now());
        return periodo.getYears();
    }

}
