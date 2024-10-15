package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import grafos.UnionFind;

public class UnionFindTest {

	private UnionFind unionFind;

	@Before
	public void setUp() {
		unionFind = new UnionFind(10);
	}

	@Test
	public void unionTest() {
		unionFind.union(0, 1);
		unionFind.union(1, 2);

		assertEquals(unionFind.find(0), unionFind.find(1));
		assertEquals(unionFind.find(0), unionFind.find(2));

		assertNotEquals(unionFind.find(0), unionFind.find(3));
	}

	@Test
	public void findTest() {
		unionFind.union(0, 1);
		unionFind.union(1, 2);

		assertEquals(0, unionFind.find(2));
		assertEquals(0, unionFind.find(1));
		assertEquals(0, unionFind.find(0));
		assertEquals(3, unionFind.find(3));
	}
}