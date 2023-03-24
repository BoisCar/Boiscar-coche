package pantallas.climatizador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import principal.Definiciones;

public class ControladorClimatizador implements ActionListener {
	final static int cambioTemperatura = 1;
	VistaClimatizador vista;
	
	public ControladorClimatizador(VistaClimatizador vista) {
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals(Definiciones.comandoInc)) {
				Climatizador.incrementar(cambioTemperatura);
		}
		else if(command.equals(Definiciones.comandoDec)) {
			Climatizador.decrementar(cambioTemperatura);
		}
	}
	
}
