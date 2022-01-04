package ImplementazionePostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}

