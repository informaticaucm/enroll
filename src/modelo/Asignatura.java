package modelo;

public class Asignatura {
	
	private int curso; 			//primary
	private char grupo; 		//primary
	private char dia; 		//primary
	private int hora; 			//primary
	private int cuatrimestre; 	//primary
	private String nombre;
	private char itinerario;
	
	public Asignatura(int curso, char grupo, char dia, int hora, int cuatrimestre, String nombre) {
		this.curso = curso;
		this.grupo = grupo;
		this.dia = dia;
		this.hora = hora;
		this.cuatrimestre = cuatrimestre;
		this.nombre = nombre;
		this.itinerario = ' ';
	}

	public Asignatura(int curso, char grupo, char dia, int hora, int cuatrimestre, String nombre, char itinerario) {
		this.curso = curso;
		this.grupo = grupo;
		this.dia = dia;
		this.hora = hora;
		this.cuatrimestre = cuatrimestre;
		this.nombre = nombre;
		this.itinerario = itinerario;
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
