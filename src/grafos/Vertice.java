package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import grafos.Arista;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Vertice {

	private static int contador = 0; // Variable estática para autoincrementar el ID
	private int id; // ID único para cada vértice
	private String nombre;
	private Coordinate coordenadas;
	private HashMap<String, Arista> enlacesDisponibles;

	public Vertice(String nombre, Coordinate coordenadas) {
		this.id = contador;
		contador++;
		this.coordenadas = coordenadas;
		this.enlacesDisponibles = new HashMap<String, Arista>();
		this.nombre = nombre;
	}

	public void agregarArista(String verticeDestino, Arista pesoEnlaceToDestino) {
		enlacesDisponibles.put(verticeDestino, pesoEnlaceToDestino);
	}

	public void quitarArista(String verticeDestino) {
		if (enlacesDisponibles.containsKey(verticeDestino)) {
			enlacesDisponibles.remove(verticeDestino);
		}
	}

	public boolean contieneArista(String verticeDestino) {
		return enlacesDisponibles.containsKey(verticeDestino);
	}

	public ArrayList<String> listaDeVecinos() {
		ArrayList<String> arrayDeVecinos = new ArrayList<String>(enlacesDisponibles.keySet());
		return arrayDeVecinos;
	}

	public String getVerticeConAristaMenorPeso() {
		int menorPeso = 100;
		String enlaceConMenorPeso = "";
		for (String s : enlacesDisponibles.keySet()) {
			if (enlacesDisponibles.get(s).getPeso() < menorPeso) {
				menorPeso = enlacesDisponibles.get(s).getPeso();
				enlaceConMenorPeso = s;
			}
		}
		return enlaceConMenorPeso;
	}

	public String getNombre() {
		return nombre;
	}

	public HashMap<String, Arista> getListaDeAristas() {
		return enlacesDisponibles;
	}

	public Coordinate getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordinate coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getLista() {
		StringBuilder sb = new StringBuilder();
		for (String s : enlacesDisponibles.keySet()) {
			sb.append(s + ", peso de la arista: -> " + enlacesDisponibles.get(s).getPeso() + "\n");
		}
		sb.append("Arista menor peso: " + getVerticeConAristaMenorPeso() + "-> "
				+ enlacesDisponibles.get(getVerticeConAristaMenorPeso()).getPeso() + "\n");
		return sb.toString();
	}

	public String getInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append(nombre + "\n" + "Coordenadas: " + coordenadas + "\n");
		sb.append(getLista());
		return sb.toString();
	}

	public int getId() {
		return id;
	}

}