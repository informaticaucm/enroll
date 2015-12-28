package controlador;

import java.util.ArrayList;

import modelo.Asignatura;
import modelo.Consultor;
import modelo.Conflicto;
import modelo.Estudiante;
import modelo.Oferta;
import modelo.db.Conector;
import vista.Ventana;

public class Controller {

	private Estudiante estudiante;
	private Oferta oferta;
	private Conector conector;
	private Consultor consultor;
	
	public Controller() {
		this.estudiante = new Estudiante();
		this.oferta = new Oferta();
		this.conector = new Conector();
		this.consultor = new Consultor();
	}
	
	/**
	 * Metodo encargado de iniciar la aplicación
	 */
	public void run(){
		Ventana v = new Ventana();
		v.setController(this);
		v.setLocationRelativeTo(null);
		
		//Conecta a la BBDD
		this.conector.openConnection();
		this.consultor.setConnection(this.conector.getConexion());
		
		//Prepara la información inicial de la ventana y la muestra
		v.run();
		
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
	
	public boolean estaElegidaEstudiante(Asignatura a){
		return this.estudiante.estaElegida(a);
	}
	
	public ArrayList<Asignatura> getAsignaturasEstudiante(){
		return this.estudiante.getEleccion();
	}
	
	public String[][] getArrayAsignaturasEstudiante(int cuatrimestre){
		return this.estudiante.getArrayAsignaturas(cuatrimestre);
	}
	
	public int totalEscogidasEstudiante(){
		return this.estudiante.cuantasEscogidas();
	}
	
	public ArrayList<Conflicto> getConflictos(){
		return this.estudiante.getConflictos();
	}
	
	public boolean quitaAsignaturaEstudiante(String nombre){
		return this.estudiante.quitaAsignatura(nombre);
	}
	
	public void vaciaEscogidasEstudiante(){
		this.estudiante.vaciaElecciones();
	}
	
	//Oferta
	public boolean addAsignaturaOferta(Asignatura a){
		return this.oferta.addAsignatura(a);
	}
	
	public int addAsignaturasOferta(ArrayList<Asignatura> listado){
		return this.oferta.addAsignaturas(listado);
	}
	
	public ArrayList<Asignatura> getAsignaturasOferta(int curso, char grupo, char itinerario, String nombre){
		return this.oferta.getAsignatura(curso, grupo, itinerario, nombre);
	}
	
	public boolean quitaAsignaturaOferta(Asignatura a){
		return this.oferta.quitaAsignatura(a);
	}
	
	public boolean quitaAsignaturaOferta(String nombre){
		return this.oferta.quitaAsignatura(nombre);
	}
	
	public void vaciaOferta(){
		this.oferta.clear();
	}
	
	//Consultor
	public Oferta consultaAsignaturasCursoGrupo(int curso, char grupo, char itinerario){
		this.oferta = this.consultor.getAsignaturasCursoGrupo(curso, grupo, itinerario); 
		return this.oferta;
	}
	
	public ArrayList<String> consultaListadoAsignaturasCursoGrupo(int curso, char grupo){
		return this.consultor.getListadoAsignaturasCursoGrupo(curso, grupo);
	}
	
	public ArrayList<Integer> consultaCursos(){
		return this.consultor.getCursos();
	}
	
	public ArrayList<Integer> consultaCursosConOpIt(){
		return this.consultor.getCursosConOpIt();
	}
	
	public ArrayList<Asignatura> consultaOptativasCursoGrIt(int curso, char grupo, char itinerario){
		return this.consultor.getOptativasCursoGrIt(curso, grupo, itinerario);
	}
	
	public ArrayList<Asignatura> consultaOptativasGenerales(){
		return this.consultor.getOptativasGenerales();
	}
	
	public ArrayList<String> consultaGruposCursoIt(int curso, char itinerario){
		return this.consultor.getGruposCursoIt(curso, itinerario);
	}
	
	public ArrayList<String> consultaGruposCursoSeleccionado(int curso){
		return this.consultor.getGruposCursoSeleccionado(curso);
	}
	
	
	
/////////////////////
////    Vista    ////
/////////////////////
	
	
	
	
}
