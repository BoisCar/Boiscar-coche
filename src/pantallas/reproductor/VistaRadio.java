package pantallas.reproductor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import pantallas.PanelAtras;
import principal.Definiciones;
import principal.ventana.ControladorVentana;

public class VistaRadio {
	final static int OTRA_OPCION = 4;
	static Boolean MODO = false;
	static int FREQ = 0;
	static boolean primeraVez = true;
	ControladorReproductor controlador;
	JPanel panelRadio;
	JList<Radio> listaRadios;
	JList<Musica> listaMusica;
	DefaultListModel<Musica> modeloUSB;
	DefaultListModel<Radio> modeloRadio;
	JLabel lMusic;
	
	public VistaRadio() {		
		this.modeloUSB = new DefaultListModel<Musica>();
		this.modeloRadio = new DefaultListModel<Radio>();
		this.controlador = new ControladorReproductor(modeloUSB, modeloRadio);
		this.controlador.setVista(this);
	}
	
	public Container crearPanelRadio() {
		panelRadio = new JPanel(new BorderLayout());
		panelRadio.setBackground(Definiciones.colorVentana);
		panelRadio.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panelRadio.add(new PanelAtras(new ControladorVentana(), Definiciones.comandoMenu), BorderLayout.NORTH);
		panelRadio.add(crearMenu(), BorderLayout.CENTER);
		
		return panelRadio;
	}

	private Component crearMenu() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
		panel.setBackground(Definiciones.colorVentana);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(crearBoton(Definiciones.comandoUSB));
		panel.add(crearBoton(Definiciones.comandoRADIO));
		
