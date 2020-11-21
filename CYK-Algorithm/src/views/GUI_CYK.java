package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.AlgorithmController;
import models.Algorithm;
import models.Variable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Jhusseth
 *
 */

public class GUI_CYK extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField cantProductions;
	private JButton answerButton;
	private JLabel answerLabel;
	
	
	/**
	 * 
	 */
	private JMenuBar menu;
	private JMenu options;
	private JMenuItem instrucctions;
	private JMenuItem contact;
	
	
	/**
	 * Button constants
	 */
	private final static String VER_RESPUESTA="Ver Respuesta";
	private final static String CANTIDAD_PRODUCCIONES="Aceptar";
	
	
	/**
	 * panel in which productions will be written
	 */
	private Panel_Productions productions;

	/*
	 * link to controller
	 */
	private AlgorithmController controller;
	
	/**
	 * panel about for information of developer
	 */
	private PanelAbout about;
	
	/**
	 * panel of the banner
	 */
	private PanelBanner banner;
	
	/**
	 * Main Interface
	 */
	public GUI_CYK () {
		getContentPane().setBackground(Color.WHITE);
		setSize(480, 220);
		setTitle("Algoritmo CYK");
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setLocationRelativeTo(null);
		
		about = new PanelAbout();
		banner = new PanelBanner();
		
		
		menu = new JMenuBar();
		options = new JMenu("Ayuda");
		instrucctions = new JMenuItem("Instrucciones de Uso");
		contact = new JMenuItem("Contacto");
		options.add(instrucctions);
		options.add(contact);
		menu.add(options);
		
		setJMenuBar(menu);
		
		menu.add(options);
		
		controller = new AlgorithmController();
		
		contact.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showConfirmDialog(null,
	                    about,
	                    "Acerca: ",
	                    JOptionPane.OK_CANCEL_OPTION,
	                    JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		
		instrucctions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File path = new File("data/Instrucciones.pdf");
					Desktop.getDesktop().open(path);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
			
		
		productions = new Panel_Productions();
		
		
		JPanel panelAux= new JPanel();
		panelAux.setBackground(Color.WHITE);
		panelAux.setLayout(new GridLayout(1, 4));
		JLabel label = new JLabel("Cantidad de producciones: ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(label);
		
		panelAux.setBorder(new EmptyBorder(4, 4, 2, 3));
		
		panelAux.add(new JLabel(" "));
		
		JPanel aux3 = new JPanel();
		aux3.setLayout(new BorderLayout());
		
		cantProductions = new JTextField();
		aux3.add(cantProductions, BorderLayout.CENTER);
		
		JButton cantidadProduccionesBoton = new JButton(CANTIDAD_PRODUCCIONES);
		cantidadProduccionesBoton.setForeground(Color.WHITE);
		cantidadProduccionesBoton.setBackground(Color.BLACK);
		cantidadProduccionesBoton.setActionCommand(CANTIDAD_PRODUCCIONES);
		cantidadProduccionesBoton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				remove(productions);
				//productions = new Panel_Productions();
				productions.modificarPanelProducciones(Integer.parseInt(cantProductions.getText()));
				getContentPane().add(productions, BorderLayout.CENTER);
				pack();
				}catch(Exception exc) {
					JOptionPane.showMessageDialog(null, "Campo vacio ó error en el dato ingresado");
				}
				
				
			}
		});
		
		
		aux3.add(cantidadProduccionesBoton, BorderLayout.EAST);
		panelAux.add(aux3);
		
		getContentPane().add(banner, BorderLayout.NORTH);
		getContentPane().add(panelAux, BorderLayout.CENTER);
		
		
		
		
		Panel panelAuxInferior = new Panel();
		panelAuxInferior.setBackground(Color.WHITE);
		panelAuxInferior.setLayout(new GridLayout(2, 1));
		JLabel label_1 = new JLabel("Cadena a probar: ");
		JTextField cadenaAProbar = new JTextField();
		
		JPanel aux4 = new JPanel();
		
		aux4.setLayout(new GridLayout(1, 2));
		
		aux4.add(label_1);
		aux4.add(cadenaAProbar);
		
		aux4.setBorder(new EmptyBorder(4, 4, 2, 4));
		aux4.setBackground(Color.WHITE);
		
		panelAuxInferior.add(aux4);
		
		
		
		answerButton = new JButton(VER_RESPUESTA);
		answerButton.setBackground(Color.BLACK);
		answerButton.setForeground(Color.WHITE);
		answerButton.setActionCommand(VER_RESPUESTA);
		answerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				Variable [] variablesEntradas = new Variable[Integer.parseInt(cantProductions.getText())];
				for (int i = 0; i < productions.getCadenaProductions().size(); i++) {
					Variable temporal = new Variable(productions.getCadenaProductions().get(i).get(0).getText(), productions.getCadenaProductions().get(i).get(1).getText().split(","));
					variablesEntradas[i]= temporal;
				}
				
				String [][] respuestaAMostrar = controller.getTheGereationMatrix(variablesEntradas, cadenaAProbar.getText());
				answerLabel.setText(respuestaAMostrar[0][respuestaAMostrar.length-1]);
				}catch(Exception exc) {
					JOptionPane.showMessageDialog(null, "Campos vacios ó algun dato esta erroneo");
				}
				
			}
		});
		
		
		answerLabel = new JLabel("Aun no se ha dado una respuesta");
		answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel aux5 = new JPanel();
		
		aux5.setLayout(new BorderLayout());
		
		aux5.add(answerButton,BorderLayout.WEST);
		aux5.add(answerLabel,BorderLayout.CENTER);
		
		panelAuxInferior.add(aux5);
		
		
		aux5.setBorder(new EmptyBorder(4, 4, 3, 4));
		aux5.setBackground(Color.WHITE);
		
		
		
		getContentPane().add(panelAuxInferior, BorderLayout.SOUTH);
		
	}



	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GUI_CYK principal = new GUI_CYK();
		principal.setVisible(true);

	}

}
