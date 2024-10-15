package CompararSimulador;

import java.awt.Color;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import grafos.Grafo;

public class Simulacion extends SwingWorker<Long, Long> {
	private Grafo grafo;
	private boolean usarPrim;
	private JProgressBar barraProgreso;
	private JTextField resultJTextField;

	public Simulacion(Grafo grafo, boolean usarPrim, JProgressBar barraProgreso, JTextField resultJTextField) {
		this.grafo = grafo;
		this.usarPrim = usarPrim;
		this.barraProgreso = barraProgreso;
		this.resultJTextField = resultJTextField;
	}

//en la clase grafo agregue los metodos que devuelven el tiempo de ejecucion	
//pero algunas llamadas a los metodos que se usan tipo prim estan mal
//por eso tira error aca

	@Override
	protected Long doInBackground() {
		barraProgreso.setIndeterminate(true);
		long tiempo;

		if (usarPrim) {
			tiempo = Grafo.tiempoEjecucionPrim(grafo);
		} else {
			tiempo = Grafo.tiempoEjecucionKruskal(grafo);
		}

		return tiempo;
	}

	@Override
	public void done() {
		try {
			barraProgreso.setIndeterminate(false);
			Long tiempoEjecucion = get();

			resultJTextField.setText("Tiempo de ejecución: " + tiempoEjecucion + " ms");
		} catch (InterruptedException ex) {
			resultJTextField.setText("Ejecución interrumpida");
		} catch (ExecutionException ex) {
			resultJTextField.setText("Error mientras se ejecutaba el algoritmo");
		}
	}

}

//algo asi seria lo de la interfaz grafica pero habria que adaptarlo a la de
//este codigo

//private void crearTiempoEjecucionFrame() {
//	frameTiempoEjecucion = new JFrame("Tiempo de Ejecución");
//	frameTiempoEjecucion.setBounds(100, 100, 600, 600);
//	frameTiempoEjecucion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	frameTiempoEjecucion.setResizable(false);
//	frameTiempoEjecucion.setVisible(true);
//	frameTiempoEjecucion.setResizable(false);
//
//	JPanel panel = new JPanel(null);
//	panel.setBackground(Color.white);
//	frameTiempoEjecucion.getContentPane().add(panel);
//}
//
//private void mostrarTiempoEjecucion(Grafo arbolGeneradorMinimo) {
//	if (frameGrafo != null) {
//		frameGrafo.dispose();
//	}
//	crearTiempoEjecucionFrame();
//
//	JPanel panel = new JPanel(null);
//
//	JButton botonPrim = crearBotonPrim(arbolGeneradorMinimo);
//	JButton botonKruskal = crearBotonKruskal(arbolGeneradorMinimo);
//	JButton botonCancelar = crearBotonCancelar();
//
//	resultJTextField = new JTextField();
//	resultJTextField.setEditable(false);
//	resultJTextField.setBounds(80, 400, 400, 30);
//
//	botonPrim.setBounds(250, 100, 100, 50);
//	botonKruskal.setBounds(250, 200, 110, 50);
//	botonCancelar.setBounds(230, 300, 150, 50);
//
//	barraProgreso = new JProgressBar();
//	barraProgreso.setBounds(80, 500, 400, 30);
//	barraProgreso.setIndeterminate(false);
//
//	panel.add(botonPrim);
//	panel.add(botonKruskal);
//	panel.add(botonCancelar);
//	panel.add(resultJTextField);
//	panel.add(barraProgreso);
//
//	frameTiempoEjecucion.add(panel);
//	frameTiempoEjecucion.setVisible(true);
//}
//
//private JButton crearBotonPrim(Grafo arbolGeneradorMinimo) {
//	JButton botonPrim = new JButton("Usar Prim");
//	botonPrim.setFocusable(false);
//	botonPrim.addActionListener(ev -> {
//
//		simulacion = new Simulacion(arbolGeneradorMinimo, true, barraProgreso, resultJTextField);
//		simulacion.execute();
//	});
//	return botonPrim;
//}
//
//private JButton crearBotonKruskal(Grafo arbolGeneradorMinimo) {
//	JButton botonKruskal = new JButton("Usar Kruskal");
//	botonKruskal.setFocusable(false);
//	botonKruskal.addActionListener(ev -> {
//
//		simulacion = new Simulacion(arbolGeneradorMinimo, false, barraProgreso, resultJTextField);
//		simulacion.execute();
//	});
//	return botonKruskal;
//}
//
//private JButton crearBotonCancelar() {
//	JButton botonCancelar = new JButton("Cancelar Ejecucion");
//	botonCancelar.setFocusable(false);
//	botonCancelar.addActionListener(ev -> {
//
//		if (simulacion != null && !simulacion.isDone()) {
//			simulacion.cancel(true);
//		}
//	});
//	return botonCancelar;
//}
//
