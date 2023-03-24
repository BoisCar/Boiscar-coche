package pantallas.gps;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import pantallas.PanelAtras;
import principal.Definiciones;
import principal.ventana.ControladorVentana;
import principal.ventana.Ventana;

import java.awt.Desktop;
import java.awt.Dimension;

public class VistaGPS {
	JTextField tOrigen;
	JTextField tDestino;
	JTable tRuta;
	Route ruta;
	JLabel imagen;
	ModeloDirecciones modelo;
	JList<Direccion> list;
	AbstractAction accAdd, accRemove, accEdit, accOrigen, accDestino, accMostrar;
	JLabel jLatitud, jLongitud;
	JButton bOrigen, bDestino, bIr;
	GestorMaps controlador;
	Geocoding ObjGeocoding = new Geocoding();
	
	JLabel mapa;
	public VistaGPS() throws UnsupportedEncodingException {
		tRuta = new JTable();
		ruta = new Route();
		crearAcciones();
		modelo = new ModeloDirecciones(this);
		mapa=new JLabel();
		controlador = new GestorMaps(this, modelo);

	}
	
	public Container crearPanelGPS() {
		JPanel panel = new JPanel(new BorderLayout(0,20));
		panel.setBackground(Definiciones.colorVentana);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(new PanelAtras(new ControladorVentana(), Definiciones.comandoMenu), BorderLayout.NORTH);
		panel.add(crearPanelCentral(), BorderLayout.CENTER);
		
		return panel;
	}

	private Container crearPanelCentral() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, crearPanelLista(), crearPanelIzq());
		panel.setDividerLocation(400);
		return panel;
	}

	private Component crearPanelIzq() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearPanelMapa(), BorderLayout.CENTER);
		panel.add(crearPanelIr(), BorderLayout.SOUTH);

		return panel;
	}

	private Component crearPanelIr() {
		JPanel panel = new JPanel();
		bIr = new JButton(accMostrar);
		panel.add(bIr);
		return panel;
	}

	private Component crearPanelMapa() {
		JPanel panel = new JPanel();
		mapa = new JLabel();
		mapa.setIcon(controlador.ensenarMapa(list.getSelectedValue()));
		panel.add(mapa);
		return panel;

	}

	public void storeInfoRequest(URL urlRequest, String info, String status, Exception exception) {
		MapsJava.storageRequest(urlRequest.toString(), "Map request", "OK", null);
	}

	

	private Component crearPanelLista() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.add(crearPanelBotones(), BorderLayout.NORTH);
		panel.add(crearPanelListaDirecciones(), BorderLayout.CENTER);
		panel.add(crearPanelBuscar(), BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelBuscar() {
		JPanel panel = new JPanel();
		bOrigen = new JButton(accOrigen);
		bDestino = new JButton(accDestino);
		panel.add(bOrigen);
		panel.add(bDestino);
		return panel;
	}

	private Component crearPanelListaDirecciones() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		list = new JList<>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(modelo);
		list.addListSelectionListener(controlador);
		list.setSelectedIndex(0);
		panel.setViewportView(list);
		return panel;

	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.add(crearPanelTexto(), BorderLayout.NORTH);
		panel.add(panelBotonesLista(), BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		return panel;
	}

	private Component panelBotonesLista() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton anadir = new JButton(accAdd);
		JButton editar = new JButton(accEdit);
		JButton quitar = new JButton(accRemove);
		panel.add(anadir);
		panel.add(quitar);
		panel.add(editar);

		return panel;
	}

	private Component crearPanelTexto() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		tOrigen = new JTextField();
		tDestino = new JTextField();
		tOrigen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Origen"));
		tDestino.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Destino"));
		panel.add(tOrigen);
		panel.add(tDestino);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"SELECCIONE DIRECCIONES"));
		return panel;
	}

	private void crearAcciones() {
		accAdd = new MiAccion("Añadir", new ImageIcon("iconos/edit_add.png"), this);
		accRemove = new MiAccion("Eliminar", new ImageIcon("iconos/edit_remove.png"), this);
		accEdit = new MiAccion("Editar", new ImageIcon("iconos/iconos/star.png"), this);
		accOrigen = new MiAccion("Origen", null, this);
		accDestino = new MiAccion("Destino", null, this);
		accMostrar = new MiAccion("Mostrar", null, this);
		bloquearAcciones();
	}

	private void bloquearAcciones() {
		accRemove.setEnabled(false);
		accEdit.setEnabled(false);
		accOrigen.setEnabled(false);
		accDestino.setEnabled(false);

	}

	private class MiAccion extends AbstractAction {
		VistaGPS vista;
		String texto;
		DialogoDireccion dialogo;

		public MiAccion(String texto, Icon imagen, VistaGPS vista) {
			super(texto, imagen);
			this.texto = texto;
			this.vista = vista;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (texto.equals("Añadir")) {
				dialogo = new DialogoDireccion(Ventana.getVentana(), "Inserte Direccion", true, null);
				// comprobarDireccion(dialogo.getDireccion())
				if (controlador.verificarDireccion(dialogo.getDireccion())) {
					modelo.addElement(dialogo.getDireccion());

					JOptionPane.showMessageDialog(null, "Direccion comprobada con exito y guardada");
					modelo.actualizarFichero();
				} else {
					JOptionPane.showMessageDialog(null, "direccion no encontrada");
				}
				// anadirAlDicionario(dialogo.getDireccion().getNombre());

			} else if (texto.equals("Eliminar")) {
				if (modelo.getSize() != 0) {
					modelo.remove(vista.list.getSelectedIndex());
					modelo.actualizarFichero();
					bloquearAcciones();

				}
			} else if (texto.equals("Editar")) {

				dialogo = new DialogoDireccion(Ventana.getVentana(), "Inserte Direccion", true,
						modelo.getElementAt(vista.list.getSelectedIndex()));

				if (controlador.verificarDireccion(dialogo.getDireccion())) {
					modelo.setElementAt(dialogo.getDireccion(), vista.list.getSelectedIndex());
					modelo.actualizarFichero();
					JOptionPane.showMessageDialog(null, "Direccion comprobada con exito y guardada");
				} else {
					JOptionPane.showMessageDialog(null, "direccion no encontrada");
				}
			} else if (texto.equals("Origen")) {
				tOrigen.setText(controlador.crearString(list.getSelectedValue()));
				controlador.ensenarMapa(list.getSelectedValue());
			} else if (texto.equals("Destino")) {
				tDestino.setText(controlador.crearString(list.getSelectedValue()));
			} else if (texto.equals("Mostrar")) {
				Route.mode modo = Route.mode.driving;
				
				Route.avoids avoid = Route.avoids.nothing;
				
				ArrayList<String> hitos = new ArrayList<>();
				 try {
					 String[][] arrayRoute;
					arrayRoute = ruta.getRoute(tOrigen.getText(), tDestino.getText(), hitos, Boolean.TRUE, modo,
								avoid);
					DialogoRuta	dialogoRuta = new DialogoRuta(Ventana.getVentana(), "Inserte Direccion", true,arrayRoute);
					
				 } catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		}

	}
}
