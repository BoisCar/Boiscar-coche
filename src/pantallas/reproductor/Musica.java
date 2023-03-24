package pantallas.reproductor;

public class Musica {
	String cantante, cancion;

	public Musica(String linea) {
		String valores [] = linea.split("[#]");
		this.cantante = valores[0];
		this.cancion = valores[1];
	}

	@Override
	public String toString() {
		return this.cantante + " - " + this.cancion;
	}
	
}
