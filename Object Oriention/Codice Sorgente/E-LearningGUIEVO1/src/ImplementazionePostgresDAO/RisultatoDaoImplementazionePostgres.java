package ImplementazionePostgresDAO;

import java.nio.file.ReadOnlyFileSystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.MonthDay;
import java.util.ArrayList;

import DAO.RisultatoDAO;
import Database.ConnessioneDatabase;
import Model.Data;
import Model.Domanda;
import Model.ListaValutazioniAperte;
import Model.RisultatoTest;
import Model.Utente;
import Model.ValutazioneAperta;

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

	@Override
	public void ResultDaDBAperta(Utente UtenteDaAggiungere) 
	{
		// TODO Auto-generated method stub
		try {
			PreparedStatement querycount= connection.prepareStatement("select count(idvalutazioneaperta) as numero from valutazionerispostaaperta as v,docente as d where d.iddocente=v.iddocente and d.codfiscale='CSTDNL82H25F839Q' and  v.idvalutazioneaperta='1' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int CountValutazioneAperte(String codFiscaleDocenteString) {
		// TODO Auto-generated method stub
		int numero=0;
		try {
			PreparedStatement querycount= connection.prepareStatement("select  count (distinct v.matricola || v.idrisultatotest)as numero from valutazionerispostaaperta as v, docente  as d where  d.codfiscale='"+codFiscaleDocenteString+"' and d.iddocente=v.iddocente and v.valutata=false;");
			ResultSet rsqueryResultSet=querycount.executeQuery();
			if (!rsqueryResultSet.isBeforeFirst() ) 
			{  
				System.out.print("No data Result For this Utente");
			}
			else 
			{
				rsqueryResultSet.next();
				numero=rsqueryResultSet.getInt("numero");
				
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numero;
	}

	public void RecuperaValutazioniDaDB(Utente daUtente) 
	{
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement querycount= connection.prepareStatement("select distinct v.matricola as matricola, v.idrisultatotest as risultato from valutazionerispostaaperta as v, docente  as d where  d.codfiscale='"+daUtente.getCodiceFiscale()+"' and d.iddocente=v.iddocente and v.valutata=false;");
			ResultSet rsqueryResultSet=querycount.executeQuery();
			while(rsqueryResultSet.next())
			{
				String MatricolaString=rsqueryResultSet.getString("matricola");
				PreparedStatement QueryPropria= connection.prepareStatement("select v.idvalutazioneaperta as idvalutazione,v.punteggioassegnato as punteggioassegnato,v.rispostainserita as rispostainserita, q.domanda as domanda, \r\n"
						+ "q.punteggiomassimo as punteggiomassimo ,q.punteggiominimo as punteggiominimo\r\n"
						+ "from valutazionerispostaaperta  as v , quizaperta as q \r\n"
						+ "where v.matricola='"+MatricolaString+"' and v.idrisultatotest='"+rsqueryResultSet.getInt("risultato")+"' and v.valutata=false and v.idquiza=q.idquiza ");
				ResultSet rsQueryEffettiva=QueryPropria.executeQuery();
				daUtente.ListaValutazioni.add(new ListaValutazioniAperte(MatricolaString, rsqueryResultSet.getInt("risultato")) );
				while (rsQueryEffettiva.next()) 
				{
					daUtente.ListaValutazioni.get(daUtente.ListaValutazioni.size()-1).Valutazioniaperte.add(new ValutazioneAperta(rsQueryEffettiva.getString("rispostainserita"), rsQueryEffettiva.getInt("idvalutazione"), rsQueryEffettiva.getInt("punteggioassegnato"), rsQueryEffettiva.getInt("punteggiomassimo"), rsQueryEffettiva.getInt("punteggiominimo"), rsQueryEffettiva.getString("domanda")));					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void UpdateRisultatoDao(int idVal, int putenggio) 
	{
		// TODO Auto-generated method stub
		try {
			PreparedStatement QueryUpdate= connection.prepareStatement("UPDATE valutazionerispostaaperta\r\n"
					+ "	SET valutata=true, punteggioassegnato='"+putenggio+"'\r\n"
					+ "	WHERE idvalutazioneaperta='"+idVal+"';");
			QueryUpdate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList RecuperaStudeteStartMatricola(String Matr) 
	{
		// TODO Auto-generated method stub
		ArrayList arrayList= new ArrayList();
		try {
			PreparedStatement QueryRecupero= connection.prepareStatement("select nome,cognome from studente where matricola='"+Matr+"'");
			ResultSet rsqueryResultSet=QueryRecupero.executeQuery();
			rsqueryResultSet.next();
			arrayList.add((String)rsqueryResultSet.getString("nome"));	
			arrayList.add((String)rsqueryResultSet.getString("cognome"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
		
	}

}
