package pantallas;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import principal.Definiciones;

@SuppressWarnings("serial")
public class PanelAtras extends JPanel {
	public final static int tama�oPanelAtras_X = 75;
	public final static int tama�oPanelAtras_Y = 35;

	public PanelAtras(ActionListener controlador, String comando) {
		this.setLayout((new FlowLayout(FlowLayout.LEFT, 0, 0)));
		this.setBackground(Definiciones.colorVentana);
		
		JButton botonMenu = new JButton(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenFlechaAtras, tama�oPanelAtras_X, tama�oPanelAtras_Y));
		botonMenu.setBackground(Definiciones.colorBotones);
		botonMenu.setFocusPainted(false);
		botonMenu.addActionListener(controlador);
		botonMenu.setActionCommand(comando);
		
		this.add(botonMenu);
	}
	
}
