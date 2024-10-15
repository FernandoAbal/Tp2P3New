
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import grafos.Grafo;

public class GrafoTest {
	
	@Test
	public void agregarVerticeTest() {
		Grafo grafo = new Grafo();
		grafo.crearVertice("1",null);
		assertTrue(grafo.getListaDeVertices().containsKey("1"));
		                                                                                             
	}
	
	@Test
	public void eliminarVerticeTest() {
		Grafo grafo = new Grafo();
		grafo.crearVertice("1",null);
		grafo.crearVertice("2",null);
		grafo.eliminarVertice("1");
		assertFalse(grafo.getListaDeVertices().containsKey("1"));                                                                                   
	}
	@Test
	public void agregarAristaTest() {
		Grafo grafo = new Grafo();
		grafo.crearVertice("1",null);
		grafo.crearVertice("2",null);
		grafo.agregarArista("2", "1", 1);
		assertTrue(grafo.getListaDeVertices().get("1").contieneArista("2"));
		assertTrue(grafo.getListaDeVertices().get("2").contieneArista("1"));  
	}
	
	@Test
	public void eliminarAristaTest() {
		Grafo grafo = new Grafo();
		grafo.crearVertice("1",null);
		grafo.crearVertice("2",null);
		grafo.agregarArista("2", "1", 1);
		grafo.quitarArista("1", "2");
		assertFalse(grafo.getListaDeVertices().get("1").contieneArista("2"));
		assertFalse(grafo.getListaDeVertices().get("2").contieneArista("1"));  
	}
	
	
	@Test
	public void getAristaMenorPesoTest() {
		Grafo grafo = crearGrafoPrueba();
		assertEquals(grafo.getVerticeConAristaMenorPeso("1"), "2");                                                                                   
	}
	
	@Test
	public void getVerticeMenorPesoPostEliminacionAristaTest() {
		Grafo grafo = crearGrafoPrueba();
		grafo.quitarArista("1", "2");
		assertEquals(grafo.getVerticeConAristaMenorPeso("1"), "4");                                                                                   
	}
	
	@Test
	public void getVerticeMenorPesoPostEliminacionVerticeTest() {
		Grafo grafo = crearGrafoPrueba();
		grafo.eliminarVertice("2");
		assertEquals(grafo.getVerticeConAristaMenorPeso("1"), "4");                                                                                   
	}	
	
	
	@Before
	private Grafo crearGrafoPrueba() {
		Grafo grafo = new Grafo();
		grafo.crearVertice("1",null);
		grafo.crearVertice("2",null);
		grafo.crearVertice("3",null);
		grafo.crearVertice("4",null);
		grafo.agregarArista("1", "2", 1);
		grafo.agregarArista("1", "3", 5);
		grafo.agregarArista("1", "4", 4);
		return grafo;
	}
	

}
