package grafos;

public class UnionFind {


	private int[] parent;
	private int[] grade;

	public UnionFind(int tamanio) {
		this.parent = new int[tamanio];
		this.grade = new int [tamanio];
		for (int i = 0; i < tamanio; i++) {
			this.parent[i] = i;
			this.grade[i] = 0;
		}
	}

	public int find(int i) {
		if (i == this.parent[i]) {
			return i;
		}
		return this.parent[i] = find(this.parent[i]);
	}

	public void union(int i, int j) {
		int ri = find(i);
		int rj = find(j);
		if (ri != rj) {
			if (this.grade[ri] > this.grade[rj]) {
				parent[rj] = ri;
			} else if (this.grade[ri] < this.grade[rj]) {
				parent[ri] = rj;
			} else {
				parent[rj] = ri;
				this.grade[ri]++;
			}
		}
	}
}

