package it.prova.gestionedipendenti.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionedipendenti.model.Societa;
import it.prova.gestionedipendenti.repository.SocietaRepository;


@Service
public class SocietaServiceImpl implements SocietaService{
	
	@Autowired
	SocietaRepository societaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Societa> ListAll() {
		return (List<Societa>) societaRepository.findAll();

	}

	@Transactional
	public void inserisciNuovo(Societa societa) {
		societaRepository.save(societa);

	}

	@Transactional
	public void aggiorna(Societa societa) {
		societaRepository.save(societa);

	}

	@Transactional
	public void rimuovi(Societa societa) {
		societaRepository.delete(societa);

	}

	@Transactional(readOnly = true)
	public Societa caricaById(Long id) {
		return societaRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Societa> findByExample(Societa example) {
		String query = "select a from Societa a where a.id = a.id ";

		if (StringUtils.isNotEmpty(example.getRagioneSociale()))
			query += " and a.ragioneSociale like '%" + example.getRagioneSociale() + "%' ";
		if (example.getDataFondazione() != null)
			query += " and a.dataFondazione >= " + example.getDataFondazione();

		return entityManager.createQuery(query, Societa.class).getResultList();
	}

	@Override
	public List<Societa> findSocietaConProgettiPiuDiUnAnno() {
		return societaRepository.listNomeBySocietaConProgettiConDurataMaggioreDiUnAnno();				
	}
	
	
}