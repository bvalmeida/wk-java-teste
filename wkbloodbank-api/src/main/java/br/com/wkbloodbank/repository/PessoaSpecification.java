package br.com.wkbloodbank.repository;

import br.com.wkbloodbank.model.PessoaModel;
import org.springframework.data.jpa.domain.Specification;

public class PessoaSpecification {

    public static Specification<PessoaModel> getByFilters(PessoaModel pessoaModel){
        Specification<PessoaModel> specificationNome = null;
        Specification<PessoaModel> specificationEstado = null;

        if(pessoaModel.getNome() != null){
            specificationNome = getByNomeLike(pessoaModel.getNome());
        }

        if(pessoaModel.getEstado() != null){
            specificationEstado = getByEstado(pessoaModel.getEstado());
        }

        return Specification.where(specificationNome)
                .and(specificationEstado);
    }

    private static Specification<PessoaModel> getByNomeLike(String nome){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("nome"), "%" + nome.trim() + "%");
        };
    }

    private static Specification<PessoaModel> getByEstado(String estado){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("estado"), estado.toUpperCase());
        };
    }

}
