package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import algoritmos.Kruskal;
import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;

import java.util.ArrayList;

public class KruskalTest {

	private Grafo grafo;
	private ArrayList<Arista> aristas;

	@Before
	public void setUp() {
		grafo = new Grafo();
		aristas = new ArrayList<>();

		grafo.crearVertice("A", new Coordinate(0, 0));
		grafo.crearVertice("B", new Coordinate(1, 1));
		grafo.crearVertice("C", new Coordinate(2, 2));
		grafo.crearVertice("D", new Coordinate(3, 3));

		aristas.add(new Arista(new Vertice("A", new Coordinate(0, 0)), new Vertice("B", new Coordinate(1, 1)), 4));
		aristas.add(new Arista(new Vertice("A", new Coordinate(0, 0)), new Vertice("C", new Coordinate(2, 2)), 3));
		aristas.add(new Arista(new Vertice("B", new Coordinate(1, 1)), new Vertice("C", new Coordinate(2, 2)), 2));
		aristas.add(new Arista(new Vertice("C", new Coordinate(2, 2)), new Vertice("D", new Coordinate(3, 3)), 1));
		aristas.add(new Arista(new Vertice("B", new Coordinate(1, 1)), new Vertice("D", new Coordinate(3, 3)), 5));
	}
	
	//falta implementar

}