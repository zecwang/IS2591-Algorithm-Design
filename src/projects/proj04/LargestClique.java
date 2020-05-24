package projects.proj04;

import java.util.HashSet;

public class LargestClique {
    private HashSet<Integer> result = new HashSet<>();
    private int[][] matrix;

    public LargestClique(int[][] matrix) {
        this.matrix = matrix;
    }

    public HashSet<Integer> find() {
        HashSet<Integer> R = new HashSet<>();
        HashSet<Integer> P = new HashSet<>();
        HashSet<Integer> X = new HashSet<>();
        for (int i = 0; i < this.matrix.length; i++) {
            P.add(i);
        }
        BronKerbosch(R, P, X);
        return result;
    }

    private void BronKerbosch(HashSet<Integer> R, HashSet<Integer> P, HashSet<Integer> X) {
        if (P.size() == 0 && X.size() == 0) {
            if (R.size() > result.size()) {
                result.clear();
                result.addAll(R);
            }
            return;
        }

        int u = union(P, X).iterator().next();
        for (int v : diff(P, N(u))) {
            R.add(v);
            HashSet<Integer> N = N(v);
            BronKerbosch(new HashSet<>(R), intersect(P, N), intersect(X, N));
            R.remove(v);
            P.remove(v);
            X.add(v);
        }
    }

    private HashSet<Integer> N(int v) {
        HashSet<Integer> adj = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[v][i] != Constant.INF) adj.add(i);
        }
        adj.remove(v);
        return adj;
    }

    private HashSet<Integer> intersect(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> res = new HashSet<>();
        for (int el : set1) {
            if (set2.contains(el))
                res.add(el);
        }
        return res;
    }

    private HashSet<Integer> union(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> res = new HashSet<>(set1);
        res.addAll(set2);
        return res;
    }

    private HashSet<Integer> diff(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> res = new HashSet<>(set1);
        for (int el : set1) {
            if (set2.contains(el)) {
                res.remove(el);
            }
        }
        return res;
    }

}
