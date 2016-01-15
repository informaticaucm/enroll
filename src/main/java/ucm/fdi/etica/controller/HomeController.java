package ucm.fdi.etica.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JFileChooser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ucm.fdi.etica.modelo.Asignatura;
import ucm.fdi.etica.modelo.Consultor;
import ucm.fdi.etica.modelo.Estudiante;
import ucm.fdi.etica.modelo.Oferta;
import ucm.fdi.etica.modelo.db.Conector;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private Estudiante estudiante;
	@Autowired
	private Oferta oferta;
	@Autowired
	private Conector conector;
	@Autowired
	private Consultor consultor;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		conectarBBDD();
		
		ArrayList<Integer> cursos = consultaCursos();
		
		model.addAttribute("cursos", cursos);
		
		return "home";
	}
	
	@RequestMapping(value = "/obtenerGrupoCurso", method = RequestMethod.POST)	
	public @ResponseBody ArrayList<String> obtenerGrupoCurso(@RequestParam(value="curso") int curso) {
		return this.consultor.getGruposCursoSeleccionado(curso);
	}	
	
	@RequestMapping(value = "/obtenerAsignaturasGrupoCurso", method = RequestMethod.POST)	
	public @ResponseBody ArrayList<String> obtenerAsignaturasGrupoCurso(@RequestParam(value="curso") int curso, 
															@RequestParam(value="grupo") char grupo,@RequestParam(value="itinerario") char itinerario) {
		
		this.oferta = this.consultor.getAsignaturasCursoGrupo(curso, grupo, itinerario);
		return this.oferta.getListadoOfertadas();
	}	
	
	//boton añadir
	@RequestMapping(value = "/obtenerHorariosAsignatura", method = RequestMethod.POST)	
	public @ResponseBody ArrayList<Asignatura> getAsignaturasOferta(@RequestParam(value="curso") int curso, 
															@RequestParam(value="grupo") char grupo, @RequestParam(value="itinerario") char itinerario,
															@RequestParam(value="asignatura") String asignatura) {	
		
		boolean estaElegida = false;
		
		ArrayList<Asignatura> horarioAsignatura = this.oferta.getAsignatura(curso, grupo, itinerario, asignatura);
		
		if(!horarioAsignatura.isEmpty()){
			estaElegida = this.estudiante.estaElegida(horarioAsignatura.get(0));
		}
		
		//Añadir asignatura
		if(!estaElegida){
			for(Asignatura a : horarioAsignatura)
				this.estudiante.addAsignatura(a);
		}
		else{
			horarioAsignatura = null;
		}
		
		return horarioAsignatura;
	}	
		
	//boton borrar
	@RequestMapping(value = "/eliminarAsignatura", method = RequestMethod.POST)
	public @ResponseBody boolean eliminarAsignatura(@RequestParam(value="asignatura") String asignatura) {			
		return this.estudiante.quitaAsignatura(asignatura);
	}	
	
	//recuperar asignaturas del estudiante una vez que se ha eliminado 1
	@RequestMapping(value = "/recuperarAsignaturas", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Asignatura> recuperarAsignaturas() {	
		return this.estudiante.getEleccion();
	}	
	
	//boton borrar todo
	@RequestMapping(value = "/limpiarTodoHorario", method = RequestMethod.PUT)	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void limpiarTodoHorario() {	
		this.estudiante.vaciaElecciones();
	}	
	
	
	//exporta el calendario
	@RequestMapping(value = "/exportarCalendario", method = RequestMethod.POST)
	public @ResponseBody String exportarCalendario() {	
		return this.estudiante.exportHorario();
	}	
		
//	private void exportaHorario(){
//        JFileChooser chooser = new JFileChooser();
//        chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
//        chooser.setDialogTitle("Selecciona la carpeta de destino");
//        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        chooser.setAcceptAllFileFilterUsed(false);
//         
//        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//        	this.estudiante.exportHorario(file);
//        }
//        else {}
//    }
	
	
	
	private void conectarBBDD(){	
		
		this.conector.openConnection();
		this.consultor.setConnection(this.conector.getConexion());
	}
	
	private ArrayList<Integer> consultaCursos(){
		return this.consultor.getCursos();
	}
	

}
