package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.Coordinate;

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
	
	
	
	public Controller() {
		this.grafo = new Grafo();
		this.grafoPrim = new Grafo();
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
					//grafoPrim = Prim.crearGrafoPrim(grafo);
					for (String verticeOrigen1 : grafo.getListaDeVertices().keySet()) {
						dibujarAristasFromVertice(grafo, verticeOrigen1);						
					}
				}
			}
		});
		
		interfazRegistros.buttonEjecutarPrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfazUsuario.mapa.removeAllMapPolygons();
				grafo = Prim.crearGrafoPrim(grafo);
				
				for (String verticeOrigen : grafo.getListaDeVertices().keySet()) {					
						dibujarAristasFromVertice(grafo, verticeOrigen);
				}
			}
		});
		
		interfazRegistros.buttonEjecutarKruskal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfazUsuario.mapa.removeAllMapPolygons();
				grafo = Kruskal.crearKruskal(grafo,grafo.getAristas());
				
				for (String verticeOrigen : grafo.getListaDeVertices().keySet()) {					
						dibujarAristasFromVertice(grafo, verticeOrigen);
				}
			}
		});

		interfazRegistros.buttonCargarPredeterminado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {                                     
			  	grafo.crearVertice("Agente_1", new Coordinate(-34.76, -61.92));    
				grafo.crearVertice("Agente_2", new Coordinate(-17.88, -48.32));       
				grafo.crearVertice("Agente_3", new Coordinate(2.94, -72.75));       
			  	grafo.crearVertice("Agente_4", new Coordinate(54.74, -100.35));       
			  	grafo.crearVertice("Agente_5", new Coordinate(37.33, -93.50));
			  	grafo.crearVertice("Agente_6", new Coordinate(24.19, -102.30));
			  	grafo.crearVertice("Agente_7", new Coordinate(-23.47, -58.24));     
				grafo.crearVertice("Agente_8", new Coordinate(-10.48, -73.98));         
				grafo.crearVertice("Agente_9", new Coordinate(49.48, 31.27));      
			  	grafo.crearVertice("Agente_10",new Coordinate(46.60, 1.88));      
			  	grafo.crearVertice("Agente_11",new Coordinate(52.21, 19.13));    
			  	grafo.crearVertice("Agente_12",new Coordinate(39.32, -4.83));    
			 	grafo.crearVertice("Agente_13",new Coordinate(52.53, -1.26));    
			  	grafo.crearVertice("Agente_14",new Coordinate(63.24, 25.92));     
				                                                                  	
				grafo.agregarArista("Agente_1", "Agente_2", 1);
				grafo.agregarArista("Agente_1", "Agente_3", 2);
				grafo.agregarArista("Agente_1", "Agente_4", 3);
				grafo.agregarArista("Agente_2", "Agente_5", 4);
				grafo.agregarArista("Agente_2", "Agente_6", 3); 
				grafo.agregarArista("Agente_3", "Agente_9", 2);
				grafo.agregarArista("Agente_4", "Agente_5", 2);
				grafo.agregarArista("Agente_4", "Agente_9", 3);
				grafo.agregarArista("Agente_4", "Agente_10", 3);
				grafo.agregarArista("Agente_4", "Agente_12", 1);
				grafo.agregarArista("Agente_5", "Agente_6", 3);
				grafo.agregarArista("Agente_5", "Agente_7", 3);
				grafo.agregarArista("Agente_7", "Agente_8", 4);
				grafo.agregarArista("Agente_7", "Agente_12", 3);
				grafo.agregarArista("Agente_8", "Agente_10", 5);
				grafo.agregarArista("Agente_8", "Agente_11", 1);
				grafo.agregarArista("Agente_10", "Agente_11", 5);
				grafo.agregarArista("Agente_11", "Agente_12", 2);
				grafo.agregarArista("Agente_11", "Agente_14", 1);
				grafo.agregarArista("Agente_12", "Agente_13", 5);
				grafo.agregarArista("Agente_12", "Agente_14", 2);
	
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
		interfazRegistros.boxUbicaciones.addItem("ALEMANIA");
		interfazRegistros.boxUbicaciones.addItem("AUSTRALIA");
		interfazRegistros.boxUbicaciones.addItem("BRASIL");
		interfazRegistros.boxUbicaciones.addItem("COLOMBIA");
		interfazRegistros.boxUbicaciones.addItem("CANADA");
		interfazRegistros.boxUbicaciones.addItem("CHINA");
		interfazRegistros.boxUbicaciones.addItem("ESTADOS UNIDOS");
		interfazRegistros.boxUbicaciones.addItem("ESPANIA");
		interfazRegistros.boxUbicaciones.addItem("EGIPTO");
		interfazRegistros.boxUbicaciones.addItem("FRANCIA");
		interfazRegistros.boxUbicaciones.addItem("FINLANDIA");
		interfazRegistros.boxUbicaciones.addItem("INGLATERRA");
		interfazRegistros.boxUbicaciones.addItem("MEXICO");
		interfazRegistros.boxUbicaciones.addItem("MARRUECOS");
		interfazRegistros.boxUbicaciones.addItem("NORUEGA");
		interfazRegistros.boxUbicaciones.addItem("PARAGUAY");
		interfazRegistros.boxUbicaciones.addItem("PERU");
		interfazRegistros.boxUbicaciones.addItem("POLONIA");
		interfazRegistros.boxUbicaciones.addItem("RUSIA");
		interfazRegistros.boxUbicaciones.addItem("SUECIA");
		interfazRegistros.boxUbicaciones.addItem("SUDAFRICA");
		interfazRegistros.boxUbicaciones.addItem("URUGUAY");
		interfazRegistros.boxUbicaciones.addItem("UCRANIA");
		interfazRegistros.boxUbicaciones.addItem("VENEZUELA");
	}
	
	public Coordinate getCoordenadasFromUbicacion() {
		switch(interfazRegistros.boxUbicaciones.getSelectedItem().toString()) {
		case "ARGENTINA" : return new Coordinate(-34.76, -61.92);
		case "BRASIL" : return new Coordinate(-17.88, -48.32);
		case "COLOMBIA" : return new Coordinate(2.94, -72.75);
		case "CANADA" : return new Coordinate(54.74, -100.35);
		case "ESTADOS UNIDOS" : return new Coordinate(37.33, -93.50);
		case "MEXICO" : return new Coordinate(24.19, -102.30);
		case "PARAGUAY" : return new Coordinate(-23.47, -58.24);
		case "PERU" : return new Coordinate(-10.48, -73.98);
		case "URUGUAY" : return new Coordinate(-33.20, -55.68);
		case "VENEZUELA" : return new Coordinate(7.13, -64.47);
		case "AUSTRALIA" : return new Coordinate(-25.27, 133.77);
		case "ALEMANIA" : return new Coordinate(51.16, 10.45);
		case "CHINA" : return new Coordinate(2.94, -72.75);
		case "ESPANIA" : return new Coordinate(39.32, -4.83);
		case "EGIPTO" : return new Coordinate(26.82, 30.80);
		case "FRANCIA" : return new Coordinate(46.60, 1.88);
		case "FINLANDIA" : return new Coordinate(63.24, 25.92);
		case "INGLATERRA" : return new Coordinate(52.53, -1.26);
		case "SUECIA" : return new Coordinate(59.67, 14.52);
		case "NORUEGA" : return new Coordinate(62.15, 8.787);
		case "MARRUECOS" : return new Coordinate(31.17, -7.33);
		case "POLONIA" : return new Coordinate(52.21, 19.13);
		case "RUSIA" : return new Coordinate(64.68, 97.74);
		case "SUDAFRICA" : return new Coordinate(-28.81, 24.99);
		case "UCRANIA" : return new Coordinate(49.48, 31.27);
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
