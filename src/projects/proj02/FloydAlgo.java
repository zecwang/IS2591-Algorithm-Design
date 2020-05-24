package projects.proj02;

import java.util.Iterator;
import java.util.LinkedList;

public class FloydAlgo {
	
	public static void main(String[] args) {
	}
	
	public static void matrixSolution(int matrix[][], int path[][]) {
		int len = matrix.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				path[i][j] = i;
			}
		}
		for (int k = 0; k < len; k++) {
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
						path[i][j] = k;
					}
				}
			}
		}
	}
	
	public static void linkedlistSolution(LinkedList<LinkedList<Pair>> head, int path[][]) {
		int len = head.size();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				path[i][j] = i;
			}
		}
		for (int k = 0; k < len; k++) {
			for (int i = 0; i < len; i++) {
				Iterator<Pair> mid = head.get(k).iterator();
				Iterator<Pair> it = head.get(i).iterator();
				int dist = head.get(i).get(k).distance;
				while (it.hasNext()) {
					Pair curr = it.next();
					Pair el = mid.next();
					if (curr.distance > dist + el.distance) {
						curr.distance = dist + el.distance;
						path[i][curr.vertex] = k;
					}
				}
			}
		}
	}
	
	public static void arraySolution(int arr[], int path[][]) {
		int len = (int) Math.ceil(Math.sqrt(arr.length * 2));
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				path[i][j] = i;
			}
		}
		for (int k = 0; k < len; k++) {
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < i; j++) {
					if (i == k) break;
					if (j == k) continue;
					if (arr[getIndex(i, j)] > arr[getIndex(i, k)] + arr[getIndex(k, j)]) {
						arr[getIndex(i, j)] = arr[getIndex(i, k)] + arr[getIndex(k, j)];
						path[i][j] = k;
						path[j][i] = k;
					}
				}
				for (int j = i + 1; j < len; j++) {
					if (i == k) break;
					if (j == k) continue;
					if (arr[getIndex(i, j)] > arr[getIndex(i, k)] + arr[getIndex(k, j)]) {
						arr[getIndex(i, j)] = arr[getIndex(i, k)] + arr[getIndex(k, j)];
						path[i][j] = k;
						path[j][i] = k;
					}
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
	
	public static void printPath(int from, int to, int path[][]) {
		System.out.print("Path : v" + from + " -> ");
		getPath(from - 1, to - 1, path);
		System.out.println("v" + to);
	}
	
	public static void getPath(int from, int to, int path[][]) {
		if (path[from][to] == from) return;
		getPath(from, path[from][to], path);
		System.out.print("v" + (path[from][to] + 1) + " -> ");
		getPath(path[from][to], to, path);
	}
	
}

