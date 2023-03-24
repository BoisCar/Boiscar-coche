package pantallas.climatizador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import pantallas.PanelAtras;
import principal.Definiciones;
import principal.ventana.ControladorVentana;

public class VistaClimatizador implements PropertyChangeListener, ActionListener {
	JLabel lbContador;
	Timer timer;
	
	ControladorClimatizador controlador;
	final static int TAMAÑO_TEMPERATURA = 120;
	
	public VistaClimatizador() {
		Climatizador.addPropertyChangeListener(this);
		this.controlador = new ControladorClimatizador(this);
	}
	
	public Container crearPanelClimatizador() {
		JPanel panel = new JPanel(new BorderLayout(0,20));
		panel.setBackground(Definiciones.colorVentana);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(new PanelAtras(new ControladorVentana(), Definiciones.comandoMenu), BorderLayout.NORTH);
		panel.add(crearPanelContador(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.EAST);
		//panel.add(new PanelBarraMusica(), BorderLayout.PAGE_END);
		
		return panel;
	}
	
	private Component crearPanelContador() {
		JPanel panel = new JPanel(new GridLayout());
		panel.setBackground(Definiciones.colorVentana);
		
		lbContador = new JLabel(String.valueOf(Climatizador.getContador()) + "°C"); 
		lbContador.setFont(new Font("Arial", Font.PLAIN, TAMAÑO_TEMPERATURA));
		lbContador.setHorizontalAlignment(JLabel.CENTER);
		lbContador.setBackground(Definiciones.colorBotones);
		lbContador.setForeground(Color.black);
		lbContador.setOpaque(true);
		
		panel.add(lbContador);
		
		return panel;
	}
	
	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(2,0));
		panel.setBackground(Definiciones.colorBotones);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(crearBotonIncrementar());
		panel.add(crearBotonDecrementar());
		
		return panel;
	}
	
	private JButton crearBotonIncrementar() {
		JButton botonInc = Definiciones.crearBotonSinBordes(Definiciones.rutaImagenInc, 100, 100);
		botonInc.addActionListener(controlador);
		botonInc.setActionCommand(Definiciones.comandoInc);
		return botonInc;
	}
	
	private JButton crearBotonDecrementar() {
		JButton botonDec = Definiciones.crearBotonSinBordes(Definiciones.rutaImagenDec, 100, 100);
		botonDec.addActionListener(controlador);
		botonDec.setActionCommand(Definiciones.comandoDec);
		return botonDec;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String property = e.getPropertyName();
		
		switch(property) {
			case "contador":			
				lbContador.setFont(new Font("Arial", Font.PLAIN, TAMAÑO_TEMPERATURA));
				lbContador.setText(String.valueOf(Climatizador.getContador())+ "°C");
			}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		lbContador.setFont(new Font("Arial", Font.PLAIN, TAMAÑO_TEMPERATURA));
	}
	
}
