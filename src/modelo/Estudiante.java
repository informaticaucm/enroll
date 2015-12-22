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
	private Hashtable<String, Asignatura> eleccion;
	
	// La clave será la letra del día y la hora (X9 -> Miércoles a las 9)
	private Hashtable<String, Conflicto> conflictos;
	
	/**
	 * Constructor.
	 * Inicializa el Hashtable
	 */
	public Estudiante() {
		this.eleccion = new Hashtable<>();
		this.conflictos = new Hashtable<>();
	}
	
	/**
	 * Intenta añadir una asignatura a la elección del estudiante.<br/>
	 * @param a - Asginatura a añadir
	 * @return true si ha tenido éxito y false si ya había sido escogida.
	 */
	public boolean addAsignatura(Asignatura a){
		boolean exito = true;
		
		if(eleccion.contains(a.getNombre()))
			return false;

		if(this.eleccion.containsKey(a.getNombre())){
			String hora = ""+a.getDia()+a.getHora();
			Conflicto con = new Conflicto(a, this.eleccion.get(a.getNombre()), hora);
			
			//Si se produce un conflicto, se almacena, pero no se devuelve error
			if(this.conflictos.containsKey(hora))
				this.conflictos.get(hora).addConflicto(a);
			else
				this.conflictos.put(hora, con);
		}
		this.eleccion.put(a.getNombre(), a);
		
		return exito;
	}
	
	/**
	 * Comprueba si una asignatura ya ha sido elegida por el usuario.<br/>
	 * @param a - Asignatura a comprobar
	 * @return true si ya ha sido elegida.
	 */
	public boolean estaElegida(Asignatura a){
		Enumeration<String> e = this.eleccion.keys(); 
		while(e.hasMoreElements()){
			if(eleccion.get(e.nextElement()).getNombre().equalsIgnoreCase(a.getNombre()))
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
		Enumeration<String> e = this.eleccion.keys(); 
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
	public ArrayList<Conflicto> buscaConflictos(){
		ArrayList<Conflicto> listado = new ArrayList<>();
		
		//Iteramos por toda la tabla
		Enumeration<String> e = this.conflictos.keys(); 
		while(e.hasMoreElements()){
			listado.add(this.conflictos.get(e.nextElement()));
		}
		
		return listado;
	}
	
	/**
	 * Elimina todas las elecciones del estudiante
	 */
	public void clear(){
		this.eleccion.clear();
	}
}
