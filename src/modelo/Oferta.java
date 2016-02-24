package modelo;

import java.util.ArrayList;

/**
 * Clase encargada de mostrar todas las materias que se extraigan de las consultas a la base de datos
 *
 */
public class Oferta {

	private ArrayList<Asignatura> listado;
	
	public Oferta() {
		this.listado = new ArrayList<>();
	}
	
	/**
	 * Devuelve el listado de ofertadas
	 * @return Listado de Asignaturas ofertadas
	 */
	public ArrayList<String> getListadoOfertadas(){
		ArrayList<String> aux = new ArrayList<>();
		
		for(Asignatura a : this.listado)
			if(!estaNombre(aux, a.getNombre()))
				aux.add(a.getNombre());
		
		return aux;
	}
	
	private boolean estaNombre(ArrayList<String> lista, String nombre){
		for(String s : lista){
			if(s.contains(nombre))
				return true;
		}
		return false;
	}
	
	/**
	 * Intenta añadir una asignatura al horario.
	 * @param a - Asginatura a aÃ±adir
	 * @return true si ha tenido Ã©xito y false si ya habÃ­a sido escogida.
	 */
	public boolean addAsignatura(Asignatura a){
		return this.listado.add(a);
	}
	
	
	
	/**
	 * Intenta añadir un listado de asignaturas al horario.
	 * @param listado - Asginaturas a añadir
	 * @return Numero de inserciones con éxito
	 */
	public int addAsignaturas(ArrayList<Asignatura> listado){
		int exitos = 0;
		
		for(Asignatura a : listado)
		{
			if(addAsignatura(a))
				exitos++;
		}
		
		return exitos;
	}
	
	/**
	 * Devuelve un listado con todas las entradas de horario de la materia del curso, grupo e itinerario indicados 
	 * @param nombre - Nombre de la asignatura
	 * @return ArrayList con las entradas de horario
	 */
	public ArrayList<Asignatura> getAsignatura(String nombre){
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		
		for(Asignatura a : this.listado)
			if(a.getNombre().equalsIgnoreCase(nombre))
				asignaturas.add(a);
		
		return asignaturas;
	}
	
	/**
	 * Elimina una asignatura de la lista de ofertadas
	 * @param a - La asignatura completa
	 * @return true si se pudo eliminar la materia del listado.
	 */
	public boolean quitaAsignatura(String nombre){
		ArrayList<Asignatura> ptr = getAsignatura(nombre);
		boolean exito = true;
		
		try{
			while(ptr.size() > 0){
				this.listado.remove(ptr.get(0));
				ptr.remove(0);
			}
		}
		catch(Exception e){
			exito = false;
		}
		
		return exito;
	}
	
	/**
	 * Elimina todas las asignaturas
	 */
	public void clear(){
		this.listado.clear();
	}
}
