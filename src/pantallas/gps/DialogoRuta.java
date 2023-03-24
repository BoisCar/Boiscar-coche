package pantallas.gps;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DialogoRuta extends JDialog {
	Route ruta;
	JTable tRuta;
	 String[][] arrayRoute;
	 TableModel tableModel;
	public DialogoRuta(JFrame owner, String title, boolean modal, String[][] arrayRoute) {
	
		super(owner, title, modal);
		rellenarTablaRuta(arrayRoute);
		this.arrayRoute=arrayRoute;
		tRuta = new JTable();
		this.setSize(500, 500);
		this.setLocation(500, 200);
		this.setContentPane(crearPanelDialogo());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		
	}

	private Container crearPanelDialogo() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		tRuta.setModel(tableModel);
		panel.add(tRuta);
		return panel;
	}

	private void rellenarTablaRuta(String[][] ruta) {
		String[] columnas = new String[5];
		columnas[0] = "Duracion tramo";
		columnas[1] = "Distancia tramo";
		columnas[2] = "Indicaciones";
		columnas[3] = "Latitud";
		columnas[4] = "Longitud";
		tableModel = new DefaultTableModel(ruta, columnas);
		

	}

}
