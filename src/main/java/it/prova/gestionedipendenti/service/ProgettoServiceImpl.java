package it.prova.gestionedipendenti.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionedipendenti.model.Progetto;
import it.prova.gestionedipendenti.repository.ProgettoRepository;


@Service
public class ProgettoServiceImpl implements ProgettoService{
	
	@Autowired
	ProgettoRepository progettoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Progetto> ListAll() {
		return (List<Progetto>) progettoRepository.findAll();

	}
	
	@Transactional
	public void inserisciNuovo(Progetto progetto) {
		progettoRepository.save(progetto);

	}

	@Transactional
	public void aggiorna(Progetto progetto) {
		progettoRepository.save(progetto);

	}

	@Transactional
	public void rimuovi(Progetto progetto) {
		progettoRepository.delete(progetto);

	}

	@Transactional(readOnly = true)
	public Progetto caricaById(Long id) {
		return progettoRepository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public List<Progetto> caricaByIdEager(Long id) {
		return progettoRepository.findByIdEager(id);
	}
	
	@Transactional(readOnly = true)
	public List<String> listClientiDiUnProgettoDataSocieta(Long idSocieta) {
		List<Progetto> progettiDellSocieta = caricaByIdEager(idSocieta);
		return progettiDellSocieta.stream().map(p -> p.getCliente()).collect(Collectors.toList());		
	}
	
	@Transactional(readOnly = true)
	public Progetto caricaProgettoEager(Long id) {
		return progettoRepository.findById(id).get();
	}
	
	@Transactional(readOnly = true)
	public List<Progetto> trovaAllByImpiegatiRal(int ral){
		return progettoRepository.findAllDistinctByImpiegati_RalIs(ral);
	}
}