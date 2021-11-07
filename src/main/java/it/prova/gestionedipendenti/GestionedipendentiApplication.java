package it.prova.gestionedipendenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionedipendenti.service.BatteriaDiTestService;

@SpringBootApplication
public class GestionedipendentiApplication implements CommandLineRunner{

	@Autowired
	BatteriaDiTestService batteriaDiTestService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionedipendentiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("################ INIZIO   #################");
		System.out.println("################ faccio i test  #################");
		
		batteriaDiTestService.testInsertSocieta();
		batteriaDiTestService.testFindByExampleSocieta();
		batteriaDiTestService.testDeleteSocieta();
		batteriaDiTestService.testInsertImpiegato();
		batteriaDiTestService.testInsertProgetto();
		batteriaDiTestService.testCollegamentoDiUnImpiegatoAPiuProgetti();
		batteriaDiTestService.testCollegamentoDiUnProgettoAPiuImpiegati();
		batteriaDiTestService.testListaDeiClientiDeiProgettiDiUnaDataSocieta();
		batteriaDiTestService.testListaSocietaConProgettiPiuDiUnAnno();
		batteriaDiTestService.testListaProgettiConImpiegatoConRal();
		batteriaDiTestService.testListImpiegatoPiuAnzianoDiSocietaFondatePrimaECheLavoraAProgettoDiPiuDi();
		
		System.out.println("################ FINE   #################");		
	}

}
