package DAO;

import java.util.ArrayList;

import Model.Test;
import Model.Utente;

public interface TestDAO 
{
	 ArrayList<Test> CreaUtenti(ArrayList<Utente> Utenti);
	 void AggiugiTestAlDB(String codice,String nome,int g,int m,int a);
	 void AggiungiQuiz(String Modalita ,String Domanda,String Risposta,int punteggioP,int PunteggioN);
	 void AggiungiRisposta(String Domanda,String risposta);
}
