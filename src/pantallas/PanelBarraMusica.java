package pantallas;

import javax.swing.JButton;
import javax.swing.JPanel;

import principal.Definiciones;

@SuppressWarnings("serial")
public class PanelBarraMusica extends JPanel {
	public final static int tamañoBotonesMusica_X = 30;
	public final static int tamañoBotonesMusica_Y = 30;
	
	public PanelBarraMusica() {
		this.setBackground(Definiciones.colorBotones);
		
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamañoBotonesMusica_X, tamañoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamañoBotonesMusica_X, tamañoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamañoBotonesMusica_X, tamañoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamañoBotonesMusica_X, tamañoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamañoBotonesMusica_X, tamañoBotonesMusica_Y));
		this.add(crearBoton(Definiciones.rutaImagenFlechaAtras, tamañoBotonesMusica_X, tamañoBotonesMusica_Y));
	}
	
	private JButton crearBoton(String ruta, int x, int y) {
		JButton boton = new JButton(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenMusica, x, y));
		boton.setBackground(Definiciones.colorBotones);
		return boton;
	}
	
}
