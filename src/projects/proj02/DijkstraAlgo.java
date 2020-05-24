package projects.proj02;

import java.util.Iterator;
import java.util.LinkedList;

public class DijkstraAlgo {
	
	public static void main(String[] args) {
	}
	
	public static void matrixSolution(int matrix[][], int parents[], int from) {
		int len = matrix.length;
		boolean added[] = new boolean[len];
		added[from] = true;
		int minVertex = from;
		
		for (int j = 0; j < len; j++) {
			parents[j] = from;
		}
		
		for (int i = 0; i < len - 1; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < len; j++) {
				if (!added[j])
					if (min > matrix[from][j]) {
						min = matrix[from][j];
						minVertex = j;
					}
			}
			added[minVertex] = true;
			for (int j = 0; j < len; j++) {
				if (matrix[from][j] > matrix[from][minVertex] + matrix[minVertex][j]) {
					matrix[from][j] = matrix[from][minVertex] + matrix[minVertex][j];
					parents[j] = minVertex;
				}
			}
		}
	}
	
	public static void linkedlistSolution(LinkedList<LinkedList<Pair>> head, int parents[], int from) {
		int len = head.size();
		boolean added[] = new boolean[len];
		added[from] = true;
		int minVertex = from;
		
		for (int j = 0; j < len; j++) {
			parents[j] = from;
		}
		
		for (int i = 0; i < len - 1; i++) {
			int min = Integer.MAX_VALUE;
			Iterator<Pair> it = head.get(from).iterator();
			while (it.hasNext()) {
				Pair curr = it.next();
				if (!added[curr.vertex]) {
					if (min > curr.distance) {
						min = curr.distance;
						minVertex = curr.vertex;
					}
				}
			}
			added[minVertex] = true;
			it = head.get(from).iterator();
			Iterator<Pair> mid = head.get(minVertex).iterator();
			int dist = head.get(from).get(minVertex).distance;
			while (it.hasNext()) {
				Pair curr = it.next();
				Pair el = mid.next();
				if (curr.distance > dist + el.distance) {
					curr.distance = dist + el.distance;
					parents[curr.vertex] = minVertex;
				}
			}
		}
	}
	
	public static void arraySolution(int arr[], int parents[], int from) {
		int len = (int) Math.ceil(Math.sqrt(arr.length * 2));
		boolean added[] = new boolean[len];
		added[from] = true;
		int minVertex = from;
		
		for (int j = 0; j < len; j++) {
			parents[j] = from;
		}
		
		for (int k = 0; k < len - 1; k++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < len; j++) {
				if (!added[j])
					if (min > arr[getIndex(from, j)]) {
						min = arr[getIndex(from, j)];
						minVertex = j;
					}
			}
			added[minVertex] = true;
			for (int j = 0; j < len; j++) {
				if (from == j) continue;
				if (minVertex == j) continue;
				if (arr[getIndex(from, j)] > arr[getIndex(from, minVertex)] + arr[getIndex(minVertex, j)]) {
					arr[getIndex(from, j)] = arr[getIndex(from, minVertex)] + arr[getIndex(minVertex, j)];
					parents[j] = minVertex;
				}
			}
		}
	}
	
	public static int getIndex(int i, int j) {
		if (i > j)
			return i * (i - 1) / 2 + j;
		else
			return j * (j - 1) / 2 + i;
	}
	
	public static void printPath(int from, int to, int parents[]) {
		System.out.print("Path : v" + from + " -> ");
		getPath(from - 1, to - 1, parents);
		System.out.println("v" + to);
	}
	
	public static void getPath(int from, int to, int parents[]) {
		if (parents[to] == from) return;
		getPath(from, parents[to], parents);
		System.out.print("v" + (parents[to] + 1) + " -> ");
		getPath(parents[to], to, parents);
	}
}
