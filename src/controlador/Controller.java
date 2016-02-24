package controlador;

import java.io.File;
import java.util.ArrayList;

import modelo.Asignatura;
import modelo.Consultor;
import modelo.Estudiante;
import modelo.Oferta;

import vista.Ventana;

public class Controller {

	private Consultor consultor;
	private Estudiante estudiante;
	private Oferta oferta;
	private Ventana v;
	
	private boolean hayConflictos;
	
	public Controller() {
		this.consultor = new Consultor();
		this.estudiante = new Estudiante();
		this.oferta = new Oferta();
		this.hayConflictos = false;
	}
	
	/**
	 * Metodo encargado de iniciar la aplicación
	 */
	public void run(){
		v = new Ventana();
		v.setController(this);
		v.setLocationRelativeTo(null);
		
		//Conecta a la BBDD
		conectar();
		
		//Prepara la información inicial de la ventana y la muestra
		v.run();
	}
	
//////////////////////
////    Modelo    ////
//////////////////////
	
	
	//////////////////////////////////////
	//    Operaciones con Consultor     //
	//////////////////////////////////////
	/**
	 * Abre la conexión a la base de datos
	 */
	public void conectar(){
		this.consultor.conectar();
	}
	
	/**
	 * Cierra la conexión con la base de datos
	 */
	public void desconectar(){
		this.consultor.desconectar();
	}
	
	//////////////////////////////////////
	//    Operaciones con Estudiante    //
	//////////////////////////////////////
	/**
	 * Intenta añadir una asignatura a la elección del estudiante
	 * @param a - La asignatura en cuestión
	 * @return true si se ha podido insertar
	 */
	public boolean addAsignaturaEstudiante(Asignatura a){
		boolean exito = true;
		
		try{
			this.estudiante.addAsignatura(a);
			this.hayConflictos = this.estudiante.hayConflictos();
		}
		catch(Exception e){
			exito = false;
		}
		
		
		return exito;
	}
	
	public boolean addAsignaturasEstudiante(ArrayList<Asignatura> listado){
		boolean ok = true;
		
		for(Asignatura a: listado){
			ok = ok && addAsignaturaEstudiante(a);
		}
		
		v.pintarConflictos();
		
		return ok;
	}
	
	/**
	 * Comprueba si una asignatura ya ha sido elegida por el estudiante
	 * @param a - La asignatura en cuestión
	 * @return true si ya ha sido elegida
	 */
	public boolean estaElegidaEstudiante(String nombre){
		return this.estudiante.estaElegida(nombre);
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
	 * Elimina la asignatura de la elección del usuario
	 * @param nombre - Nombre de la asignatura
	 * @return - true si se pudo eliminar la asignatura
	 */
	public boolean quitaAsignaturaEstudiante(String nombre){
		boolean aux = this.estudiante.quitaAsignatura(nombre);
		this.hayConflictos = this.estudiante.hayConflictos();
		v.pintarConflictos();
		return aux;
	}
	
	/**
	 * Elimina las asignaturas escogidas, los conflictos y el horario
	 */
	public void vaciaEscogidasEstudiante(){
		this.hayConflictos = false;
		this.estudiante.vaciaElecciones();
		v.pintarConflictos();
	}
	
	/**
	 * 
	 * @return true si hay conflictos. false en caso contrario
	 */
	public boolean hayConflictos(){
		return this.hayConflictos;
	}
	
	public boolean addAsignaturaFromVentana(String nombre){
		return addAsignaturasEstudiante(this.oferta.getAsignatura(nombre));
	}
	
	//////////////////////////////////////
	//	  Operaciones con Oferta        //
	//////////////////////////////////////
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
	 * Devuelve un listado con todas las entradas de horario de la materia
	 * @param nombre - Nombre de la materia
	 * @return ArrayList con las entradas de horario
	 */
	public ArrayList<Asignatura> getAsignaturasOferta(String nombre){
		return this.oferta.getAsignatura(nombre);
	}
	
	/**
	 * Quita una asignatura de la oferta
	 * @param a - Asignatura a eliminar
	 * @return true si se pudo eliminar la asignatura en cuestion
	 */
	public boolean quitaAsignaturaOferta(Asignatura a){
		return this.oferta.quitaAsignatura(a.getNombre());
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
	
	public ArrayList<String> getListadoOfertadas(){
		return this.oferta.getListadoOfertadas();
	}
	
	//////////////////////////////////////
	//    Operaciones con Consultor     //
	//////////////////////////////////////
	/**
	 * Devuelve las asignaturas de un curso y un grupo indicados
	 * @param curso - Curso seleccionado
	 * @param grupo - Grupo seleccionado
	 * @param itinerario - Itinerario seleccionado
	 * @return Oferta con listado de asignaturas
	 */
	public void consultaAsignaturasCursoGrupo(int curso, char grupo, char itinerario){
		vaciaOferta();
		addAsignaturasOferta(this.consultor.getAsignaturasCursoGrupo(curso, grupo, itinerario)); 
	}
	
	/**
	 * Devuelve los nombres de las asignaturas del curso y del grupo indicados
	 * @param curso - Curso seleccionado
	 * @param grupo - Grupo seleccionado
	 * @return Listado de nombres de las asignaturas
	 */
	public ArrayList<String> consultaListadoAsignaturasCursoGrupo(){
		return this.oferta.getListadoOfertadas();
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
