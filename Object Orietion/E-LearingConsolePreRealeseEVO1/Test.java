import java.util.*;

public class Test 
{
	
	private String NomeTest;
	public ArrayList <Quiz> QuizPresenti;
	public int PunteggioFinale;
	public Utente OwenerUtente;
	
	public Test(String nome,Utente utente) 
	{
		// TODO Auto-generated constructor stub
		setNomeTest(nome);
		OwenerUtente=utente;
		QuizPresenti =new ArrayList<Quiz>();
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
	

}