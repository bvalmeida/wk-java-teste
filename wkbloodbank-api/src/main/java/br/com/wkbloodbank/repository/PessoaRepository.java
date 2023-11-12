package br.com.wkbloodbank.repository;

import br.com.wkbloodbank.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long>, JpaSpecificationExecutor<PessoaModel> {

    List<PessoaModel> findAll();

    @Query("SELECT DISTINCT p.estado FROM PessoaModel p")
    List<String> findDistinctEstados();

    Long countByEstado(String estado);

    List<PessoaModel> findBySexo(String sexo);

    @Query("SELECT DISTINCT p.tipoSanguineo FROM PessoaModel p")
    List<String> findByDistinctTipoSanguineo();

    List<PessoaModel> findByTipoSanguineo(String tipoSanguineo);

    @Query("SELECT p FROM PessoaModel p WHERE p.tipoSanguineo IN :tiposSanguineos")
    List<PessoaModel> findByTipoSanguineoList(@Param("tiposSanguineos") List<String> tiposSanguineos);
}
