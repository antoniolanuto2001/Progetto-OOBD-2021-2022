import java.util.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Borsa Bozza Pre EVO 1.5");			
		ArrayList<Utente> ListaUtenti = new ArrayList<Utente>();
		ArrayList<Test>   ListaTest = new ArrayList<Test>();
		int numeroDiquizDacreare=0;
		int scegliModalità;
		int scegliModalità2;
		boolean ciao=true;
		String risposteBufferString;
		String DomandeBufferString;
		String selezioneModalitaString;
		Scanner readScanner= new Scanner(System.in);
		Scanner utentiScanner= new Scanner(System.in);
		Scanner inputScanner =new Scanner(System.in);
		
		
		/*Vecchie variabili e oggetti
		 * 
		Data datadiData= new Data(29, 9, 2001);
		Utente antonioUtente=new Utente("Antonio", "Lanuto", datadiData, "LNTntN01p29f839r");
		Test provaTest=new Test("Prova Primo test",antonioUtente);
		Test testdinamicoTest=new Test("PrimoTestoDinamico",antonioUtente);
		Quiz provaQuiz=new Quiz(provaTest);
		Domanda nuovaDomanda =new Domanda("La spiaggia piu bella di procida", "Porfirio");
		*/
		
		while (ciao) 
		{
			System.out.println("Scegliere la modalità Programma");
			System.out.println("-Modalita 1 Insegnante ");
			System.out.println("-Modalita 2 Studente   ");
			scegliModalità=inputScanner.nextInt();
			if (scegliModalità==1) 
			{
				System.out.println("Menu Insegnante");
				System.out.println("-Modalita 1 Visualizza Insegnanti");
				System.out.println("-Modalita 2 Creare Nuovo Insegnanti");
				System.out.println("-Modalita 3 Creare Nuovo Test");
				scegliModalità2=inputScanner.nextInt();
				//Visual_Utenti
				if (scegliModalità2==1&&ListaUtenti.size()==0) 
				{
					System.out.println("Lista Insegnanti Vuota");
					System.out.println("Crearne uno");
				}
				else if (scegliModalità2==1)
				{
					System.out.println("Insegnanti Trovati :");
					for (int i = 0; i < ListaUtenti.size(); i++) 
					{
						
						System.out.println("Informazioni  Utente n : "+ i+1);
						System.out.println("Nome           : "+ListaUtenti.get(i).getNome());
						System.out.println("Cognome        : "+ListaUtenti.get(i).getCognome());
						System.out.println("Codice Fiscale : "+ListaUtenti.get(i).getCodiceFiscale());
						System.out.println("Data           : "+ListaUtenti.get(i).getDatadinascita().getGiorno()+"/"+ListaUtenti.get(i).getDatadinascita().getMese()+"/"+ListaUtenti.get(i).getDatadinascita().getAnno());						
					}	
				}
				else if(scegliModalità2==2)
				{
					
					String nome,cognome,codicefiscale;
					int mese,giorno,anno;
					System.out.print("Nome           : ");
					nome=utentiScanner.nextLine();
					System.out.print("Cognome        : ");
					cognome=utentiScanner.nextLine();
					System.out.print("Codice Fiscale : ");
					codicefiscale=utentiScanner.nextLine();
					System.out.print("Giorno Nascita : ");
					giorno=inputScanner.nextInt();
					System.out.print("Mese   Nascita : ");
					mese=inputScanner.nextInt();
					System.out.print("Anno   Nascita : ");
					anno=inputScanner.nextInt();
					ListaUtenti.add(new Utente(nome, cognome, (new Data(giorno, mese, anno)), codicefiscale));
				}
				else 
				{
					if(ListaUtenti.size()==0)
					{
						System.out.println("Prima di poter creare un test");
						System.out.println("Devi creare un utente");
					}
					else 
					{
						String nomeTest;
						int index;
						System.out.print("Come vuoi chiamare il test : ");
						nomeTest=readScanner.nextLine();
						ListaTest.add(new Test(nomeTest, ListaUtenti.get(0)));//Per comodità scegliamo utente n1 solo a fini del debug
						//TODO Implementare Utente dinamico Lista Test		
						index=ListaTest.size()-1;
						System.out.println("Inizio procedura Dinamica ...");
						System.out.println("Quanti Quiz vuoi creare : ");
						numeroDiquizDacreare=inputScanner.nextInt();
						for (int i = 0; i < numeroDiquizDacreare; i++) 
						{
							ListaTest.get(index).AddQuiz(new Quiz(ListaTest.get(index)));
							System.out.print("Punteggio Positivo: ");
							int punteggiopositvo=inputScanner.nextInt();
							System.out.print("Punteggio Negativo: ");
							int negativopositvo=inputScanner.nextInt();
							System.out.print("Seleziona Modalita Domanda : ");
							selezioneModalitaString=inputScanner.next();
							selezioneModalitaString.toUpperCase();
							if (selezioneModalitaString.contentEquals("M")) 
							{
								ListaTest.get(index).QuizPresenti.get(i).AddParametri(punteggiopositvo, negativopositvo, "M");
								System.out.print("Inserire il numero di domande ");
								int numerodomande=inputScanner.nextInt();
								System.out.println("Domanda Principale - ");
								System.out.print("Domanda: ");
								DomandeBufferString=readScanner.nextLine();
								System.out.print("Risposta: ");
								risposteBufferString=readScanner.nextLine();
								ListaTest.get(index).QuizPresenti.get(i).AggiungiDomanda(new Domanda(DomandeBufferString, risposteBufferString));
								char lettera='A';
								for (int j = 0; j < numerodomande; j++) 
								{
									System.out.print("Domanda "+lettera+"- ");
									DomandeBufferString=Character.toString(lettera)+"- "+readScanner.nextLine();
									risposteBufferString="";
									ListaTest.get(index).QuizPresenti.get(i).AggiungiDomanda(new Domanda(DomandeBufferString, risposteBufferString));
									lettera++;	
								}	
							} 
							else 
							{
								ListaTest.get(index).QuizPresenti.get(i).AddParametri(punteggiopositvo, negativopositvo, "A");
								System.out.print("Domanda: ");
								DomandeBufferString=readScanner.nextLine();
								System.out.print("Risposta: ");
								risposteBufferString=readScanner.nextLine();
								ListaTest.get(index).QuizPresenti.get(i).AggiungiDomanda(new Domanda(DomandeBufferString, risposteBufferString));	
							}	
						}
					}
					
				}
			} 
			else 
			{
				int punteggiototale=0;
				int index2;
				System.out.println("Bevenuto nella Sezione Studente dove potrai svolgere i tuoi Test");
				System.out.println("Selezionare Il test che vuoi eseguire ");
				for (int i = 0; i < ListaTest.size(); i++) 
				{
					System.out.println("Test n "+ i + " nome : "+ListaTest.get(i).getNomeTest()); 
				}
				System.out.print("Sceglire quale test si vuole eseguire :  ");
				index2=inputScanner.nextInt();
				
				
				
				for (int i = 0; i < numeroDiquizDacreare; i++) 
				{
					if(ListaTest.get(index2).QuizPresenti.get(i).getTipologiaTest().contentEquals("M"))
					{
						System.out.println("Domanda - "+ListaTest.get(index2).QuizPresenti.get(i).Domande.get(0).Domanda);
						for (int j = 1; j < ListaTest.get(index2).QuizPresenti.get(i).Domande.size(); j++) 
						{
							System.out.println(ListaTest.get(index2).QuizPresenti.get(i).Domande.get(j).Domanda);
						}
						System.out.print("Risposta  : ");	
						risposteBufferString=readScanner.nextLine();
						System.out.println("Risposta- "+ListaTest.get(index2).QuizPresenti.get(i).Domande.get(0).Riposta);
						if(risposteBufferString.contentEquals(ListaTest.get(index2).QuizPresenti.get(i).Domande.get(0).Riposta))
						{
							punteggiototale=+ListaTest.get(index2).QuizPresenti.get(i).Positivopunteggio;	
						}
						else 
						{
							punteggiototale=+ListaTest.get(index2).QuizPresenti.get(i).Negativopunteggio;
						}
					}
					else 
					{
						System.out.println("Domanda - "+ListaTest.get(index2).QuizPresenti.get(i).Domande.get(0).Domanda);
						System.out.print("Risposta  : ");	
						risposteBufferString=readScanner.nextLine();
						System.out.println("Risposta- "+ListaTest.get(index2).QuizPresenti.get(i).Domande.get(0).Riposta);
						if(risposteBufferString.contentEquals(ListaTest.get(index2).QuizPresenti.get(i).Domande.get(0).Riposta))
						{
							punteggiototale+=ListaTest.get(index2).QuizPresenti.get(i).Positivopunteggio;	
						}
						else 
						{
							punteggiototale+=ListaTest.get(index2).QuizPresenti.get(i).Negativopunteggio;
						}
					}
					
				}
				System.out.println("Il punteggio finale ottenuto dal utente è : "+punteggiototale);
			}
			}
		}
}		
		/* Con un implementazione grafica Utente Puo autenticarsi come insegnante in realta e non vedere proprio 
		 nel caso fosse studente la sezione crea 
		 
		 */
		
		
		//Sistema_Configurazione_Manuale
		/*
		provaQuiz.AddParametri(12, 0, "A");
		provaQuiz.AggiungiDomanda(nuovaDomanda);
		provaTest.AddQuiz(provaQuiz);
		System.out.println("Test           : "+provaTest.getNomeTest());
		System.out.println("Domanda: "+provaTest.QuizPresenti.get(0).Domande.get(0).Domanda);
		risposteBufferString=inputScanner.next();
		System.out.println("Risposta: "+provaTest.QuizPresenti.get(0).Domande.get(0).Riposta);		
		*/
	
		
		
		
	


