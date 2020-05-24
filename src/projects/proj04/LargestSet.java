package projects.proj04;

import java.util.HashSet;

public class LargestSet {
    private int[][] matrix;

    public LargestSet(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == Constant.INF) {
                    matrix[i][j] = 1;
                }else if (matrix[i][j] != 0) {
                    matrix[i][j] = Constant.INF;
                }
            }
        }
        this.matrix = matrix;
    }

    public HashSet<Integer> find() {
        return new LargestClique(matrix).find();
    }
}
