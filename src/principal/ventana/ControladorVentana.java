package principal.ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pantallas.GestorPantallas;

public class ControladorVentana implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GestorPantallas.setPantalla(e.getActionCommand());
	}
	
}
