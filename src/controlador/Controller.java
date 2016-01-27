package controlador;

import java.io.File;
import java.util.ArrayList;

import modelo.Asignatura;
import modelo.Consultor;
import modelo.Conflicto;
import modelo.Estudiante;
import modelo.Oferta;
import modelo.db.Conector;
import vista.Ventana;

public class Controller {

	
	private Estudiante estudiante;	//Materias escogidas por el estudiante, horas ocupadas y conflictos
	private Oferta oferta;			//Materias que oferta la facultad
	private Conector conector;		//Clase encargada de la conexión a la base de datos
	private Consultor consultor;	//Clase encargada de las consultas a la base de datos
	
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
	
	//->Conector
	/**
	 * Abre la conexión a la base de datos
	 */
	public void openConnection(){
		this.conector.openConnection();
	}
	
	/**
	 * Cierra la conexión con la base de datos
	 */
	public void closeConnection(){
		this.conector.closeConnection();
	}
	
	//->Estudiante
	/**
	 * Intenta añadir una asignatura a la elección del estudiante
	 * @param a - La asignatura en cuestión
	 * @return true si se ha podido insertar
	 */
	public boolean addAsignaturaEstudiante(Asignatura a){
		return this.estudiante.addAsignatura(a);
	}
	
	/**
	 * Comprueba si una asignatura ya ha sido elegida por el estudiante
	 * @param a - La asignatura en cuestión
	 * @return true si ya ha sido elegida
	 */
	public boolean estaElegidaEstudiante(Asignatura a){
		return this.estudiante.estaElegida(a);
	}
	
	/**
	 * Devuelve el listado de las asignaturas elegidas por el estudiante
	 * @return Listado de asignaturas
	 */
	public ArrayList<Asignatura> getAsignaturasEstudiante(){
		return this.estudiante.getEleccion();
	}
	
	/**
	 * Devuelve un array bidimensional con las asignaturas del cuatrimestre
	 * indicado para mostrarlas en el horario
	 * @param cuatrimestre - Cuatrimestre en cuestion
	 * @return Array con las asignaturas del cuatrimestre
	 */
	public String[][] getArrayAsignaturasEstudiante(int cuatrimestre){
		return this.estudiante.getArrayAsignaturas(cuatrimestre);
	}
	
	/**
	 * Devuelve los conflictos que haya en el horario del estudiante
	 * @return Listado de conflictos
	 */
	public ArrayList<Conflicto> getConflictos(){
		return this.estudiante.getConflictos();
	}
	
	/**
	 * Elimina la asignatura de la elección del usuario
	 * @param nombre - Nombre de la asignatura
	 * @return - true si se pudo eliminar la asignatura
	 */
	public boolean quitaAsignaturaEstudiante(String nombre){
		return this.estudiante.quitaAsignatura(nombre);
	}
	
	/**
	 * Elimina las asignaturas escogidas, los conflictos y el horario
	 */
	public void vaciaEscogidasEstudiante(){
		this.estudiante.vaciaElecciones();
	}
	
	//Oferta
	
	/**
	 * Intenta añadir una asignatura a la oferta
	 * @param a - Asginatura en cuestion
	 * @return true si ha habido exito
	 */
	public boolean addAsignaturaOferta(Asignatura a){
		return this.oferta.addAsignatura(a);
	}
	
	/**
	 * Intenta añadir un listado de asignaturas a la oferta
	 * @param listado - Listado de asignaturas
	 * @return Número de insercciones exitosas
	 */
	public int addAsignaturasOferta(ArrayList<Asignatura> listado){
		return this.oferta.addAsignaturas(listado);
	}
	
	/**
	 * Devuelve un listado con todas las entradas de horario de la materia del curso, grupo e itinerario indicados 
	 * @param curso - Curso indicado
	 * @param grupo - Grupo indicado
	 * @param itinerario - Itinerario indicado
	 * @param nombre - Nombre de la oferta
	 * @return ArrayList con las entradas de horario
	 */
	public ArrayList<Asignatura> getAsignaturasOferta(int curso, char grupo, char itinerario, String nombre){
		return this.oferta.getAsignatura(curso, grupo, itinerario, nombre);
	}
	
