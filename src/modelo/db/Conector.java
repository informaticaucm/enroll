package modelo.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mchange.v2.c3p0.*;

/**
 * Clase encargada de conectar a la BD MySQL y de mantener la conexión abierta
 *
 */
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
                this.ds = new  ComboPooledDataSource();
                this.ds.setDriverClass("org.gjt.mm.mysql.Driver");
                this.ds.setJdbcUrl("jdbc:mysql://85.10.205.173:3306/fdi_ucm_hor_1516");
                this.ds.setUser("consultorbd");
                this.ds.setPassword("Santesmases");
                     
                this.con = ds.getConnection();
            }
            catch(Exception e){
            	closeConnection();
            	JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos", "Error al conectar", JOptionPane.ERROR_MESSAGE, null);
                //e.printStackTrace();
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
