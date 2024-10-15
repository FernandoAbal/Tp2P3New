package visual;

import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class VentanaPrincipal {

	public JFrame frame;
	public JMapViewer mapa;
	public JButton buttonSalir;
	public JTextArea textArea;

	public VentanaPrincipal() {
		mapa = new JMapViewer();
		mapa.setBounds(10, 11, 970, 616);
		initialize();

	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1330, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(mapa);

		buttonSalir = new JButton("SALIR");

		buttonSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonSalir.setBounds(380, 632, 89, 23);
		frame.getContentPane().add(buttonSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(990, 11, 314, 616);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		frame.setVisible(true);

	}
}
