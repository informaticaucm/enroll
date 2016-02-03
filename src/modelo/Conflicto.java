package modelo;

import java.util.ArrayList;

/**
 * Objeto en el que se guardarán los conflictos de varias asginaturas en la misma hora
 *
 */
public class Conflicto {

	private ArrayList<Asignatura> asignaturas;
	private String hora;
	
	/**
	 * Constructor que solo inicializa las variables
	 */
	public Conflicto() {
		this.asignaturas = new ArrayList<>();
		this.hora = "";
	}
	
	public ArrayList<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	/**
	 * Devuelve una cadena con la hora del conficto en formato "Cuatrimestre-Dia-Hora"
	 * @return Hora del conflito
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Establece el conflicto entre las asignaturas A y B a la hora indicada
	 * @param a - Asignatura A
	 * @param b - Asignatura B
	 * @param hora - Hora en cuestión
	 */
	public Conflicto(Asignatura a, Asignatura b, String hora){
		this.asignaturas = new ArrayList<>();
		this.asignaturas.add(a);
		this.asignaturas.add(b);
		this.hora = hora;
	}
	
	/**
	 * Inserta una asignatura al conflito
	 * @param a - Nueva asignatura en conflicto
	 */
	public void addConflicto(Asignatura a){
		this.asignaturas.add(a);
	}
	
	/**
	 * Elimina una asignatura del conflicto
	 * @param a
	 */
	public void removeConflicto(String asginatura){
		boolean encontrado = false;
		int size = 0;
		
		while(size < this.asignaturas.size() && !encontrado){
			if(this.asignaturas.get(size).getNombre().equalsIgnoreCase(asginatura))
				encontrado = true;
			
			if(!encontrado)
				size++;
		}
		this.asignaturas.remove(size);
	}
}
