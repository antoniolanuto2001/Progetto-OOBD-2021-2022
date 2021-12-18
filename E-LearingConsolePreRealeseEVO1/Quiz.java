import java.util.*;

public class Quiz
{
	private String TipologiaTest;
	public Test testAppartenza;
	public int Positivopunteggio;
	public int Negativopunteggio;
	public ArrayList <Domanda> Domande;
	
	
	public Quiz() {
		// TODO Auto-generated constructor stub
	}
	
	public void QuizconParamentri(Test t,int P,int N,Domanda dom,String tipo)
	{
		Domande.add(dom);
		Positivopunteggio=P;
		Negativopunteggio=N;
		testAppartenza=t;
		TipologiaTest=tipo;
	}
	
	public void AggiungiDomanda(Domanda dom)
	{
		Domande.add(dom);
	}
	public int getNegativopunteggio() 
	{
		return Negativopunteggio;
	}	
	public int getPositivopunteggio() 
	{
		return Positivopunteggio;
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
