package ImplementazionePostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.MonthDay;

import DAO.RisultatoDAO;
import Database.ConnessioneDatabase;
import Model.Data;
import Model.Domanda;
import Model.RisultatoTest;
import Model.Utente;

public class RisultatoDaoImplementazionePostgres implements RisultatoDAO 
{
	private Connection connection;
	public int IdRisultatoTest;
	
	public RisultatoDaoImplementazionePostgres() 
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
	
	@Override
	public void AggiungiValutazionePrePost(String Table,String RispostaInserita,String LetteraInserita,String DomandaRicostruente,String nomeTest,String CodDocente,String Codstudente,int Idrisultato)
	{
		
		if(Table.contentEquals("Aperta"))
		{
			try {
				
				
				String Matricola;
				PreparedStatement queryRecuperoCodDocente=connection.prepareStatement("select iddocente from docente where codfiscale='"+CodDocente+"'");
				ResultSet rscodDocente=queryRecuperoCodDocente.executeQuery();
				rscodDocente.next();
				String codiceDocenteVarchar5=rscodDocente.getString("iddocente");
				PreparedStatement queryRecuperoTest=connection.prepareStatement("select idtest from test where nome='"+nomeTest+"' and propietariotest='"+codiceDocenteVarchar5+"'");
				ResultSet rsidTest=queryRecuperoTest.executeQuery();
				rsidTest.next();
				int Idtest=rsidTest.getInt("idtest");
				PreparedStatement queryRecuperaMatricola= connection.prepareStatement("select matricola from studente where codfiscale='"+Codstudente+"'");
				ResultSet rsMatricola=queryRecuperaMatricola.executeQuery();
				rsMatricola.next();
				Matricola=rsMatricola.getString("matricola");
				PreparedStatement queryRecuperoIdquiza=connection.prepareStatement("select idquiza from quizaperta where idtestriferimento='"+Idtest+"' and domanda = '"+DomandaRicostruente+"'");
				ResultSet rsIdquizA=queryRecuperoIdquiza.executeQuery();
				rsIdquizA.next();
				int idquiza=rsIdquizA.getInt("idquiza");
				PreparedStatement queryInsertValAperta=connection.prepareStatement("INSERT INTO valutazionerispostaaperta("
						+ "idvalutazioneaperta, rispostainserita, punteggioassegnato, valutata, matricola, iddocente, idrisultatotest, idquiza)"
						+ "VALUES (default, '"+RispostaInserita+"', 0, false, '"+Matricola+"', '"+codiceDocenteVarchar5+"', "+Idrisultato+", '"+idquiza+"'); ");
				queryInsertValAperta.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
try {
				
				
				String Matricola;
				PreparedStatement queryRecuperoCodDocente=connection.prepareStatement("select iddocente from docente where codfiscale='"+CodDocente+"'");
				ResultSet rscodDocente=queryRecuperoCodDocente.executeQuery();
				rscodDocente.next();
				String codiceDocenteVarchar5=rscodDocente.getString("iddocente");
				PreparedStatement queryRecuperoTest=connection.prepareStatement("select idtest from test where nome='"+nomeTest+"' and propietariotest='"+codiceDocenteVarchar5+"'");
				ResultSet rsidTest=queryRecuperoTest.executeQuery();
				rsidTest.next();
				int Idtest=rsidTest.getInt("idtest");
				PreparedStatement queryRecuperaMatricola= connection.prepareStatement("select matricola from studente where codfiscale='"+Codstudente+"'");
				ResultSet rsMatricola=queryRecuperaMatricola.executeQuery();
				rsMatricola.next();
				Matricola=rsMatricola.getString("matricola");
				System.out.println("Id test vale "+Idtest+"la domanda invece e "+DomandaRicostruente);
				PreparedStatement queryRecuperoIdquizM=connection.prepareStatement("select idquizm from quizmultipla where idtestriferimento = '"+Idtest+"' and domanda ='"+DomandaRicostruente+"' ");
				ResultSet rsIdquizM=queryRecuperoIdquizM.executeQuery();
				rsIdquizM.next();
				int idquizm=rsIdquizM.getInt("idquizm");
				PreparedStatement queryInsertValMultipla=connection.prepareStatement("INSERT INTO valutazionerispostamultipla(\r\n"
						+ "	idvalutazionemultipla, letterainserita, matricola, punteggioottenuto, valutata, idrisultatotest, idquizm)\r\n"
						+ "	VALUES (default,'"+LetteraInserita+"', '"+Matricola+"', 0, false, '"+Idrisultato+"', '"+idquizm+"');");
				queryInsertValMultipla.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public int AggiungiRisultatoTest(String nomeTest,String CodDocente,String Codstudente)
	{
		try {
			int Idtest;
			String codiceDocenteVarchar5;
			String Matricola;
			PreparedStatement queryRecuperoCodDocente=connection.prepareStatement("select iddocente from docente where codfiscale='"+CodDocente+"'");
			ResultSet rscodDocente=queryRecuperoCodDocente.executeQuery();
			rscodDocente.next();
			codiceDocenteVarchar5=rscodDocente.getString("iddocente");
			PreparedStatement queryRecuperoTest=connection.prepareStatement("select idtest from test where nome='"+nomeTest+"' and propietariotest='"+codiceDocenteVarchar5+"'");
			ResultSet rsidTest=queryRecuperoTest.executeQuery();
			rsidTest.next();
			Idtest=rsidTest.getInt("idtest");
			PreparedStatement queryRecuperaMatricola= connection.prepareStatement("select matricola from studente where codfiscale='"+Codstudente+"'");
			ResultSet rsMatricola=queryRecuperaMatricola.executeQuery();
			rsMatricola.next();
			Matricola=rsMatricola.getString("matricola");
			String spinnerRiferimentoString= String.valueOf(java.time.Year.now());
		    MonthDay m = MonthDay.now();
		    int Year=Integer.parseInt(spinnerRiferimentoString);
		    int Day=m.getDayOfMonth();
		    int mese=m.getMonthValue();
			PreparedStatement queryAggiungiRisultatoTest= connection.prepareStatement("INSERT INTO risultatotest("
					+ "	idrisultatotest, matricola, punteggiototale, datatest, idtest, numeroquiz)"
					+ "	VALUES (default, '"+Matricola+"', 0, '"+mese+"/"+Day+"/"+Year+"/', '"+Idtest+"', 0);");
			queryAggiungiRisultatoTest.executeUpdate();
			PreparedStatement queryassegnanaIdrisulatoTest=connection.prepareStatement("select idrisultatotest from risultatotest order by idrisultatotest DESC");
			ResultSet rsIdRisultatoTest=queryassegnanaIdrisulatoTest.executeQuery();
			rsIdRisultatoTest.next();
			IdRisultatoTest=rsIdRisultatoTest.getInt("idrisultatotest");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IdRisultatoTest;
	}

	@Override
	public int CountRisultato(String Utente) {
		// TODO Auto-generated method stub
		int count=0;
	
		try {
			PreparedStatement  queryRecuperaMatricola = connection.prepareStatement("select matricola from studente where codfiscale='"+Utente+"'");
			ResultSet rsMatricola=queryRecuperaMatricola.executeQuery();
			rsMatricola.next();
			String Matricola=rsMatricola.getString("matricola");
			PreparedStatement queryRecuperoNumero = connection.prepareStatement("select count(idrisultatotest) from risultatoTest where matricola='"+Matricola+"'");
			ResultSet rsNumero=queryRecuperoNumero.executeQuery();
			if (!rsNumero.isBeforeFirst() ) 
			{  
				System.out.print("No data Result For this Utente");
			}
			else 
			{
				rsNumero.next();
				count=rsNumero.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void ResulDaDb(Utente UtenteDaAggiungere) 
	{
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement queryRecuperaMatricola = connection.prepareStatement("select matricola from studente where codfiscale='"+(String)UtenteDaAggiungere.getCodiceFiscale()+"'");
			ResultSet rsMatricola=queryRecuperaMatricola.executeQuery();
			rsMatricola.next();
			String Matricola=rsMatricola.getString("matricola");
			PreparedStatement queryIdRisultatoTest= connection.prepareStatement("select idrisultatotest,punteggiototale,EXTRACT(DAY FROM datatest)as Giorno,EXTRACT(MONTH FROM datatest)as mese,EXTRACT(YEAR FROM datatest)as YEAR from risultatotest where matricola='"+Matricola+"'");
			ResultSet rsIdRTs=queryIdRisultatoTest.executeQuery();
			while (rsIdRTs.next()) 
			{
				if(UtenteDaAggiungere.ListaRisultati.size()==0)
				{
					UtenteDaAggiungere.ListaRisultati.add(new RisultatoTest(rsIdRTs.getInt("idrisultatotest"), Matricola, new Data(rsIdRTs.getInt("giorno"),rsIdRTs.getInt("mese") ,rsIdRTs.getInt("year") ),rsIdRTs.getInt("punteggiototale")));
					PreparedStatement queryValutazioniAperte= connection.prepareStatement("select  q.domanda as domanda,v.rispostainserita as rispostainserita from valutazionerispostaaperta as v ,quizaperta as q where q.idquiza= v.idquiza and v.idrisultatotest='"+UtenteDaAggiungere.ListaRisultati.get(0).idRisultatoTest+"'");
					ResultSet rsVats=queryValutazioniAperte.executeQuery();
					while (rsVats.next()) 
					{
						UtenteDaAggiungere.ListaRisultati.get(0).DomandePresenti.add(new Domanda(rsVats.getString("domanda"),rsVats.getString("rispostainserita")));
					}
					PreparedStatement queryValutazioniMultiple= connection.prepareStatement("select  q.domanda as domanda, v.letterainserita as letterainserita   from valutazionerispostamultipla as v ,quizmultipla as q where q.idquizm= v.idquizm and v.idrisultatotest='"+UtenteDaAggiungere.ListaRisultati.get(0).idRisultatoTest+"'");
					ResultSet rsVmts=queryValutazioniMultiple.executeQuery();
					while (rsVmts.next()) 
					{
						UtenteDaAggiungere.ListaRisultati.get(0).DomandePresenti.add(new Domanda(rsVmts.getString("domanda"),rsVmts.getString("letterainserita")));
					}
				}
				else 
				{
					boolean autorizzato=true;
					for (int i = 0; i < UtenteDaAggiungere.ListaRisultati.size(); i++) 
					{
						if (UtenteDaAggiungere.ListaRisultati.get(i).idRisultatoTest==rsIdRTs.getInt("idrisultatotest")) 
						{
							autorizzato=false;
						}
					}
					if (autorizzato) 
					{
						UtenteDaAggiungere.ListaRisultati.add(new RisultatoTest(rsIdRTs.getInt("idrisultatotest"), Matricola, new Data(rsIdRTs.getInt("giorno"),rsIdRTs.getInt("mese") ,rsIdRTs.getInt("year") ),rsIdRTs.getInt("punteggiototale")));
						PreparedStatement queryValutazioniAperte= connection.prepareStatement("select  q.domanda as domanda,v.rispostainserita as rispostainserita from valutazionerispostaaperta as v ,quizaperta as q where q.idquiza= v.idquiza and v.idrisultatotest='"+UtenteDaAggiungere.ListaRisultati.get(UtenteDaAggiungere.ListaRisultati.size()-1).idRisultatoTest+"'");
						ResultSet rsVats=queryValutazioniAperte.executeQuery();
						while (rsVats.next()) 
						{
							UtenteDaAggiungere.ListaRisultati.get(UtenteDaAggiungere.ListaRisultati.size()-1).DomandePresenti.add(new Domanda(rsVats.getString("domanda"),rsVats.getString("rispostainserita")));
						}
						PreparedStatement queryValutazioniMultiple= connection.prepareStatement("select  q.domanda as domanda, v.letterainserita as letterainserita from valutazionerispostamultipla as v ,quizmultipla as q where q.idquizm= v.idquizm and v.idrisultatotest='"+UtenteDaAggiungere.ListaRisultati.get(UtenteDaAggiungere.ListaRisultati.size()-1).idRisultatoTest+"'");
						ResultSet rsVmts=queryValutazioniMultiple.executeQuery();
						while (rsVmts.next()) 
						{
							UtenteDaAggiungere.ListaRisultati.get(UtenteDaAggiungere.ListaRisultati.size()-1).DomandePresenti.add(new Domanda(rsVmts.getString("domanda"),rsVmts.getString("letterainserita")));
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public String RisultatoNome(int index) 
	{
		String nomeString="";
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement queryRecuperoNome = connection.prepareStatement("select  te.nome as testnome from test as te, risultatotest  as r where te.idtest=r.idtest and r.idrisultatotest='"+index+"'");
			ResultSet rsRecuperoNome=queryRecuperoNome.executeQuery();
			rsRecuperoNome.next();
			nomeString=rsRecuperoNome.getString("testnome");
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nomeString;
	}

}
