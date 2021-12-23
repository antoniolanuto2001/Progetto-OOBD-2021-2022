package Model;
import java.util.*;
public class Utente 
{
	private String Nome;
	private String Cognome;
	private Data Datadinascita;
	private String CodiceFiscale;
	
	public Utente(String n,String c,Data data, String cod) {
		// TODO Auto-generated constructor stub
		setNome(n);
		setCognome(c);
		setDatadinascita(data);
		setCodiceFiscale(cod);
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

}
