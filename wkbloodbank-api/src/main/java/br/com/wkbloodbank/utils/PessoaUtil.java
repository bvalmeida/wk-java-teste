package br.com.wkbloodbank.utils;

import br.com.wkbloodbank.model.PessoaModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PessoaUtil {

    public static Double calcularPorcentagemObesosPorSexo(List<PessoaModel> pessoasPorSexo){
        Long contagemPessoasObesas = pessoasPorSexo
                .stream()
                .filter(pessoaModel -> ImcUtil.cacularImc(pessoaModel.getPeso(), pessoaModel.getAltura()) > 30)
                .count();

        double resultadoPorcentagem = contagemPessoasObesas * 100.0 / pessoasPorSexo.size();

        BigDecimal resultadoPorcentagemFormatado = new BigDecimal(resultadoPorcentagem).setScale(2, RoundingMode.HALF_UP);
        return resultadoPorcentagemFormatado.doubleValue();
    }

    public static String calcularMediaIdade(List<PessoaModel> pessoaModelList){
        List<Integer> idades = new ArrayList<>();

        pessoaModelList.stream().forEach(pessoaModel -> {
            idades.add(DataUtil.calcularIdade(pessoaModel.getDataNascimento()));
        });

        double mediaIdades = idades.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();

        BigDecimal resultadoMediaIdadesFormatado = new BigDecimal(mediaIdades).setScale(2, RoundingMode.HALF_UP);
        return resultadoMediaIdadesFormatado.toString();
    }

    public static List<PessoaModel> validarPessoasQuePodemDoar(List<PessoaModel> pessoaModelList){
        List<PessoaModel> pessoasQuePodemDoarSangue = new ArrayList<>();

        pessoaModelList.stream().forEach(pessoaModel -> {
            Integer idade = DataUtil.calcularIdade(pessoaModel.getDataNascimento());
            if(idade >= 16 && idade <= 69 && pessoaModel.getPeso() > 50){
                pessoasQuePodemDoarSangue.add(pessoaModel);
            }
        });

        return pessoasQuePodemDoarSangue;
    }

    public static List<String> validarDoacaoPorTipoSangue(String tipoSanguineo){
        Map<String, List<String>> doacoesPermitidas = new HashMap<>();
        doacoesPermitidas.put("A+", Arrays.asList("A+", "A-", "O+", "O-"));
        doacoesPermitidas.put("A-", Arrays.asList("A-", "O-"));
        doacoesPermitidas.put("B+", Arrays.asList("B+", "B-", "0+", "0-"));
        doacoesPermitidas.put("B-", Arrays.asList("B-", "0-"));
        doacoesPermitidas.put("AB+", Arrays.asList("A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-"));
        doacoesPermitidas.put("AB-", Arrays.asList("A-", "B-", "O-", "AB-"));
        doacoesPermitidas.put("O+", Arrays.asList("O+", "O-"));
        doacoesPermitidas.put("O-", Arrays.asList("O-"));
        return doacoesPermitidas.getOrDefault(tipoSanguineo, Arrays.asList());
    }

}
