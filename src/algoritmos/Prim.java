package algoritmos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import javax.swing.JOptionPane;

import controller.ClaseAuxiliar;
import grafos.Arista;
import grafos.Grafo;

public class Prim {
	
	@SuppressWarnings("unlikely-arg-type")
	public static Grafo crearGrafoPrim(Grafo grafoOriginal) {
		
		
			HashSet<String> vertices = new HashSet<>();
			Grafo nuevoGrafo = new Grafo();
			PriorityQueue<Arista> pq = new PriorityQueue<>(Comparator.comparingInt(Arista::getPeso));
			
			pq.addAll(grafoOriginal.getAristas());
			
			while (vertices.size() < grafoOriginal.tamanio()) {
				Arista aristaPesoMin = pq.poll();
				
				if (aristaPesoMin != null && !vertices.contains(aristaPesoMin.getDestino())) {
					vertices.add(aristaPesoMin.getOrigen().getNombre());
					nuevoGrafo.crearVertice(aristaPesoMin.getOrigen().getNombre(), aristaPesoMin.getOrigen().getCoordenadas());
					vertices.add(aristaPesoMin.getDestino().getNombre());
					nuevoGrafo.crearVertice(aristaPesoMin.getDestino().getNombre(), aristaPesoMin.getDestino().getCoordenadas());
					nuevoGrafo.agregarArista(aristaPesoMin);
					

					for (Arista arista : grafoOriginal.getAristasVecinos(aristaPesoMin.getDestino().getNombre())) {
						if (!vertices.contains(arista.getDestino().getNombre())) {
							pq.offer(arista);
						}
					}
				}
			}
			return nuevoGrafo;
		}
		/*
		ArrayList<String> listaDeVertices = new ArrayList<String>(grafoOriginal.getListaDeVertices().keySet());
		HashSet<String> verticesVisitados = new HashSet<String>();
		Grafo grafoPrim = new Grafo();	
		copiarVerticesFromGrafoOriginal(grafoPrim, grafoOriginal);
		verticesVisitados.add(listaDeVertices.get(0));
		while (verticesVisitados.size() < listaDeVertices.size()){
			int peso = 100;
			String verticeDestino = "";
			String verticeOrigen = "";
			for(String vertice: verticesVisitados) {
				
				for(String arista: grafoOriginal.getListaDeVertices().get(vertice).getListaDeAristas().keySet()) {
					if(grafoOriginal.getListaDeVertices().get(vertice).getListaDeAristas().get(arista) < peso && !verticesVisitados.contains(arista) ) {
						peso = grafoOriginal.getListaDeVertices().get(vertice).getListaDeAristas().get(arista);
						verticeDestino = arista;
						verticeOrigen = vertice;
					}
				}
			}
			
			grafoPrim.agregarArista(verticeOrigen, verticeDestino, peso);
			verticesVisitados.add(verticeDestino);
			//System.out.println(verticeOrigen + " --"+ peso + "--> " + verticeDestino);
			
		}
		return grafoPrim;
	}*/

	
	private static void copiarVerticesFromGrafoOriginal(Grafo grafoPrim, Grafo grafoOriginal) {
		for(String vertice : grafoOriginal.getListaDeVertices().keySet()) {
			grafoPrim.crearVertice(vertice ,grafoOriginal.getListaDeVertices().get(vertice).getCoordenadas() );
		}
	}	
	
}
