package pantallas;

import javax.swing.JButton;
import javax.swing.JPanel;

import principal.Definiciones;

@SuppressWarnings("serial")
public class PanelBarraMusica extends JPanel {
	public final static int tama�oBotonesMusica_X = 30;
	public final static int tama�oBotonesMusica_Y = 30;
	
	public PanelBarraMusica() {
		this.setBackground(Definiciones.colorBotones);
		
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tama�oBotonesMusica_X, tama�oBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tama�oBotonesMusica_X, tama�oBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tama�oBotonesMusica_X, tama�oBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tama�oBotonesMusica_X, tama�oBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tama�oBotonesMusica_X, tama�oBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tama�oBotonesMusica_X, tama�oBotonesMusica_Y));
	}
	
	private JButton crearBoton(String ruta, int x, int y) {
		JButton boton = new JButton(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenMusica, x, y));
		boton.setBackground(Definiciones.colorBotones);
		return boton;
	}
	
}
