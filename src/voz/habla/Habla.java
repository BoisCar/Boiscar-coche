package voz.habla;

import javax.speech.AudioException;
import javax.speech.EngineException;

import principal.Principal;
import voz.reconocimiento.Reconocedor;

public class Habla implements Runnable {
	String texto;
	
	public Habla(String texto) {
		this.texto = texto;
	}
	
	private void hablar(String texto) {
		if(Voz.isHablando()) Voz.pauseSpeak();
		try {
			Reconocedor.pausarRec();
			System.out.println("Reconocimiento pausado");
			Voz.doSpeak(texto);
			System.out.println("Continuando reconocimiento");
			Reconocedor.continuarRec();
		} catch (EngineException | AudioException | IllegalArgumentException | InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("Fin de habla");
	}

	@Override
	public void run() {
		hablar(texto);
	}
}
