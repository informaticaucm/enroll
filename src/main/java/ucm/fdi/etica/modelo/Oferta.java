package ucm.fdi.etica.modelo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.springframework.stereotype.Component;

/**
 * Clase encargada de mostrar todas las materias que se extraigan de las consultas a la base de datos
 *
 */

@Component
public class Oferta {

	//Array<clave,valor> similares a los de PHP
	private Hashtable<Integer, Asignatura> listado;
	
	private ArrayList<String> nombresOfertadas;
	
	public Oferta() {
		this.listado = new Hashtable<>();
		this.nombresOfertadas = new ArrayList<>();
	}
	
	public boolean addNombreOfertada(String nombre){
		if(estaInsertada(nombre))
			return false;
		this.nombresOfertadas.add(nombre);
		return true;
	}
	
	public ArrayList<String> getListadoOfertadas(){
		return this.nombresOfertadas;
	}
	
	/**
	 * Comprueba si una asignatura ya ha sido insertada en nombresOfertadas.<br/>
	 * @param a - Asignatura a comprobar
	 * @return true si ya ha sido elegida.
	 */
	public boolean estaInsertada(String nombre){
		Enumeration<Integer> e = this.listado.keys(); 
		while(e.hasMoreElements()){
			if(listado.get(e.nextElement()).getNombre().equalsIgnoreCase(nombre))
				return true;
		}
		return false;
	}
	
	/**
	 * Intenta añadir una asignatura al horario.<br/>
	 * @param a - Asginatura a añadir
	 * @return true si ha tenido éxito y false si ya había sido escogida.
	 */
	public boolean addAsignatura(Asignatura a){
		boolean exito = true;
		
		if(listado.containsKey(a.getId()))
			return false;
		
		this.listado.put(a.getId(), a);
		
		return exito;
	}
	
	public ArrayList<Asignatura> getAsignaturasCursoGrupoIt(int curso, char grupo, char itinerario){
ArrayList<Asignatura> entradas = new ArrayList<>();
		
		char noIt = ' '; //Itinerario opuesto al escogido
		if(itinerario == 'I')
			noIt = 'C';
		else
			noIt = 'I';
		Enumeration<Integer> e = this.listado.keys(); 
		while(e.hasMoreElements()){
			Asignatura a = this.listado.get(e.nextElement());
			if(a.getCurso() == curso || a.getCurso() == 0) //Curso 
				if(a.getGrupo() == grupo || a.getCurso() == 0) //Grupo -> En caso de ser general, solo suele haber grupo A
					if(a.getItinerario() != noIt) //!=Itinerario
							entradas.add(a);
		}
		
		return entradas;
	}
	
	/**
	 * Devuelve un listado con todas las entradas de horario de la materia del curso, grupo e itinerario indicados 
	 * @param curso - Curso indicado
	 * @param grupo - Grupo indicado
	 * @param itinerario - Itinerario indicado
	 * @param nombre - Nombre de la asignatura
	 * @return ArrayList con las entradas de horario
	 */
	public ArrayList<Asignatura> getAsignatura(int curso, char grupo, char itinerario, String nombre){
		ArrayList<Asignatura> entradas = new ArrayList<>();
		
		char noIt = ' '; //Itinerario opuesto al escogido
		if(itinerario == 'I')
			noIt = 'C';
		else
			noIt = 'I';
		Enumeration<Integer> e = this.listado.keys(); 
		while(e.hasMoreElements()){
			Asignatura a = this.listado.get(e.nextElement());
			if(a.getCurso() == curso || a.getCurso() == 0) //Curso 
				if(a.getGrupo() == grupo || a.getCurso() == 0) //Grupo -> En caso de ser general, solo suele haber grupo A
					if(a.getItinerario() != noIt) //!=Itinerario
						if(a.getNombre().equalsIgnoreCase(nombre)) //Nombre
							entradas.add(a);
		}
		
		return entradas;
	}
	
	/**
	 * Intenta añadir un listado de asignaturas al horario.<br/>
	 * @param listado - Asginaturas a añadir
	 * @return Número de inserciones con éxito
	 */
	public int addAsignaturas(ArrayList<Asignatura> listado){
		int exitos = 0;
		
		for(Asignatura a : listado)
		{
			if(!this.listado.containsKey(a.getId()))
			{
				this.listado.put(a.getId(), a);
				exitos++;
			}
		}
		return exitos;
	}
	
	/**
	 * Elimina una asignatura de la lista de ofertadas
	 * @param a - La asignatura completa
	 * @return true si se pudo eliminar la materia del listado.
	 */
	public boolean quitaAsignatura(Asignatura a){
		boolean eliminado = false;
		
		Enumeration<Integer> e = this.listado.keys();
		while (e.hasMoreElements()) {
			int clave = e.nextElement();
			if(this.listado.get(clave).getNombre().equalsIgnoreCase(a.getNombre())){
				this.listado.remove(clave);
				eliminado = true;
			}
		}
		return eliminado;
	}
	
	/**
	 * Elimina una asignatura de la lista de ofertadas
	 * @param nombre - El nombre de la asignatura a borrar
	 * @return true si se pudo eliminar la materia del listado.
	 */
	public boolean quitaAsignatura(String nombre){
		boolean eliminado = false;
		
		Enumeration<Integer> e = this.listado.keys();
		while (e.hasMoreElements()) {
			int clave = e.nextElement();
			if(this.listado.get(clave).getNombre().equalsIgnoreCase(nombre)){
				this.listado.remove(clave);
				eliminado = true;
			}
		}
		return eliminado;
	}
	
	/**
	 * Elimina todas las asignaturas
	 */
	public void clear(){
		this.listado.clear();
	}
}