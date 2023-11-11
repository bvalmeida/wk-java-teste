package br.com.wkbloodbank.repository;

import br.com.wkbloodbank.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long>, JpaSpecificationExecutor<PessoaModel> {

    List<PessoaModel> findAll();

    @Query("SELECT DISTINCT p.estado FROM PessoaModel p")
    List<String> findDistinctEstados();

    Long countByEstado(String estado);

}