		return panel;
	}

	private JButton crearBoton(String texto) {
		JButton boton = new JButton(texto);
		boton.setForeground(Color.BLACK);
		boton.setBackground(Definiciones.colorBotones);
		boton.setFont(new Font("Verdana", Font.BOLD, 20));
		boton.setActionCommand(texto);
		boton.addActionListener(controlador);
		return boton;
	}
	
	public void recargar(int opcionRecarga, String value) {
		recargarPanelRadio();
		System.out.println(Integer.toString(opcionRecarga));
		switch(opcionRecarga) {
			case 0: panelRadio.add(crearMenu(), BorderLayout.CENTER); break;
			case 1: panelRadio.add(crearUSB(), BorderLayout.CENTER); break;
			case 2: panelRadio.add(crearRadio(), BorderLayout.CENTER); break;
			default : panelRadio.add(crearPanelRadioMusica(value), BorderLayout.CENTER); break;
		}
		panelRadio.updateUI();
	}

	private void recargarPanelRadio() {
		panelRadio.removeAll();
		panelRadio.add(new PanelAtras(controlador, Definiciones.comandoAtras), BorderLayout.NORTH);
		panelRadio.setBackground(Definiciones.colorVentana);
	}

	private Component crearRadio() {
		JPanel panel = new JPanel(new BorderLayout(0, 20));
		panel.setBackground(Definiciones.colorVentana);
		panel.add(crearPanelDatosRadio(), BorderLayout.CENTER);
		
		return panel;
	}

	private Component crearPanelDatosRadio() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		listaRadios = new JList<>();
		listaRadios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaRadios.setModel(modeloRadio);
		listaRadios.setCellRenderer(new RendererCanciones());
		
		panel.setViewportView(listaRadios);
		panel.setBackground(Definiciones.colorVentana);
		panel.setOpaque(true);
		
		return panel;
	}

	private Component crearUSB() {
		JPanel panel = new JPanel(new BorderLayout(0, 20));
		panel.setBackground(Definiciones.colorVentana);
		panel.add(crearPanelDatosUSB(), BorderLayout.CENTER);
		
		return panel;
	}

	private Component crearPanelDatosUSB() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		listaMusica = new JList<>();
		listaMusica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaMusica.setModel(modeloUSB);
		listaMusica.setCellRenderer(new RendererCanciones());
		
		panel.setViewportView(listaMusica);
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		
		return panel;
	}
	
	private Component crearPanelRadioMusica(String value) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
		panel.add(crearDatos(value), BorderLayout.NORTH);
		panel.add(crearBoton(), BorderLayout.SOUTH);
		panel.add(crearPanelBotones(), BorderLayout.EAST);
		
		return panel;
	}
	
	 private Component crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 0, 60));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

		JButton	bPlus = new JButton(new ImageIcon("iconos/edit_add.png"));
		bPlus.setActionCommand("plus");
		bPlus.addActionListener(controlador);		
		
		JButton	bMinus = new JButton(new ImageIcon("iconos/edit_remove.png"));
		bMinus.setActionCommand("minus");
		bMinus.addActionListener(controlador);	
		
		panel.add(bPlus);
		panel.add(bMinus);
		
		return panel;
	}

	private Component crearBoton() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 0, 10));
		panel.setBackground(Color.WHITE);
		
		JButton	bNext = new JButton(new ImageIcon("iconos/2rightarrow.png"));
		bNext.setActionCommand(Definiciones.comandoRadioSiguiente);
		bNext.addActionListener(controlador);		
		
		JButton	bPrevious = new JButton(new ImageIcon("iconos/2leftarrow.png"));
		bPrevious.setActionCommand(Definiciones.comandoRadioAnterior);
		bPrevious.addActionListener(controlador);	
		
		panel.add(bPrevious);
		
		if(!MODO) {
			JButton	bPause = new JButton(new ImageIcon("iconos/pause.png"));	
			panel.add(bPause);
		}
		
		panel.add(bNext);
		
		return panel;
	}

	private Component crearDatos(String value) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel.setBackground(Color.WHITE);
		
		if(!MODO) {
			JLabel lIcon = new JLabel(new ImageIcon("iconos/music.png"));
			panel.add(lIcon);
		}
		
		lMusic = new JLabel(value.toString()); 
		lMusic.setFont(new Font("Arial", Font.ITALIC, 16));
		lMusic.setBackground(Color.WHITE);
		
		panel.add(lMusic);
				
		return panel;
	}

	@SuppressWarnings("serial")
	private class RendererCanciones extends DefaultListCellRenderer {

        @SuppressWarnings("rawtypes")
		@Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
        	JLabel label = new JLabel(value.toString());
    		label.setBorder(BorderFactory.createCompoundBorder(
    				BorderFactory.createLineBorder(Color.white, 2), 
    				BorderFactory.createEmptyBorder(10,20,10,20)));
    		label.setFont(new Font("arial", Font.PLAIN, 23));
    		if(isSelected) {
    			label.setBackground(Definiciones.colorVentana);
    			label.setForeground(Definiciones.colorBotones);
    			if(value instanceof Radio) MODO = true;
    			else MODO = false;
    			recargar(OTRA_OPCION, value.toString());
    		}
    		label.setOpaque(true);
    		return label;
        }
	}
	
	public void changeLabel(String tipo) {
		if(tipo.equals(Definiciones.comandoRadioSiguiente)) {
			if(MODO) {
				if(primeraVez) {
					if((FREQ == 0) && (modeloRadio.getSize() - 1 > FREQ))FREQ = listaRadios.getSelectedIndex();
					primeraVez = false;
				}
				if(modeloRadio.getSize() - 1 > FREQ) FREQ++;
				lMusic.setText(modeloRadio.elementAt(FREQ).toString());
			}
			else {
				if(primeraVez) {
					if((FREQ == 0) && (modeloUSB.getSize() - 1 > FREQ))FREQ = listaMusica.getSelectedIndex();
					primeraVez = false;
				}
				if(modeloUSB.getSize() - 1 > FREQ) FREQ++;
				lMusic.setText(modeloUSB.elementAt(FREQ).toString());
			}
		}
		else {
			if(MODO) {
				if(primeraVez) {
					if((FREQ == 0) && (modeloRadio.getSize() - 1 > FREQ))FREQ = listaRadios.getSelectedIndex();
					primeraVez = false;
				}
				if(FREQ > 0) FREQ--;
				lMusic.setText(modeloRadio.elementAt(FREQ).toString());
			}
			else {
				if(primeraVez) {
					if((FREQ == 0) && (modeloUSB.getSize() - 1 > FREQ))FREQ = listaMusica.getSelectedIndex();
					primeraVez = false;
				}
				if(FREQ > 0) FREQ--;
				lMusic.setText(modeloUSB.elementAt(FREQ).toString());
			}
		}
	}

	public void restaurar() {
		VistaRadio.FREQ = 0;
		VistaRadio.primeraVez = true;
	}
	
}
