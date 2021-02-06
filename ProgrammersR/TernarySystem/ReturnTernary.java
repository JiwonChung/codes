package TernarySystem;

import java.util.Arrays;

public class ReturnTernary {

    public int[] returnTernary(int n) {
        int i = 0;
        while (true) {
            if (n <= Math.pow(3, i + 1) && n > Math.pow(3, i)) {
                break;
            }
            i++;
        }
        int[] ternarys = new int[i + 1];

        int forTernarys = i;


        while (true) {
            int j = 2;
            while (true) {
                if (n >= Math.pow(3, i) * j) {
                    ternarys[forTernarys - i] = j;
                    n -= Math.pow(3, i) * j;
                    i--;
                    System.out.println(Arrays.toString(ternarys) + 
                    "" + j + "  n = " + n);
                    break;

                } else {
                    j--;
                }
            }
            if (i < 0) {
                return ternarys;
            }
        }

    }
}
