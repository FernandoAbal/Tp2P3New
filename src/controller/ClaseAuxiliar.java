package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import grafos.Grafo;

public class ClaseAuxiliar {
	

	
	
	public static Set<String> verticesAlcanzables(Grafo grafo, String vertice) {
		Set<String> setAlcanzables = new HashSet<String>();
		ArrayList<String> vertices = new ArrayList<String>();
		vertices.add(vertice);
		ArrayList<String> visitados = new ArrayList<String>();
		while (vertices.size() > 0) {
			visitados.add(vertices.get(0));
			setAlcanzables.add(vertices.get(0));
			agregarVecinosPendientes(grafo, vertices.get(0), visitados, vertices);
			vertices.remove(0);
		}
		return setAlcanzables;
	}
	
	public static Set<String> verticesAlcanzablesVerticeRandom(Grafo grafo) {
		Set<String> setAlcanzables = new HashSet<String>();
		ArrayList<String> vertices = new ArrayList<String>();
		ArrayList<String> visitados = new ArrayList<String>();
		int i = 0;
		for(String s: grafo.getListaDeVertices().keySet()) {
			vertices.add(s);
			i++;
			if(i == 1) {
				break;
			}
		}
		while (vertices.size() > 0) {
			visitados.add(vertices.get(0));
			setAlcanzables.add(vertices.get(0));
			agregarVecinosPendientes(grafo, vertices.get(0), visitados, vertices);
			vertices.remove(0);
		}
		return setAlcanzables;
	}
	
	
	
	private static void agregarVecinosPendientes(Grafo grafo, String vertice, ArrayList<String> verticesVisitados, ArrayList<String> listaDeVertices) {
			for (String verticeVecino : grafo.getListaDeVertices().get(vertice).listaDeVecinos()) {
				if (!verticesVisitados.contains(verticeVecino) && !listaDeVertices.contains(verticeVecino)) {
					listaDeVertices.add(verticeVecino);
					verticesVisitados.add(verticeVecino);}
			}
		}
	}


	


