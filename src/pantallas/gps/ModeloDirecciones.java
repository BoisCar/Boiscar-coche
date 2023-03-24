package pantallas.gps;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ModeloDirecciones extends DefaultListModel<Direccion> {
	final String FICHERO_DIRECCIONES = "files/gps/direcciones.txt";

	VistaGPS vista;
	public ModeloDirecciones(VistaGPS vista) {
		this.vista=vista;
		leerDireccionesFichero();
	}
	
	private List<Direccion> leerDireccionesFichero() {
		List<Direccion> lista = new ArrayList<>();
		try(BufferedReader in = new BufferedReader(new FileReader(FICHERO_DIRECCIONES ))){
			String linea;
			while ((linea = in.readLine())!=null){
				String []s=linea.split("[$]");
				addElement(new Direccion (s[0],s[1],s[2],s[3],s[4]));
			}
		} catch (FileNotFoundException e) {
			 JOptionPane.showMessageDialog(null,"fichero de direcciones no encontrado","ERROR",JOptionPane.ERROR_MESSAGE);
			 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void actualizarFichero() {
		try(PrintWriter out = new PrintWriter(new FileWriter(FICHERO_DIRECCIONES ))){
			for (int i=0;i<this.getSize();i++) {
				out.println(getElementAt(i).nombre +" "+"$"+getElementAt(i).getPais()+" "+"$"+getElementAt(i).getLocalidad()+" "+"$"+getElementAt(i).getCalle()+" "+"$"+getElementAt(i).getNumero()+" ");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
