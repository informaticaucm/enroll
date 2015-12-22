package controlador;

import java.util.ArrayList;

import modelo.Asignatura;
import modelo.Conflicto;
import modelo.Estudiante;
import modelo.Oferta;
import modelo.db.Conector;

public class Controller {

	private Estudiante estudiante;
	private Oferta oferta;
	private Conector conector;
	
	
	/**
	 * Metodo encargado de iniciar la aplicación
	 */
	public void run(){
		
	}
	
//////////////////////
////    Modelo    ////
//////////////////////
	
	//Conector
	public void openConnection(){
		this.conector.openConnection();
	}
	
	public void closeConnection(){
		this.conector.closeConnection();
	}
	
	//Estudiante
	public boolean addAsignatura(Asignatura a){
		return this.estudiante.addAsignatura(a);
	}
	
	public ArrayList<Asignatura> getAsignaturas(){
		return this.estudiante.getEleccion();
	}
	
	public int totalEscogidas(){
		return this.estudiante.cuantasEscogidas();
	}
	
	public ArrayList<Conflicto> buscaConflictos(){
		return this.estudiante.buscaConflictos();
	}
	
	public void vaciaEscogidas(){
		this.estudiante.clear();
	}
	
	//Oferta
	
	
	
	
	
	
	
	
/////////////////////
////    Vista    ////
/////////////////////
	
	
	
	
}
