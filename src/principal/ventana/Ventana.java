package principal.ventana;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pantallas.menu.VistaMenu;
import principal.Definiciones;

public class Ventana {
	static JFrame ventana;
	
	public static Runnable iniciar() {
		Runnable run = new Runnable() {
			@Override
			public void run() {
				ventana = new JFrame();
				ventana.setLocation(0,0);
				ventana.setSize(Definiciones.anchoVentana,Definiciones.altoVentana);
				ventana.setContentPane(crearPanelInicio());
				ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  	
			  	try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			  	
			  	ventana.setUndecorated(true);
			  	ventana.setVisible(true);
			  	try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			  	Ventana.setPanel(new VistaMenu().crearPanelMenu());
			}
		};
		
		return run;
	}
	
	private static Container crearPanelInicio() {
		JPanel panel = new JPanel(new GridLayout(1,1));
		JLabel imagen = new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenLogo, Definiciones.anchoVentana, Definiciones.altoVentana));
		panel.add(imagen);
		return panel;
	}
	
	public static void setPanel(Container container) {
		ventana.setContentPane(container);
		ventana.setVisible(true);
	}

	public static JFrame getVentana() {
		return ventana;
	}
	
}
