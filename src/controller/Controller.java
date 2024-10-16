package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import algoritmos.Kruskal;
import algoritmos.Prim;
import grafos.Arista;
import grafos.Grafo;
import visual.VentanaPrincipal;
import visual.VentanaRegistros;

public class Controller {
	
	private VentanaPrincipal interfazUsuario;
	private VentanaRegistros interfazRegistros;
	private Grafo grafoOriginal;
	private Grafo grafoPrim;
	private Grafo grafoKruskal;
	private ArrayList<String> ubicacionesSeleccionadas;
	
	
	public Controller() {
		this.grafoOriginal = new Grafo();
		this.grafoPrim = new Grafo();
		this.grafoKruskal = new Grafo();
		this.interfazUsuario = new VentanaPrincipal();
		this.interfazRegistros = new VentanaRegistros();
		this.ubicacionesSeleccionadas = new ArrayList<String>();
		inicializarBotones();
	}
	
	public void inicializarBotones() {
		
		agregarUbicacionesToBoxUbicaciones();
		
		interfazRegistros.buttonGuardarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(grafoOriginal.getListaDeVertices().containsKey(interfazRegistros.fieldVertice.getText())) {
					JOptionPane.showMessageDialog(null,"El vertice ya se encuentra registrado");
				}
				else {
					if(interfazRegistros.buttonRadio.isSelected()) {
						if(ubicacionesSeleccionadas.contains((String) interfazRegistros.boxUbicaciones.getSelectedItem())) {
							JOptionPane.showMessageDialog(null, "Seleccione otra ubicacion");
						}
						else {
							crearVerticeUsandoUbicaciones();
						}
					}
					else {
						if(interfazRegistros.fieldVertice.getText().isEmpty()
							|| interfazRegistros.fieldLongitud.getText().isEmpty()
							|| interfazRegistros.fieldLatitud.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null,"Ingrese el nombre del vertice / coordenadas");
						}
						else {
							crearVerticeUsandoCoordenadas();
						}
					}
				}
				actualizarAreaText();
				limpiarTextFields();
			}


		});
		
		interfazRegistros.buttonGuardarArista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(interfazRegistros.fieldOrigen.getText().isEmpty()
						|| interfazRegistros.fieldDestino.getText().isEmpty()
						|| interfazRegistros.fieldPeso.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Ingrese el vertice destino / origen / peso");
				}else {
					agregarAristaOrigenToDestino();
					actualizarAreaText();
					limpiarTextFields();
					for (String verticeOrigen1 : grafoOriginal.getListaDeVertices().keySet()) {
						dibujarAristasFromVertice(grafoOriginal, verticeOrigen1);						
					}
				}
			}

		});
		
		interfazRegistros.buttonEjecutarPrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!ClaseAuxiliar.esGrafoConexo(grafoOriginal)) {
					JOptionPane.showMessageDialog(null, "No se puede ejecutar el Algoritmo de Prim, es grafo no es conexo");
				}
				else {
					interfazUsuario.mapa.removeAllMapPolygons();
					grafoPrim = Prim.crearGrafoPrim(grafoOriginal);
					for (String verticeOrigen : grafoPrim.getListaDeVertices().keySet()) {					
							dibujarAristasFromVertice(grafoPrim, verticeOrigen);
					}
				}
			}
		});
		
		interfazRegistros.buttonEjecutarKruskal.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			
				if(!ClaseAuxiliar.esGrafoConexo(grafoOriginal)) {
					JOptionPane.showMessageDialog(null, "No se puede ejecutar el Algoritmo de Kruskal, es grafo no es conexo");
				}
				else {
					interfazUsuario.mapa.removeAllMapPolygons();
					grafoKruskal = Kruskal.crearKruskal(grafoOriginal,grafoOriginal.getAristas());
					for (String verticeOrigen : grafoKruskal.getListaDeVertices().keySet()) {	
						dibujarAristasFromVertice(grafoKruskal, verticeOrigen);
					}	
				}
			}
		});

		interfazRegistros.buttonCargarPredeterminado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				interfazUsuario.mapa.removeAllMapPolygons();
				interfazUsuario.mapa.removeAllMapMarkers();
				cargarGrafoDePrueba();
				actualizarAreaText();
			}
		});
	
		interfazUsuario.buttonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfazUsuario.frame.dispose();
				if(interfazRegistros.frame.isVisible()) {
					interfazRegistros.frame.dispose();
				}
			}
		});
		
		interfazRegistros.buttonLimpiarMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfazUsuario.mapa.removeAllMapMarkers();
				interfazUsuario.mapa.removeAllMapPolygons();
			}
		});
		
		
		interfazRegistros.buttonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfazRegistros.frame.dispose();
			}
		});
		
	}
	private void agregarAristaOrigenToDestino() {
		String verticeOrigen = interfazRegistros.fieldOrigen.getText();
		String verticeDestino = interfazRegistros.fieldDestino.getText();
		int pesoArista = Integer.parseInt(interfazRegistros.fieldPeso.getText());
		grafoOriginal.agregarArista(verticeOrigen,verticeDestino,pesoArista);
	}
	
	private void crearVerticeUsandoCoordenadas() {
		String vertice = interfazRegistros.fieldVertice.getText();
		double latitud = Double.parseDouble(interfazRegistros.fieldLatitud.getText());
		double longitud = Double.parseDouble(interfazRegistros.fieldLongitud.getText());
		Coordinate coord = new Coordinate(latitud, longitud);
		grafoOriginal.crearVertice(vertice, coord);
		marcarUbicacion(vertice, coord);
	}
	
	private void crearVerticeUsandoUbicaciones() {
		String vertice = interfazRegistros.fieldVertice.getText();
		Coordinate coord = getCoordenadasFromUbicacion();
		grafoOriginal.crearVertice(vertice, coord);
		marcarUbicacion(vertice, coord);
		ubicacionesSeleccionadas.add((String) interfazRegistros.boxUbicaciones.getSelectedItem());
	}
	private void dibujarAristasFromVertice(Grafo grafo, String vertice) {
		for(String verticeDestino: grafo.getListaDeVertices().get(vertice).getListaDeAristas().keySet()) {
			crearLineaEntrePuntos(grafo.getListaDeVertices().get(vertice).getCoordenadas(), 
					grafo.getListaDeVertices().get(verticeDestino).getCoordenadas(), grafo.getListaDeVertices().get(vertice).getListaDeAristas().get(verticeDestino));
		}
	}

	public void agregarUbicacionesToBoxUbicaciones() {
		
		interfazRegistros.boxUbicaciones.addItem("ARGENTINA");
		interfazRegistros.boxUbicaciones.addItem("BRASIL");
		interfazRegistros.boxUbicaciones.addItem("COLOMBIA");
		interfazRegistros.boxUbicaciones.addItem("CANADA");
		interfazRegistros.boxUbicaciones.addItem("ESTADOS UNIDOS");
		interfazRegistros.boxUbicaciones.addItem("MEXICO");
		interfazRegistros.boxUbicaciones.addItem("URUGUAY");
		interfazRegistros.boxUbicaciones.addItem("PERU");
		interfazRegistros.boxUbicaciones.addItem("VENEZUELA");
		
	}
	
	public Coordinate getCoordenadasFromUbicacion() {
		switch(interfazRegistros.boxUbicaciones.getSelectedItem().toString()) {
		case "ARGENTINA" : return new Coordinate(-34.76, -61.92);
		case "BRASIL" : return new Coordinate	(-17.88, -48.32);
		case "COLOMBIA" : return new Coordinate (2.94, -72.75);
		case "CANADA" : return new Coordinate	(54.74, -100.35);
		case "ESTADOS UNIDOS" : return new Coordinate(37.33, -93.50);
		case "MEXICO" : return new Coordinate		 (24.19, -102.30);
		case "PERU" : return new Coordinate			 (-10.48, -73.98);
		case "URUGUAY" : return new Coordinate		 (-33.20, -55.68);
		case "VENEZUELA" : return new Coordinate	 (7.13, -64.47);

		default:
			break;
		}
		return null;
	}
	
	private void limpiarTextFields(){
		interfazRegistros.fieldDestino.setText(null);
		interfazRegistros.fieldLatitud.setText(null);
		interfazRegistros.fieldLongitud.setText(null);
		interfazRegistros.fieldOrigen.setText(null);
		interfazRegistros.fieldPeso.setText(null);
		interfazRegistros.fieldVertice.setText(null);
	}
	
	public void crearLineaEntrePuntos(Coordinate origen, Coordinate destino, Arista arista) {
		ArrayList<Coordinate> ruta = new ArrayList<Coordinate>();
		// MAPPOLYGONIMPL UTILIZA TRES COORDENADAS PARA CREAR UN POLIGONO
		// PARA CREAR UNA LINEA ENTRE DOS COORDENADAS
		// SE LE DEBE PASAR UN ARREGLO DONDE SE REPITA UNA COORDENADA CUALQUIERA
		Color color = obtenerColorPorPeso(arista.getPeso());
		ruta.add(origen);
		ruta.add(destino);
		ruta.add(destino);
		MapPolygonImpl linea =  new MapPolygonImpl(ruta);
		linea.setColor(color);
		interfazUsuario.mapa.addMapPolygon(linea);
		
	}
	
	
	private Color obtenerColorPorPeso(int peso) {
		// El color verde (0, 255, 0) en el extremo más bajo (peso = 0) 
	    // El color rojo (255, 0, 0) en el extremo más alto (peso = 10)
	    int rojo = (int) (255 * (peso / 10.0));  // Incrementa el componente rojo
	    int verde = 255 - rojo;  // Disminuye el componente verde
	    return new Color(rojo, verde, 0);
	}

	private void marcarUbicacion(String vertice, Coordinate coord) {
		MapMarker ubicacion = new MapMarkerDot(vertice, coord);
		ubicacion.getStyle().setBackColor(Color.RED);
		ubicacion.getStyle().setColor(Color.orange);
		interfazUsuario.mapa.addMapMarker(ubicacion);
	}
	
	private void actualizarAreaText() {
		interfazUsuario.textArea.setText(grafoOriginal.getInfoVertices());
	}

	private void cargarGrafoDePrueba() {
		grafoOriginal = new Grafo();
		grafoOriginal.crearVertice("Agente_1", new Coordinate (-34.76, -61.92));
		grafoOriginal.crearVertice("Agente_2", new Coordinate (-17.88, -48.32));
		grafoOriginal.crearVertice("Agente_3", new Coordinate (2.94, -72.75));
		grafoOriginal.crearVertice("Agente_4", new Coordinate (54.74, -100.35));
		grafoOriginal.crearVertice("Agente_5", new Coordinate (37.33, -93.50)); 
		grafoOriginal.crearVertice("Agente_6", new Coordinate (24.19, -102.30)); 
		grafoOriginal.crearVertice("Agente_7", new Coordinate (-10.48, -73.98)); 
		                                                                                                                                 	
		grafoOriginal.agregarArista("Agente_1", "Agente_2", 5);
		grafoOriginal.agregarArista("Agente_1", "Agente_3", 6);
		grafoOriginal.agregarArista("Agente_1", "Agente_5", 3);
		grafoOriginal.agregarArista("Agente_2", "Agente_3", 5);
		grafoOriginal.agregarArista("Agente_2", "Agente_4", 2);
		grafoOriginal.agregarArista("Agente_3", "Agente_5", 4);
		grafoOriginal.agregarArista("Agente_3", "Agente_7", 2);
		grafoOriginal.agregarArista("Agente_4", "Agente_7", 3);
		grafoOriginal.agregarArista("Agente_5", "Agente_6", 8);
		grafoOriginal.agregarArista("Agente_6", "Agente_7", 9);
	
		for(String vertice : grafoOriginal.getListaDeVertices().keySet()) {
			marcarUbicacion(vertice, grafoOriginal.getListaDeVertices().get(vertice).getCoordenadas());
		}
		
		for (String verticeOrigen : grafoOriginal.getListaDeVertices().keySet()) {
				dibujarAristasFromVertice(grafoOriginal, verticeOrigen);						
		}
	}
	
	

}
