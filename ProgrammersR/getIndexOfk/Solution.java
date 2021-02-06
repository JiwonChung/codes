package getIndexOfk;

import java.util.Arrays;

public class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        int[] first = new int[commands.length];
        int[] second = new int[commands.length];
        int[] key = new int[commands.length];
        for (int i = 0; i < first.length; i++) {
            first[i] = commands[i][0] - 1;
        }
        for (int i = 0; i < first.length; i++) {
            second[i] = commands[i][1] - 1;
        }        
        for (int i = 0; i < first.length; i++) {
            key[i] = commands[i][2] - 1;
        }

        for (int i = 0; i < commands.length; i++) {
            int[] truncatedArray = new int[second[i] - first[i] + 1];
            for (int j = 0; j < truncatedArray.length; j++) {
                truncatedArray[j] = array[first[i] + j];
            }
            Arrays.sort(truncatedArray);
            answer[i] = truncatedArray[key[i]];
        }
        
        return answer;
    }
}
