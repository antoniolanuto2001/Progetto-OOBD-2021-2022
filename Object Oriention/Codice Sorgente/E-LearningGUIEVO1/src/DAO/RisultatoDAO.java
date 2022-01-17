package DAO;

import java.util.ArrayList;

import Model.Utente;

public interface RisultatoDAO 
{
	void AggiungiValutazionePrePost(String Table,String RispostaInserita,String LetteraInserita,String DomandaRicostruente,String nomeTest,String CodDocente,String Codstudente,int Idrisultato);
	int AggiungiRisultatoTest(String nomeTest,String CodDocente,String Codstudente);
	int CountRisultato(String Utente);
	int CountValutazioneAperte(String codFiscaleDocenteString);
	void ResulDaDb(Utente UtenteDaAggiungere);
	String RisultatoNome(int index);
	void ResultDaDBAperta(Utente UtenteDaAggiungere);
	void RecuperaValutazioniDaDB(Utente daUtente);
	void UpdateRisultatoDao(int idVal,int putenggio);
	ArrayList RecuperaStudeteStartMatricola(String Matr);
}
