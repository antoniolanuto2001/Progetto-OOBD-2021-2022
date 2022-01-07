package DAO;

public interface RegisterDAO {
	
	void RecuperaDiparmenti(String[] dipartimString);
	void RecuperaCdL(String[] CDLStrings);
	void AggiungiUtente(String Table,String nome,String Cognome,String email,String ps,String diparString,String cdl,String sesso,String codfiscale,int g,int m,int a);
}	


