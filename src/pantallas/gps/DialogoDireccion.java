package pantallas.gps;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoDireccion extends JDialog implements ActionListener {
	JTextField nombre, pais, localidad, calle, numero;
	JLabel texto;
	JButton anadir;
Direccion direccion,direccion2;
boolean editar;
	public DialogoDireccion(JFrame owner,String titulo, boolean b,Direccion direccion2){
		super(owner,titulo,b);
		this.direccion2=direccion2;
		this.setSize(500, 500);
		this.setLocation(500, 200);
		this.setContentPane(crearPanelDialogo());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		

	}

	private Container crearPanelDialogo() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearPanelNorte(), BorderLayout.NORTH);
		panel.add(crearPanelDatos(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		return panel;

	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(5,1,0,10));
		nombre = new JTextField();
		pais = new JTextField();
		calle = new JTextField();
		localidad = new JTextField();
		numero = new JTextField();
		
		nombre.setBorder(BorderFactory.createTitledBorder("nombre"));
		pais.setBorder(BorderFactory.createTitledBorder("pais"));
		calle.setBorder(BorderFactory.createTitledBorder("calle"));
		localidad.setBorder(BorderFactory.createTitledBorder("localidad"));
		numero.setBorder(BorderFactory.createTitledBorder("numero"));
		
		if(direccion2!=null)llenarCampos();
		
		panel.add(nombre);
		panel.add(pais);
		panel.add(localidad);
		panel.add(calle);
		panel.add(numero);
		return panel;
	}

	private void llenarCampos() {
		nombre.setText(direccion2.getNombre());
		pais.setText(direccion2.getPais());
		calle.setText(direccion2.getCalle());
		localidad.setText(direccion2.getLocalidad());
		numero.setText(direccion2.getNumero());
		
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new FlowLayout());
		anadir = new JButton("guardar");
		anadir.addActionListener(this);
		anadir.setActionCommand("guardar");
		panel.add(anadir);
		return panel;
	}

	private Component crearPanelNorte() {
		JPanel panel = new JPanel();
		texto = new JLabel("Intorduzca la direccion");
		panel.add(texto);
		return panel;
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals("guardar")){
	
		direccion=new Direccion(nombre.getText(), pais.getText(),localidad.getText(),calle.getText(),numero.getText());
		System.out.println(nombre.getText()+"     "+ pais.getText()+","+localidad.getText()+","+calle.getText()+","+numero.getText());
		
	}
		dispose();
	}

	public Direccion getDireccion() {
		return direccion;
	}

}
