package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controller;
import modelo.Asignatura;
import modelo.Conflicto;
import modelo.Oferta;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
import java.util.Arrays;

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

	private static final long serialVersionUID = 4564152682927067006L;
	
	private static final String[] columnas = {"Hora", "lunes", "martes", "miercoles", "jueves", "viernes"};

	private static final int LEVELITINERARIO = 1;
	private static final int LEVELCURSOS = 2;
	private static final int LEVELGRUPOS = 3;
	
	private JPanel contentPane;
	private Controller c;
	private JTable primerQ;
	private JTable segundoQ;
	private final ButtonGroup btnsItinerario = new ButtonGroup();
	
	private String itinerario;
	private String curso;
	private String grupo;
	private String asignatura;
	private JList<String> listaAsignaturas;
	
	private Oferta oferta;
	private JComboBox<String> cboxGrupo;
	private JComboBox<String> cboxCursos;
	
	private boolean avisado;
	private ArrayList<String> listaSeleccionadas;
	private JList<String> tuSeleccion;
	
	public Ventana() {
		this.curso = "1";
		this.grupo = "A";
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
		
		JRadioButton rdbtnCienciasDeLa = new JRadioButton("Informaci\u00F3n");
		rdbtnCienciasDeLa.setSelected(true);
		rdbtnCienciasDeLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cambiaItinerario("Informacion");
			}
		});
		btnsItinerario.add(rdbtnCienciasDeLa);
		itinerariosPanel.add(rdbtnCienciasDeLa);
		
		JRadioButton rdbtnTecnologaDeLa = new JRadioButton("Computaci\u00F3n");
		rdbtnTecnologaDeLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiaItinerario("Computacion");
			}
		});
		btnsItinerario.add(rdbtnTecnologaDeLa);
		itinerariosPanel.add(rdbtnTecnologaDeLa);
		
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
					cursoElegido((String) cboxCursos.getSelectedItem());
					seleccionaPrimeros(LEVELCURSOS);
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
					grupoElegido((String) cboxGrupo.getSelectedItem());
					seleccionaPrimeros(LEVELGRUPOS);
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
					asignaturaElegida(listaAsignaturas.getSelectedValue());
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
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminaAsignatura();
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
		//Se establece un itinerario por defecto
		this.itinerario = "Informacion";
		
		//Se actualizan las listas con los datos de 1º
		setListaCursos();
		setListaGrupos(this.c.consultaGruposCursoSeleccionado(1));
		this.oferta = this.c.consultaAsignaturasCursoGrupo(1, 'A', 'I');
		setListaAsignaturas();
		
		//Se realiza una selección por defecto, tanto visualmente como internamente
		this.listaAsignaturas.setSelectedIndex(0);
		this.cboxGrupo.setSelectedIndex(0);
		this.cboxCursos.setSelectedIndex(0);
		this.asignatura = this.listaAsignaturas.getSelectedValue();
		this.grupo = (String) this.cboxGrupo.getSelectedItem();
		this.curso = (String) this.cboxCursos.getSelectedItem();
		
		//Se muestra la ventana
		this.setVisible(true);
	}
	
	private void cambiaItinerario(String it){
		if(!this.itinerario.equalsIgnoreCase(it))
		{
			switch (it) {
				case "Informacion":{
					//System.out.println("Cambio a Informacion");
					this.itinerario = it;
					//Cambio de la lista de grupos
					setListaGrupos(this.c.consultaGruposCursoSeleccionado(1));
					
					//Cambio de la lista de asignaturas
					this.oferta = this.c.consultaAsignaturasCursoGrupo(1, 'A', 'I');
					setListaAsignaturas();
				}
				break;
				case "Computacion":{
					//System.out.println("Cambio a Computacion");
					this.itinerario = it;
					//Cambio de la lista de grupos
					setListaGrupos(this.c.consultaGruposCursoSeleccionado(1));
					
					//Cambio de la lista de asignaturas
					this.oferta = this.c.consultaAsignaturasCursoGrupo(1, 'A', 'C');
					setListaAsignaturas();
				}
				break;
				default: break;
			}
			seleccionaPrimeros(LEVELITINERARIO);
		}
	}

	private void seleccionaPrimeros(int level){
		if(level <= LEVELGRUPOS){
			this.listaAsignaturas.setSelectedIndex(0);
			this.asignatura = this.listaAsignaturas.getSelectedValue();
		}
		
		if(level <= LEVELCURSOS){
			this.cboxGrupo.setSelectedIndex(0);
			this.grupo = (String) this.cboxGrupo.getSelectedItem();
			
		}
		
		if(level <= LEVELITINERARIO){
			this.cboxCursos.setSelectedIndex(0);
			this.curso = (String) this.cboxCursos.getSelectedItem();
		}
	}
	
	private void setListaCursos(){
		DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<String>();
		
		for(Integer i : this.c.consultaCursos()){
			cbm.addElement(""+i);
		}
		this.cboxCursos.setModel(cbm);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private void setListaGrupos(ArrayList<String> grupos){
		DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<String>();
		for(String i : grupos){
			cbm.addElement(""+i);
		}
		this.cboxGrupo.setModel(cbm);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private void setListaAsignaturas(){
		DefaultListModel<String> lm = new DefaultListModel<>();
		
		int auxCurso = Integer.parseInt(this.curso);
		char auxGrupo = this.grupo.charAt(0);
		char auxItinerario = this.itinerario.charAt(0);
		
		this.oferta = this.c.consultaAsignaturasCursoGrupo(auxCurso, auxGrupo, auxItinerario);
		
		for(String a : this.oferta.getListadoOfertadas()){
			lm.addElement(a);
		}
		this.listaAsignaturas.setModel(lm);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	//Si un curso es seleccionado, se modifican las listas de grupos y de asignaturas
	private void cursoElegido(String curso){
		this.curso = curso;
		
		int aux = Integer.parseInt(curso);
		
		//Cambio de la lista de grupos
		setListaGrupos(this.c.consultaGruposCursoSeleccionado(aux));
		
		//Cambio de la lista de asignaturas
		this.oferta = this.c.consultaAsignaturasCursoGrupo(aux, 'A', this.itinerario.charAt(0));
		setListaAsignaturas();
	}
	
	//Si un grupo es seleccionado, se modifica la lista de asignaturas
	private void grupoElegido(String grupo){
		if(grupo == null)
			return;
		
		this.grupo = grupo;
		
		int auxCurso = Integer.parseInt(this.curso);
		char auxGrupo = grupo.charAt(0);
		
		//Cambio de la lista de asignaturas
		this.oferta = this.c.consultaAsignaturasCursoGrupo(auxCurso, auxGrupo, this.itinerario.charAt(0));
		setListaAsignaturas();
	}
	
	//Si una materia es seleccionada, no afecta a ninguna lista
	private void asignaturaElegida(String asignatura){
		this.asignatura = asignatura;
	}
	
	private void anyadeAsignatura() {
		// Añade las asignaturas a las elegidas por el estudiante
		
		//System.out.println("Se intenta añadir -> It: " + this.itinerario + ", Curso: " + this.curso + "º, Nombre: " + this.asignatura + ", Grupo: " + this.grupo);
		
		if(!checkSeleccion())
			JOptionPane.showMessageDialog(this, "Primero selecciona un curso, un grupo y la materia correspondiente.", "Error al añadir asignatura", JOptionPane.ERROR_MESSAGE, null);
		else
		{
			ArrayList<Asignatura> elegida = this.c.getAsignaturasOferta(Integer.parseInt(this.curso), this.grupo.charAt(0), this.itinerario.charAt(0), this.asignatura);
			
			//Como todas las entradas del ArrayList tienen el mismo nombre, la 1ª entrada vale para comprobar si ya está
			if(this.c.estaElegidaEstudiante(elegida.get(0)))
			{
				JOptionPane.showMessageDialog(this, "¿De verdad te quieres matricular dos veces en la misma asignatura?", "No se puede añadir asignatura", JOptionPane.QUESTION_MESSAGE, null);
			}
			else
			{
				//Añadir asignatura
				for(Asignatura a : elegida)
					this.c.addAsignaturaEstudiante(a);
				
				updateTablas();
				
				listarSeleccionada(this.curso, this.grupo, this.asignatura);
				
				//Muestra de conflictos
				ArrayList<Conflicto> conflictos = this.c.getConflictos();
				if(conflictos.size() > 0){
					muestraConflictos(conflictos);
					if(!this.avisado){
						JOptionPane.showMessageDialog(this, "¡Cuidado! ¡Tienes materias solapadas!",
								"Materias solapadas", JOptionPane.WARNING_MESSAGE, null);
						this.avisado = true;
					}
				}
			}
		}
	}
	
	//Añade una asignatura al listado de "seleccionadas"
	private void listarSeleccionada(String curso, String grupo, String asignatura){
		String s = asignatura + " ( " + curso + "º " + grupo + " )";
		
		this.listaSeleccionadas.add(s);
		
		updatetuSeleccion();
	}
	
	
	//Toma el listado de asignaturas y lo muestra
	private void updatetuSeleccion(){
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		
		for(String a : this.listaSeleccionadas){
			dlm.addElement(a);
		}
		
		this.tuSeleccion.setModel(dlm);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	//Elimina la asignatura de la lista de eligdas por el estudiante	
	private void eliminaAsignatura(){
		if(this.tuSeleccion.getSelectedIndex() < 0){
			JOptionPane.showMessageDialog(this, "Primero selecciona una asignatura del listado de la derecha.", "Error al eliminar asignatura", JOptionPane.ERROR_MESSAGE, null);
		}
		else{
			String split[] = this.listaSeleccionadas.get(this.tuSeleccion.getSelectedIndex()).split(" ");
			
			if(!this.c.quitaAsignaturaEstudiante(split[0]))
				JOptionPane.showMessageDialog(this, "No se puede quitar " + this.asignatura + " del horario porque no está.", "Error al eliminar asignatura", JOptionPane.ERROR_MESSAGE, null);
			else
			{
				//Elimina Asignatura
				updateTablas();	//Vuelve a mostrar los datos
				this.listaSeleccionadas.remove(this.tuSeleccion.getSelectedIndex());
				updatetuSeleccion();
				
				if(this.c.getConflictos().size() == 0)
					this.avisado = false;
			}
		}
	}
	
	private void eliminaTodas(){
		this.c.vaciaEscogidasEstudiante();
		this.listaSeleccionadas.clear();
		updateTablas();
		updatetuSeleccion();
		this.avisado = false;
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
	
	/**
	 * Comprueba que se ha seleccionado un curso, un grupo y una asignatura
	 * @return true si se ha seleccionado un elemento de cada lista
	 */
	private boolean checkSeleccion(){
		if(this.asignatura == null || this.grupo == null || this.curso == null)
			return false;
		else
			return true;
	}
	
	private void muestraConflictos(ArrayList<Conflicto> conflictos){
		boolean tabla[][] = new boolean[12][6];
		int coordenadas[] = new int[2];
		Asignatura aux;
		
		
		for (boolean[] row: tabla)
			Arrays.fill(row, false);
		
		this.primerQ.setBackground(Color.WHITE);
		this.segundoQ.setBackground(Color.WHITE);
		
		for(Conflicto c : conflictos){
			aux = c.getAsignaturas().get(0);
			coordenadas = getCoordenadasTabla(aux);
			
			int x = coordenadas[0]; //filas
			int y = coordenadas[1]; //columnas
			
			tabla[x][y] = true;
			
			if(aux.getCuatrimestre() == 1){
				//pintar en el primer Q
				//System.out.println(this.primerQ.getComponentAt(coordenadas[0], coordenadas[1])).getClass().toString())));;
				//System.out.println(this.primerQ.getComponentAt(x, y).getComponentAt(x, y).getClass());
				TableCellRenderer defaultRenderer = this.primerQ.getDefaultRenderer(Object.class);
				TableCellRenderer nuevoRenderer = new CellBackgroundRenderer(defaultRenderer);
				for(int i = 1; i < 6; i++){
					this.primerQ.getColumnModel().getColumn(i).setCellRenderer(nuevoRenderer);
				}
			}
			else{
				//pintar en el segundo Q
				//((JTextField)this.segundoQ.getComponentAt(coordenadas[0], coordenadas[1])).setBackground(Color.RED);
				//System.out.println(this.segundoQ.getComponentAt(x, y).getComponentAt(x, y).getClass());
				TableCellRenderer defaultRenderer = this.segundoQ.getDefaultRenderer(Object.class);
				TableCellRenderer nuevoRenderer = new CellBackgroundRenderer(defaultRenderer);
				for(int i = 1; i < 6; i++){
					this.segundoQ.getColumnModel().getColumn(i).setCellRenderer(nuevoRenderer);
				}
			}
		}
		
	}
	
	private int[] getCoordenadasTabla(Asignatura a){
		int coord[] = new int[2];
		
		String dia = ""+ a.getDia();
		int hora = a.getHora();
		
		switch (dia.toLowerCase()) {
			case "l": coord[1] = 1; break;
			case "m": coord[1] = 2; break;
			case "x": coord[1] = 3; break;
			case "j": coord[1] = 4; break;
			case "v": coord[1] = 5; break;
			default : break;
		}		
		
		switch (hora) {
			case 9:  coord[0] = 1; break;
			case 10: coord[0] = 2; break;
			case 11: coord[0] = 3; break;
			case 12: coord[0] = 4; break;
			case 13: coord[0] = 5; break;
			case 14: coord[0] = 6; break;
			case 15: coord[0] = 7; break;
			case 16: coord[0] = 8; break;
			case 17: coord[0] = 9; break;
			case 18: coord[0] = 10; break;
			case 19: coord[0] = 11; break;
			case 20: coord[0] = 12; break;
			default : break;
		}
		
		return coord;
	}
	
	private void exportaHorario(){
		File destino = new File("miHorario.csv");
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
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
