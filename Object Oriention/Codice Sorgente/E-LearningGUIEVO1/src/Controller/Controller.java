package Controller;

import java.util.ArrayList;

import javax.sql.rowset.JoinRowSet;

import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.ui.internal.handlers.WizardHandler.New;

import DAO.RegisterDAO;
import DAO.RisultatoDAO;
import DAO.TestDAO;
import DAO.UtenteDao;
import ImplementazionePostgresDAO.RegisterDaoImplementazioneDAO;
import ImplementazionePostgresDAO.RisultatoDaoImplementazionePostgres;
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
		System.out.println("Debug Line- index "+i);
		ListaTest.add(new Test(Nome, ListaUtenti.get(i),new Data(gio, mese, anno)));
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
	public void Recuperadipartimenti(String []diparimenti)
	{
		RegisterDAO recuperaDipartimentiDao= new RegisterDaoImplementazioneDAO();
		recuperaDipartimentiDao.RecuperaDiparmenti(diparimenti);
		
	}
	public int SizeTestArrayList()
	{
		int size=ListaTest.size();
		return size;
		
	}
	public void AggiungiTestalDb()
	{
		
		if(ListaTest.size()==0)
		{
			String codiceFiscaleString=ListaTest.get(0).OwenerUtente.getCodiceFiscale();
			String nomeTestString=ListaTest.get(0).getNomeTest();
			int Giorno=ListaTest.get(0).scandenzaData.getGiorno();
			int Mese=ListaTest.get(0).scandenzaData.getMese();
			int Anno=ListaTest.get(0).scandenzaData.getAnno();
			TestDAO AggTestDB=  new TestDaoImplementazionePostgres();
			AggTestDB.AggiugiTestAlDB(codiceFiscaleString, nomeTestString, Giorno, Mese, Anno);
		}
		else 
		{
			String codiceFiscaleString=ListaTest.get(ListaTest.size()-1).OwenerUtente.getCodiceFiscale();
			String nomeTestString=ListaTest.get(ListaTest.size()-1).getNomeTest();
			int Giorno=ListaTest.get(ListaTest.size()-1).scandenzaData.getGiorno();
			int Mese=ListaTest.get(ListaTest.size()-1).scandenzaData.getMese();
			int Anno=ListaTest.get(ListaTest.size()-1).scandenzaData.getAnno();
			TestDAO AggTestDB=  new TestDaoImplementazionePostgres();
			AggTestDB.AggiugiTestAlDB(codiceFiscaleString, nomeTestString, Giorno, Mese, Anno);
		}
		
	}
	public void AggiungiQuizAlDB(String modalitaString) 
	{
		if(ListaTest.size()==0)
		{
			String domanda=ListaTest.get(0).QuizPresenti.get(0).Domande.get(0).Domanda;
			String risposta=ListaTest.get(0).QuizPresenti.get(0).Domande.get(0).Riposta;
			int puntenggioP=ListaTest.get(0).QuizPresenti.get(0).Positivopunteggio;
			int NegativoP=ListaTest.get(0).QuizPresenti.get(0).Negativopunteggio;
			TestDAO AggQuizDB=  new TestDaoImplementazionePostgres();
			AggQuizDB.AggiungiQuiz(modalitaString, domanda, risposta, puntenggioP, NegativoP);
		}
		else 
		{
			String domanda=ListaTest.get(ListaTest.size()-1).QuizPresenti.get(ListaTest.get(ListaTest.size()-1).QuizPresenti.size()-1).Domande.get(0).Domanda;
			String risposta=ListaTest.get(ListaTest.size()-1).QuizPresenti.get(ListaTest.get(ListaTest.size()-1).QuizPresenti.size()-1).Domande.get(0).Riposta;
			int puntenggioP=ListaTest.get(ListaTest.size()-1).QuizPresenti.get(ListaTest.get(ListaTest.size()-1).QuizPresenti.size()-1).Positivopunteggio;
			int NegativoP=ListaTest.get(ListaTest.size()-1).QuizPresenti.get(ListaTest.get(ListaTest.size()-1).QuizPresenti.size()-1).Negativopunteggio;
			
			TestDAO AggQuizDB=  new TestDaoImplementazionePostgres();
			AggQuizDB.AggiungiQuiz(modalitaString, domanda, risposta, puntenggioP, NegativoP);
		}
	}
	public void AggiungiRipostaAlDB(String Domanda,String risposta)
	{
		TestDAO aggiungiRispostaDao= new TestDaoImplementazionePostgres();
		aggiungiRispostaDao.AggiungiRisposta(Domanda,risposta);
		
	}
	public ArrayList getTestArrayList(int index)
	{
		ArrayList a = new ArrayList();
		a.add(ListaTest.get(index).getNomeTest());
		a.add(ListaTest.get(index).OwenerUtente.getNome());
		a.add(ListaTest.get(index).OwenerUtente.getCognome());
		a.add(ListaTest.get(index).OwenerUtente.getCodiceFiscale());
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
	public void RecuperaCDL(String[] Cdl)
	{
		RegisterDAO recuperaCDLDao= new RegisterDaoImplementazioneDAO();
		recuperaCDLDao.RecuperaCdL(Cdl);
	}
	public void AggiungiUtenteAlDB(String Table,String nome,String Cognome,String email,String password,String diparString,String cdl,String sesso,String codfiscale,int g,int m,int a) 
	{
		RegisterDAO aggiungiRegisterDAO= new RegisterDaoImplementazioneDAO();
		aggiungiRegisterDAO.AggiungiUtente(Table, nome, Cognome, email, password, diparString, cdl, sesso,codfiscale, g, m, a);
	}
    public int VerificaUtente(String email,String passString)
    {
    	UtenteDao DAOUtenteVerified = new UtenteImplementazioneDAO();
    	int result=DAOUtenteVerified.controllaDao(email,passString);
    	return result;
    }
    public int UtenteDatabaseCreation(String emailString,String Table)
    {
    	int index=-1;
    	UtenteDao DaoDatabaseCreation = new UtenteImplementazioneDAO();
    	
    	String codiceFiscaleString=DaoDatabaseCreation.VedeSePresente(emailString);
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
    		ListaUtenti.add(DaoDatabaseCreation.creaNuovoUtente(emailString,Table));
    		index=ListaUtenti.size()-1;
    		return index;
    	}
		return index;
    }
    public int AggiungiRisultatoTestalDB(String nomeTest,String CodDocente,String Codstudente)
    {
    	int result;
    	RisultatoDAO AggiungiResultTestdb = new RisultatoDaoImplementazionePostgres();
    	result=AggiungiResultTestdb.AggiungiRisultatoTest(nomeTest, CodDocente, Codstudente);
    	return result;
    }
    public void AggiungiValutazioneAlDB(String Table,String RispostaInserita,String LetteraInserita,String DomandaRicostruente,String nomeTest,String CodDocente,String Codstudente,int Idrisultato)
    {
    	RisultatoDAO AggValutazioneDao= new RisultatoDaoImplementazionePostgres();
    	AggValutazioneDao.AggiungiValutazionePrePost(Table, RispostaInserita, LetteraInserita, DomandaRicostruente, nomeTest, CodDocente, Codstudente, Idrisultato);
    }
    public void AggiungiResultDaDB(String codFiscaleStudente)
    {
    	int index=0;
    	for (int i = 0; i < ListaUtenti.size(); i++) 
    	{
    		if (ListaUtenti.get(i).getCodiceFiscale().contentEquals(codFiscaleStudente))
    		{
    			index=i;
    		}
		}
    	RisultatoDAO RisultatiDaDB= new RisultatoDaoImplementazionePostgres();
    	RisultatiDaDB.ResulDaDb(ListaUtenti.get(index));
    }
    public int ReturnNumberOfResult(String CodFiscaleStudente)
    {
    	int count=0;
    	RisultatoDAO coutnResultDAO= new RisultatoDaoImplementazionePostgres();
    	count=coutnResultDAO.CountRisultato(CodFiscaleStudente);
    	return count;
    }
    public String ReturnNomeTestFromidResultTest(int index)
    {
    	String nomeString="";
    	RisultatoDAO ReturnNome=new RisultatoDaoImplementazionePostgres();
    	nomeString=ReturnNome.RisultatoNome(index);
    	return nomeString;
    }
    public int CountNumeroValutazioniDacorreggere(String codicefiscaledocente) 
    {
    	int numero;
    	RisultatoDAO coutnDaovalutazioniDao= new RisultatoDaoImplementazionePostgres();
    	numero=coutnDaovalutazioniDao.CountValutazioneAperte(codicefiscaledocente);
     	return numero;
	}
    public void AggiungiValutazioniAperteDaDB(String codicefiscaleDocente)
    {
    	int index=-1;
    	for (int i = 0; i < ListaUtenti.size(); i++) 
    	{
    		if (ListaUtenti.get(i).getCodiceFiscale().contentEquals(codicefiscaleDocente))
    		{
    			index=i;
    		}
		}
    	RisultatoDAO aggiungiValAperteDao = new RisultatoDaoImplementazionePostgres();
    	aggiungiValAperteDao.RecuperaValutazioniDaDB(ListaUtenti.get(index));
    }
    public void UpdateValutazioniAperte(int idvalutazioneAperta,int punteggio)
    {
    	RisultatoDAO updateValAperteDao = new RisultatoDaoImplementazionePostgres();
    	updateValAperteDao.UpdateRisultatoDao(idvalutazioneAperta, punteggio);
    }
    public void ClearAllListaValutazioni(String codiceFiscale)
    {
    	int index=-1;
    	for (int i = 0; i < ListaUtenti.size(); i++) 
    	{
    		if (ListaUtenti.get(i).getCodiceFiscale().contentEquals(codiceFiscale))
    		{
    			index=i;
    		}
		}
    	ListaUtenti.get(index).ListaValutazioni.clear();
    }
    public ArrayList RecuperaStudenteFromMatricola(String matricolaString ) 
    {
    	ArrayList a;
    	RisultatoDAO RecuperaUtenteDao= new RisultatoDaoImplementazionePostgres();
    	a=RecuperaUtenteDao.RecuperaStudeteStartMatricola(matricolaString);
    	return a;
	}
    public Utente ReturnCopiaofUtente(String codiceFiscale)throws CloneNotSupportedException
    {
    	int index=-1;
    	for (int i = 0; i < ListaUtenti.size(); i++) 
    	{
    		if (ListaUtenti.get(i).getCodiceFiscale().contentEquals(codiceFiscale))
    		{
    			index=i;
    		}
		}
    	Utente utenteCopiaUtente= (Utente) ListaUtenti.get(index).clone();
		return utenteCopiaUtente;
		
    }
	public Test ReturnCopiaOfTest(int Index) throws CloneNotSupportedException
	{
		Test nuovoTest= (Test) ListaTest.get(Index).clone();
		return nuovoTest;
	}
}
