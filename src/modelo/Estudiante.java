package modelo;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase encargada de guardar todas las materias que vaya escogiendo el usuario
 * De esta clase se saca la cuadrícula con el horario en la interfaz gráfica
 */

public class Estudiante {

	//Array<clave,valor> similares a los de PHP
	// La clave será Cuatrimestre-Dia-Hora (Ej. 1-X-19)
	private Hashtable<String, ArrayList<Asignatura>> eleccion;
	
	//Indice con los nombres de las asignaturas escogidas a fin de acelerar el proceso de comprobacion
	private ArrayList<String> nombresEscogidas;
	
	/**
	 * Constructor.
	 * Inicializa el Hashtable
	 */
	public Estudiante() {
		this.eleccion = new Hashtable<>();
		this.nombresEscogidas = new ArrayList<>();
	}
	
	/**
	 * Intenta añadir una asignatura a la elección del estudiante.
	 * @param a - Asginatura a añadir
	 * @return true si ha tenido éxito y false si ya había sido escogida.
	 */
	public boolean addAsignatura(Asignatura a){
		boolean exito = true;
		try
		{
			String clave = a.crearClave();
			if(this.eleccion.containsKey(clave)){
				this.eleccion.get(clave).add(a);
				this.nombresEscogidas.add(a.getNombre());
			}
			else{
				ArrayList<Asignatura> listado = new ArrayList<>();
				listado.add(a);
				this.eleccion.put(clave, listado);
				this.nombresEscogidas.add(a.getNombre());
			}
		}
		catch(Exception e){
			System.err.println("Error al intentar añadir una asignatura.");
			e.printStackTrace();
			exito = false;
		}
		
		return exito;
	}
	
	/**
	 * Comprueba si una asignatura ya ha sido elegida por el usuario.
	 * @param a - Asignatura a comprobar
	 * @return true si ya ha sido elegida.
	 */
	public boolean estaElegida(Asignatura a){
		for(String s : this.nombresEscogidas)
			if(s.equalsIgnoreCase(a.getNombre()))
				return true;
		
		return false;
	}
	
	/**
	 * Devuelve una lista con todas las asignaturas elegidas hasta el momento.
	 * @return Asignaturas elegidas
	 */
	public ArrayList<Asignatura> getEleccion(){
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		
		Enumeration<String> e = this.eleccion.keys();
		while(e.hasMoreElements())
		{
			asignaturas.addAll(this.eleccion.get(e.nextElement()));
		}
		
		return asignaturas;
	}
	
	/**
	 * Devuelve una tabla con las materias elegidas que correspondan al cuatrimestre indicado
	 * @param cuatrimestre - El cuatrimestre indicado
	 * @return String[][] con las Asignaturas seleccionadas
	 */
	public String[][] getArrayAsignaturas(int cuatrimestre){
		String tabla[][] = new String[12][6];
		
		//Llena todas las casillas con ""
		for (String[] row: tabla)
			Arrays.fill(row, "");
		
		//[Fila][Columna]
		//Inicialuizamos las horas
		tabla[0][0] = "9";
		tabla[1][0] = "10";
		tabla[2][0] = "11";
		tabla[3][0] = "12";
		tabla[4][0] = "13";
		tabla[5][0] = "14";
		tabla[6][0] = "15";
		tabla[7][0] = "16";
		tabla[8][0] = "17";
		tabla[9][0] = "18";
		tabla[10][0] = "19";
		tabla[11][0] = "20";
		
		int[] coord;
		String aux;
		for(Asignatura a : getEleccion()){
			if(a.getCuatrimestre() == cuatrimestre)
			{
				coord = getCoordenadasTabla(a);
				aux = tabla[coord[0]][coord[1]];
				if(aux.length() == 0)
					tabla[coord[0]][coord[1]] += a.getNombre();
				else
					tabla[coord[0]][coord[1]] += " - " + a.getNombre();
			}
		}
		return tabla;
	}
	
