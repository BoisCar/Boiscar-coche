package pantallas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.UnsupportedEncodingException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pantallas.climatizador.VistaClimatizador;
import pantallas.gps.VistaGPS;
import pantallas.mantenimiento.VistaMantenimiento;
import pantallas.menu.VistaMenu;
import pantallas.reproductor.VistaRadio;
import principal.Definiciones;
import principal.ventana.Ventana;
import voz.habla.Voz;

public class GestorPantallas {
	static String pantallaActual;
	
	public static void iniciar() {
		pantallaActual = Definiciones.comandoMenu;
	}
	
	public static void setPantalla(String pantalla) {
		JFrame ventana = Ventana.getVentana();
		switch(pantalla) {
			case Definiciones.comandoReproductor:
				ventana.setContentPane(new VistaRadio().crearPanelRadio());
				break;
			case Definiciones.comandoClimatizador:
				ventana.setContentPane(new VistaClimatizador().crearPanelClimatizador());
				break;
			case Definiciones.comandoGPS:
			try {
				ventana.setContentPane(new VistaGPS().crearPanelGPS());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
				break;
			case Definiciones.comandoMantenimiento:
				ventana.setContentPane(new VistaMantenimiento().crearPanelMantenimiento());
				break;
			case Definiciones.comandoMenu:
				ventana.setContentPane(new VistaMenu().crearPanelMenu());
				break;
		}
		if(pantallaActual != pantalla) {
			Voz.hablar("Abriendo" + pantalla);
			pantallaActual = pantalla;
		}
		ventana.setVisible(true);
	}

	public static void setPantallaActual(String pantallaActual) {
		GestorPantallas.pantallaActual = pantallaActual;
	}

	public static void fireWarning() {
		JFrame ventana = Ventana.getVentana();
		
		JLabel mensaje = new JLabel("Varias averias al mismo tiempo");
		mensaje.setFont(new Font("Arial", Font.BOLD, 50));
		mensaje.setForeground(Color.red);
		
		JOptionPane.showConfirmDialog(ventana, mensaje, "Aviso",  
					JOptionPane.CLOSED_OPTION);
	}
	
}