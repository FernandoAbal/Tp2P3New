package tests;

import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GrafoTest {

	private Grafo grafo;
	private Coordinate coordenadasA;
	private Coordinate coordenadasB;
	private Coordinate coordenadasC;

	@Before
	public void setUp() {
		grafo = new Grafo();

		coordenadasA = new Coordinate(10, 20);
		coordenadasB = new Coordinate(15, 25);
		coordenadasC = new Coordinate(20, 30);
	}

	@Test
	public void crearVerticeTest() {
		grafo.crearVertice("A", coordenadasA);
		assertTrue(grafo.getListaDeVertices().containsKey("A"));
		assertEquals(1, grafo.tamanio());
	}

	@Test
	public void crearVerticeDuplicadoTest() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("A", coordenadasA);
		assertEquals(1, grafo.tamanio());
	}

	@Test
	public void agregarArista() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("B", coordenadasB);
		grafo.agregarArista("A", "B", 10);

		ArrayList<Arista> aristas = grafo.getAristas();
		assertEquals(1, aristas.size());
		Arista arista = aristas.get(0);
		assertEquals("A", arista.getOrigen().getNombre());
		assertEquals("B", arista.getDestino().getNombre());
		assertEquals(10, arista.getPeso());
	}

	@Test
	public void quitarAristaTest() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("B", coordenadasB);
		grafo.agregarArista("A", "B", 10);
		grafo.quitarArista("A", "B");

		assertFalse(grafo.getListaDeVertices().get("A").contieneArista("B"));
		assertFalse(grafo.getListaDeVertices().get("B").contieneArista("A"));
	}

	@Test
	public void eliminarVerticeTest() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("B", coordenadasB);
		grafo.agregarArista("A", "B", 10);

		grafo.eliminarVertice("A");
		assertFalse(grafo.getListaDeVertices().containsKey("A"));
		assertFalse(grafo.getListaDeVertices().get("B").contieneArista("A"));
	}

	@Test
	public void getVerticeConAristaMenorPesoTest() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("B", coordenadasB);
		grafo.crearVertice("C", coordenadasC);
		grafo.agregarArista("A", "B", 10);
		grafo.agregarArista("A", "C", 5);

		String menorPeso = grafo.getVerticeConAristaMenorPeso("A");
		assertEquals("C", menorPeso);
	}

	@Test
	public void infoVerticesTest() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("B", coordenadasB);

		String info = grafo.getInfoVertices();
		assertTrue(info.contains("A"));
		assertTrue(info.contains("B"));
	}

	@Test
	public void getListaDeVerticesTest() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("B", coordenadasB);
		grafo.crearVertice("C", coordenadasC);

		HashMap<String, Vertice> vertices = grafo.getListaDeVertices();
		assertEquals(3, vertices.size());
		assertTrue(vertices.containsKey("A"));
		assertTrue(vertices.containsKey("B"));
		assertTrue(vertices.containsKey("C"));
	}

	@Test
	public void testTamanio() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("B", coordenadasB);
		assertEquals(2, grafo.tamanio());
	}

	@Test
	public void getAristasTest() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("B", coordenadasB);
		grafo.agregarArista("A", "B", 10);

		ArrayList<Arista> aristas = grafo.getAristas();
		assertEquals(1, aristas.size());
		assertEquals("A", aristas.get(0).getOrigen().getNombre());
		assertEquals("B", aristas.get(0).getDestino().getNombre());
		assertEquals(10, aristas.get(0).getPeso());
	}

	@Test
	public void getAristasVecinosTest() {
		grafo.crearVertice("A", coordenadasA);
		grafo.crearVertice("B", coordenadasB);
		grafo.crearVertice("C", coordenadasC);
		grafo.agregarArista("A", "B", 10);
		grafo.agregarArista("A", "C", 15);

		Collection<Arista> vecinos = grafo.getAristasVecinos("A");
		assertEquals(2, vecinos.size());
	}


}