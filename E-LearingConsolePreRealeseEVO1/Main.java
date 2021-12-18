import java.util.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Borsa Bozza Pre EVO1");
		//Prova_Nuovo_Utente
		Data datadiData= new Data(29, 9, 2001);
		Utente antonioUtente=new Utente("Antonio", "Lanuto", datadiData, "LNTntN01p29f839r");
		Test provaTest=new Test("Prova Primo test",antonioUtente);
		Quiz provaQuiz=new Quiz(provaTest);
		Domanda nuovaDomanda =new Domanda("La spiaggia piu bella di procida", "Porfirio");
		System.out.println("Ho fatto Accesso con utente : "+antonioUtente.getNome());
		System.out.println("Informazioni : ");
		System.out.println("Nome           : "+antonioUtente.getNome());
		System.out.println("Cognome        : "+antonioUtente.getCognome());
		System.out.println("Codice Fiscale : "+antonioUtente.getCodiceFiscale());
		System.out.println("Data           : "+antonioUtente.getDatadinascita().getGiorno()+"/"+antonioUtente.getDatadinascita().getMese()+"/"+antonioUtente.getDatadinascita().getAnno());
		System.out.println("Sistema di Configurazione Domande Primitivo EVO 1.1");
		
		provaQuiz.NuovoQuizParametri(12, 0, "A");
		provaQuiz.AggiungiDomanda(nuovaDomanda);
		provaTest.AddTest(provaQuiz);
		
		System.out.println("Test           : "+provaTest.getNomeTest());
		System.out.println("Domanda: "+provaTest.QuizPresenti.get(0).Domande.get(0).Domanda);
		
		
		
		
		
		
	
	}
	

}
