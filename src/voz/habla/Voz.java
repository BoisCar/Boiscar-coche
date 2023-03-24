package voz.habla;

import java.util.Locale;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

public class Voz {
	static SynthesizerModeDesc desc;
	static Synthesizer synthesizer;
	static Voice voice;
	static boolean hablando;
	
	public static void init(String voiceName) {
		hablando = false;
		if (desc == null) {
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
      
			desc = new SynthesizerModeDesc(Locale.ROOT);
			try {
				Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
				synthesizer = Central.createSynthesizer(desc);
				synthesizer.allocate();
				synthesizer.resume();
			} catch (EngineException | AudioException | EngineStateError e) {
				e.printStackTrace();
			}
			
			SynthesizerModeDesc smd = (SynthesizerModeDesc)synthesizer.getEngineModeDesc();
			Voice[] voices = smd.getVoices();
			Voice voice = null;
			for(int i = 0; i < voices.length; i++) {
				if(voices[i].getName().equals(voiceName)) {
					voice = voices[i];
					break;
				}
			}	
			synthesizer.getSynthesizerProperties().setVoice(voice);
		}
	}
	
	public static void terminate() throws EngineException, EngineStateError {
		synthesizer.deallocate();
	}
	
	public static void doSpeak(String speakText) throws EngineException, AudioException, IllegalArgumentException, InterruptedException {
		synthesizer.speakPlainText(speakText, null);
		synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
		hablando = false;
	}
	
	public static void pauseSpeak() {
		synthesizer.cancel();
		hablando = false;
	}
	
	public static void hablar(String texto) {
		hablando = true;
        Thread t = new Thread(new Habla(texto));
        t.start();
	}
	
	public static boolean isHablando() {
		return hablando;
	}
}
