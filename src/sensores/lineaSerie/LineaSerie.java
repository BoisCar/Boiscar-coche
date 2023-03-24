package sensores.lineaSerie;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import emergencia.Emergencia;
import pantallas.GestorPantallas;
import pantallas.climatizador.Climatizador;
import principal.Definiciones;
import principal.Principal;
import voz.habla.Voz;

public class LineaSerie implements PropertyChangeListener{
	final static String PUERTO_SELECIONAR="COM6";
	
	PropertyChangeSupport soporte;
	SerialReader reader;
	SerialPort port;
	char lastSentCommand;
	int firstDigit, secondDigit;

	public LineaSerie() throws Exception {
		port= SerialPort.getCommPort(PUERTO_SELECIONAR);
		connect();
		lastSentCommand = 'Z';
	}

	void connect () throws Exception {
		port.openPort();
        port.setComPortParameters(9600,8,SerialPort.ONE_STOP_BIT,SerialPort.NO_PARITY);
                             
        reader = new SerialReader(port);
        reader.addPropertyChangeListener(this);
        port.addDataListener(reader);        
                    
        System.out.println("Conexión establecida con el puerto");
	}

	public void enviarTemperatura() throws InterruptedException {
		port.writeBytes("_".getBytes(), "_".getBytes().length);
		Thread.sleep(5);
        port.writeBytes(" ".getBytes(), " ".getBytes().length);
        Thread.sleep(10);
        port.writeBytes(Integer.toString(firstDigit).getBytes(), Integer.toString(firstDigit).getBytes().length);//firstDigit
        Thread.sleep(10);
        port.writeBytes("/".getBytes(), "/".getBytes().length);
        Thread.sleep(10);
        port.writeBytes(Integer.toString(secondDigit).getBytes(), Integer.toString(secondDigit).getBytes().length);//secondDigit
	}

	public void enviaAyuda() throws InterruptedException {
        port.writeBytes("_".getBytes(), "_".getBytes().length);
        Thread.sleep(10);
        port.writeBytes("h".getBytes(), "h".getBytes().length);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		char caracter;
		if (e.getPropertyName().equals("recibido")) {
			String stringCaracter = (String) e.getNewValue();
			caracter = stringCaracter.charAt(0);
			
			if(caracter == lastSentCommand) return;
			else lastSentCommand = caracter;
			
			switch (caracter) {
			default:
				int idSensor;
				if(caracter == 'A') idSensor = 10;
				else if(caracter == 'B') idSensor = 11;
				else idSensor = Integer.parseInt(String.valueOf(caracter));
				if(Principal.getSensores().getSensor(idSensor).isCorrecto()) {
					Principal.getSensores().getSensor(idSensor).setCorrecto(false);
					Voz.hablar("Alerta: " + Principal.getSensores().getSensor(idSensor).toString());
				}
				Principal.getSensores().actualizarListaSensores();
				GestorPantallas.setPantallaActual(Definiciones.comandoMantenimiento);
				GestorPantallas.setPantalla(Definiciones.comandoMantenimiento);
				break;
			case '™':
				Voz.hablar("Asistente de averia desactivado");
				break;
			case '!':
				Voz.hablar("Varias averias, acuda al taller más cercano");
				GestorPantallas.setPantallaActual(Definiciones.comandoMantenimiento);
				GestorPantallas.setPantalla(Definiciones.comandoMantenimiento);
				GestorPantallas.fireWarning();
				break;
			case 'š':
				Voz.hablar("Todas las averias arregladas");
				Principal.getSensores().setTodoArreglado();
				Principal.getSensores().actualizarListaSensores();
				GestorPantallas.setPantalla(Definiciones.comandoMantenimiento);
				break;
			case 'H':
				Emergencia.pedirAyuda();
				break;
			}
		}
	}

	public static class SerialReader implements SerialPortDataListener {
		private SerialPort port;
		PropertyChangeSupport soporte;
		
		public SerialReader(SerialPort port) {
			this.port = port;
			this.soporte = new PropertyChangeSupport(this);
		}
		
		@Override
		public int getListeningEvents() {
			return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; 
		}

		@Override
		public void serialEvent(SerialPortEvent event) {
			if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
		         return;
		      byte[] newData = new byte[port.bytesAvailable()];
		      port.readBytes(newData, newData.length);
		      
		      soporte.firePropertyChange("recibido", null, new String(newData));	
		}
		public void addPropertyChangeListener(PropertyChangeListener listener) {
			soporte.addPropertyChangeListener(listener);
		}
	}

	public void setFirstDigit(int firstDigit) {
		this.firstDigit = firstDigit;
	}

	public void setSecondDigit(int secondDigit) {
		this.secondDigit = secondDigit;
	}
	
}
