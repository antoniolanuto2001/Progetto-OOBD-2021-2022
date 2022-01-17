package Model;
import java.util.*;

public class Quiz
{
	private String TipologiaTest;
	public Test testAppartenza;
	public int Positivopunteggio;
	public int Negativopunteggio;
	public ArrayList <Domanda> Domande;
	
	
	public Quiz(Test t) {
		// TODO Auto-generated constructor stub
		testAppartenza=t;
		Positivopunteggio=0;
		Negativopunteggio=0;
		setTipologiaTest("");
		Domande = new ArrayList<Domanda>();
	}
	public void ResetStats() 
	{
		Positivopunteggio=0;
		Negativopunteggio=0;
		setTipologiaTest("");
	}
	public void AddParametri(int P,int N,String tipo)
	{
		Positivopunteggio=P;
		Negativopunteggio=N;
		setTipologiaTest(tipo);
	}
	
	public void AggiungiDomanda(Domanda dom)
	{
		Domande.add(dom);
	}
	public String getTipologiaTest()
	{
		return TipologiaTest;
	}
	public void setTipologiaTest(String tipologiaTest) 
	{
		TipologiaTest = tipologiaTest;
	}
	
}
