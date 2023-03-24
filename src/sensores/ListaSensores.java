package sensores;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pantallas.GestorPantallas;
import principal.Definiciones;

@SuppressWarnings({ "rawtypes", "serial" })
public class ListaSensores extends AbstractListModel implements ListSelectionListener {
	final static int cantidadSensores = 11;
	List<Sensor> listaSensores;
	
	public ListaSensores() {
		listaSensores = new ArrayList<>();
	}
	
	@Override
	public int getSize() {
		return listaSensores.size();
	}
	
	public void add(Sensor element) {
		if (listaSensores.add(element)) {
			System.out.println(getSize());
			fireContentsChanged(this, 0, getSize());
		}
	}
	
	@Override
	public Sensor getElementAt(int index) {
		return (Sensor)listaSensores.toArray()[index];
	}
	
	public Sensor getSensor(int id) {
		for(Sensor sens:listaSensores) {
			if(sens.getId() == id) return sens;
		}
		return null;
	}
	
	public Sensor[] getListaDatosSensores() {
		return listaSensores.toArray(new Sensor[0]);
	}
	
	public void actualizarListaSensores() {
		for(int i = 0; i < cantidadSensores; i++) {
			if(!listaSensores.get(i).isCorrecto()) {
				Sensor aux = listaSensores.get(i);
				listaSensores.remove(listaSensores.get(i));
				listaSensores.add(0, aux);
			}
		}
	}
	
	public void setTodoArreglado() {
		for(Sensor sensor:listaSensores) {
			sensor.setCorrecto(true);
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			for(int i = e.getFirstIndex(); i <= e.getLastIndex(); i++) {
				getElementAt(i).switchCorrecto();
			}
			actualizarListaSensores();
			GestorPantallas.setPantalla(Definiciones.comandoMantenimiento);
		}
	}
	
}
