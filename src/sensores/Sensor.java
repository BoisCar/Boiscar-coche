package sensores;

public class Sensor implements Comparable<Sensor> {
	String nombre;
	String tipo;
	boolean correcto;
	int id;
	
	public Sensor(String nombre, String tipo, boolean correcto, int id) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.correcto = correcto;
		this.id = id;
	}
	
	public void switchCorrecto() {
		this.correcto = !this.correcto;
	}
	
	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	public boolean isCorrecto() {
		return correcto;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int compareTo(Sensor o) {
		Boolean thisBool = correcto;
		return thisBool.compareTo(o.correcto);
	}
	
}
