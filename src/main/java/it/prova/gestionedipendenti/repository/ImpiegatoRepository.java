package it.prova.gestionedipendenti.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionedipendenti.model.Impiegato;

public interface ImpiegatoRepository extends CrudRepository<Impiegato, Long>, QueryByExampleExecutor<Impiegato> {

	@EntityGraph(attributePaths = { "progetti" })
	List<Impiegato> findAllDistinctBySocieta_IdIs(Long id);

	@EntityGraph(attributePaths = { "progetti" })
	Optional<Impiegato> findById(Long id);

	@Query("select distinct i from Societa s join s.impiegati i join i.progetti p where s.dataFondazione < ?1 and p.durataInMesi >= ?2 order by i.dataAssunzione ASC")
	List<Impiegato> findByImpiegatoPiuVecchioDiUnaSocietaFondataPrimaDiECheLavoraAProgettoCheDuraDa(Date data, int mesi);
}