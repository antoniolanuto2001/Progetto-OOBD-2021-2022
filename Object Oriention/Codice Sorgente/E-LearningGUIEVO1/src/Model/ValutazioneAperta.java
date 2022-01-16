package Model;

public class ValutazioneAperta 
{
	public int idvalutazioneApertaDB;
	public String rispostaInseritaString;
	public String domandaInseritaString;
	public int punteggioOttenuto;
	public int MaxPunt;
	public int MinPunt;
	
	public ValutazioneAperta(String risposta, int idvalutazione, int PunteggioOttenuto,int punteggiomassimo,int putenggiominimo,String domanda) 
	{
	// TODO Auto-generated constructor stub
		rispostaInseritaString=risposta;
		idvalutazioneApertaDB=idvalutazione;
		punteggioOttenuto=PunteggioOttenuto;
		MaxPunt=punteggiomassimo;
		MinPunt=putenggiominimo;
		domandaInseritaString=domanda;
	}
}
