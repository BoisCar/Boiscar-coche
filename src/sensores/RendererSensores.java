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
	final static int tama�oIconos = 40;
	final static int tama�oLetra = 23;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Sensor> list, Sensor value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JPanel panel = new JPanel(new BorderLayout(10,0));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.white, 2), 
				BorderFactory.createEmptyBorder(10,20,10,20)));
		
		JLabel label = new JLabel();
		label.setFont(new Font("arial", Font.PLAIN, tama�oLetra));
		
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
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenRuedaBien, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
					break;
				case "luz":
					label.setText(value.toString() + ": Buen estado");
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenBombillaON, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
					break;
				case "combustible":
					label.setText(value.toString() + ": Nivel adecuado");
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenBateriaLlena, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
					break;
				case "freno":
					label.setText(value.toString() + ": Buen estado");
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenFrenoBien, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
					break;
				case "airbag":
					label.setText(value.toString() + ": Buen estado");
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenAirbagBien, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
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
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenRuedaMal, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
					break;
				case "luz":
					label.setText(value.toString() + ": Mal estado");
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenBombillaOFF, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
					break;
				case "combustible":
					label.setText(value.toString() + ": Combustible bajo");
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenBateriaBaja, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
					break;
				case "freno":
					label.setText(value.toString() + ": Mal estado");
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenFrenoMal, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
					break;
				case "airbag":
					label.setText(value.toString() + ": Mal estado");
					panel.add(new JLabel(Definiciones.crearIconoConTama�o(Definiciones.rutaImagenAirbagMal, tama�oIconos, tama�oIconos)), BorderLayout.WEST);
					break;
			}
		}
		panel.add(label, BorderLayout.CENTER);
		
		return panel;
	}

}
