package ImplementazionePostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import DAO.TestDAO;
import Database.ConnessioneDatabase;
import Model.Data;
import Model.Domanda;
import Model.Quiz;
import Model.Test;
import Model.Utente;

public class TestDaoImplementazionePostgres implements TestDAO
{
	private Connection connection;
	
	public TestDaoImplementazionePostgres() 
	{
		try 
		{
			connection = ConnessioneDatabase.getInstance().getConnection();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Test> CreaUtenti(ArrayList<Utente> Utenti) 
	{
		ArrayList<Test>ListaTest=new ArrayList<Test>();;
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement queryTest=connection.prepareStatement("select idtest,nome,limitetempo,numeroquiz,propietarioTest,"
					+ "EXTRACT(DAY FROM scadenzadata)as GiornoScandenza,"
					+ "EXTRACT(MONTH FROM scadenzadata)as MeseScandenza,"
					+ "EXTRACT(YEAR FROM scadenzadata)as AnnoScandenza  from test ORDER BY idtest ASC");
			ResultSet rs2 = queryTest.executeQuery();			
			while(rs2.next()) 
			{
				int aggiungi=0;
				int index = 0;
				String codiceDocente=rs2.getString("propietarioTest");
				PreparedStatement queryControllo = connection.prepareStatement("SELECT Nome,cognome,upper(codfiscale)as CodFiscale, EXTRACT(DAY FROM datadinascita)as Giorno,"
						+ "EXTRACT(MONTH FROM datadinascita)as Mese,"
						+ "EXTRACT(YEAR FROM datadinascita)as Anno from docente where idDocente='"+codiceDocente+"'" );
				ResultSet rs = queryControllo.executeQuery();
				rs.next();
				String nome,cognome,codicefiscaString;
				int giorno,mese,anno;
				codicefiscaString=rs.getString("CodFiscale");
				for (int i = 0; i <Utenti.size(); i++) 
				{
					if (Utenti.get(i).getCodiceFiscale().contentEquals(codicefiscaString))
		    		{
						aggiungi=1;
						index=i;
		    		}
				}
				
				if(aggiungi==0)
				{
					nome=rs.getString("Nome");
					cognome=rs.getString("Cognome");
					giorno=rs.getInt("Giorno");
					mese=rs.getInt("Mese");
					anno=rs.getInt("Anno");
					Utenti.add(new Utente(nome, cognome, new Data(giorno, mese, anno), codicefiscaString));
					index=Utenti.size()-1;
				}
				String nomeTest=rs2.getString("nome");
				giorno=rs2.getInt("GiornoScandenza");
				mese=rs2.getInt("MeseScandenza");
				anno=rs2.getInt("AnnoScandenza");
				ListaTest.add(new Test(nomeTest, Utenti.get(index), new Data(giorno, mese, anno)));
				String idtestString=rs2.getString("idtest");
				PreparedStatement queryQuizMultipla = connection.prepareStatement("select * from quizmultipla where idtestriferimento='"
						+ idtestString+"'");
				ResultSet rs3 = queryQuizMultipla.executeQuery();
				int k=0;
				while(rs3.next())
				{
					int punteggioP=rs3.getInt("PunteggioRispostaCorretta");
					int negativoN=rs3.getInt("PunteggioRispostaSbagliato");
					String modalita="M";
					if(ListaTest.size()==0)
					{
						ListaTest.get(0).AddQuiz(new Quiz(ListaTest.get(0)));
						ListaTest.get(0).QuizPresenti.get(k).AddParametri(punteggioP , negativoN, modalita);
						ListaTest.get(0).QuizPresenti.get(k).AggiungiDomanda(new Domanda(rs3.getString("Domanda"), ""));
						String idquizMultiploString=rs3.getString("idquizm");
						PreparedStatement queryDomandaMultipla=connection.prepareStatement("select * from risposta where idquizriferimento='"
								+ idquizMultiploString+"'");
						ResultSet rs4 = queryDomandaMultipla.executeQuery();
						char lettera='A';  
						String letteraString=String.valueOf(lettera);  
						while(rs4.next())
						{
							ListaTest.get(0).QuizPresenti.get(k).AggiungiDomanda(new Domanda(letteraString, rs4.getString("risposta")));
							lettera++;
						}
						lettera='A';
					}
					else 
					{
						ListaTest.get(ListaTest.size()-1).AddQuiz(new Quiz(ListaTest.get(ListaTest.size()-1)));
						ListaTest.get(ListaTest.size()-1).QuizPresenti.get(k).AddParametri(punteggioP , negativoN, modalita);
						ListaTest.get(ListaTest.size()-1).QuizPresenti.get(k).AggiungiDomanda(new Domanda(rs3.getString("Domanda"), ""));
						String idquizMultiploString=rs3.getString("idquizm");
						PreparedStatement queryDomandaMultipla=connection.prepareStatement("select * from risposta where idquizriferimento='"
								+ idquizMultiploString+"'");
						ResultSet rs4 = queryDomandaMultipla.executeQuery();
						char lettera='A';  
						String letteraString=String.valueOf(lettera);  
						while(rs4.next())
						{
							ListaTest.get(ListaTest.size()-1).QuizPresenti.get(k).AggiungiDomanda(new Domanda(letteraString, rs4.getString("risposta")));
							lettera++;
							letteraString=String.valueOf(lettera);
						}
					}		
					k++;
				}
				PreparedStatement queryQuizaperta = connection.prepareStatement("select * from quizaperta where idtestriferimento='"
						+ idtestString+"'");
				ResultSet rs4 = queryQuizaperta.executeQuery();
				while (rs4.next())
				{
					int punteggioP=rs4.getInt("PunteggioMassimo");
					int negativoN=rs4.getInt("PunteggioMinimo");
					String modalita="A";
					if (ListaTest.size()==0) 
					{
						ListaTest.get(0).AddQuiz(new Quiz(ListaTest.get(0)));
						ListaTest.get(0).QuizPresenti.get(k).AddParametri(punteggioP , negativoN, modalita);
						ListaTest.get(0).QuizPresenti.get(k).AggiungiDomanda(new Domanda(rs4.getString("Domanda"),rs4.getString("risposta") ));
					}
					else 
					{
						ListaTest.get(ListaTest.size()-1).AddQuiz(new Quiz(ListaTest.get(ListaTest.size()-1)));
						ListaTest.get(ListaTest.size()-1).QuizPresenti.get(k).AddParametri(punteggioP , negativoN, modalita);
						ListaTest.get(ListaTest.size()-1).QuizPresenti.get(k).AggiungiDomanda(new Domanda(rs4.getString("Domanda"),rs4.getString("risposta")));
					}
					k++;
				}			
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ListaTest;
	}
	public void AggiugiTestAlDB(String codice, String nome, int g, int m, int a) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement queryRecuperoElemento=connection.prepareStatement("select iddocente from docente where codfiscale='"+ codice+ "'");
			ResultSet rSet=queryRecuperoElemento.executeQuery();
			rSet.next();
			codice=rSet.getString("iddocente");
			PreparedStatement queryAggiuElementoDB=connection.prepareStatement("INSERT INTO test VALUES"
					+ "(default,'"+nome+"',120,10,'"+m+"/"+g+"/"+a+ "','"+codice+"')");
			queryAggiuElementoDB.executeUpdate();
			
		} 
		catch (SQLException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	public void AggiungiQuiz(String Modalita, String Domanda, String Risposta, int punteggioP,
			int PunteggioN) 
	{
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement QueryRecuperoTest=connection.prepareStatement("select idtest  from test  ORDER BY idtest DESC");
			ResultSet rSet = QueryRecuperoTest.executeQuery();
			rSet.next();
			int numeroTest=rSet.getInt("idtest");
			if(Modalita.contentEquals("A"))
			{
				PreparedStatement queryAggiugngiQuizA= connection.prepareStatement("INSERT INTO  quizaperta VALUES"
						+ "(default,'"+Domanda+"','"+Risposta+"',100,"+PunteggioN+","+punteggioP+","+numeroTest+")");
				queryAggiugngiQuizA.executeUpdate();
			}
			else 
			{
				
				PreparedStatement queryAggiugngiQuizM= connection.prepareStatement("INSERT INTO quizmultipla VALUES "
						+ "(default,'"+Risposta+"','"+Domanda+"',"+punteggioP+","+PunteggioN+","+numeroTest+")");
				queryAggiugngiQuizM.executeUpdate();
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Override
	public void AggiungiRisposta(String Domanda,String risposta) 
	{
		// TODO Auto-generated method stub
		PreparedStatement QueryRecuperoTest;
		try {
			QueryRecuperoTest = connection.prepareStatement("select idquizm  from quizmultipla  ORDER BY idquizm DESC");
			ResultSet rSet = QueryRecuperoTest.executeQuery();
			rSet.next();
			int numeroQuiz=rSet.getInt("idquizm");
			System.out.println("Domanda vale :"+Domanda);
			PreparedStatement queryAggiungiRispoStatement=connection.prepareStatement("INSERT INTO RISPOSTA values\r\n"
					+ "(default,'"+risposta+"','"+Domanda+"',"+numeroQuiz+")");
			queryAggiungiRispoStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
