package br.com.wkbloodbank.utils;

public class UtilImc {

    public static Double cacularImc(Double peso, Double altura){
        return peso / Math.pow(altura, 2);
    }



}
