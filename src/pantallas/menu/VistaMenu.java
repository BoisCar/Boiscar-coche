package pantallas.menu;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import principal.Definiciones;
import principal.ventana.ControladorVentana;

public class VistaMenu {
	Definiciones definiciones;
	ControladorVentana controlador;
	int altoIconos, anchoIconos;
	
	public VistaMenu() {
		definiciones = new Definiciones();
		controlador = new ControladorVentana();
		altoIconos = 190;
		anchoIconos = 190;
	}
	
	public Container crearPanelMenu() {
		JPanel panel = new JPanel(new BorderLayout(0,20));
		panel.setBackground(Definiciones.colorVentana);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panel.add(crearPanelCentral());
		//panel.add(new PanelBarraMusica(), BorderLayout.SOUTH);
		return panel;
	}
	
	private Component crearPanelCentral() {
		JPanel panel = new JPanel(new GridLayout(2,2,1,1));
		panel.setBackground(Definiciones.colorVentana);
		panel.add(crearBotonMusica());
		panel.add(crearBotonClimatizador());
		panel.add(crearBotonGPS());
		panel.add(crearBotonMantenimiento());
		return panel;
	}
	
	private Component crearBotonMusica() {
		JButton botonMusica = new JButton(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenReproductor, anchoIconos, altoIconos));
		botonMusica.setBackground(Definiciones.colorBotones);
		botonMusica.addActionListener(controlador);
		botonMusica.setActionCommand(Definiciones.comandoReproductor);
		botonMusica.setFocusPainted(false);
		return botonMusica;
	}
	
	private Component crearBotonClimatizador() {
		JButton botonClimatizador = new JButton(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenClimatizador, anchoIconos, altoIconos));
		botonClimatizador.setBackground(Definiciones.colorBotones);
		botonClimatizador.addActionListener(controlador);
		botonClimatizador.setActionCommand(Definiciones.comandoClimatizador);
		botonClimatizador.setFocusPainted(false);
		return botonClimatizador;
	}
	
	private Component crearBotonGPS() {
		JButton botonGPS = new JButton(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenGPS, anchoIconos, altoIconos));
		botonGPS.setBackground(Definiciones.colorBotones);
		botonGPS.addActionListener(controlador);
		botonGPS.setActionCommand(Definiciones.comandoGPS);
		botonGPS.setFocusPainted(false);
		return botonGPS;
	}
	
	private Component crearBotonMantenimiento() {
		JButton botonMantenimiento = new JButton(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenMantenimiento, anchoIconos, altoIconos));
		botonMantenimiento.setBackground(Definiciones.colorBotones);
		botonMantenimiento.addActionListener(controlador);
		botonMantenimiento.setActionCommand(Definiciones.comandoMantenimiento);
		botonMantenimiento.setFocusPainted(false);
		return botonMantenimiento;
	}
		
}
