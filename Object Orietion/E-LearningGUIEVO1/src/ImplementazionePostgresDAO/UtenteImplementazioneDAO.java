package ImplementazionePostgresDAO;

import DAO.UtenteDao;
import Database.ConnessioneDatabase;
import Model.Data;
import Model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UtenteImplementazioneDAO implements UtenteDao
{
	private Connection connection;

	public UtenteImplementazioneDAO() 
	{
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int controllaDao(String email, String passString) {
		// TODO Auto-generated method stub
		int result=2;
		try {
			PreparedStatement queryControllo = connection.prepareStatement("SELECT * from studente where email= '"
									+ email+"' and password='" +passString +"'");
			ResultSet rs = queryControllo.executeQuery();
			if (!rs.isBeforeFirst() ) 
			{    
			    System.out.println("No data in Studente"); 
			    PreparedStatement queryControllo2 = connection.prepareStatement("SELECT * from docente where email= '"
						+ email+"' and password='" +passString +"'");
			    ResultSet rs2 = queryControllo2.executeQuery();
			    while (rs2.next()) 
				{
					result=0;
				}	
			} 
			else 
			{
				while (rs.next()) 
					{
						result=1;
					}
			}
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String VedeSePresente(String email) {
		// TODO Auto-generated method stub
		String codiceFiscaleString="ciao";
		try {
			PreparedStatement queryControllo = connection.prepareStatement("SELECT codfiscale from docente where email= '"
					+email+"'");
			ResultSet rs = queryControllo.executeQuery();
			while (rs.next()) 
			{
				codiceFiscaleString=rs.getString("codfiscale");
			}	
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		return codiceFiscaleString;
	}

	@Override
	public Utente creaNuovoUtente(String email,String table) 
	{
		Utente nuovoUtente = null;
		String nome,cognome,codicefiscaString;
		int giorno,mese,anno;
		// TODO Auto-generated method stub
		try {
			PreparedStatement queryControllo = connection.prepareStatement("SELECT Nome,cognome,upper(codfiscale)as CodFiscale, EXTRACT(DAY FROM datadinascita)as Giorno,"
					+ "EXTRACT(MONTH FROM datadinascita)as Mese,"
					+ "EXTRACT(YEAR FROM datadinascita)as Anno from "+table+" where email= '"+email+"'" );
			ResultSet rs = queryControllo.executeQuery();
			while (rs.next()) 
			{
				nome=rs.getString("Nome");
				cognome=rs.getString("Cognome");
				codicefiscaString=rs.getString("CodFiscale");
				giorno=rs.getInt("Giorno");
				mese=rs.getInt("Mese");
				anno=rs.getInt("Anno");
				nuovoUtente= new Utente(nome, cognome, new Data(giorno, mese, anno), codicefiscaString);
			}	
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		return nuovoUtente;
	}



	
	
	
}
