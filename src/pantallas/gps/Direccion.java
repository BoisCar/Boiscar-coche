package pantallas.gps;


public class Direccion {

String nombre,pais,localidad,calle,numero;


	public Direccion(String nombre,String pais,String localidad,String calle,String numero) {
		this.pais=pais;
		this.localidad=localidad;
		this.calle=calle;
		this.numero=numero;
		this.nombre=nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public String getCalle() {
		return calle;
	}
	public String getNumero() {
		return numero;
	}
	public String getPais() {
		return pais;
	}
	@Override
	public String toString() {
		return nombre;
	}

	
}
