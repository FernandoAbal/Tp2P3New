package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private Grafo grafo;
	private Grafo grafoPrim;
	private Grafo grafoKruskal;
	
	
	public Controller() {
		this.grafo = new Grafo();
		this.grafoPrim = new Grafo();
		this.grafoKruskal = new Grafo();
		this.interfazUsuario = new VentanaPrincipal();
		this.interfazRegistros = new VentanaRegistros();
		inicializarBotones();
	}
	
	public void inicializarBotones() {
		interfazRegistros.buttonGuardarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(interfazRegistros.buttonRadio.isSelected()) {
					String vertice = interfazRegistros.fieldVertice.getText();
					Coordinate coord = getCoordenadasFromUbicacion();
					grafo.crearVertice(vertice, coord);
					marcarUbicacion(vertice, coord);	
				}
				else {
				
					if(interfazRegistros.fieldVertice.getText().isEmpty()
						|| interfazRegistros.fieldLongitud.getText().isEmpty()
						|| interfazRegistros.fieldLatitud.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Ingrese el nombre del vertice / coordenadas");
				}
					else {
						String vertice = interfazRegistros.fieldVertice.getText();
						double latitud = Double.parseDouble(interfazRegistros.fieldLatitud.getText());
						double longitud = Double.parseDouble(interfazRegistros.fieldLongitud.getText());
						Coordinate coord = new Coordinate(latitud, longitud);
						grafo.crearVertice(vertice, coord);
						marcarUbicacion(vertice, coord);
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
					String verticeOrigen = interfazRegistros.fieldOrigen.getText();
					String verticeDestino = interfazRegistros.fieldDestino.getText();
					int pesoArista = Integer.parseInt(interfazRegistros.fieldPeso.getText());
					grafo.agregarArista(verticeOrigen,verticeDestino,pesoArista);
					actualizarAreaText();
					limpiarTextFields();
					for (String verticeOrigen1 : grafo.getListaDeVertices().keySet()) {
						dibujarAristasFromVertice(grafo, verticeOrigen1);						
					}
				}
			}
		});
		
		interfazRegistros.buttonEjecutarPrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfazUsuario.mapa.removeAllMapPolygons();
				grafoPrim = Prim.crearGrafoPrim(grafo);
				
				for (String verticeOrigen : grafoPrim.getListaDeVertices().keySet()) {					
						dibujarAristasFromVertice(grafoPrim, verticeOrigen);
				}
			}
		});
		
		interfazRegistros.buttonEjecutarKruskal.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				interfazUsuario.mapa.removeAllMapPolygons();
				grafoKruskal = Kruskal.crearKruskal(grafo,grafo.getAristas());
				
				for (String verticeOrigen : grafoKruskal.getListaDeVertices().keySet()) {					
						dibujarAristasFromVertice(grafoKruskal, verticeOrigen);
				}
			}
		});

		interfazRegistros.buttonCargarPredeterminado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				interfazUsuario.mapa.removeAllMapPolygons();
				interfazUsuario.mapa.removeAllMapMarkers();
				grafo = new Grafo();
			  	grafo.crearVertice("Agente_1", new Coordinate (-34.76, -61.92));
				grafo.crearVertice("Agente_2", new Coordinate (-17.88, -48.32));
				grafo.crearVertice("Agente_3", new Coordinate (2.94, -72.75));
			  	grafo.crearVertice("Agente_4", new Coordinate (54.74, -100.35));
			  	grafo.crearVertice("Agente_5", new Coordinate (37.33, -93.50)); 
			  	grafo.crearVertice("Agente_6", new Coordinate (24.19, -102.30)); 
			  	grafo.crearVertice("Agente_7", new Coordinate (-10.48, -73.98)); 
				grafo.crearVertice("Agente_8", new Coordinate (-33.20, -55.68)); 
				grafo.crearVertice("Agente_9", new Coordinate (7.13, -64.47)); 
                                                                               
				                                                                  	
				grafo.agregarArista("Agente_1", "Agente_2", 1);
				grafo.agregarArista("Agente_1", "Agente_3", 2);
				grafo.agregarArista("Agente_1", "Agente_4", 3);
				grafo.agregarArista("Agente_2", "Agente_5", 4);
				grafo.agregarArista("Agente_2", "Agente_6", 3); 
				grafo.agregarArista("Agente_3", "Agente_9", 2);
				grafo.agregarArista("Agente_4", "Agente_5", 2);
				grafo.agregarArista("Agente_4", "Agente_9", 3);
				grafo.agregarArista("Agente_5", "Agente_7", 1);
				grafo.agregarArista("Agente_6", "Agente_7", 5);
				grafo.agregarArista("Agente_8", "Agente_2", 5);
	
	
				for(String vertice : grafo.getListaDeVertices().keySet()) {
					marcarUbicacion(vertice, grafo.getListaDeVertices().get(vertice).getCoordenadas());
				}
				
				for (String verticeOrigen : grafo.getListaDeVertices().keySet()) {
						dibujarAristasFromVertice(grafo, verticeOrigen);						
				}
				
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
		agregarUbicaciones();
		
	}
	
	private void dibujarAristasFromVertice(Grafo grafo, String vertice) {
		for(String verticeDestino: grafo.getListaDeVertices().get(vertice).getListaDeAristas().keySet()) {
			crearLineaEntrePuntos(grafo.getListaDeVertices().get(vertice).getCoordenadas(), 
					grafo.getListaDeVertices().get(verticeDestino).getCoordenadas(), grafo.getListaDeVertices().get(vertice).getListaDeAristas().get(verticeDestino));
		}
	}

	public void agregarUbicaciones() {
		
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
		interfazUsuario.textArea.setText(grafo.getInfoVertices());
	}
	
	

}
