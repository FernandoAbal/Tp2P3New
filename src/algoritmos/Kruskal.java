package algoritmos;

import java.util.ArrayList;

import grafos.Arista;
import grafos.Grafo;
import grafos.UnionFind;

public class Kruskal {
	public static Grafo crearKruskal(Grafo g, ArrayList<Arista> aristas) {
		aristas.sort(null);
		Grafo nuevoGrafo = new Grafo();
		UnionFind uf = new UnionFind(g.tamanio());

		for (Arista arista : aristas) {
			System.out.println(arista.getDestino().getId());
			int vert1 = arista.getOrigen().getId();
			int vert2 = arista.getDestino().getId();
			nuevoGrafo.crearVertice(arista.getOrigen().getNombre(), arista.getOrigen().getCoordenadas());
			nuevoGrafo.crearVertice(arista.getDestino().getNombre(), arista.getDestino().getCoordenadas());
			if (uf.find(vert1) != uf.find(vert2)) {
				uf.union(vert1, vert2);
				nuevoGrafo.agregarArista(arista);
			}
		}
		return nuevoGrafo;
	}
}
