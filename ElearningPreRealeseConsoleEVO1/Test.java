import java.util.*;

public class Test 
{
	
	private String NomeTest;
	public ArrayList <Quiz> QuizPresenti;
	public int PunteggioFinale;
	
	public Test(String Nome,int punteggio)
	{
		NomeTest=Nome;
		PunteggioFinale=punteggio;
		
	}
	public boolean AddTest(Quiz Funzione)
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
	

}
