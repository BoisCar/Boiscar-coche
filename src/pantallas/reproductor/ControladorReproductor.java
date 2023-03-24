package pantallas.reproductor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;

import pantallas.menu.VistaMenu;
import principal.Definiciones;
import principal.ventana.Ventana;

public class ControladorReproductor implements ActionListener {
	final static String FICHERO_RADIO = "files/ficheroradio.txt";
	final static String FICHERO_USB = "files/ficherousb.txt";
	final static int CARGAR_MENU = 0;
	final static int CARGAR_USB = 1;
	final static int CARGAR_RADIO = 2;
	final static String NULL = "null";
	VistaRadio vista;
	Boolean menuPrincipal;
	DefaultListModel<Musica> modeloUSB;
	DefaultListModel<Radio> modeloRadio;
	
	public ControladorReproductor(DefaultListModel<Musica> modeloUSB, DefaultListModel<Radio> modeloRadio) {
		this.menuPrincipal = false;
		this.modeloRadio = modeloRadio;
		this.modeloUSB = modeloUSB;
	}
	
	public void setVista(VistaRadio vista) {
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {
			case Definiciones.comandoAtras: {
				vista.restaurar();
				if(menuPrincipal) {
					vista.recargar(CARGAR_MENU, NULL);
					this.menuPrincipal = false;
				}
				else {
					Ventana.setPanel(new VistaMenu().crearPanelMenu());
					System.out.println(Definiciones.comandoMenu);
				}
				break;
			}					
			case Definiciones.comandoUSB: {
				vista.recargar(CARGAR_USB, NULL);
				cargarUSB();
				this.menuPrincipal = true;
				break;
			}
			case Definiciones.comandoRADIO: {
				vista.recargar(CARGAR_RADIO, NULL);
				cargarRadio();
				this.menuPrincipal = true;
				break;
			}
			case Definiciones.comandoRadioSiguiente: {
				vista.changeLabel(Definiciones.comandoRadioSiguiente);
				break;
			}
			case Definiciones.comandoRadioAnterior: {
				vista.changeLabel(Definiciones.comandoRadioAnterior);
				break;
			}
		}
	} 

	private void cargarRadio() {
		modeloRadio.removeAllElements();
		
		Radio emisora;
		String linea;
		
		try(BufferedReader in = new BufferedReader(new FileReader(FICHERO_RADIO))) {
			while(((linea = in.readLine()) != null)) {
				emisora = new Radio(linea);
				if(emisora != null) modeloRadio.addElement(emisora);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void cargarUSB() {
		modeloUSB.removeAllElements();

		Musica cancion;
		String linea;
		
		try(BufferedReader in = new BufferedReader(new FileReader(FICHERO_USB))) {
			while(((linea = in.readLine()) != null)) {
				cancion = new Musica(linea);
				if(cancion != null) modeloUSB.addElement(cancion);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
