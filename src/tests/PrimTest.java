package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import algoritmos.Prim;
import grafos.Arista;
import grafos.Grafo;

import java.util.ArrayList;

public class PrimTest {

    private Grafo grafoOriginal;
    private Grafo grafoResultante;

    @Before
    public void setUp() {
        grafoOriginal = new Grafo();
        
        grafoOriginal.crearVertice("A", new Coordinate(0, 0));
        grafoOriginal.crearVertice("B", new Coordinate(1, 1));
        grafoOriginal.crearVertice("C", new Coordinate(2, 2));
        grafoOriginal.crearVertice("D", new Coordinate(3, 3));

        grafoOriginal.agregarArista("A", "B", 1);
        grafoOriginal.agregarArista("A", "C", 4);
        grafoOriginal.agregarArista("B", "C", 2);
        grafoOriginal.agregarArista("B", "D", 5);
        grafoOriginal.agregarArista("C", "D", 3);
    }

    //falta implementar
   

}