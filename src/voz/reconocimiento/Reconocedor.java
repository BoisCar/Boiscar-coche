package voz.reconocimiento;

import java.io.FileReader;
import java.util.Locale;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.EngineStateError;
import javax.speech.recognition.Recognizer;
import javax.speech.recognition.RuleGrammar;

public class Reconocedor {
	static Recognizer reconocedor;
	
	public static void iniciarRec() {
		try {
			reconocedor = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
			reconocedor.allocate();
			FileReader grammar1 =new FileReader("files/voz/Diccionario.txt");
			RuleGrammar rg = reconocedor.loadJSGF(grammar1);
			reconocedor.getRecognizerProperties().setConfidenceLevel(1);
			rg.setEnabled(true);
			reconocedor.addResultListener(new Programas());
			
			for(int i=0;i<=23;i++) System.out.println("");
			
			System.out.println("Control por voz iniciado");
			reconocedor.commitChanges();
			reconocedor.requestFocus();
			reconocedor.resume();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exit");
			System.exit(0);
		}
	}
	
	public static void pausarRec() {
		reconocedor.pause();
	}
	
	public static void continuarRec() {
		try {
			reconocedor.resume();
		} catch (AudioException | EngineStateError e) {
			e.printStackTrace();
		}
	}
	
}
