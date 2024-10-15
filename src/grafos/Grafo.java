package grafos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Grafo {
	
	private HashMap<String, Vertice> listaDeVertices;
	private ArrayList<Arista> aristas;
	
	public Grafo() {
		this.aristas = new ArrayList<Arista>();
		this.listaDeVertices = new HashMap<String, Vertice>();
	}
	
	public void crearVertice(String nombre, Coordinate coord) {
		if(!listaDeVertices.containsKey(nombre)) {
			listaDeVertices.put(nombre, new Vertice(nombre, coord));
		}
		
	}
	
	public void agregarArista(String origen, String destino, int peso) {
		aristas.add(new Arista(listaDeVertices.get(origen),listaDeVertices.get(destino),peso));
		listaDeVertices.get(origen).agregarArista(destino, new Arista(listaDeVertices.get(origen),listaDeVertices.get(destino),peso));
		listaDeVertices.get(destino).agregarArista(origen, new Arista(listaDeVertices.get(origen),listaDeVertices.get(destino),peso));
	}
	
	//EN ESTE METODO PUEDE EXISTIR UN ERROR DE DOBLE CARGA revisar mas tarde
	
	
	
	
	
	

	public void agregarArista(Arista arista) {
		aristas.add(arista);
		listaDeVertices.get(arista.getOrigen().getNombre()).agregarArista(arista.getDestino().getNombre(), arista);
		listaDeVertices.get(arista.getDestino().getNombre()).agregarArista(arista.getOrigen().getNombre(), arista);
	}
	
	
	public void quitarArista(String origen, String destino) {
		listaDeVertices.get(origen).quitarArista(destino);
		listaDeVertices.get(destino).quitarArista(origen);
	}
	
	public void eliminarVertice(String origen) {
		for(String s: listaDeVertices.keySet()) {
			listaDeVertices.get(s).quitarArista(origen);
		}
		listaDeVertices.remove(origen);
	}
	
	public String getVerticeConAristaMenorPeso(String verticeOrigen) {
		return listaDeVertices.get(verticeOrigen).getVerticeConAristaMenorPeso();
	}
	
	
	public String getInfoVertices() {
		StringBuilder sb = new StringBuilder();
		for(String s: listaDeVertices.keySet()) {
			sb.append(listaDeVertices.get(s).getInfo());
			sb.append("----------------\n");
		}
		return sb.toString();
	}

	public HashMap<String, Vertice> getListaDeVertices() {
		return listaDeVertices;
	}
	
	public int tamanio() {
		return listaDeVertices.size();
	}
	public ArrayList<Arista> getAristas(){
		return aristas;
	}

	public Collection<Arista> getAristasVecinos(String vertice) {
		Vertice vert = listaDeVertices.get(vertice);
		return vert.getListaDeAristas().values();
	}
	
//	public static long tiempoEjecucionPrim(Grafo grafo) {
//		long tiempoInicio, tiempoFin;
//
//		tiempoInicio = System.nanoTime();
//		Prim(grafo);
//		tiempoFin = System.nanoTime();
//
//		return tiempoFin - tiempoInicio;
//	}
//
//	public static long tiempoEjecucionKruskal(Grafo grafo) {
//		long tiempoInicio, tiempoFin;
//
//		List<Arista> aristas = vertice.getListaDeAristas();
//		tiempoInicio = System.nanoTime();
//
//		Kruskal(grafo, aristas);
//		tiempoFin = System.nanoTime();
//
//		return tiempoFin - tiempoInicio;
//	}
	
}
