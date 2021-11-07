package it.prova.gestionedipendenti.service;

import java.util.List;

import it.prova.gestionedipendenti.model.Progetto;

public interface ProgettoService {
	
	public List<Progetto> ListAll();
	
	public void inserisciNuovo(Progetto progetto);
	
	public void aggiorna(Progetto progetto);
	
	public void rimuovi(Progetto progetto);
	
	public Progetto caricaById(Long id);
	
	public List<Progetto> caricaByIdEager(Long id);
	
	public List<String> listClientiDiUnProgettoDataSocieta(Long idSocieta);
	
	public Progetto caricaProgettoEager(Long id);
	
	List<Progetto> trovaAllByImpiegatiRal(int ral);
	
}