package pantallas.reproductor;

public class Radio {
	String emisora, frecuencia;
	
	public Radio(String linea) {
		String valores [] = linea.split("[#]");
		this.emisora = valores[0];
		this.frecuencia = valores[1];
	}

	@Override
	public String toString() {
		return this.emisora + " - " + this.frecuencia + " Hz";
	}
	
}
