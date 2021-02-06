package TernarySystem;

class Solution {
    public int solution(int n) {

        int i = 0;
        while (true) {
            if (n >= Math.pow(3, i) && n < Math.pow(3, i + 1)) {
                break;
            }
            i++;
        }
        int[] ternary = new int[i + 1];
        int forTernaryReverse = i;

        // System.out.println(i);
        while (true) {
            int num = 0;
            while (true) {
                if (n >= Math.pow(3, i) * num) {
                    num++;
                } else {

                    ternary[forTernaryReverse - i] = num - 1;
                    n = n - (int) Math.pow(3, i) * (num - 1);
                    // System.out.println((forTernaryReverse - i) + ", " + (num - 1));
                    i--;

                    break;

                }
            }
            if (i < 0) {
                break;
            }
        }

        // System.out.println(Arrays.toString(ternary));
        // System.out.println(forTernaryReverse);
        
        i = 0;
        int answer = 0;
        while (true) {
            answer += ternary[forTernaryReverse - i] * (int) Math.pow(3, forTernaryReverse - i);
            // System.out.println(ternary[i]);
            i++;
            if (i > forTernaryReverse) {
                return answer;
            }
        }

    }
}