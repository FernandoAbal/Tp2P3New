package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import algoritmos.Kruskal;
import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;

import java.util.ArrayList;

public class KruskalTest {

    @Test
    public void testKruskal() {
        Vertice verticeA = new Vertice("A", new Coordinate(0, 0));
        Vertice verticeB = new Vertice("B", new Coordinate(1, 1));
        Vertice verticeC = new Vertice("C", new Coordinate(2, 2));
        Vertice verticeD = new Vertice("D", new Coordinate(3, 3));

        Arista aristaAB = new Arista(verticeA, verticeB, 1);
        Arista aristaAC = new Arista(verticeA, verticeC, 2);
        Arista aristaBD = new Arista(verticeB, verticeD, 3);
        Arista aristaCD = new Arista(verticeC, verticeD, 4);

        Grafo grafoInicial = new Grafo();
        grafoInicial.crearVertice(verticeA.getNombre(), verticeA.getCoordenadas());
        grafoInicial.crearVertice(verticeB.getNombre(), verticeB.getCoordenadas());
        grafoInicial.crearVertice(verticeC.getNombre(), verticeC.getCoordenadas());
        grafoInicial.crearVertice(verticeD.getNombre(), verticeD.getCoordenadas());

        ArrayList<Arista> aristas = new ArrayList<>();
        aristas.add(aristaAB);
        aristas.add(aristaAC);
        aristas.add(aristaBD);
        aristas.add(aristaCD);

        Grafo grafoResultado = Kruskal.crearKruskal(grafoInicial, aristas);
        assertEquals(3, grafoResultado.getAristas().size());

        assertTrue(grafoResultado.getAristas().contains(aristaAB));
        assertTrue(grafoResultado.getAristas().contains(aristaAC));
        assertTrue(grafoResultado.getAristas().contains(aristaBD));

        assertFalse(grafoResultado.getAristas().contains(aristaCD));
    }
}