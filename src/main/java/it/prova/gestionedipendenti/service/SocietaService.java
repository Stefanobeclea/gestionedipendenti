package it.prova.gestionedipendenti.service;

import java.util.List;

import it.prova.gestionedipendenti.model.Societa;


public interface SocietaService {
	
	public List<Societa> ListAll();
	
	public void inserisciNuovo(Societa societa);
	
	public void aggiorna(Societa societa);
	
	public void rimuovi(Societa societa);
	
	public Societa caricaById(Long id);
	
	public List<Societa> findByExample(Societa example);
	
	public List<Societa> findSocietaConProgettiPiuDiUnAnno();
		
}