	/**
	 * Elimina una asignatura de la lista de ofertadas
	 * @param nombre - El nombre de la asignatura a borrar
	 * @return true si se pudo eliminar la materia del listado.
	 */
	public boolean quitaAsignatura(String nombre){
		ArrayList<Asignatura> aux = new ArrayList<>();
		ArrayList<Asignatura> ptr;
		
		try
		{
			Enumeration<String> e = this.eleccion.keys();
			while(e.hasMoreElements())
			{
				ptr = this.eleccion.get(e.nextElement());
				for(Asignatura a : ptr)
				{
					if(a.getNombre().equalsIgnoreCase(nombre))
						aux.add(a);
				}
				while(aux.size() > 0)
				{
					ptr.remove(aux.get(0));
					aux.remove(0);
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("Error al intentar eliminar la asignatura " + nombre + ".");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Devuelve la casilla de la tabla en la que tiene que estar la asignatura
	 * @param a - La asignatura
	 * @return coord[1] es el día, coord[0] es la hora
	 */
	private int[] getCoordenadasTabla(Asignatura a){
		int coord[] = new int[2];
		
		String dia = ""+ a.getDia();
		int hora = a.getHora();
		
		switch (hora) {
			case 9:  coord[0] = 0; break;
			case 10: coord[0] = 1; break;
			case 11: coord[0] = 2; break;
			case 12: coord[0] = 3; break;
			case 13: coord[0] = 4; break;
			case 14: coord[0] = 5; break;
			case 15: coord[0] = 6; break;
			case 16: coord[0] = 7; break;
			case 17: coord[0] = 8; break;
			case 18: coord[0] = 9; break;
			case 19: coord[0] = 10; break;
			case 20: coord[0] = 11; break;
		}
		
		switch (dia.toLowerCase()) {
			case "l": coord[1] = 1; break;
			case "m": coord[1] = 2; break;
			case "x": coord[1] = 3; break;
			case "j": coord[1] = 4; break;
			case "v": coord[1] = 5; break;
		}	
		
		return coord;
	}
	
	/**
	 * Elimina todas las elecciones del estudiante
	 */
	public void vaciaElecciones(){
		this.eleccion.clear();
		this.nombresEscogidas.clear();
	}

	/**
	 * Dada una ruta, deposita un fichero .csv con el horario
	 * @param file - Ruta destino
	 */
	public void exportHorario(File file) {
		PrintStream  ps = null;
		try{

			ps = new PrintStream(file);
			
			for(String s : preparaLineasExcel(1))
				ps.println(new String(s.getBytes(), "UTF-8"));
			
			ps.println(";;;;;");
			ps.println(";;;;;");
			
			for(String s : preparaLineasExcel(2))
				ps.println(new String(s.getBytes(), "UTF-8"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(ps != null)
				ps.close();
		}
	}
	
	/**
	 * Vuelca todo el horario en un solo String para ser utilizado en la Web
	 * @return String con el horario
	 */
	public String exportHorarioWeb() {
		String horario = "";
		
		for(String s : preparaLineasExcel(1))
			horario += s + "\n";
			
		horario += ";;;;;\n";
		horario += ";;;;;\n";
			
		for(String s : preparaLineasExcel(2))
			horario += s + "\n";
		
		return horario;
	}
	
	/**
	 * Convierte la tabla del horario de un cuatrimestre en una lista de String.
	 * Cada entrada de la lista es una fila de la tabla.
	 * @param cuatrimestre - Cuatrimestre seleccionado
	 * @return Listado con las filas de la tabla convertidas en String
	 */
	private ArrayList<String> preparaLineasExcel(int cuatrimestre){
		ArrayList<String> lineas = new ArrayList<>();
		String q1[][] = getArrayAsignaturas(cuatrimestre);
		if(cuatrimestre == 1)
			lineas.add(";;Primer;Cuatrimestre;;");
		else
			lineas.add(";;Segundo;Cuatrimestre;;");
		lineas.add("Hora;Lunes;Martes;Miércoles;Jueves;Viernes");
		for(int i = 0; i < 12; i++){
			String linea = q1[i][0] + ";" + q1[i][1] + ";" + q1[i][2] + ";" + q1[i][3] + ";" + q1[i][4] + ";" + q1[i][5];
			lineas.add(linea);
		}
		
		return lineas;
	}
}
