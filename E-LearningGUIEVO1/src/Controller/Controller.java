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
	private ArrayList<Test>   ListaTest;
	
	
	public Controller()
	{
		ListaTest =new ArrayList<Test>();
		ListaUtenti = new ArrayList<Utente>();
	}
	
	public void aggiungiUtente(String n,String c,int g,int m,int a, String cod)
	{	
		ListaUtenti.add(new Utente(n, c, (new Data(g, m, a)), cod));
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
