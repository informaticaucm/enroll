package modelo;

public class Asignatura {
	
	private int curso; 			//primary
	private String grupo; 		//primary
	private String dia; 		//primary
	private int hora; 			//primary
	private int cuatrimestre; 	//primary
	private String nombre;
	private String itinerario;
	
	public Asignatura(int curso, String grupo, String dia, int hora, int cuatrimestre, String nombre) {
		this.curso = curso;
		this.grupo = grupo;
		this.dia = dia;
		this.hora = hora;
		this.cuatrimestre = cuatrimestre;
		this.nombre = nombre;
	}

	public Asignatura(int curso, String grupo, String dia, int hora, int cuatrimestre, String nombre, String itinerario) {
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

	public String getGrupo() {
		return grupo;
	}

	public String getDia() {
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

	public String getItinerario() {
		return itinerario;
	}
	
}
