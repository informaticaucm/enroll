package ucm.fdi.etica.modelo.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.*;

/**
 * Clase encargada de conectar a la BD MySQL y de mantener la conexión abierta
 *
 */
@Component
public class Conector {

	private Connection con;
	private ComboPooledDataSource ds;
	
	public Conector() {
		this.con = null;
		this.ds = null;
	}
	
	/**
	 * Devuelve la conexión con la base de datos
	 * @return La conexión si está creada o null en cualquier otro caso.
	 */
	public Connection getConexion(){
		return this.con;
	}
	
	/**
	 * Abre la conexión con la base de datos
	 */
	public void openConnection(){
        if(this.con == null){
            try{
                this.ds = new ComboPooledDataSource();
                this.ds.setDriverClass("org.gjt.mm.mysql.Driver");
                this.ds.setJdbcUrl("jdbc:mysql://localhost/aplicacion");
                this.ds.setUser("aplicacion");
                this.ds.setPassword("SHVBzh2Q93CUGJhU");
                     
                this.con = ds.getConnection();
            }
            catch(Exception e){
            	closeConnection();
            }
        }
    }
	
	/**
	 * Cierra la conexxión con la base de datos
	 */
	public void closeConnection(){
        try {
            if(this.con != null)
                this.con.close();
            if(this.ds != null)
            	this.ds.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        this.con = null;
        this.ds = null;
    }
	
}
