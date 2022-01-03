package ImplementazionePostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.icu.util.BytesTrie.Result;

import DAO.TestDAO;
import Database.ConnessioneDatabase;
import Model.Data;
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
		int Numerotest=0;
		ArrayList<Test>ListaTest=new ArrayList<Test>();;
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement queryTest=connection.prepareStatement("select idtest,nome,limitetempo,numeroquiz,propietarioTest,"
					+ "EXTRACT(DAY FROM scadenzadata)as GiornoScandenza,"
					+ "EXTRACT(MONTH FROM scadenzadata)as MeseScandenza,"
					+ "EXTRACT(YEAR FROM scadenzadata)as AnnoScandenza  from test");

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
				//Da Implementare Quiz
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ListaTest;
	}

	
}