	/**
	 * Quita una asignatura de la oferta
	 * @param a - Asignatura a eliminar
	 * @return true si se pudo eliminar la asignatura en cuestion
	 */
	public boolean quitaAsignaturaOferta(Asignatura a){
		return this.oferta.quitaAsignatura(a);
	}
	
	/**
	 * Quita una asignatura de la oferta
	 * @param nombre - Nombre de la asignatura
	 * @return true si se pudo eliminar la asignatura
	 */
	public boolean quitaAsignaturaOferta(String nombre){
		return this.oferta.quitaAsignatura(nombre);
	}
	
	/**
	 * Elimina todas las asignaturas de la oferta
	 */
	public void vaciaOferta(){
		this.oferta.clear();
	}
	
	//Consultor
	/**
	 * Devuelve las asignaturas de un curso y un grupo indicados
	 * @param curso - Curso seleccionado
	 * @param grupo - Grupo seleccionado
	 * @param itinerario - Itinerario seleccionado
	 * @return Oferta con listado de asignaturas
	 */
	public Oferta consultaAsignaturasCursoGrupo(int curso, char grupo, char itinerario){
		this.oferta = this.consultor.getAsignaturasCursoGrupo(curso, grupo, itinerario); 
		return this.oferta;
	}
	
	/**
	 * Devuelve los nombres de las asignaturas del curso y del grupo indicados
	 * @param curso - Curso seleccionado
	 * @param grupo - Grupo seleccionado
	 * @return Listado de nombres de las asignaturas
	 */
	public ArrayList<String> consultaListadoAsignaturasCursoGrupo(int curso, char grupo){
		return this.consultor.getListadoAsignaturasCursoGrupo(curso, grupo);
	}
	
	/**
	 * Devuelve los cursos disponibles
	 * @return Listado de los cursos
	 */
	public ArrayList<Integer> consultaCursos(){
		return this.consultor.getCursos();
	}
	
	/**
	 * Devuelve los cursos que tienen optativas de itinerario
	 * @return Listado de cursos
	 */
	public ArrayList<Integer> consultaCursosConOpIt(){
		return this.consultor.getCursosConOpIt();
	}
	
	/**
	 * Devuelve las asignaturas que correspondan al curso, grupo e itinerario seleccionado
	 * @param curso - Curso seleccionado
	 * @param grupo - Grupo seleccionado
	 * @param itinerario - Itinerario seleccionado
	 * @return Lista con las asignaturas relacionadas
	 */
	public ArrayList<Asignatura> consultaOptativasCursoGrIt(int curso, char grupo, char itinerario){
		return this.consultor.getOptativasCursoGrIt(curso, grupo, itinerario);
	}
	
	/**
	 * Devuelve todas las optativas generales
	 * @return Listado de asignaturas generales
	 */
	public ArrayList<Asignatura> consultaOptativasGenerales(){
		return this.consultor.getOptativasGenerales();
	}
	
	/**
	 * Devuelve los grupos para el curso y el itinerario seleccionado
	 * @param curso - Curso seleccionado
	 * @param itinerario - Itinerario seleccionado
	 * @return Grupos existentes relacionados
	 */
	public ArrayList<String> consultaGruposCursoIt(int curso, char itinerario){
		return this.consultor.getGruposCursoIt(curso, itinerario);
	}
	
	/**
	 * Devuelve los grupos que hay en el curso seleccionado
	 * @param curso - Curso seleccionado
	 * @return Grupos disponibles
	 */
	public ArrayList<String> consultaGruposCursoSeleccionado(int curso){
		return this.consultor.getGruposCursoSeleccionado(curso);
	}
	
	
/////////////////////
////    Vista    ////
/////////////////////
	
	/**
	 * Exporta el horario donde se le indique
	 * @param file - Ruta de destino
	 */
	public void exportarHorarios(File file) {
		this.estudiante.exportHorario(file);
	}
	
	
}
