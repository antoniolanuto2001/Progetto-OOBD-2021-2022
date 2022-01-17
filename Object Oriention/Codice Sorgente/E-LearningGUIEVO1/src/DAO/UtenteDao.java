package DAO;

import Model.Utente;

public interface UtenteDao 
{
	int controllaDao(String email,String passString);
	String VedeSePresente(String email);
	Utente creaNuovoUtente(String email,String table);
}
