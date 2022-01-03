package Controller;

import java.util.ArrayList;

import DAO.TestDAO;
import DAO.UtenteDao;
import ImplementazionePostgresDAO.TestDaoImplementazionePostgres;
import ImplementazionePostgresDAO.UtenteImplementazioneDAO;
import Model.Utente;
import Model.Data;
import Model.Domanda;
import Model.Quiz;
import Model.Test;

public class Controller 
{
	private ArrayList<Utente> ListaUtenti;
	private ArrayList<Test>ListaTest;
	
	public int PrimoAccessoController;
	
	public Controller()
	{
		PrimoAccessoController=0;
		ListaTest =new ArrayList<Test>();
		ListaUtenti = new ArrayList<Utente>();
	}
	
	public void aggiungiUtente(String n,String c,int g,int m,int a, String cod)
	{	
		ListaUtenti.add(new Utente(n, c, (new Data(g, m, a)), cod));
	}
	public void aggiungiTest(String Nome,String codiceString,int gio, int mese, int anno)
	{	
		int i=-1;
		for (int j = 0; j < ListaUtenti.size(); j++) 
		{
			if(codiceString.contentEquals(ListaUtenti.get(j).getCodiceFiscale()))
			{
				i=j;
				break;
			}
		}
		System.out.println("Ho preso index "+i);
		ListaTest.add(new Test(Nome, ListaUtenti.get(i),new Data(gio, mese, anno)));
		System.out.println("Test Creato Forza Capooo");
	}
	public void addQuizToTest(int punteggioP,int negativoN,String modalita) 
	{
		if(ListaTest.size()==0)
		{
			ListaTest.get(0).AddQuiz(new Quiz(ListaTest.get(0)));
			ListaTest.get(0).QuizPresenti.get(0).AddParametri(punteggioP , negativoN, modalita);
		}
		else 
		{
			ListaTest.get(ListaTest.size()-1).AddQuiz(new Quiz(ListaTest.get(ListaTest.size()-1)));
			ListaTest.get(ListaTest.size()-1).QuizPresenti.get(ListaTest.get(ListaTest.size()-1).QuizPresenti.size()-1).AddParametri(punteggioP , negativoN, modalita);
		}		
	}
	
	public void addDomanda(String Domanda,String Risposta) 
	{
		if(ListaTest.size()==0&&ListaTest.get(0).QuizPresenti.size()==0)
		{
			ListaTest.get(0).QuizPresenti.get(0).AggiungiDomanda(new Domanda(Domanda, Risposta));
		}
		else 
		{
			ListaTest.get(ListaTest.size()-1).QuizPresenti.get(ListaTest.get(ListaTest.size()-1).QuizPresenti.size()-1).AggiungiDomanda(new Domanda(Domanda, Risposta));		
		}		
	}
	public void Debug()
	{
		System.out.println("Nome Test : "+ListaTest.get(0).getNomeTest());
		if (ListaTest.get(0).QuizPresenti.get(0).Domande.size()>=2) 
		{
			System.out.println("Domanda: "+ListaTest.get(0).QuizPresenti.get(0).Domande.get(0).Domanda);
			for (int i = 1; i < ListaTest.get(0).QuizPresenti.get(0).Domande.size(); i++) 
			{
				System.out.println("Risposta: " + i+"  "+ListaTest.get(0).QuizPresenti.get(0).Domande.get(i).Riposta);
			}
			System.out.println("Risposta: "+ListaTest.get(0).QuizPresenti.get(0).Domande.get(0).Riposta);
		}
	}
	public int SizeTestArrayList()
	{
		int size=ListaTest.size();
		return size;
	}
	public ArrayList getTestArrayList(int index)
	{
		ArrayList a = new ArrayList();
		a.add(ListaTest.get(index).getNomeTest());
		a.add(ListaTest.get(index).OwenerUtente.getNome());
		a.add(ListaTest.get(index).OwenerUtente.getCognome());
		return a;
	}
	public ArrayList getUtente(int index) 
	{		
		ArrayList a = new ArrayList();
		a.add(ListaUtenti.get(index).getNome());
		a.add(ListaUtenti.get(index).getCognome());
		a.add(ListaUtenti.get(index).getCodiceFiscale());
		a.add(ListaUtenti.get(index).getDatadinascita().getGiorno());
		a.add(ListaUtenti.get(index).getDatadinascita().getMese());
		a.add(ListaUtenti.get(index).getDatadinascita().getAnno());
		return a;
	}
	public void PrimoAccessoFlagOff()
	{
		PrimoAccessoController=1;
	}
    public void CretionTestFromDB() 
    {
    	TestDAO testprovaDao=new TestDaoImplementazionePostgres();
    	ListaTest.addAll(testprovaDao.CreaUtenti(ListaUtenti));
	}
	public int returnIndexListaTest(String n,String u)
    {
    	int a=-1;
    	for (int i = 0; i < ListaTest.size(); i++) 
    	{
    		if (ListaTest.get(i).OwenerUtente.getNome().contentEquals(u)&&ListaTest.get(i).getNomeTest().contentEquals(n))
    		{
    			a=i;
    			return a;
    		}
		}
    	return a;
    }
    public int VerificaUtente(String email,String passString)
    {
    	UtenteDao DaoCarabiniere = new UtenteImplementazioneDAO();
    	int result=DaoCarabiniere.controllaDao(email,passString);
    	return result;
    }
    public int UtenteDatabaseCreation(String emailString,String Table)
    {
    	int index=-1;
    	UtenteDao DaoIlControllore = new UtenteImplementazioneDAO();
    	String codiceFiscaleString=DaoIlControllore.VedeSePresente(emailString);
    	for (int i = 0; i < ListaUtenti.size(); i++) 
    	{
    		if (ListaUtenti.get(i).getCodiceFiscale().contentEquals(codiceFiscaleString))
    		{
    			index=i;
    			return index;
    		}
		}
    	if(index==-1)
    	{
    		ListaUtenti.add(DaoIlControllore.creaNuovoUtente(emailString,Table));
    		index=ListaUtenti.size()-1;
    		System.out.print("Prova nome "+ListaUtenti.get(index).getNome());
    		return index;
    	}
		return index;
    }
	public Test ReturnCopiaOfTest(int Index) throws CloneNotSupportedException
	{
		Test nuovoTest= (Test) ListaTest.get(Index).clone();
		return nuovoTest;
	}
}
