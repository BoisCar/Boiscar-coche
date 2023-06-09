package pantallas;

import javax.swing.JButton;
import javax.swing.JPanel;

import principal.Definiciones;

@SuppressWarnings("serial")
public class PanelBarraMusica extends JPanel {
	public final static int tamaņoBotonesMusica_X = 30;
	public final static int tamaņoBotonesMusica_Y = 30;
	
	public PanelBarraMusica() {
		this.setBackground(Definiciones.colorBotones);
		
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamaņoBotonesMusica_X, tamaņoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamaņoBotonesMusica_X, tamaņoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamaņoBotonesMusica_X, tamaņoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamaņoBotonesMusica_X, tamaņoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamaņoBotonesMusica_X, tamaņoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamaņoBotonesMusica_X, tamaņoBotonesMusica_Y));
	}
	
	private JButton crearBoton(String ruta, int x, int y) {
		JButton boton = new JButton(Definiciones.crearIconoConTamaņo(Definiciones.rutaImagenMusica, x, y));
		boton.setBackground(Definiciones.colorBotones);
		return boton;
	}
	
}
