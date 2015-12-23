package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Consultor {

	private Connection con;
	
	public Consultor(Connection con) {
		this.con = con;
	}
	
	/**
	 * Devuelve los cursos disponibles
	 * @return Cursos disponibles
	 */
	public ArrayList<Integer> getCursos(){
		ArrayList<Integer> cursos = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = this.con.prepareStatement("SELECT DISTINCT curso FROM db WHERE curso != 0 ORDER BY curso;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				cursos.add(rs.getInt("curso"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cursos;
	}
	
	/**
	 * Devuelve los cursos que tienen optativas de itinerario
	 * @return Cursos con optativas de itinerario
	 */
	public ArrayList<Integer> cursosConOpIt(){
		ArrayList<Integer> cursos = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = this.con.prepareStatement("SELECT DISTINCT curso FROM db WHERE itinerario IN ('I','C') ORDER BY curso;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				cursos.add(rs.getInt("curso"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cursos;
	}
	
	/**
	 * Devuelve los grupos que hay en el curso seleccionado
	 * @param curso - Curso seleccionado
	 * @return Grupos disponibles
	 */
	public ArrayList<Integer> gruposCursoSeleccionado(int curso){
		ArrayList<Integer> cursos = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = this.con.prepareStatement("SELECT DISTINCT grupo FROM db WHERE curso = ?;");
			ps.setInt(1, curso);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				cursos.add(rs.getInt("grupo"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cursos;
	}
	
	/**
	 * Devuelve las asignaturas de un curso y grupo especificados
	 * @param curso - Curso seleccionado
	 * @param grupo - grupo seleccionado
	 * @return Listado de asignaturas de ese curso y grupo
	 */
	public ArrayList<Asignatura> getAsignaturasCursoGrupo(int curso, char grupo){
		ArrayList<Asignatura> cursos = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = this.con.prepareStatement("SELECT DISTINCT * FROM db WHERE curso = ? AND grupo = ? AND itinerario NOT IN ('I','C');");
			ps.setInt(1, curso);
			ps.setString(2, ""+grupo);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				cursos.add(build(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cursos;
	}
	
	
	
	//obtener grupos existentes para el curso e itinerario seleccionado
	
	
	//obtener optativas generales
	
	
	
	
	
	/**
	 * Toma el ResultSet y devuelve la asignatura que está apuntando
	 * @param rs - ResultSet
	 * @return Una Asignatura
	 */
	private Asignatura build(ResultSet rs){
		Asignatura a = null;
		int id = 0;
		int curso = 0;
		char grupo = ' ';
		char dia = ' ';
		int hora = 0;
		int cuatrimestre = 0;
		String nombre = "";
		char itinerario = ' ';
		boolean minimo = false;
		try{
			curso = rs.getInt("curso");
			grupo = rs.getString("grupo").trim().charAt(0);
			dia = rs.getString("dia").trim().charAt(0);
			hora = rs.getInt("hora");
			cuatrimestre = rs.getInt("cuatrimestre");
			nombre = rs.getString("nombre");
			minimo = true;
			itinerario = rs.getString("itinerario").trim().charAt(0);
		}catch(Exception e){}
		if(minimo)
			if(itinerario != ' ')
				a = new Asignatura(id, curso, grupo, dia, hora, cuatrimestre, nombre, itinerario);
			else
				a = new Asignatura(id, curso, grupo, dia, hora, cuatrimestre, nombre);
		
		return a;
	}
}
