package Model;
import java.util.*;
public class Utente implements Cloneable
{
	private String Nome;
	private String Cognome;
	private Data Datadinascita;
	private String CodiceFiscale;
	public ArrayList <RisultatoTest>ListaRisultati;
	public ArrayList <ListaValutazioniAperte>ListaValutazioni;
	
	public Utente(String n,String c,Data data, String cod) {
		// TODO Auto-generated constructor stub
		setNome(n);
		setCognome(c);
		setDatadinascita(data);
		setCodiceFiscale(cod);
		ListaRisultati = new ArrayList<RisultatoTest>();
		ListaValutazioni= new ArrayList<ListaValutazioniAperte>();
	}

	public String getCodiceFiscale() {
		return CodiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale.toUpperCase();
	}

	public Data getDatadinascita() {
		return Datadinascita;
	}

	public void setDatadinascita(Data datadinascita) {
		Datadinascita = datadinascita;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
