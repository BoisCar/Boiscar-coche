package emergencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import principal.Definiciones;
import principal.Principal;
import voz.habla.Voz;

public class Emergencia {
	
	private final static String SEPARADOR = "$";
	
	public static void notificarEnFichero(String nombre, int coordX, int coordY) {
		try (BufferedWriter br = new BufferedWriter((new FileWriter(Definiciones.FICHERO_ACCIDENTES, true)))) {
			br.write(nombre + SEPARADOR + Integer.toString(coordX) + SEPARADOR + Integer.toString(coordY) + "\n");
		/*} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "fichero de historial no encontrado", "ERROR",
					JOptionPane.ERROR_MESSAGE);*/
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void pedirAyuda() {
		System.out.println("Enviando ayuda");
    	try {
			Principal.getLineaSerie().enviaAyuda();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	notificarEnFichero("test", 12, 13);
    	Voz.hablar("Enviando ayuda");
	}
	
}
