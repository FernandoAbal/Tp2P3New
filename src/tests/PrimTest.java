package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import algoritmos.Prim;
import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;

import java.util.ArrayList;

public class PrimTest {

	@Test
	public void testPrim() {
		Vertice verticeA = new Vertice("A", new Coordinate(0, 0));
		Vertice verticeB = new Vertice("B", new Coordinate(1, 1));
		Vertice verticeC = new Vertice("C", new Coordinate(2, 2));
		Vertice verticeD = new Vertice("D", new Coordinate(3, 3));

		Grafo grafoInicial = new Grafo();
		grafoInicial.crearVertice(verticeA.getNombre(), verticeA.getCoordenadas());
		grafoInicial.crearVertice(verticeB.getNombre(), verticeB.getCoordenadas());
		grafoInicial.crearVertice(verticeC.getNombre(), verticeC.getCoordenadas());
		grafoInicial.crearVertice(verticeD.getNombre(), verticeD.getCoordenadas());

		grafoInicial.agregarArista("A", "B", 1);
		grafoInicial.agregarArista("A", "C", 4);
		grafoInicial.agregarArista("B", "C", 2);
		grafoInicial.agregarArista("B", "D", 5);
		grafoInicial.agregarArista("C", "D", 3);

		Grafo grafoResultado = Prim.crearGrafoPrim(grafoInicial);

		// verifica que el numero de aristas es igual al numero de vertices -1
		assertEquals(3, grafoResultado.getAristas().size());

		assertTrue(grafoResultado.getAristas().contains(new Arista(verticeA, verticeB, 1)));
		assertTrue(grafoResultado.getAristas().contains(new Arista(verticeB, verticeC, 2)));
		assertTrue(grafoResultado.getAristas().contains(new Arista(verticeC, verticeD, 3)));

		assertFalse(grafoResultado.getAristas().contains(new Arista(verticeA, verticeC, 4)));
		assertFalse(grafoResultado.getAristas().contains(new Arista(verticeB, verticeD, 5)));
	}
}