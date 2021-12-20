import java.util.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Borsa Bozza Pre EVO1");
		Scanner inputScanner =new Scanner(System.in);
		//Prova_Nuovo_Utente	
		Data datadiData= new Data(29, 9, 2001);
		Utente antonioUtente=new Utente("Antonio", "Lanuto", datadiData, "LNTntN01p29f839r");
		Test provaTest=new Test("Prova Primo test",antonioUtente);
		Test testdinamicoTest=new Test("PrimoTestoDinamico",antonioUtente);
		Quiz provaQuiz=new Quiz(provaTest);
		Domanda nuovaDomanda =new Domanda("La spiaggia piu bella di procida", "Porfirio");
		int numeroDiquizDacreare;
		String risposteBufferString;
		String DomandeBufferString;
		String SvuotaBuffer;
		Scanner readScanner= new Scanner(System.in);
		
		
		System.out.println("Ho fatto Accesso con utente : "+antonioUtente.getNome());
		System.out.println("Informazioni : ");
		System.out.println("Nome           : "+antonioUtente.getNome());
		System.out.println("Cognome        : "+antonioUtente.getCognome());
		System.out.println("Codice Fiscale : "+antonioUtente.getCodiceFiscale());
		System.out.println("Data           : "+antonioUtente.getDatadinascita().getGiorno()+"/"+antonioUtente.getDatadinascita().getMese()+"/"+antonioUtente.getDatadinascita().getAnno());
		System.out.println("Sistema di Configurazione Domande Primitivo EVO 1.1");
		
		provaQuiz.AddParametri(12, 0, "A");
		provaQuiz.AggiungiDomanda(nuovaDomanda);
		provaTest.AddQuiz(provaQuiz);
		
		System.out.println("Test           : "+provaTest.getNomeTest());
		System.out.println("Domanda: "+provaTest.QuizPresenti.get(0).Domande.get(0).Domanda);
		risposteBufferString=inputScanner.next();
		System.out.println("Risposta: "+provaTest.QuizPresenti.get(0).Domande.get(0).Riposta);		
		
		//Prova_in_maniera_dinamica
		System.out.println("Inizio procedura Dinamica ...");
		System.out.println("Quanti Quiz vuoi creare : ");
		numeroDiquizDacreare=inputScanner.nextInt();
		for (int i = 0; i < numeroDiquizDacreare; i++) 
		{
			testdinamicoTest.AddQuiz(new Quiz(provaTest));
			System.out.print("Punteggio Positivo: ");
			int punteggiopositvo=inputScanner.nextInt();
			System.out.print("Punteggio Negativo: ");
			int negativopositvo=inputScanner.nextInt();
			testdinamicoTest.QuizPresenti.get(i).AddParametri(punteggiopositvo, negativopositvo, "A");
			System.out.print("Domanda: ");
			DomandeBufferString=readScanner.nextLine();
			System.out.print("Risposta: ");
			risposteBufferString=readScanner.nextLine();
			testdinamicoTest.QuizPresenti.get(i).AggiungiDomanda(new Domanda(DomandeBufferString, risposteBufferString));	
			
		}
		System.out.println("Ho finito correttamente");
		System.out.println("Ora Domande \n \n");
		for (int i = 0; i < numeroDiquizDacreare; i++) 
		{
			System.out.println("Domanda - "+testdinamicoTest.QuizPresenti.get(i).Domande.get(0).Domanda);
			risposteBufferString=readScanner.nextLine();
			System.out.println("Risposta- "+testdinamicoTest.QuizPresenti.get(i).Domande.get(0).Riposta);
		}
		
		
		
	
	}
	

}