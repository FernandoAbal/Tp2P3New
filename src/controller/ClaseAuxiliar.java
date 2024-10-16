package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import grafos.Grafo;

public class ClaseAuxiliar {
	
	
	private static ArrayList<String> verticesVisitados; 
	private static ArrayList<String> listaDeVertices;
	

	
	
	public static Set<String> verticesAlcanzables(Grafo grafo, String vertice) {
		Set<String> setAlcanzables = new HashSet<String>();
		listaDeVertices = new ArrayList<String>();
		listaDeVertices.add(vertice);
		verticesVisitados = new ArrayList<String>();
		while (listaDeVertices.size() > 0) {
			verticesVisitados.add(listaDeVertices.get(0));
			setAlcanzables.add(listaDeVertices.get(0));
			agregarVecinosPendientes(grafo, listaDeVertices.get(0));
			listaDeVertices.remove(0);
		}
		return setAlcanzables;
	}
	
	public static Set<String> verticesAlcanzablesVerticeRandom(Grafo grafo) {
		Set<String> setAlcanzables = new HashSet<String>();
		listaDeVertices = new ArrayList<String>();
		verticesVisitados = new ArrayList<String>();
		int i = 0;
		for(String s: grafo.getListaDeVertices().keySet()) {
			listaDeVertices.add(s);
			i++;
			if(i == 1) {
				break;
			}
		}
		while (listaDeVertices.size() > 0) {
			verticesVisitados.add(listaDeVertices.get(0));
			setAlcanzables.add(listaDeVertices.get(0));
			agregarVecinosPendientes(grafo, listaDeVertices.get(0));
			listaDeVertices.remove(0);
		}
		return setAlcanzables;
	}
	
	
	
	private static void agregarVecinosPendientes(Grafo grafo, String vertice) {
		for (String verticeVecino : grafo.getListaDeVertices().get(vertice).listaDeVecinos()) {
			if (!verticesVisitados.contains(verticeVecino) && !listaDeVertices.contains(verticeVecino)) {
				listaDeVertices.add(verticeVecino);
				verticesVisitados.add(verticeVecino);
			}
		}
	}
	
	public static boolean esGrafoConexo(Grafo grafo) {
		return grafo.getListaDeVertices().size() == verticesAlcanzablesVerticeRandom(grafo).size() ;
		
	}
	
}


	


