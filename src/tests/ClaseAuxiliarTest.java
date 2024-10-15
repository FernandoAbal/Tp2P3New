
package tests;

//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.function.BooleanSupplier;
//
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//
//import controller.ClaseAuxiliar;
//import grafos.Grafo;
//
//public class ClaseAuxiliarTest {
//
//	@Test
//	public void verticesAlcanzablesTest() {
//		Grafo grafo = crearGrafoDePrueba();
//		Set<String> esperado = new HashSet<String>();
//		esperado.add("A");
//		esperado.add("B");
//		esperado.add("C");
//		esperado.add("E");
//		esperado.add("F");
//		
//		
//		assertTrue(listasSonIguales( ClaseAuxiliar.verticesAlcanzables(grafo, "A"), esperado));
//	}
//	
//	
//
//	@Test
//	public void verticesAlcanzablesFalseTest() {
//		Grafo grafo = crearGrafoDePrueba();
//		Set<String> esperado = new HashSet<String>();
//		esperado.add("A");
//		esperado.add("B");
//	
//		assertFalse(listasSonIguales( ClaseAuxiliar.verticesAlcanzables(grafo, "A"), esperado));
//	}
//	
//	
//	
//	private boolean listasSonIguales(Set<String> verticesAlcanzables, Set<String> esperado) {
//		boolean flag = true;
//		for(String s: esperado) {
//			flag &= verticesAlcanzables.contains(s);
//		}
//		for(String s: verticesAlcanzables) {
//			flag &= esperado.contains(s);
//		}
//		return flag;
//	}
//
//	private Grafo crearGrafoDePrueba() {
//		Grafo grafo = new Grafo();
//		grafo.crearVertice("A",null);
//		grafo.crearVertice("B", null);
//		grafo.crearVertice("C",null);
//		grafo.crearVertice("D",null);
//		grafo.crearVertice("E",null);
//		grafo.crearVertice("F",null);
//		
//		grafo.agregarArista("A", "B", 1);
//		grafo.agregarArista("A", "C", 2);
//		grafo.agregarArista("A", "E", 1);
//		grafo.agregarArista("A", "F", 2);
//	
//		
//		return grafo;
//	}
//	
//	
//}
