package controlador;

import java.util.ArrayList;

import modelo.Asignatura;
import modelo.Consultor;
import modelo.Conflicto;
import modelo.Estudiante;
import modelo.Oferta;
import modelo.db.Conector;

public class Controller {

	private Estudiante estudiante;
	private Oferta oferta;
	private Conector conector;
	private Consultor consultor;
	
	public Controller() {
		this.estudiante = new Estudiante();
		this.oferta = new Oferta();
		this.conector = new Conector();
		this.consultor = new Consultor(this.conector.getConexion());
	}
	
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
	public boolean addAsignaturaEstudiante(Asignatura a){
		return this.estudiante.addAsignatura(a);
	}
	
	public boolean estaElegida(Asignatura a){
		return this.estudiante.estaElegida(a);
	}
	
	public ArrayList<Asignatura> getAsignaturas(){
		return this.estudiante.getEleccion();
	}
	
	public int totalEscogidas(){
		return this.estudiante.cuantasEscogidas();
	}
	
	public ArrayList<Conflicto> getConflictos(){
		return this.estudiante.getConflictos();
	}
	
	public void vaciaEscogidas(){
		this.estudiante.vaciaElecciones();
	}
	
	//Oferta
	public boolean addAsignaturaOferta(Asignatura a){
		return this.oferta.addAsignatura(a);
	}
	
	public int addAsignaturasOferta(ArrayList<Asignatura> listado){
		return this.oferta.addAsignaturas(listado);
	}
	
	public boolean quitaAsignaturaOferta(Asignatura a){
		return this.oferta.quitaAsignatura(a);
	}
	
	public void vaciaOferta(){
		this.oferta.clear();
	}
	
	//Consultor
	public ArrayList<Asignatura> getAsignaturasCursoGrupo(int curso, char grupo){
		return this.consultor.getAsignaturasCursoGrupo(curso, grupo);
	}
	
	public ArrayList<Integer> getCursos(){
		return this.consultor.getCursos();
	}
	
	public ArrayList<Integer> getCursosConOpIt(){
		return this.consultor.getCursosConOpIt();
	}
	
	public ArrayList<Asignatura> getOptativasCursoGrIt(int curso, char grupo, char itinerario){
		return this.consultor.getOptativasCursoGrIt(curso, grupo, itinerario);
	}
	
	public ArrayList<Asignatura> getOptativasGenerales(){
		return this.consultor.getOptativasGenerales();
	}
	
	public ArrayList<String> getGruposCursoIt(int curso, char itinerario){
		return this.consultor.getGruposCursoIt(curso, itinerario);
	}
	
	public ArrayList<String> gruposCursoSeleccionado(int curso){
		return this.consultor.getGruposCursoSeleccionado(curso);
	}
	
	
	
/////////////////////
////    Vista    ////
/////////////////////
	
	
	
	
}
