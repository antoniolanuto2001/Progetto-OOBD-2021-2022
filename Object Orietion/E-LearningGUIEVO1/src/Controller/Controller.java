package Controller;

import java.util.ArrayList;
import Model.Utente;
import Model.Data;
import Model.Domanda;
import Model.Quiz;
import Model.Test;

public class Controller 
{
	private ArrayList<Utente> ListaUtenti;
	private ArrayList<Test>ListaTest;
	
	
	public Controller()
	{
		ListaTest =new ArrayList<Test>();
		ListaUtenti = new ArrayList<Utente>();
	}
	
	public void aggiungiUtente(String n,String c,int g,int m,int a, String cod)
	{	
		ListaUtenti.add(new Utente(n, c, (new Data(g, m, a)), cod));
	}
	public void aggiungiTest(String Nome,String codiceString)
	{	
		int i=-1;
		for (int j = 0; j < ListaUtenti.size(); j++) 
		{
			if(codiceString.contentEquals(ListaUtenti.get(j).getCodiceFiscale()))
			{
				i=j;
				break;
			}
		}
		System.out.println("Ho preso index "+i);
		ListaTest.add(new Test(Nome, ListaUtenti.get(i)));
		System.out.println("Test Creato Forza Capooo");
	}
	public void addQuizToTest(int punteggioP,int negativoN,String modalita) 
	{
		if(ListaTest.size()==0)
		{
			ListaTest.get(0).AddQuiz(new Quiz(ListaTest.get(0)));
			ListaTest.get(0).QuizPresenti.get(0).AddParametri(punteggioP , negativoN, modalita);
		}
		else 
		{
			ListaTest.get(ListaTest.size()-1).AddQuiz(new Quiz(ListaTest.get(ListaTest.size()-1)));
			ListaTest.get(ListaTest.size()-1).QuizPresenti.get(ListaTest.get(ListaTest.size()-1).QuizPresenti.size()-1).AddParametri(punteggioP , negativoN, modalita);
		}		
	}
	
	public void addDomanda(String Domanda,String Risposta) 
	{
		if(ListaTest.size()==0&&ListaTest.get(0).QuizPresenti.size()==0)
		{
			ListaTest.get(0).QuizPresenti.get(0).AggiungiDomanda(new Domanda(Domanda, Risposta));
		}
		else 
		{
			ListaTest.get(ListaTest.size()-1).QuizPresenti.get(ListaTest.get(ListaTest.size()-1).QuizPresenti.size()-1).AggiungiDomanda(new Domanda(Domanda, Risposta));		
		}		
	}
	public void Debug()
	{
		System.out.println("Nome Test : "+ListaTest.get(0).getNomeTest());
		if (ListaTest.get(0).QuizPresenti.get(0).Domande.size()>=2) 
		{
			System.out.println("Domanda: "+ListaTest.get(0).QuizPresenti.get(0).Domande.get(0).Domanda);
			for (int i = 1; i < ListaTest.get(0).QuizPresenti.get(0).Domande.size(); i++) 
			{
				System.out.println("Risposta: " + i+"  "+ListaTest.get(0).QuizPresenti.get(0).Domande.get(i).Riposta);
			}
			System.out.println("Risposta: "+ListaTest.get(0).QuizPresenti.get(0).Domande.get(0).Riposta);
		}
	}
	public ArrayList getUtente(int index) 
	{
		//TODO al posto di 0 ci andrebbe index
		
		ArrayList a = new ArrayList();
		a.add(ListaUtenti.get(0).getNome());
		a.add(ListaUtenti.get(0).getCognome());
		a.add(ListaUtenti.get(0).getCodiceFiscale());
		a.add(ListaUtenti.get(0).getDatadinascita().getGiorno());
		a.add(ListaUtenti.get(0).getDatadinascita().getMese());
		a.add(ListaUtenti.get(0).getDatadinascita().getAnno());
		return a;
	}
	
}
