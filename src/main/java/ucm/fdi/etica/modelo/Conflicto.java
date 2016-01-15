package ucm.fdi.etica.modelo;

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
	 * Intenta insertar una asignatura al conflito
	 * @param a - Nueva asignatura en conflicto
	 * @return true si se pudo añadir. false si ya existía
	 */
	public boolean addConflicto(Asignatura a){
		
		this.asignaturas.add(a);
		
		return true;
	}
}
