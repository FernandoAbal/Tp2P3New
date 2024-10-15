package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafos.Arista;
import grafos.Vertice;

public class AristaTest {

	private Vertice verticeA;
	private Vertice verticeB;
	private Vertice verticeC;
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
		verticeC = new Vertice("C", coordenadasC);

		aristaAB = new Arista(verticeA, verticeB, 5);
		aristaAC = new Arista(verticeA, verticeC, 3);
	}

	@Test
	public void inicializacionAristaTest() {
		assertEquals(verticeA, aristaAB.getOrigen());
		assertEquals(verticeB, aristaAB.getDestino());
		assertEquals(5, aristaAB.getPeso());
	}

	@Test
	public void toStringTest() {
		String expectedString = "{o: " + verticeA + ", d: " + verticeB + ", p: 5}";
		assertEquals(expectedString, aristaAB.toString());
	}

	@Test
	public void compareToTest() {
		Arista aristaMayorPeso = new Arista(verticeA, verticeB, 7);

		assertTrue(aristaAC.compareTo(aristaAB) < 0);
		assertTrue(aristaAB.compareTo(aristaMayorPeso) < 0);
		assertTrue(aristaMayorPeso.compareTo(aristaAB) > 0);
		assertTrue(aristaAB.compareTo(new Arista(verticeA, verticeB, 5)) == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void excepcionParaOrigenNuloTest() {
		new Arista(null, verticeB, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void excepcionParaDestinoNuloTest() {
		new Arista(verticeA, null, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void excepcionParaPesoNegativoOCeroTest() {
		new Arista(verticeA, verticeB, 0);
	}

	@Test
	public void parametrosValidosTest() {
		Arista aristaValida = new Arista(verticeA, verticeB, 5);
		assertNotNull(aristaValida);
	}

	@Test
	public void getOrigenYDestinoTest() {
		assertEquals(verticeA, aristaAB.getOrigen());
		assertEquals(verticeB, aristaAB.getDestino());
	}

	@Test
	public void compararMismaAristaConDiferentePesoTest() {
		Arista aristaDiferentePeso = new Arista(verticeA, verticeB, 7);

		assertTrue(aristaAB.compareTo(aristaDiferentePeso) < 0);
	}

	@Test
	public void igualdadAristasTest() {
		Arista aristaIgual = new Arista(verticeA, verticeB, 5);

		assertTrue(aristaAB.compareTo(aristaIgual) == 0);
		assertEquals(aristaAB.getOrigen(), aristaIgual.getOrigen());
		assertEquals(aristaAB.getDestino(), aristaIgual.getDestino());
	}

}