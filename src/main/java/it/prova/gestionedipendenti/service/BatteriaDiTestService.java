package it.prova.gestionedipendenti.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionedipendenti.model.Impiegato;
import it.prova.gestionedipendenti.model.Progetto;
import it.prova.gestionedipendenti.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private ImpiegatoService impiegatoService;

	@Autowired
	private ProgettoService progettoService;

	@Autowired
	private SocietaService societaService;

	public void testInsertSocieta() {

		Societa societa = new Societa("Societa", new Date("2000/01/01"));

		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testInsertSocieta FALLITO: societa non inserita");

		System.out.println("testInsertSocieta ok................................................");
	}

	public void testFindByExampleSocieta() {

		Societa societa = new Societa("Google", new Date("2000/01/01"));
		Societa societa2 = new Societa("Amazon", new Date("2000/01/01"));
		Societa societa3 = new Societa("Tesla", new Date("2000/01/01"));

		societaService.inserisciNuovo(societa);
		societaService.inserisciNuovo(societa2);
		societaService.inserisciNuovo(societa3);

		Societa example = new Societa("Amazon", null);

		if (societaService.findByExample(example).size() != 1)
			throw new RuntimeException("testFindByExampleSocieta FALLITO: findByExample fallito");

		System.out.println("testFindByExampleSocieta ok................................................");
	}

	public void testDeleteSocieta() {

		Societa societaDaRimuovere = new Societa("Societa", new Date("2000/01/01"));
		societaService.inserisciNuovo(societaDaRimuovere);

		societaService.rimuovi(societaDaRimuovere);
		
		if (societaService.caricaById(societaDaRimuovere.getId()) != null)
			throw new RuntimeException("testDeleteSocieta FALLITO: societa non rimossa");

		System.out.println("testDeleteSocieta ok..................................................");
	}

	public void testInsertImpiegato() {

		Societa societa = new Societa("Societa", new Date("2000/01/01"));
		societaService.inserisciNuovo(societa);

		Impiegato impiegato = new Impiegato("Francesco", "Totti", new Date("2000/10/01"), 20000, societa);
		impiegatoService.inserisciNuovo(impiegato);

		if (impiegato.getId() == null)
			throw new RuntimeException("testInsertImpiegato FALLITO: impiegato non inserito");

		System.out.println("testInsertImpiegato ok................................................");
	}

	public void testInsertProgetto() {

		Progetto progetto = new Progetto("MyEbay", "Alberto", 1);
		progettoService.inserisciNuovo(progetto);

		if (progetto.getId() == null)
			throw new RuntimeException("testInsertProgetto FALLITO: progetto non inserito");

		System.out.println("testInsertProgetto ok..................................................");
	}

	public void testCollegamentoDiUnImpiegatoAPiuProgetti() {

		Societa societa = new Societa("Societa", new Date("2000/01/01"));
		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("Progetto1", "Cliente1", 1);
		progettoService.inserisciNuovo(progetto);

		Progetto progetto2 = new Progetto("Progetto2", "Cliente2", 1);
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato = new Impiegato("Mario", "Rossi", new Date("2000/10/01"), 14000, societa);

		impiegatoService.inserisciNuovo(impiegato);
		impiegato.addToProgetti(progetto);
		impiegato.addToProgetti(progetto2);
		impiegatoService.aggiorna(impiegato);

		Impiegato impiegatoRicaricato = impiegatoService.caricaImpiegatoEagerProgetti(impiegato.getId());
		if (impiegatoRicaricato.getProgetti().size() != 2)
			throw new RuntimeException("testCollegamentoDiUnImpiegatoAPiuProgetti FALLITO: query sbagliata");

		System.out.println("testCollegamentoDiUnImpiegatoAPiuProgetti ok.........................................");
	}

	public void testCollegamentoDiUnProgettoAPiuImpiegati() {

		Societa societa = new Societa("Societa", new Date("2000/01/01"));
		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("Progetto", "Cliente", 1);
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato = new Impiegato("Mario", "Rossi", new Date("2000/01/01"), 10000, societa);
		Impiegato impiegato2 = new Impiegato("Francesco", "Totti", new Date("2000/01/01"), 10000, societa);

		impiegatoService.inserisciNuovo(impiegato);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato.addToProgetti(progetto);
		impiegato2.addToProgetti(progetto);

		impiegatoService.aggiorna(impiegato);
		impiegatoService.aggiorna(impiegato2);

		if (progettoService.caricaProgettoEager(progetto.getId()).getImpiegati().size() != 2)
			throw new RuntimeException("testCollegamentoDiUnProgettoAPiuImpiegati FALLITO: query sbagliata");

		System.out.println("testCollegamentoDiUnProgettoAPiuImpiegati ok..............................................");
	}

	public void testListaDeiClientiDeiProgettiDiUnaDataSocieta() {

		Societa societa = new Societa("Societa", new Date("2000/01/01"));
		societaService.inserisciNuovo(societa);

		Progetto progetto1 = new Progetto("Progetto1", "Cliente1", 1);
		Progetto progetto2 = new Progetto("Progetto2", "Cliente2", 1);
		
		progettoService.inserisciNuovo(progetto1);
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegato = new Impiegato("Francesco", "Totti", new Date("2000/01/01"), 25000, societa);
		impiegatoService.inserisciNuovo(impiegato);

		impiegato.addToProgetti(progetto1);
		impiegato.addToProgetti(progetto2);

		impiegatoService.aggiorna(impiegato);

		List<String> listaClienti = progettoService.listClientiDiUnProgettoDataSocieta(societa.getId());

		if (listaClienti.size() != 2)
			throw new RuntimeException("testListaDeiClientiDeiProgettiDiUnaDataSocieta FALLITO: metodo fallito");

		System.out.println("testListaDeiClientiDeiProgettiDiUnaDataSocieta ok..................................");
	}

	public void testListaSocietaConProgettiPiuDiUnAnno() {

		Societa societa = new Societa("Societa1", new Date("2000/01/01"));
		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("Progetto1", "Cliente1", 15);
		Progetto progetto2 = new Progetto("Progetto2", "Cliente2", 1);
		progettoService.inserisciNuovo(progetto);
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegato = new Impiegato("Mario", "Rossi", new Date("2000/01/01"), 5000, societa);
		impiegatoService.inserisciNuovo(impiegato);

		impiegato.addToProgetti(progetto);
		impiegato.addToProgetti(progetto2);

		impiegatoService.aggiorna(impiegato);

		List<Societa> societaRisultato = societaService.findSocietaConProgettiPiuDiUnAnno();

		if (societaRisultato.size() != 1)
			throw new RuntimeException("testListaSocietaConProgettiPiuDiUnAnno FALLITO: query sbagliata");

		System.out.println("testListaSocietaConProgettiPiuDiUnAnno ok..................................");
	}

	public void testListaProgettiConImpiegatoConRal() {

		Societa societa = new Societa("Societa", new Date("2000/01/01"));
		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("Progetto", "Cliente", 1);
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato = new Impiegato("Mario", "Rossi", new Date("2000/01/01"), 30000, societa);
		impiegatoService.inserisciNuovo(impiegato);
		Impiegato impiegato2 = new Impiegato("Francesco", "Totti", new Date("2000/01/01"), 25000, societa);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato.addToProgetti(progetto);
		impiegato2.addToProgetti(progetto);

		impiegatoService.aggiorna(impiegato);
		impiegatoService.aggiorna(impiegato2);

		List<Progetto> progettiRisulato = progettoService.trovaAllByImpiegatiRal(30000);
		if (progettiRisulato.size() != 1)
			throw new RuntimeException("testListaProgettiConImpiegatoConRal FALLITO: query sbagliata");

		System.out.println("testListaProgettiConImpiegatoConRal ok..................................................");
	}

	public void testListImpiegatoPiuAnzianoDiSocietaFondatePrimaECheLavoraAProgettoDiPiuDi() {

		Societa societa = new Societa("Societa", new Date("1989/01/01"));
		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("Progetto", "Cliente", 7);
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato = new Impiegato("Mario", "Rossi", new Date("2000/01/01"), 15000, societa);
		impiegatoService.inserisciNuovo(impiegato);
		Impiegato impiegato2 = new Impiegato("Francesco", "Totti", new Date("1990/01/01"), 20000, societa);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato.addToProgetti(progetto);
		impiegato2.addToProgetti(progetto);

		impiegatoService.aggiorna(impiegato);
		impiegatoService.aggiorna(impiegato2);

		List<Impiegato> impiegatiRisultato = impiegatoService.caricaByImpiegatoPiuVecchioDiUnaSocietaFondataPrimaDiECheLavoraAProgettoCheDuraDa(new Date("1990/01/01"), 6);
		if (impiegatiRisultato.size() != 2)
			throw new RuntimeException("testListImpiegatoPiuAnzianoDiSocietaFondatePrimaECheLavoraAProgettoDiPiuDi FALLITO: query sbagliata");
		
		System.out.println("testListImpiegatoPiuAnzianoDiSocietaFondatePrimaECheLavoraAProgettoDiPiuDi ok...............");
	}
}