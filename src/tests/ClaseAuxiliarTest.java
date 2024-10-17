package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import controller.ClaseAuxiliar;
import grafos.Grafo;

import java.util.HashSet;
import java.util.Set;

public class ClaseAuxiliarTest {

    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo();
        grafo.crearVertice("A", new Coordinate(0, 0));
        grafo.crearVertice("B", new Coordinate(1, 1));
        grafo.crearVertice("C", new Coordinate(2, 2));
        grafo.crearVertice("D", new Coordinate(3, 3));

        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("B", "C", 2);
        grafo.agregarArista("C", "D", 3);
    }

    @Test
    public void testVerticesAlcanzables() {
        Set<String> alcanzablesEsperados = new HashSet<>();
        alcanzablesEsperados.add("A");
        alcanzablesEsperados.add("B");
        alcanzablesEsperados.add("C");
        
        Set<String> alcanzables = ClaseAuxiliar.verticesAlcanzables(grafo, "A");
        assertEquals(alcanzablesEsperados, alcanzables);
    }

    @Test
    public void testVerticesAlcanzablesVerticeRandom() {
        Set<String> alcanzables = ClaseAuxiliar.verticesAlcanzablesVerticeRandom(grafo);
        assertFalse("Debe devolver al menos un vértice alcanzable", alcanzables.isEmpty());
    }

    @Test
    public void testAgregarVecinosPendientes() {
        Set<String> alcanzablesEsperados = new HashSet<>();
        alcanzablesEsperados.add("B");
        alcanzablesEsperados.add("C");

        ClaseAuxiliar.verticesAlcanzables(grafo, "B");
        Set<String> alcanzables = ClaseAuxiliar.verticesAlcanzables(grafo, "B");

     // Probar agregar los vecinos de b
        assertEquals(alcanzablesEsperados, alcanzables);
    }

    @Test
    public void testEsGrafoConexo() {
        assertTrue("El grafo debe ser conexo", ClaseAuxiliar.esGrafoConexo(grafo));

        grafo.quitarArista("C", "D");
        assertFalse("El grafo no debe ser conexo", ClaseAuxiliar.esGrafoConexo(grafo));
    }
}