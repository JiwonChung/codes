package Ant;

public class Solution {
    public int solution (int length, int[] array) {
        // number of cases
        int[] maxValue = new int[length];
        maxValue[0] = array[0];
        maxValue[1] = Math.max(maxValue[0], array[1]);

        for (int i = 2; i < length; i++) {
            maxValue[i] = Math.max(maxValue[i - 1], maxValue[i - 2] + array[i]);
        }
        return maxValue[length - 1];


    }
}
