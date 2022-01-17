package ImplementazionePostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import DAO.RegisterDAO;
import Database.ConnessioneDatabase;

public class RegisterDaoImplementazioneDAO implements RegisterDAO
{

	private Connection connection;

	public RegisterDaoImplementazioneDAO() 
	{
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void RecuperaDiparmenti(String[] dipartimString) 
	{
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement queryRecuperaDipartimenti = connection.prepareStatement("select nome from dipartimento");
			ResultSet rSet= queryRecuperaDipartimenti.executeQuery();
			int i=0;
			while(rSet.next())
			{
				dipartimString[i]=rSet.getString("nome");
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void RecuperaCdL(String[] CDLStrings) {
		try {
			PreparedStatement queryRecuperacdl= connection.prepareStatement("select distinct cdl from studente");
			ResultSet rSet= queryRecuperacdl.executeQuery();
			int i=0;
			while(rSet.next())
			{
				CDLStrings[i]=rSet.getString("cdl");
				i++;
			}
			CDLStrings[i]="Nuovo";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AggiungiUtente(String Table, String nome, String Cognome, String email, String ps, String diparString,
			String cdl, String sesso, String codfiscale, int g, int m, int a) 
	{
		// TODO Auto-generated method stub
		if(Table.contentEquals("Studente"))
		{
			try 
			{
				PreparedStatement queryRecuperamatricola;
				queryRecuperamatricola = connection.prepareStatement("select matricola from studente");
				ResultSet rSet= queryRecuperamatricola.executeQuery();
				ArrayList<Integer> excludeRows=new ArrayList<Integer>();
				while(rSet.next())
				{
				 String devoLevareString=rSet.getString("matricola");
				 StringBuilder sb = new StringBuilder(devoLevareString);
				 sb.deleteCharAt(0);
				 devoLevareString=sb.toString();
				 excludeRows.add(Integer.parseInt(devoLevareString));
				}
				
				Random rand = new Random();
			    int range = 9999997 - 1000000  + 1;
			    int random = rand.nextInt(range) + 1;
			    while(excludeRows.contains(random)) 
			    {
			        random = rand.nextInt(range) + 1;
			    }
			    String Matricola ="N"+random;
			    PreparedStatement queryRecuperacodiceStruttura = connection.prepareStatement("	select codicestruttura from dipartimento where nome='"+diparString+"'");
			    ResultSet rDipaResultSet=queryRecuperacodiceStruttura.executeQuery();
			    rDipaResultSet.next();
			    String codiceStrutturaString=rDipaResultSet.getString("codicestruttura");
		
			    PreparedStatement queryAggiungiStudente=connection.prepareStatement("INSERT INTO Studente VALUES"
			    		+ "('"+Matricola+"','"+nome+"','"+Cognome+"','"+m+"/"+g+"/"+a+"','"+codfiscale+"','"+sesso+"','"+cdl+"','"+email+"','"+ps+"','"+codiceStrutturaString+"')");
			    queryAggiungiStudente.executeUpdate();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else 
		{
			
			try 
			{
				PreparedStatement  queryRecuperamatricola = connection.prepareStatement("select iddocente from docente");
				ResultSet rSet= queryRecuperamatricola.executeQuery();
				ArrayList<Integer> excludeRows=new ArrayList<Integer>();
				while(rSet.next())
				{
				 String devoLevareString=rSet.getString("iddocente");
				 StringBuilder sb = new StringBuilder(devoLevareString);
				 sb.deleteCharAt(0);
				 devoLevareString=sb.toString();
				 excludeRows.add(Integer.parseInt(devoLevareString));
				}
				
				Random rand = new Random();
			    int range =  9997-1000 + 1;
			    int random = rand.nextInt(range) + 1;
			    while(excludeRows.contains(random)) 
			    {
			        random = rand.nextInt(range) + 1;
			    }
			    String iddocente ="D"+random;
			    PreparedStatement queryRecuperacodiceStruttura = connection.prepareStatement("	select codicestruttura from dipartimento where nome='"+diparString+"'");
			    ResultSet rDipaResultSet=queryRecuperacodiceStruttura.executeQuery();
			    rDipaResultSet.next();
			    String codiceStrutturaString=rDipaResultSet.getString("codicestruttura");
			    PreparedStatement  queryAggiungiDocente = connection.prepareStatement("INSERT INTO docente VALUES"
			    		+ "('"+iddocente+"','"+nome+"','"+Cognome+"','"+m+"/"+g+"/"+a+"','"+codfiscale+"','"+sesso+"','0813056267','https://www.docenti.unina.it/"+nome+"."+Cognome+"','"+email+"','"+ps+"','"+codiceStrutturaString+"')");
			    queryAggiungiDocente.executeUpdate();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}

