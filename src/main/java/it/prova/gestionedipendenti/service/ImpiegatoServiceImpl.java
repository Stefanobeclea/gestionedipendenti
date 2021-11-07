package it.prova.gestionedipendenti.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionedipendenti.model.Impiegato;
import it.prova.gestionedipendenti.model.Progetto;
import it.prova.gestionedipendenti.repository.ImpiegatoRepository;

@Service
public class ImpiegatoServiceImpl implements ImpiegatoService{

	@Autowired
	ImpiegatoRepository impiegatoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Impiegato> ListAll() {
		return (List<Impiegato>)impiegatoRepository.findAll();
		
	}

	@Transactional
	public void inserisciNuovo(Impiegato impiegato) {
		impiegatoRepository.save(impiegato);
		
	}

	@Transactional
	public void aggiorna(Impiegato impiegato) {
		impiegatoRepository.save(impiegato);
		
	}

	@Transactional
	public void rimuovi(Impiegato impiegato) {
		impiegatoRepository.delete(impiegato);
		
	}

	@Transactional(readOnly = true)
	public Impiegato caricaById(Long id) {
		return impiegatoRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Impiegato> caricaByIdEager(Long id) {
		return impiegatoRepository.findAllDistinctBySocieta_IdIs(id);
	}
	
	@Transactional
	public void aggiungiProgetto(Impiegato impiegato, Progetto progetto) {
		impiegato.getProgetti().add(progetto);
	}
	
	@Transactional(readOnly = true)
	public Impiegato caricaImpiegatoEagerProgetti(Long id) {
		 return impiegatoRepository.findById(id).get();
	}
	
	@Transactional
	public List<Impiegato> caricaByImpiegatoPiuVecchioDiUnaSocietaFondataPrimaDiECheLavoraAProgettoCheDuraDa(Date data, int mesi) {
		return impiegatoRepository.findByImpiegatoPiuVecchioDiUnaSocietaFondataPrimaDiECheLavoraAProgettoCheDuraDa(data, mesi);
	}
}