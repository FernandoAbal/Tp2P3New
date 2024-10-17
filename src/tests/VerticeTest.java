package tests;

import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafos.Arista;
import grafos.Vertice;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class VerticeTest {

	private Vertice verticeA;
	private Vertice verticeB;
	private Arista aristaAB;
	private Arista aristaAC;
	private Coordinate coordenadasA;
	private Coordinate coordenadasB;
	private Coordinate coordenadasC;

	@Before
	public void setUp() {

		coordenadasA = new Coordinate(10, 20);
		coordenadasB = new Coordinate(15, 25);
		coordenadasC = new Coordinate(20, 30);

		verticeA = new Vertice("A", coordenadasA);
		verticeB = new Vertice("B", coordenadasB);

		aristaAB = new Arista(verticeA, verticeB, 5); // Peso 5
		aristaAC = new Arista(verticeA, new Vertice("C", coordenadasC), 3); // Peso 3
	}

	@Test
	public void testInicializacionVertice() {
		Vertice verticeC = new Vertice("C", coordenadasC);

		assertEquals("C", verticeC.getNombre());
		assertTrue(verticeC.getListaDeAristas().isEmpty());
		assertEquals(coordenadasC, verticeC.getCoordenadas());
	}

	@Test
	public void agregarAristaTest() {
		verticeA.agregarArista(verticeB.getNombre(), aristaAB);

		assertTrue(verticeA.contieneArista(verticeB.getNombre()));
		assertEquals(1, verticeA.getListaDeAristas().size());
	}

	@Test
	public void quitarAristaTest() {
		verticeA.agregarArista(verticeB.getNombre(), aristaAB);
		verticeA.quitarArista(verticeB.getNombre());

		assertFalse(verticeA.contieneArista(verticeB.getNombre()));
		assertEquals(0, verticeA.getListaDeAristas().size());
	}

	@Test
	public void contieneAristaTest() {
		verticeA.agregarArista(verticeB.getNombre(), aristaAB);

		assertTrue(verticeA.contieneArista(verticeB.getNombre()));
		assertFalse(verticeA.contieneArista("C")); 
	}

	@Test
	public void listaDeVecinosTest() {
		verticeA.agregarArista(verticeB.getNombre(), aristaAB);
		verticeA.agregarArista("C", aristaAC);

		ArrayList<String> vecinos = verticeA.listaDeVecinos();
		assertEquals(2, vecinos.size());
		assertTrue(vecinos.contains("B"));
		assertTrue(vecinos.contains("C"));
	}

	@Test
	public void verticeConAristaMenorPesoTest() {
		verticeA.agregarArista(verticeB.getNombre(), aristaAB);
		verticeA.agregarArista("C", aristaAC);

		assertEquals("C", verticeA.getVerticeConAristaMenorPeso());
	}

	@Test
	public void getListaTest() {
		String expected = "B, peso de la arista: -> {o: A, d: B, p: 2}\n"
				+ "C, peso de la arista: -> {o: A, d: C, p: 2}\n" + "Arista menor peso: B-> {o: A, d: B, p: 2}\n";

		assertEquals(expected, verticeA.getLista());
	}

	@Test
	public void getInfoTest() {
		verticeA.agregarArista(verticeB.getNombre(), aristaAB);
		verticeA.agregarArista("C", aristaAC);

		String info = verticeA.getInfo();
		assertTrue(info.contains("A"));
		assertTrue(info.contains("peso de la arista"));
		assertTrue(info.contains("Arista menor peso: C"));
	}

	@Test
	public void idTest() {
		Vertice verticeC = new Vertice("C", coordenadasC);
		Vertice verticeD = new Vertice("D", new Coordinate(25, 35));

		assertNotEquals(verticeC.getId(), verticeD.getId());
	}

	@Test
	public void coordenadasTest() {
		verticeA.setCoordenadas(coordenadasA);

		assertEquals(coordenadasA, verticeA.getCoordenadas());
	}

}