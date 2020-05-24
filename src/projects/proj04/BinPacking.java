package projects.proj04;

import java.util.HashMap;

public class BinPacking {
    private int W;
    private int[] goods;

    public BinPacking(int[] goods, int W) {
        this.goods = goods;
        this.W = W;
    }

    public int solution() {
        int i = 0;
        HashMap<Integer, Integer> bins = new HashMap<>();
        while (i < goods.length) {
            boolean sw = true;
            int k = 1;
            while (k <= bins.size() && sw) {
                int v = bins.get(k);
                if (goods[i] + v <= W) {
                    v += goods[i];
                    bins.put(k, v);
                    sw = false;
                }
                k++;
            }
            if (sw)
                bins.put(k, goods[i]);
            i++;
        }
        return bins.size();
    }
}
