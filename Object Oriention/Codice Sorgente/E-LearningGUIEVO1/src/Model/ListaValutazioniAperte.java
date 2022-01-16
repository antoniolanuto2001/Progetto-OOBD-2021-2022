package Model;

import java.util.ArrayList;

public class ListaValutazioniAperte 
{
	public String matricolaString;
	public int idRisultatoTest;
	public ArrayList<ValutazioneAperta> Valutazioniaperte;
	
	public ListaValutazioniAperte(String matricola,int id)
	{
		matricolaString=matricola;
		idRisultatoTest=id;
		Valutazioniaperte= new ArrayList<ValutazioneAperta>();
	}
}
