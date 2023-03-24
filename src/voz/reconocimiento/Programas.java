package voz.reconocimiento;

import java.util.ArrayList;
import java.util.List;

import javax.speech.recognition.Recognizer;
import javax.speech.recognition.Result;
import javax.speech.recognition.ResultAdapter;
import javax.speech.recognition.ResultEvent;
import javax.speech.recognition.ResultToken;

import emergencia.Emergencia;
import pantallas.GestorPantallas;
import pantallas.climatizador.Climatizador;
import principal.Definiciones;
import principal.Principal;
import principal.ventana.ControladorVentana;
import voz.habla.Voz;

public class Programas extends ResultAdapter {
	static Recognizer reconocedor;
	ControladorVentana controlador;
	boolean escuchando;
	
	public void resultAccepted(ResultEvent e) {
		System.out.println(e.toString());
		try {
			Result res = (Result)(e.getSource());
			List<String> palabras = new ArrayList<>();
			for(ResultToken token: res.getBestTokens()) {
				palabras.add(token.getSpokenText());
			}
			
			if(palabras.get(0).equals("Boiscar") && palabras.get(1).equals("escuchame") && palabras.size() == 2) {
				escuchando = true;
				System.out.println("Boiscar escuchame");
				Voz.hablar("Te escucho");
			}
			else if(palabras.get(0).equals("Boiscar") && (palabras.get(1).equals("socorro") || palabras.get(1).equals("ayuda")) && palabras.size() == 2) {
				Emergencia.pedirAyuda();
			}
			
			else if(escuchando) {
				System.out.println("ESCUCHANDO");
				System.out.println(palabras.get(0));
				switch(palabras.get(0)) {
					case "Cancelar":
						Voz.hablar("Cancelado");
						escuchando = false;
						break;
					case "Mantenimiento":
						GestorPantallas.setPantalla(Definiciones.comandoMantenimiento);
						escuchando = false;
						break;
					case "Musica":
						GestorPantallas.setPantalla(Definiciones.comandoReproductor);
						escuchando = false;
						break;
					case "Climatizador":
						GestorPantallas.setPantalla(Definiciones.comandoClimatizador);
						escuchando = false;
						break;
					case "GPS":
						GestorPantallas.setPantalla(Definiciones.comandoGPS);
						escuchando = false;
						break;
					case "Menu":
						GestorPantallas.setPantalla(Definiciones.comandoMenu);
						escuchando = false;
						break;
					case "socorro": {
						System.out.println("Enviando ayuda");
						Principal.getLineaSerie().enviaAyuda();
						Emergencia.notificarEnFichero("test", 12, 13);
			        	Voz.hablar("Enviando ayuda");
			        	escuchando = false;
			        	break;
			        }
				}
				if(palabras.get(0).equals("Dato") && palabras.get(1).equals("curioso")) {
					Voz.hablar("La Guerra chimpancé de Gombe fue un conflicto violento entre dos comunidades de chimpancés en el Parque nacional Gombe Stream, en Tanzania, que comenzó en 1974 y terminó en 1978. Los dos grupos combatientes pertenecían originalmente a la comunidad Kasakela.");
					escuchando = false;
				}
				if(palabras.get(1).equals("Temperatura") && palabras.get(3).equals("grados")) {
					if(palabras.get(0).equals("Subir")) {
						if(Climatizador.incrementar(Integer.parseInt(palabras.get(2)))) {
							Voz.hablar("Temperatura actualizada");
						}
					}
					else if(palabras.get(0).equals("Bajar")) {
						if(Climatizador.decrementar(Integer.parseInt(palabras.get(2)))) {
							Voz.hablar("Temperatura actualizada");
						}
					}
					escuchando = false;
				}
				if(palabras.get(0).equals("Temperatura") && palabras.get(1).equals("actual")) {
					Voz.hablar("La temperatura actual es de " + Climatizador.getContador() + " grados");
					escuchando = false;
				}
				reconocedor.suspend();
				reconocedor.resume();
			}
		} catch(Exception ex) {
			System.out.println("Excepcion resultAccepted");
		}
	}
	
}
