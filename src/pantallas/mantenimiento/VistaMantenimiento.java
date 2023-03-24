package pantallas.mantenimiento;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pantallas.PanelAtras;
import principal.Definiciones;
import principal.Principal;
import principal.ventana.ControladorVentana;
import sensores.ListaSensores;
import sensores.RendererSensores;
import sensores.Sensor;

public class VistaMantenimiento {
	Definiciones definiciones;
	ControladorVentana controlador;
	JList<Sensor> listaMantenimiento;
	
	public VistaMantenimiento() {
		definiciones = new Definiciones();
		controlador = new ControladorVentana();
	}
	
	public Container crearPanelMantenimiento() {
		JPanel panel = new JPanel(new BorderLayout(0,20));
		panel.setBackground(Definiciones.colorVentana);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(new PanelAtras(controlador, Definiciones.comandoMenu), BorderLayout.NORTH);
		panel.add(crearPanelSensores());
		//panel.add(new PanelBarraMusica(),BorderLayout.SOUTH);
		
		return panel;
	}
	
	private Container crearPanelSensores() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setBackground(Definiciones.colorBotones);
		
		ListaSensores sensores = Principal.getSensores();
		
		listaMantenimiento = new JList<>(sensores.getListaDatosSensores());
		listaMantenimiento.addListSelectionListener(sensores);
		listaMantenimiento.setCellRenderer(new RendererSensores());
		
		panel.setViewportView(listaMantenimiento);
		
		return panel;
	}

}
