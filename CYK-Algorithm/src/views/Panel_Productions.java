package views;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Jhusseth
 *
 */
public class Panel_Productions extends JPanel {
	
	
	private ArrayList<ArrayList<JTextField>> cadenaProductions;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Panel_Productions () {
		setBorder(new EmptyBorder(35, 4, 4, 4));
		setBackground(Color.WHITE);
		
	}
	
	public ArrayList<ArrayList<JTextField>> getCadenaProductions() {
		return cadenaProductions;
	}

	public void setCadenaProductions(ArrayList<ArrayList<JTextField>> cadenaProductions) {
		this.cadenaProductions = cadenaProductions;
	}

	/**
	 * 
	 * @param cantidadProducciones
	 * @param cadenaProductions
	 */
	public void modificarPanelProducciones (int cantidadProductions) {
		this.setLayout(new GridLayout(cantidadProductions+1, 2) );
		JLabel pd = new JLabel("Produccion ");
		pd.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(pd);
		
		JLabel pv = new JLabel("Variables que produce");
		pv.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(pv);
		
		cadenaProductions = new ArrayList<ArrayList<JTextField>>();
		for (int i = 0; i < cantidadProductions; i++) {
			JTextField a = new JTextField();
			a.setHorizontalAlignment(SwingConstants.CENTER);
			JTextField b = new JTextField();
			b.setHorizontalAlignment(SwingConstants.CENTER);
			ArrayList<JTextField> temp = new ArrayList<JTextField>();
			temp.add(a);
			temp.add(b);
			cadenaProductions.add(temp);
			this.add(cadenaProductions.get(i).get(0));
			this.add(cadenaProductions.get(i).get(1));
			
		}
		this.setSize(480, 110 + cantidadProductions*50);
	}
	

}
