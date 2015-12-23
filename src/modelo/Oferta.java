package modelo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase encargada de mostrar todas las materias que se extraigan de las consultas a la base de datos
 *
 */
public class Oferta {

	//Array<clave,valor> similares a los de PHP
	//String -> Nombre + Dia + Hora (Ej. ASORL16 -> ASOR Lunes a las 16)
	private Hashtable<String, Asignatura> listado = new Hashtable<>();
	
	public Oferta() {
		this.listado = new Hashtable<>();
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
		
		this.listado.put(a.getNombre()+a.getDia()+a.getHora(), a);
		
		return exito;
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
			if(!this.listado.contains(a.getId()))
			{
				this.listado.put(a.getNombre()+a.getDia()+a.getHora(), a);
				exitos++;
			}
		}
		return exitos;
	}
	
	/**
	 * Elimina una asignatura de la lista de ofertadas
	 * @param a
	 * @return true si se pudo eliminar la materia del listado.
	 */
	public boolean quitaAsignatura(Asignatura a){
		boolean eliminado = false;
		
		Enumeration<String> e = this.listado.keys();
		while (e.hasMoreElements()) {
			String clave = e.nextElement();
			if(this.listado.get(clave).getNombre().equalsIgnoreCase(a.getNombre())){
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
