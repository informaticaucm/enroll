package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controller;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Ventana extends JFrame {

	//Constantes
	private static final long serialVersionUID = 4564152682927067006L;
	private static final String[] columnas = {"Hora", "lunes", "martes", "miercoles", "jueves", "viernes"};
	private static final int LEVELITINERARIO = 1;
	private static final int LEVELCURSOS = 2;
	private static final int LEVELGRUPOS = 3;
	
	//Variables de ventana
	private JPanel contentPane;
	private Controller c;
	private JTable primerQ;
	private JTable segundoQ;
	private JList<String> tuSeleccion;
	private JList<String> listaAsignaturas;
	private JComboBox<String> cboxGrupo;
	private JComboBox<String> cboxCursos;
	private JRadioButton rdbtnInformacion;
	private JRadioButton rdbtnComputacion;
	private final ButtonGroup btnsItinerario = new ButtonGroup();
	
	//Variables de funcionamiento
	private String itinerario;
	private String curso;
	private String grupo;
	private String asignatura;
	private boolean avisado;
	private ArrayList<String> listaSeleccionadas;
	
	
	
	public Ventana() {
		this.curso = "";
		this.grupo = "";
		this.itinerario = "I";
		this.avisado = false;
		this.listaSeleccionadas = new ArrayList<>();
		
		setResizable(false);
		setTitle("Enroll - Desktop version");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 599, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel leftPanel = new JPanel();
		GridBagConstraints gbc_leftPanel = new GridBagConstraints();
		gbc_leftPanel.insets = new Insets(0, 0, 0, 5);
		gbc_leftPanel.fill = GridBagConstraints.BOTH;
		gbc_leftPanel.gridx = 0;
		gbc_leftPanel.gridy = 0;
		contentPane.add(leftPanel, gbc_leftPanel);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[]{0, 0};
		gbl_leftPanel.rowHeights = new int[]{0, 0, 0};
		gbl_leftPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_leftPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_leftPanel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Primer Cuatrimestre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		leftPanel.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		primerQ = new JTable();
		primerQ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		primerQ.setCellSelectionEnabled(true);
		primerQ.setModel(new DefaultTableModel(
			new Object[][] {
				{"9", null, null, null, null, null},
				{"10", null, null, null, null, null},
				{"11", null, null, null, null, null},
				{"12", null, null, null, null, null},
				{"13", null, null, null, null, null},
				{"14", null, null, null, null, null},
				{"15", null, null, null, null, null},
				{"16", null, null, null, null, null},
				{"17", null, null, null, null, null},
				{"18", null, null, null, null, null},
				{"19", null, null, null, null, null},
				{"20", null, null, null, null, null},
			},
			new String[] {
				"Hora", "lunes", "martes", "miercoles", "jueves", "viernes"
			}
		){
			private static final long serialVersionUID = -4643051052426062178L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		primerQ.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(primerQ);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Segundo Cuatrimestre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		leftPanel.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		segundoQ = new JTable();
		segundoQ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		segundoQ.setCellSelectionEnabled(true);
		segundoQ.getTableHeader().setReorderingAllowed(false);
		segundoQ.setModel(new DefaultTableModel(
			new Object[][] {
				{"9", null, null, null, null, null},
				{"10", null, null, null, null, null},
				{"11", null, null, null, null, null},
				{"12", null, null, null, null, null},
				{"13", null, null, null, null, null},
				{"14", null, null, null, null, null},
				{"15", null, null, null, null, null},
				{"16", null, null, null, null, null},
				{"17", null, null, null, null, null},
				{"18", null, null, null, null, null},
				{"19", null, null, null, null, null},
				{"20", null, null, null, null, null},
			},
			new String[] {
				"Hora", "lunes", "martes", "miercoles", "jueves", "viernes"
			}
		){
			private static final long serialVersionUID = -4643051052426062178L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		scrollPane_1.setViewportView(segundoQ);
		
		JPanel rigthPanel = new JPanel();
		GridBagConstraints gbc_rigthPanel = new GridBagConstraints();
		gbc_rigthPanel.fill = GridBagConstraints.BOTH;
		gbc_rigthPanel.gridx = 1;
		gbc_rigthPanel.gridy = 0;
		contentPane.add(rigthPanel, gbc_rigthPanel);
		GridBagLayout gbl_rigthPanel = new GridBagLayout();
		gbl_rigthPanel.columnWidths = new int[]{549, 0};
		gbl_rigthPanel.rowHeights = new int[]{0, 0, 0};
		gbl_rigthPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_rigthPanel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		rigthPanel.setLayout(gbl_rigthPanel);
		
		JPanel seleccionPanel = new JPanel();
		GridBagConstraints gbc_seleccionPanel = new GridBagConstraints();
		gbc_seleccionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_seleccionPanel.fill = GridBagConstraints.BOTH;
		gbc_seleccionPanel.gridx = 0;
		gbc_seleccionPanel.gridy = 0;
		rigthPanel.add(seleccionPanel, gbc_seleccionPanel);
		GridBagLayout gbl_seleccionPanel = new GridBagLayout();
		gbl_seleccionPanel.columnWidths = new int[]{0, 0, 0};
		gbl_seleccionPanel.rowHeights = new int[]{0, 0};
		gbl_seleccionPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_seleccionPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		seleccionPanel.setLayout(gbl_seleccionPanel);
		
		JPanel eleccionPanel = new JPanel();
		eleccionPanel.setBorder(new TitledBorder(null, "Selecciona Asignatura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_eleccionPanel = new GridBagConstraints();
		gbc_eleccionPanel.insets = new Insets(0, 0, 0, 5);
		gbc_eleccionPanel.fill = GridBagConstraints.BOTH;
		gbc_eleccionPanel.gridx = 0;
		gbc_eleccionPanel.gridy = 0;
		seleccionPanel.add(eleccionPanel, gbc_eleccionPanel);
		GridBagLayout gbl_eleccionPanel = new GridBagLayout();
		gbl_eleccionPanel.columnWidths = new int[]{213, 0};
		gbl_eleccionPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_eleccionPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_eleccionPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		eleccionPanel.setLayout(gbl_eleccionPanel);
		
		JPanel itinerariosPanel = new JPanel();
		GridBagConstraints gbc_itinerariosPanel = new GridBagConstraints();
		gbc_itinerariosPanel.fill = GridBagConstraints.BOTH;
		gbc_itinerariosPanel.insets = new Insets(0, 0, 5, 0);
		gbc_itinerariosPanel.gridx = 0;
		gbc_itinerariosPanel.gridy = 0;
		eleccionPanel.add(itinerariosPanel, gbc_itinerariosPanel);
		itinerariosPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Itinerario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		itinerariosPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		rdbtnInformacion = new JRadioButton("Informaci\u00F3n");
		rdbtnInformacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cambiaItinerario("Informacion");
			}
		});
		btnsItinerario.add(rdbtnInformacion);
		itinerariosPanel.add(rdbtnInformacion);
		
		rdbtnComputacion = new JRadioButton("Computaci\u00F3n");
		rdbtnComputacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiaItinerario("Computacion");
			}
		});
		btnsItinerario.add(rdbtnComputacion);
		itinerariosPanel.add(rdbtnComputacion);
		
		JPanel cursoGrupoPanel = new JPanel();
		GridBagConstraints gbc_cursoGrupoPanel = new GridBagConstraints();
		gbc_cursoGrupoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_cursoGrupoPanel.fill = GridBagConstraints.BOTH;
		gbc_cursoGrupoPanel.gridx = 0;
		gbc_cursoGrupoPanel.gridy = 1;
		eleccionPanel.add(cursoGrupoPanel, gbc_cursoGrupoPanel);
		GridBagLayout gbl_cursoGrupoPanel = new GridBagLayout();
		gbl_cursoGrupoPanel.columnWidths = new int[]{0, 0, 0};
		gbl_cursoGrupoPanel.rowHeights = new int[]{0, 0};
		gbl_cursoGrupoPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_cursoGrupoPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		cursoGrupoPanel.setLayout(gbl_cursoGrupoPanel);
		
		JPanel cursoPanel = new JPanel();
		cursoPanel.setBorder(new TitledBorder(null, "Curso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_cursoPanel = new GridBagConstraints();
		gbc_cursoPanel.insets = new Insets(0, 0, 0, 5);
		gbc_cursoPanel.fill = GridBagConstraints.BOTH;
		gbc_cursoPanel.gridx = 0;
		gbc_cursoPanel.gridy = 0;
		cursoGrupoPanel.add(cursoPanel, gbc_cursoPanel);
		cursoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		cboxCursos = new JComboBox<String>();
		cboxCursos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					cursoElegido();
				}
			}
		});
		cursoPanel.add(cboxCursos);
		
		JPanel grupoPanel = new JPanel();
		grupoPanel.setBorder(new TitledBorder(null, "Grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_grupoPanel = new GridBagConstraints();
		gbc_grupoPanel.fill = GridBagConstraints.BOTH;
		gbc_grupoPanel.gridx = 1;
		gbc_grupoPanel.gridy = 0;
		cursoGrupoPanel.add(grupoPanel, gbc_grupoPanel);
		grupoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		cboxGrupo = new JComboBox<String>();
		cboxGrupo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					grupoElegido();
				}
			}
		});
		grupoPanel.add(cboxGrupo);
		
		JPanel asignaturaPanel = new JPanel();
		GridBagConstraints gbc_asignaturaPanel = new GridBagConstraints();
		gbc_asignaturaPanel.insets = new Insets(0, 0, 5, 0);
		gbc_asignaturaPanel.fill = GridBagConstraints.BOTH;
		gbc_asignaturaPanel.gridx = 0;
		gbc_asignaturaPanel.gridy = 2;
		eleccionPanel.add(asignaturaPanel, gbc_asignaturaPanel);
		asignaturaPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Asignatura", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		asignaturaPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		asignaturaPanel.add(scrollPane_4, BorderLayout.CENTER);
		
		listaAsignaturas = new JList<>();
		listaAsignaturas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					asignaturaElegida();
				}
			}
		});
		scrollPane_4.setViewportView(listaAsignaturas);
		
		JPanel anyadirPanel = new JPanel();
		GridBagConstraints gbc_anyadirPanel = new GridBagConstraints();
		gbc_anyadirPanel.fill = GridBagConstraints.BOTH;
		gbc_anyadirPanel.gridx = 0;
		gbc_anyadirPanel.gridy = 3;
		eleccionPanel.add(anyadirPanel, gbc_anyadirPanel);
		anyadirPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAadir = new JButton("A\u00F1adir");
		anyadirPanel.add(btnAadir);
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anyadeAsignatura();
				pintarConflictos();
			}
		});
		
		JPanel seleccionadosPanel = new JPanel();
		GridBagConstraints gbc_seleccionadosPanel = new GridBagConstraints();
		gbc_seleccionadosPanel.fill = GridBagConstraints.BOTH;
		gbc_seleccionadosPanel.gridx = 1;
		gbc_seleccionadosPanel.gridy = 0;
		seleccionPanel.add(seleccionadosPanel, gbc_seleccionadosPanel);
		GridBagLayout gbl_seleccionadosPanel = new GridBagLayout();
		gbl_seleccionadosPanel.columnWidths = new int[]{276, 0};
		gbl_seleccionadosPanel.rowHeights = new int[]{0, 0};
		gbl_seleccionadosPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_seleccionadosPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		seleccionadosPanel.setLayout(gbl_seleccionadosPanel);
		
		JPanel seleccionadasPanel = new JPanel();
		GridBagConstraints gbc_seleccionadasPanel = new GridBagConstraints();
		gbc_seleccionadasPanel.fill = GridBagConstraints.BOTH;
		gbc_seleccionadasPanel.gridx = 0;
		gbc_seleccionadasPanel.gridy = 0;
		seleccionadosPanel.add(seleccionadasPanel, gbc_seleccionadasPanel);
		seleccionadasPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tu Selecci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_seleccionadasPanel = new GridBagLayout();
		gbl_seleccionadasPanel.columnWidths = new int[]{0, 0};
		gbl_seleccionadasPanel.rowHeights = new int[]{0, 0, 0};
		gbl_seleccionadasPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_seleccionadasPanel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		seleccionadasPanel.setLayout(gbl_seleccionadasPanel);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 0;
		seleccionadasPanel.add(scrollPane_2, gbc_scrollPane_2);
		
		tuSeleccion = new JList<String>();
		scrollPane_2.setViewportView(tuSeleccion);
		
		JPanel eliminarPanel = new JPanel();
		GridBagConstraints gbc_eliminarPanel = new GridBagConstraints();
		gbc_eliminarPanel.fill = GridBagConstraints.BOTH;
		gbc_eliminarPanel.gridx = 0;
		gbc_eliminarPanel.gridy = 1;
		seleccionadasPanel.add(eliminarPanel, gbc_eliminarPanel);
		
		JButton btnEliminar = new JButton("Eliminar");
		eliminarPanel.add(btnEliminar);
		
		JButton btnEliminarTodas = new JButton("Eliminar todas");
		eliminarPanel.add(btnEliminarTodas);
		btnEliminarTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminaTodas();
				pintarConflictos();
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminaAsignatura();
				pintarConflictos();
			}
		});
		
		JPanel exportarPanel = new JPanel();
		GridBagConstraints gbc_exportarPanel = new GridBagConstraints();
		gbc_exportarPanel.fill = GridBagConstraints.BOTH;
		gbc_exportarPanel.gridx = 0;
		gbc_exportarPanel.gridy = 1;
		rigthPanel.add(exportarPanel, gbc_exportarPanel);
		exportarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnExportarHorario = new JButton("Exportar horario");
		exportarPanel.add(btnExportarHorario);
		btnExportarHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportaHorario();
			}
		});
		
		 try {
	            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	            SwingUtilities.updateComponentTreeUI(this);
	        } catch (Exception e) {}
	}

	/**
	 * Establece el controlador de la ventana
	 * @param c - Controlador
	 */
	public void setController(Controller c){
		this.c = c;	
	}
	
	/**
	 * Prepara la información inicial y muestra la ventana
	 */
	public void run(){
		this.rdbtnInformacion.setSelected(true);
		this.itinerario = "I";
		
		cambiaItinerario(this.itinerario);
		
		this.setVisible(true);
	}
	
	private void cambiaItinerario(String itinerario){
		this.itinerario = itinerario;
		
		actualizaListados(LEVELITINERARIO);
	}
	
	private void cursoElegido(){
		this.curso = (String) cboxCursos.getSelectedItem();
		actualizaListados(LEVELCURSOS);
	}
	
	private void grupoElegido(){
		this.grupo = (String) this.cboxGrupo.getSelectedItem();
		actualizaListados(LEVELGRUPOS);
	}
	
	private void asignaturaElegida(){
		this.asignatura = this.listaAsignaturas.getSelectedValue();
	}
	
	private void actualizaListados(int level){
		if(level <= LEVELITINERARIO){
			actualizaCursos();
			
			if(this.cboxCursos.getItemCount() > 0){
				this.cboxCursos.setSelectedIndex(0);
				this.curso = (String) this.cboxCursos.getSelectedItem();
			}
		}
		
		if(level <= LEVELCURSOS){
			actualizaGrupos();
			
			if(this.cboxGrupo.getItemCount() > 0){
				this.cboxGrupo.setSelectedIndex(0);
				this.grupo = (String) this.cboxGrupo.getSelectedItem();
			}
		}
		
		if(level <= LEVELGRUPOS){
			actualizaAsignaturas();
			
			if(this.listaAsignaturas.getModel().getSize() > 0){
				this.listaAsignaturas.setSelectedIndex(0);
				this.asignatura = this.listaAsignaturas.getSelectedValue();
			}
		}
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private void actualizaCursos(){
		ArrayList<Integer> cursos = this.c.consultaCursos();
		
		DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<String>();
		
		for(Integer i : cursos){
			cbm.addElement(""+i);
		}
		this.cboxCursos.setModel(cbm);
	}
	
	private void actualizaGrupos(){
		ArrayList<String> grupos = this.c.consultaGruposCursoSeleccionado(Integer.parseInt(this.curso));
		
		DefaultComboBoxModel<String> cmb = new DefaultComboBoxModel<String>();
		
		for(String g : grupos){
			cmb.addElement(g);
		}
		this.cboxGrupo.setModel(cmb);
		
	}
	
	private void actualizaAsignaturas(){
		System.out.println("");
		this.c.consultaAsignaturasCursoGrupo(Integer.parseInt(this.curso), this.grupo.charAt(0), this.itinerario.charAt(0));
		
		ArrayList<String> listado = this.c.getListadoOfertadas();
		
		DefaultListModel<String> dlm = new DefaultListModel<>();
		
		for(String a : listado){
			dlm.addElement(a);
		}
		this.listaAsignaturas.setModel(dlm);
	}
	
	private void anyadeAsignatura(){
		if(comprobacion())
		{
			if(this.c.estaElegidaEstudiante(this.asignatura))
			{
				JOptionPane.showMessageDialog(this, "¿De verdad quieres matricularte dos veces de la misma asignatura?", "¿En serio?", JOptionPane.QUESTION_MESSAGE, null);
			}
			else
			{
				this.c.addAsignaturaFromVentana(this.asignatura);
				this.listaSeleccionadas.add( this.asignatura + " - " + this.curso + "º " + this.grupo);
				updatetuSeleccion();
				
				if(this.c.hayConflictos() && !this.avisado){
					JOptionPane.showMessageDialog(this, "¡Cuidado! ¡Tienes materias solapadas!", "Materias solapadas", JOptionPane.ERROR_MESSAGE, null);
					this.avisado = true;
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Primero selecciona un curso, un grupo y la materia correspondiente.", "Error al añadir asignatura", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	private void eliminaAsignatura(){
		int index = this.tuSeleccion.getSelectedIndex(); 
		if(index > -1){
			String value = this.tuSeleccion.getSelectedValue();
			String split[] = value.split("-");
			String nombre = split[0].trim();
			
			this.listaSeleccionadas.remove(index);
			this.c.quitaAsignaturaEstudiante(nombre);
			if(!this.c.hayConflictos() && this.avisado)
				this.avisado = false;
			updatetuSeleccion();
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Primero selecciona una asignatura", "Error al eliminar asignatura", JOptionPane.ERROR_MESSAGE, null);	
		}
	}
	
	private void updatetuSeleccion(){
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		
		for(String a : this.listaSeleccionadas){
			dlm.addElement(a);
		}
		
		this.tuSeleccion.setModel(dlm);
		updateTablas();
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private void eliminaTodas(){
		this.listaSeleccionadas.clear();
		this.avisado = false;
		this.c.vaciaEscogidasEstudiante();
		updatetuSeleccion();
		actualizaListados(LEVELITINERARIO);
	}
	
	private void updateTablas(){
		DefaultTableModel dtm1 = new DefaultTableModel(this.c.getArrayAsignaturasEstudiante(1), columnas){
			private static final long serialVersionUID = -4643051052426062178L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		this.primerQ.setModel(dtm1);
		
		DefaultTableModel dtm2 = new DefaultTableModel(this.c.getArrayAsignaturasEstudiante(2), columnas){
			private static final long serialVersionUID = -4643051052426062179L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		this.segundoQ.setModel(dtm2);
	}
	
	private void pintarConflictos(){
		this.primerQ.setBackground(Color.WHITE);
		this.segundoQ.setBackground(Color.WHITE);
		
//		TableCellRenderer defaultRenderer = this.primerQ.getDefaultRenderer(Object.class);
//		TableCellRenderer nuevoRenderer = new CellBackgroundRenderer(defaultRenderer);
//		for(int i = 1; i < 6; i++){
//			this.primerQ.getColumnModel().getColumn(i).setCellRenderer(nuevoRenderer);
//		}
		
		TableCellRenderer defaultRenderer2 = this.segundoQ.getDefaultRenderer(Object.class);
		TableCellRenderer nuevoRenderer2 = new CellBackgroundRenderer(defaultRenderer2);
		
		TableColumnModel tcm = null;
		TableColumn tc = null;
		
		for(int j = 1; j < 6; j++){
			tcm = this.segundoQ.getColumnModel(); 
			tc = tcm.getColumn(j);
			tc.setCellRenderer(nuevoRenderer2);
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private boolean comprobacion(){
		boolean ok = true;
		
		ok = ok && (this.itinerario != null && this.itinerario.length() > 0);
		
		ok = ok && (this.curso != null && this.curso.length() > 0);
		
		ok = ok && (this.grupo != null && this.grupo.length() > 0);
		
		ok = ok && (this.asignatura != null && this.asignatura.length() > 0);
		
		return ok;
	}
	
	private void exportaHorario(){
		File destino = new File("miHorario.csv");
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setDialogTitle("Especifica el archivo a guardar");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichero csv", "csv", "csv");
        chooser.setFileFilter(filter);
        chooser.setSelectedFile(destino);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            this.c.exportarHorarios(chooser.getSelectedFile());
        }
        else {}
    }
	
	
}
