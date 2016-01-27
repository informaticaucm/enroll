package modelo;

public class Asignatura {
	
	private int id;
	private int curso; 			//primary
	private char grupo; 		//primary
	private char dia; 			//primary
	private int hora; 			//primary
	private int cuatrimestre; 	//primary
	private String nombre;
	private char itinerario;
	
	/**
	 * Constructor sin itinerario
	 * @param id - ID de la asignatura
	 * @param curso - Curos de la asignatura
	 * @param grupo - Grupo de la asignatura
	 * @param dia - Dia en el que se imparte
	 * @param hora - Hora a la que se imparte
	 * @param cuatrimestre - Cuatrimestre en el que se imparte
	 * @param nombre - Nombre de la asignatura
	 */
	public Asignatura(int id, int curso, char grupo, char dia, int hora, int cuatrimestre, String nombre) {
		this.id = id;
		this.curso = curso;
		this.grupo = grupo;
		this.dia = dia;
		this.hora = hora;
		this.cuatrimestre = cuatrimestre;
		this.nombre = nombre;
		this.itinerario = ' ';
	}

	/**
	 * Constructor con itinerario
	 * @param id - ID de la asignatura
	 * @param curso - Curos de la asignatura
	 * @param grupo - Grupo de la asignatura
	 * @param dia - Dia en el que se imparte
	 * @param hora - Hora a la que se imparte
	 * @param cuatrimestre - Cuatrimestre en el que se imparte
	 * @param nombre - Nombre de la asignatura
	 * @param itinerario - Itinerario al que pertenece
	 */
	public Asignatura(int id, int curso, char grupo, char dia, int hora, int cuatrimestre, String nombre, char itinerario) {
		this.id = id;
		this.curso = curso;
		this.grupo = grupo;
		this.dia = dia;
		this.hora = hora;
		this.cuatrimestre = cuatrimestre;
		this.nombre = nombre;
		this.itinerario = itinerario;
	}
	
	public int getId(){
		return id;
	}
	
	public int getCurso() {
		return curso;
	}

	public char  getGrupo() {
		return grupo;
	}

	public char getDia() {
		return dia;
	}

	public int getHora() {
		return hora;
	}

	public int getCuatrimestre() {
		return cuatrimestre;
	}

	public String getNombre() {
		return nombre;
	}

	public char getItinerario() {
		return itinerario;
	}
	
}
