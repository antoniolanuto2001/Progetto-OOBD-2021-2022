package Model;

import java.util.ArrayList;

public class RisultatoTest 
{
	private String Matricola;
	public int idRisultatoTest;
	public int punteggioOttenuto;
	public Data DataTest;
	public ArrayList <Domanda>DomandePresenti;
	
	public RisultatoTest(int Id,String Matr,Data DataInserita,int putenggio) 
	{
		// TODO Auto-generated constructor stub
		DomandePresenti=new ArrayList<Domanda>();
		idRisultatoTest=Id;
		setMatricola(Matr);
		punteggioOttenuto=putenggio;
		DataTest=DataInserita;
	}

	/**
	 * @return the matricola
	 */
	public String getMatricola() {
		return Matricola;
	}

	/**
	 * @param matricola the matricola to set
	 */
	public void setMatricola(String matricola) {
		Matricola = matricola;
	}
	
}
