package projects.proj03;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.stream.IntStream;

class HamiltonianCircuits {
	private LinkedList<Node> queue = new LinkedList<>();
	private int minLength = Integer.MAX_VALUE;
	private int n;
	private LinkedList<Integer> optPath;
	private int W[][];
	
	HamiltonianCircuits(int matrix[][]) {
		this.W = matrix;
		n = W.length;
		filter();
	}
	
	private void filter() {
		if (n > 2)
			travel();
		else if (n == 2) {
			Node u = new Node();
			IntStream.of(0, 1, 0).forEach(it -> u.path.add(it));
			minLength = length(u);
			optPath = new LinkedList<>(u.path);
		} else {
			minLength = 0;
			optPath = new LinkedList<>();
		}
	}
	
	int getMinLength() {
		return this.minLength;
	}
	
	LinkedList<Integer> getOptPath() {
		return this.optPath;
	}
	
	private void travel() {
		Node v = new Node();
		Node u = new Node();
		v.level = 0;
		v.path.add(0);
		v.bound = bound(v);
		queue.add(v);
		while (!queue.isEmpty()) {
			v = queue.remove();
			if (v.bound < minLength) {
				u.level = v.level + 1;
				int i = 0;
				boolean visited[] = new boolean[n];
				for (int el : v.path)
					visited[el] = true;
				while (++i < n) {
					if (!visited[i]) {
						u.path = new LinkedList<>(v.path);
						u.path.add(i);
						if (u.level == n - 2) {
							u.path.add(remaining(u.path));
							u.path.add(0);
							int len = length(u);
							if (len < minLength) {
								minLength = len;
								optPath = new LinkedList<>(u.path);
							}
						} else {
							u.bound = bound(u);
							if (u.bound < minLength) {
								if (queue.isEmpty())
									queue.addFirst(new Node(u));
								else {
									ListIterator<Node> it = queue.listIterator();
									boolean sw = true;
									while (sw && it.hasNext()) {
										Node curr = it.next();
										if (curr.bound > u.bound) {
											it.previous();
											it.add(new Node(u));
											sw = false;
										}
									}
									if (sw)
										queue.addLast(new Node(u));
								}
							}
						}
					}
				}
			} else break;
		}
	}
	
	private int remaining(LinkedList<Integer> path) {
		int sum = 0;
		for (int el : path)
			sum += el;
		
		return n * (n - 1) / 2 - sum;
	}
	
	private int length(Node u) {
		Iterator<Integer> it = u.path.iterator();
		int i = it.next();
		int sum = 0;
		while (it.hasNext()) {
			int j = it.next();
			sum += W[i][j];
			i = j;
		}
		return sum;
	}
	
	private int bound(Node u) {
		int visited[] = new int[n];
		for (int el : u.path)
			visited[el] = 1;
		int sum = 0;
		LinkedList<Integer> notVisited = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) notVisited.add(i);
		}
		LinkedList<Integer> list = new LinkedList<>(notVisited);
		list.add(0);
		for (int el : notVisited) {
			sum += getMinValue(el, list);
		}
		sum += getMinValue(u.path.getLast(), notVisited);
		sum += length(u);
		
		return sum;
	}
	
	private int getMinValue(int i, LinkedList<Integer> list) {
		int min = Integer.MAX_VALUE;
		for (int el : list)
			if (el != i && W[i][el] < min)
				min = W[i][el];
		
		return min;
	}
	
}

class Node {
	int level;
	LinkedList<Integer> path = new LinkedList<>();
	int bound;
	
	Node() {
	}
	
	Node(Node u) {
		this.level = u.level;
		this.path = new LinkedList<>(u.path);
		this.bound = u.bound;
	}
}