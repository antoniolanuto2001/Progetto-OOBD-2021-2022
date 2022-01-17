package Model;
import java.util.*;

public class Test implements Cloneable
{
	
	private String NomeTest;
	public ArrayList <Quiz> QuizPresenti;
	public int PunteggioFinale;
	public Utente OwenerUtente;
	public Data scandenzaData;
	
	
	public Test(String nome,Utente utente,Data scData) 
	{
		// TODO Auto-generated constructor stub
		setNomeTest(nome);
		OwenerUtente=utente;
		QuizPresenti =new ArrayList<Quiz>();
		scandenzaData=scData;
	}
	
	
	public boolean AddQuiz(Quiz Funzione)
	{
		Boolean check;
		check=QuizPresenti.add(Funzione);
		return check;
	}
	public String getNomeTest() {
		return NomeTest;
	}
	public void setNomeTest(String nomeTest) {
		NomeTest = nomeTest;
	}
	public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

}
