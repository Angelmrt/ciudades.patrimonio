package conexion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;


public class Conexion {
	 public Conexion() {
	    }
	    public Connection getConexion() throws SQLException,Exception{
	    	String url="jdbc:oracle:thin:city/city@10.0.1.12:1521:oradai";
			//String url="jdbc:oracle:thin:VS2DAWBIBLIOTECA18/VS2DAWBIBLIOTECA18@80.28.158.14:1521:oradai"; para casa
	        Connection con;
	        OracleDataSource ods;
	        try{

	        	ods=new OracleDataSource();
	            ods.setURL(url);
	            con=ods.getConnection();
	            DatabaseMetaData meta = con.getMetaData();
	            System.out.println("JDBC driver version is " + meta.getDriverVersion());
	            System.out.println("Data Source definido y conexion establecida");
	        }
	        catch(SQLException sqle){
	        	System.out.println(sqle.toString());
	            throw sqle;
	        }
	        catch(Exception e){
	        	System.out.println(e.toString());
	            throw e;
	        }
	        return con;
	    }
	
}
