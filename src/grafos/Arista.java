package grafos;

public class Arista implements Comparable<Arista> {
	private final Vertice origen;
	private final Vertice destino;
	private final int peso;

	public Arista(Vertice origen, Vertice destino, int peso) {
		verificarParametros(origen, destino, peso);
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
	}

	public Vertice getOrigen() {
		return origen;
	}

	public Vertice getDestino() {
		return destino;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return "{o: "+ origen + ", d: " + destino + ", p: " + peso + "}";
	}

	@Override
	public int compareTo(Arista otra) {
		return Integer.compare(this.peso, otra.peso);
	}

	private void verificarParametros(Vertice val1, Vertice val2, int val3) {
		if (val1 == null || val2 == null || val3 <= 0) {
			throw new IllegalArgumentException("El parametro pasado no puede ser negativo: " + val1 + ", " + val2 + ", " + val3);
		}
	}
}
