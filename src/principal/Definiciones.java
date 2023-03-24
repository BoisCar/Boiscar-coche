package principal;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Definiciones {
	public final static String FICHERO_ACCIDENTES = "../ficherosEmergencias/accidentes.txt";
	
	public final static String comandoMenu = "Menu";
	public final static String comandoClimatizador = "Climatizador";
	public final static String comandoGPS = "GPS";
	public final static String comandoMantenimiento = "Mantenimiento";
	public final static String comandoReproductor = "Reproductor";
	
	public final static String comandoUSB = "USB";
	public final static String comandoRADIO = "RADIO";
	public final static String comandoRadioSiguiente = "next";
	public final static String comandoRadioAnterior = "previous";
	
	public final static String comandoAtras = "atras";
	
	public final static String comandoInc = "inc";
	public final static String comandoDec = "dec";
	
	public final static String rutaImagenLogo = "img/logo.png";
	
	public final static String rutaImagenClimatizador = "img/pantallas/climatizador.png";
	public final static String rutaImagenGPS = "img/pantallas/gps.png";
	public final static String rutaImagenMantenimiento = "img/pantallas/mantenimiento.png";
	public final static String rutaImagenReproductor = "img/pantallas/reproductor.png";
	
	public final static String rutaImagenFlechaAtras = "img/flecha_atras.png";
	public final static String rutaImagenInc = "img/inc.png";
	public final static String rutaImagenDec = "img/dec.png";
	public final static String rutaImagenMusica = "img/musica.png";
	
	public final static String rutaImagenRuedaBien = "img/sensores/ruedaBien.png";
	public final static String rutaImagenRuedaMal = "img/sensores/ruedaMal.png";
	public final static String rutaImagenBombillaON = "img/sensores/bombillaON.png";
	public final static String rutaImagenBombillaOFF = "img/sensores/bombillaOFF.png";
	public final static String rutaImagenBateriaBaja = "img/sensores/bateriaBaja.png";
	public final static String rutaImagenBateriaLlena = "img/sensores/bateriaLlena.png";
	public final static String rutaImagenAirbagBien = "img/sensores/airbagBien.png";
	public final static String rutaImagenAirbagMal = "img/sensores/airbagMal.png";
	public final static String rutaImagenFrenoBien = "img/sensores/frenoBien.png";
	public final static String rutaImagenFrenoMal = "img/sensores/frenoMal.png";
	
	public static Color colorVentana = new java.awt.Color(67, 67, 67);
	public static Color colorBotones = new java.awt.Color(239, 239, 239);
	public static Color colorAveria = new java.awt.Color(171, 42, 62);
	
	public static int anchoPantalla = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
	public static int altoPantalla = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
	public static int anchoVentana = anchoPantalla;//800;
	public static int altoVentana = altoPantalla;//500;
	
	public static ImageIcon crearIconoConTamaño(String ruta, int x, int y) {
		Image img = new ImageIcon(ruta).getImage();  
		Image newimg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);  
		return new ImageIcon(newimg);
	}
	
	public static JButton crearBotonSinBordes(String ruta, int x, int y) {
		JButton boton = new JButton(Definiciones.crearIconoConTamaño(ruta, 100, 100));
		boton.setBorderPainted(false);
	    boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
		return boton;
	}
	
}
