package algoritmos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

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
	
}
