package pantallas.gps;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GestorMaps implements ListSelectionListener {

	private static final String FICHERO_API = "files/gps/claveAPI.txt";
	Geocoding geo;
	VistaGPS vista;
	ModeloDirecciones modelo;
	StaticMaps map = new StaticMaps();

	public GestorMaps(VistaGPS vista, ModeloDirecciones modelo) {
		guardarClave();
		this.vista = vista;
		geo = new Geocoding();

	}

	private void guardarClave() {
		String clave = leerClave();
		if (comprobarClave(clave).equalsIgnoreCase("OK")) {
			MapsJava.setKey(clave);
			System.out.println("clave correcta");
		}
		MapsJava.setSensor(true);
	}

	private String comprobarClave(String clave) {
		return MapsJava.APIkeyCheck(clave);

	}

	private String leerClave() {

		try (BufferedReader in = new BufferedReader(new FileReader(FICHERO_API))) {
			String linea;
			if ((linea = in.readLine()) != null) {
				return linea;
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "fichero de direcciones no encontrado", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean verificarDireccion(Direccion direccion) {
		Point2D.Double resultado = null;

		try {
			resultado = geo.getCoordinates(crearString(direccion));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resultado.getX() + "," + resultado.getY());
		if ((resultado.getX() != 0 && resultado.getY() != 0) || (resultado == null)) {
			return true;
		} else {
			return false;
		}

	}

	String crearString(Direccion direccion) {
		String resultado = "";

		resultado += (direccion.getPais() + " ");

		resultado += direccion.getLocalidad() + " ";

		resultado += (direccion.getCalle() + " ");

		resultado += (direccion.getNumero());

		return resultado;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (vista.list.getSelectedIndex() != -1) {
			vista.accAdd.setEnabled(true);
			vista.accRemove.setEnabled(true);
			vista.accEdit.setEnabled(true);
			vista.accOrigen.setEnabled(true);
			vista.accDestino.setEnabled(true);
			vista.mapa.setIcon(ensenarMapa(vista.list.getSelectedValue()));
		}
	}

	public ImageIcon ensenarMapa(Direccion direccion) {
		Image imagenMapa = null;
		String s = crearString(direccion);

		ImageIcon imgIcon;
		try {
			imagenMapa = map.getStaticMap(s, 10, new Dimension(500, 500), 1, StaticMaps.Format.jpg,
					StaticMaps.Maptype.roadmap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (imgIcon = new ImageIcon(imagenMapa));

	}
}
