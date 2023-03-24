package pantallas.climatizador;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import principal.Principal;
import voz.habla.Voz;

public class Climatizador {
	public final static int limiteSuperior = 30;
	public final static int limiteInferior = 5;
	private final static String PROPIEDAD = "contador";
	private static int contador;
	private static PropertyChangeSupport conector;
	
	public static void iniciar() {
		contador = 20;
		conector = new PropertyChangeSupport(Climatizador.class);
	}
	
	public static int getContador() {
		return contador;
	}
	
	public static void actualizarTemperatura() {
		int temperatura = Climatizador.getContador();
    	Principal.getLineaSerie().setFirstDigit(temperatura/10);
    	Principal.getLineaSerie().setSecondDigit(temperatura%10);
		try {
			Principal.getLineaSerie().enviarTemperatura();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean incrementar(int incremento) {
		if(contador + incremento <= limiteSuperior) {
			contador += incremento;
			actualizarTemperatura();
			conector.firePropertyChange(PROPIEDAD, 0, contador);
			return true;
		}
		else { 
			contador = limiteSuperior;
			actualizarTemperatura();
			conector.firePropertyChange(PROPIEDAD, 0, contador);
			Voz.hablar("Temperatura máxima alcanzada, " + Climatizador.limiteSuperior + "grados");
			return false;
		}
	}
	
	public static boolean decrementar(int decremento) {
		if(contador - decremento >= limiteInferior) {
			contador -= decremento;
			actualizarTemperatura();
			conector.firePropertyChange(PROPIEDAD, 0, contador);
			return true;
		}
		else { 
			contador = limiteInferior;
			actualizarTemperatura();
			conector.firePropertyChange(PROPIEDAD, 0, contador);
			Voz.hablar("Temperatura mínima alcanzada,  " + Climatizador.limiteInferior +  "grados");
			return false;
		}
	}
	
	public static void addPropertyChangeListener(PropertyChangeListener listener) {
		conector.addPropertyChangeListener(listener);
	}
	
	public static void removePropertyChangeListener(PropertyChangeListener listener) {
		conector.removePropertyChangeListener(listener);
	}
	
}
