package br.com.wkbloodbank.utils;

import br.com.wkbloodbank.model.PessoaModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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

}
