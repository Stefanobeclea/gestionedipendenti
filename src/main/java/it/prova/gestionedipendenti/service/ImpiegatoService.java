package it.prova.gestionedipendenti.service;

import java.util.Date;
import java.util.List;

import it.prova.gestionedipendenti.model.Impiegato;
import it.prova.gestionedipendenti.model.Progetto;


public interface ImpiegatoService {
	
	public List<Impiegato> ListAll();
	
	public void inserisciNuovo(Impiegato impiegato);
	
	public void aggiorna(Impiegato impiegato);
	
	public void rimuovi(Impiegato impiegato);
	
	public Impiegato caricaById(Long id);
	
	public List<Impiegato> caricaByIdEager(Long id);
	
	public void aggiungiProgetto(Impiegato impiegato, Progetto progetto);
	
	public Impiegato caricaImpiegatoEagerProgetti(Long id);
	
	public List<Impiegato> caricaByImpiegatoPiuVecchioDiUnaSocietaFondataPrimaDiECheLavoraAProgettoCheDuraDa(Date data, int mesi);
	
}