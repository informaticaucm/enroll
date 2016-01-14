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
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

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
	private JList<String> listaCursos;
	private JList<String> listaGrupos;
	private JList<String> listaAsignaturas;
	
	private Oferta oferta;
	
	
	
	/**
	 * Create the frame.
	 */
	public Ventana() {
		this.curso = "1";
		this.grupo = "A";
		this.itinerario = "I";
		
		setResizable(false);
		setTitle("Enroll - Desktop version");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Primer Cuatrimestre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_1.add(panel_3, gbc_panel_3);
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
		panel_1.add(panel_2, gbc_panel_2);
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
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Selecciona Itinerario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panel.add(panel_6, gbc_panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JRadioButton rdbtnCienciasDeLa = new JRadioButton("Ciencias de la Informaci\u00F3n");
		rdbtnCienciasDeLa.setSelected(true);
		rdbtnCienciasDeLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cambiaItinerario("Informacion");
			}
		});
		btnsItinerario.add(rdbtnCienciasDeLa);
		panel_6.add(rdbtnCienciasDeLa);
		
		JRadioButton rdbtnTecnologaDeLa = new JRadioButton("Tecnolog\u00EDa de la Computaci\u00F3n");
		rdbtnTecnologaDeLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiaItinerario("Computacion");
			}
		});
		btnsItinerario.add(rdbtnTecnologaDeLa);
		panel_6.add(rdbtnTecnologaDeLa);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		panel.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Curso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.insets = new Insets(0, 0, 0, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 0;
		panel_5.add(panel_9, gbc_panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_9.add(scrollPane_2, BorderLayout.CENTER);
		
		listaCursos = new JList<>();
		listaCursos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					cursoElegido(listaCursos.getSelectedValue());
					seleccionaPrimeros(LEVELCURSOS);
				}
			}
		});
		scrollPane_2.setViewportView(listaCursos);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 0, 5);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 1;
		gbc_panel_8.gridy = 0;
		panel_5.add(panel_8, gbc_panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_8.add(scrollPane_3, BorderLayout.CENTER);
		
		listaGrupos = new JList<>();
		listaGrupos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					grupoElegido(listaGrupos.getSelectedValue());
					seleccionaPrimeros(LEVELGRUPOS);
				}
			}
		});
		scrollPane_3.setViewportView(listaGrupos);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Asignatura", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 2;
		gbc_panel_7.gridy = 0;
		panel_5.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panel_7.add(scrollPane_4, BorderLayout.CENTER);
		
		listaAsignaturas = new JList<>();
		listaAsignaturas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					asignaturaElegida(listaAsignaturas.getSelectedValue());
				}
			}
		});
		scrollPane_4.setViewportView(listaAsignaturas);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 2;
		panel.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anyadeAsignatura();
			}
		});
		panel_4.add(btnAadir);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminaAsignatura();
			}
		});
		panel_4.add(btnEliminar);
		
		JButton btnEliminarTodas = new JButton("Eliminar todas");
		btnEliminarTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminaTodas();
			}
		});
		panel_4.add(btnEliminarTodas);
		
		JButton btnExportarHorario = new JButton("Exportar horario");
		btnExportarHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportaHorario();
			}
		});
		panel_4.add(btnExportarHorario);
		
		 try {
	            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	            SwingUtilities.updateComponentTreeUI(this);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (InstantiationException e) {
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        } catch (UnsupportedLookAndFeelException e) {
	            e.printStackTrace();
	        }
	}

	public void setController(Controller c){
		this.c = c;	
	}
	
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
		this.listaGrupos.setSelectedIndex(0);
		this.listaCursos.setSelectedIndex(0);
		this.asignatura = this.listaAsignaturas.getSelectedValue();
		this.grupo = this.listaGrupos.getSelectedValue();
		this.curso = this.listaCursos.getSelectedValue();
		
		//Se muestra la ventana
		this.setVisible(true);
	}
	
	private void cambiaItinerario(String it){
		if(!this.itinerario.equalsIgnoreCase(it))
		{
			switch (it) {
				case "Informacion":{
					System.out.println("Cambio a Informacion");
					this.itinerario = it;
					//Cambio de la lista de grupos
					setListaGrupos(this.c.consultaGruposCursoSeleccionado(1));
					
					//Cambio de la lista de asignaturas
					this.oferta = this.c.consultaAsignaturasCursoGrupo(1, 'A', 'I');
					setListaAsignaturas();
				}
				break;
				case "Computacion":{
					System.out.println("Cambio a Computacion");
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
			this.listaGrupos.setSelectedIndex(0);
			this.grupo = this.listaGrupos.getSelectedValue();
			
		}
		
		if(level <= LEVELITINERARIO){
			this.listaCursos.setSelectedIndex(0);
			this.curso = this.listaCursos.getSelectedValue();
		}
	}
	
	private void setListaCursos(){
		DefaultListModel<String> lm = new DefaultListModel<>();
		for(Integer i : this.c.consultaCursos()){
			lm.addElement(""+i);
		}
		this.listaCursos.setModel(lm);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private void setListaGrupos(ArrayList<String> grupos){
		DefaultListModel<String> lm = new DefaultListModel<>();
		for(String i : grupos){
			lm.addElement(""+i);
		}
		this.listaGrupos.setModel(lm);
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
		
		System.out.println("Se intenta añadir -> It: " + this.itinerario + ", Curso: " + this.curso + "º, Nombre: " + this.asignatura + ", Grupo: " + this.grupo);
		
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
				
				//Muestra de conflictos
				ArrayList<Conflicto> conflictos = this.c.getConflictos();
				if(conflictos.size() > 0){
					muestraConflictos(conflictos);
					JOptionPane.showMessageDialog(this, "¡Cuidado! ¡Tienes materias solapadas!", "Materias solapadas", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		}
	}
	
	//Elimina la asignatura de la lista de eligdas por el estudiante	
	private void eliminaAsignatura(){
		if(!checkSeleccion())
			JOptionPane.showMessageDialog(this, "Primero selecciona un curso, un grupo y la materia correspondiente.", "Error al eliminar asignatura", JOptionPane.ERROR_MESSAGE, null);
		else if(!this.c.quitaAsignaturaEstudiante(this.asignatura))
			JOptionPane.showMessageDialog(this, "No se puede quitar " + this.asignatura + " del horario porque no está.", "Error al eliminar asignatura", JOptionPane.ERROR_MESSAGE, null);
		else
		{
			//Elimina Asignatura
			updateTablas();	//Vuelve a mostrar los datos
			
		}
	}
	
	private void eliminaTodas(){
		this.c.vaciaEscogidasEstudiante();
		updateTablas();
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
				System.out.println(this.primerQ.getComponentAt(x, y).getComponentAt(x, y).getClass());
				TableCellRenderer defaultRenderer = this.primerQ.getDefaultRenderer(Object.class);
				TableCellRenderer nuevoRenderer = new CellBackgroundRenderer(defaultRenderer);
				for(int i = 1; i < 6; i++){
					this.primerQ.getColumnModel().getColumn(i).setCellRenderer(nuevoRenderer);
				}
			}
			else{
				//pintar en el segundo Q
				//((JTextField)this.segundoQ.getComponentAt(coordenadas[0], coordenadas[1])).setBackground(Color.RED);
				System.out.println(this.segundoQ.getComponentAt(x, y).getComponentAt(x, y).getClass());
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
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
        chooser.setDialogTitle("Selecciona la carpeta de destino");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
         
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            this.c.exportarHorarios(chooser.getSelectedFile());
        }
        else {}
    }
	
	
}
