package modelo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase encargada de guardar todas las materias que vaya escogiendo el usuario
 * De esta clase se saca la cuadrícula con el horario en la interfaz gráfica
 */

public class Estudiante {

	//Array<clave,valor> similares a los de PHP
	private Hashtable<Integer, Asignatura> eleccion;
	
	//Cuatrimestre-Dia-Hora (Ej. 1-X-19)
	private ArrayList<String> horasOcupadas;
	
	// La clave será Cuatrimestre-Dia-Hora (Ej. 1-X-19)
	private Hashtable<String, Conflicto> conflictos;
	
	/**
	 * Constructor.
	 * Inicializa el Hashtable
	 */
	public Estudiante() {
		this.eleccion = new Hashtable<>();
		this.horasOcupadas = new ArrayList<>();
		this.conflictos = new Hashtable<>();
	}
	
	/**
	 * Intenta añadir una asignatura a la elección del estudiante.<br/>
	 * @param a - Asginatura a añadir
	 * @return true si ha tenido éxito y false si ya había sido escogida.
	 */
	public boolean addAsignatura(Asignatura a){
		boolean exito = true;
		
		if(eleccion.containsKey(a.getId()))
			return false;

		String hora = a.getCuatrimestre()+"-"+a.getDia()+"-"+a.getHora();
		
		//Comprueba si alguna materia está ocupando esa hora
		if(horaOcupada(hora)){
			//Conflicto detectado -> se busca la clase 
			Conflicto con = new Conflicto(a, buscaConflito(hora), hora);
			
			//Si ya había conflicto, se añade la asignatura al conflicto. Si no, se crea
			if(this.conflictos.containsKey(hora))
				this.conflictos.get(hora).addConflicto(a);
			else
				this.conflictos.put(hora, con);
		}
		else
			this.horasOcupadas.add(hora);
		
		this.eleccion.put(a.getId(), a);
		
		return exito;
	}
	
	/**
	 * Comprueba si una hora ya está siendo ocupada
	 * @param hora - Hora a comprobar en formato <i>Cuatrimestre-Dia-Hora</i>
	 * @return true si alguna materia elegida tiene clase a esa hora
	 */
	private boolean horaOcupada(String hora){
		for(String s : this.horasOcupadas){
			if(s.equalsIgnoreCase(hora))
				return true;
		}
		return false;
	}
	
	/**
	 * Busca la materia con la que está en conflicto
	 * @param hora - Hora a comprobar en formato <i>Cuatrimestre-Dia-Hora</i>
	 * @return La asignatura con la que se está en conflicto o <b>null</b> si no hay conflicto
	 */
	private Asignatura buscaConflito(String hora){
		Asignatura a = null;
		String aux = "";
		
		Enumeration<Integer> e = this.eleccion.keys();
		while (e.hasMoreElements()) {
			a = this.eleccion.get(e.nextElement());
			aux = a.getCuatrimestre() + "-" + a.getDia() + "-" + a.getHora();
			if(aux.equalsIgnoreCase(hora))
				return a;
		}
		return null;
	}
	
	/**
	 * Comprueba si una asignatura ya ha sido elegida por el usuario.<br/>
	 * @param a - Asignatura a comprobar
	 * @return true si ya ha sido elegida.
	 */
	public boolean estaElegida(Asignatura a){
		Enumeration<Integer> e = this.eleccion.keys(); 
		while(e.hasMoreElements()){
			if(eleccion.get(e.nextElement()).getId() == a.getId())
				return true;
		}
		return false;
	}
	
	/**
	 * Devuelve un ArrayList<Asignatura> con todas las asignaturas elegidas hasta el momento
	 * @return Asignaturas elegidas
	 */
	public ArrayList<Asignatura> getEleccion(){
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		
		//Iteramos por toda la tabla
		Enumeration<Integer> e = this.eleccion.keys(); 
		while(e.hasMoreElements()){
			asignaturas.add(this.eleccion.get(e.nextElement()));
		}
		
		return asignaturas;
	}
	
	/**
	 * Devuelve el número de asignaturas que lleva escogidas el alumno
	 * @return Número de asignaturas escogidas
	 */
	public int cuantasEscogidas(){
		return this.eleccion.size();
	}
	
	/**
	 * Comprueba si hay conflictos y, si los hay, devuelve un arrayList.<br/>
	 * Si no hay conflictos, devuelve null
	 * @return Un ArrayList con todos los conflictos en el horario del alumno
	 */
	public ArrayList<Conflicto> getConflictos(){
		ArrayList<Conflicto> listado = new ArrayList<>();
		
		//Iteramos por toda la tabla
		Enumeration<String> e = this.conflictos.keys(); 
		while(e.hasMoreElements()){
			listado.add(this.conflictos.get(e.nextElement()));
		}
		
		return listado;
	}
	
	/**
	 * Elimina todas las elecciones del estudiante, con ello los conflictos y las horas ocupadas
	 */
	public void vaciaElecciones(){
		this.eleccion.clear();
		this.horasOcupadas.clear();
		this.conflictos.clear();
	}
}
