package br.com.wkbloodbank.utils;

import br.com.wkbloodbank.model.PessoaModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

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

}
