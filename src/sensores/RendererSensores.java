package sensores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import principal.Definiciones;

public class RendererSensores implements ListCellRenderer<Sensor> {
	final static int tamañoIconos = 40;
	final static int tamañoLetra = 23;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Sensor> list, Sensor value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JPanel panel = new JPanel(new BorderLayout(10,0));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.white, 2), 
				BorderFactory.createEmptyBorder(10,20,10,20)));
		
		JLabel label = new JLabel();
		label.setFont(new Font("arial", Font.PLAIN, tamañoLetra));
		
		if (isSelected) {
			//value.setCorrecto(!value.isCorrecto());
			panel.setBackground(Definiciones.colorVentana);
			label.setBackground(Definiciones.colorVentana);
			label.setForeground(Definiciones.colorBotones);
		}
		label.setOpaque(true);
		
		if(value.isCorrecto()) {
			switch(value.getTipo()) {
				case "rueda":
					label.setText(value.toString() + ": Buen estado");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenRuedaBien, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
				case "luz":
					label.setText(value.toString() + ": Buen estado");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenBombillaON, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
				case "combustible":
					label.setText(value.toString() + ": Nivel adecuado");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenBateriaLlena, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
				case "freno":
					label.setText(value.toString() + ": Buen estado");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenFrenoBien, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
				case "airbag":
					label.setText(value.toString() + ": Buen estado");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenAirbagBien, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
			}
		}
		else {
			panel.setBackground(Definiciones.colorAveria);
			label.setBackground(Definiciones.colorAveria);
			label.setForeground(Color.white);
			label.setText(value.toString());
			switch(value.getTipo()) {
				case "rueda":
					label.setText(value.toString() + ": Mal estado");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenRuedaMal, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
				case "luz":
					label.setText(value.toString() + ": Mal estado");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenBombillaOFF, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
				case "combustible":
					label.setText(value.toString() + ": Combustible bajo");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenBateriaBaja, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
				case "freno":
					label.setText(value.toString() + ": Mal estado");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenFrenoMal, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
				case "airbag":
					label.setText(value.toString() + ": Mal estado");
					panel.add(new JLabel(Definiciones.crearIconoConTamaño(Definiciones.rutaImagenAirbagMal, tamañoIconos, tamañoIconos)), BorderLayout.WEST);
					break;
			}
		}
		panel.add(label, BorderLayout.CENTER);
		
		return panel;
	}

}
