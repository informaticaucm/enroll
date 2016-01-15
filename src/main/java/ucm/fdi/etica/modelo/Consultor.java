package ucm.fdi.etica.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Consultor {

	private Connection con;
	
	public Consultor() {	}

	public void setConnection(Connection con){
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
	public ArrayList<Integer> getCursosConOpIt(){
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
	public ArrayList<String> getGruposCursoSeleccionado(int curso){
		ArrayList<String> grupos = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = this.con.prepareStatement("SELECT DISTINCT grupo FROM db WHERE curso = ?;");
			ps.setInt(1, curso);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				grupos.add(rs.getString("grupo"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return grupos;
	}
	
	/**
	 * Devuelve las asignaturas de un curso y grupo especificados
	 * @param curso - Curso seleccionado
	 * @param grupo - grupo seleccionado
	 * @return Listado de asignaturas de ese curso y grupo
	 */
	public Oferta getAsignaturasCursoGrupo(int curso, char grupo, char itinerario){
		Oferta oferta = new Oferta();
		String noIt = ""; //Itinerario opuesto al escogido
		if(itinerario == 'I')
			noIt = "C";
		else
			noIt = "I";
		
		try
		{
			PreparedStatement ps = null;
			if(curso < 3)
				ps = this.con.prepareStatement("SELECT DISTINCT * FROM db WHERE curso = ? AND grupo = ? AND itinerario != ? ORDER BY asignatura;");
			else
				ps = this.con.prepareStatement("SELECT DISTINCT * FROM db WHERE (curso = ? AND grupo = ? AND itinerario != ?) OR curso = 0 ORDER BY asignatura;");
			
			ps.setInt(1, curso);
			ps.setString(2, ""+grupo);
			ps.setString(3, ""+noIt);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Asignatura a = build(rs);
				//System.out.println("ID: " + a.getId() + ", Nombre: " + a.getNombre() + ", Grupo: " + a.getGrupo() + ", Dia: " + a.getDia() + ", Hora: " + a.getHora());
				oferta.addNombreOfertada(a.getNombre());
				oferta.addAsignatura(a);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return oferta;
	}
	
	/**
	 * Devuelve las asignaturas de un curso y grupo especificados
	 * @param curso - Curso seleccionado
	 * @param grupo - grupo seleccionado
	 * @return Listado de asignaturas de ese curso y grupo
	 */
	public ArrayList<String> getListadoAsignaturasCursoGrupo(int curso, char grupo){
		ArrayList<String> grupos = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = this.con.prepareStatement("SELECT DISTINCT asignatura FROM db WHERE curso = ? AND grupo = ? AND itinerario NOT IN ('I','C') ORDER BY asignatura;");
			ps.setInt(1, curso);
			ps.setString(2, ""+grupo);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				grupos.add(rs.getString("asignatura"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return grupos;
	}
	
	/**
	 * Devuelve los grupos para el curso y el itinerario seleccionado
	 * @param curso - Curso seleccionado
	 * @param itinerario - Itinerario seleccionado
	 * @return Grupos existentes relacionados
	 */
	public ArrayList<String> getGruposCursoIt(int curso, char itinerario){
		ArrayList<String> grupos = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = this.con.prepareStatement("SELECT DISTINCT grupo FROM db WHERE curso = ? AND itinerario = ? ORDER BY grupo;");
			ps.setInt(1, curso);
			ps.setString(2, "" + itinerario);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				grupos.add(rs.getString("grupo"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return grupos;
	}
	
	/**
	 * Devuelve las asignaturas que correspondan al curso, grupo e itinerario seleccionado
	 * @param curso - Curso seleccionado
	 * @param grupo - Grupo seleccionado
	 * @param itinerario - Itinerario seleccionado
	 * @return Lista con las asignaturas relacionadas
	 */
	public ArrayList<Asignatura> getOptativasCursoGrIt(int curso, char grupo, char itinerario){
		ArrayList<Asignatura> optativas = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = this.con.prepareStatement("SELECT DISTINCT * FROM db WHERE curso = ? AND grupo = ? AND itinerario = ?;");
			ps.setInt(1, curso);
			ps.setString(2, ""+grupo);
			ps.setString(3, ""+itinerario);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				optativas.add(build(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return optativas;
	}
	
	/**
	 * Devuelve todas las optativas generales
	 * @return Lista de optativas generales
	 */
	public ArrayList<Asignatura> getOptativasGenerales(){
		ArrayList<Asignatura> optativas = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = this.con.prepareStatement("SELECT DISTINCT * FROM db WHERE curso = 0;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				optativas.add(build(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return optativas;
	}
	
	
	
	
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
			id = rs.getInt("id");
			curso = rs.getInt("curso");
			grupo = rs.getString("grupo").trim().charAt(0);
			dia = rs.getString("dia").trim().charAt(0);
			hora = rs.getInt("hora");
			cuatrimestre = rs.getInt("cuatrimestre");
			nombre = rs.getString("asignatura");
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
