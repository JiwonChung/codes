package PracticeTest;

public class Solution {
    public int[] solution(int[] answers) {
        int[] mathHater1Pattern = {1, 2, 3, 4, 5};
        int[] mathHater2Pattern = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] mathHater3Pattern = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] rightCount = new int[3];

        // save math hater1's correct number in rightCount[0]
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == mathHater1Pattern[i%5]) {
                rightCount[0]++;
            }
        }
        // save math hater2's correct number in rightCount[1]
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == mathHater2Pattern[i%8]) {
                rightCount[1]++;
            }
        }// save math hater3's correct number in rightCount[2]
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == mathHater3Pattern[i%10]) {
                rightCount[2]++;
            }
        }

        if (rightCount[0] > rightCount[1]) {
            if (rightCount[0] > rightCount[2]) {
                // returnValues[0] = 1;
                int[] answer = {1};
                return answer;
            } else if (rightCount[0] == rightCount[2]) {
                // returnValues[0] = 1;
                // returnValues[1] = 3;
                int[] answer = {1, 3};
                return answer;
            } else {
                // returnValues[0] = 3;
                int[] answer = {3};
                return answer;
            }
        } else if (rightCount[0] == rightCount[1]) {
            if (rightCount[0] > rightCount[2]) {
                // returnValues[0] = 1;
                // returnValues[1] = 2;
                int[] answer = {1, 2};
                return answer;
            } else if (rightCount[0] == rightCount[2]) {
                // returnValues[0] = 1;
                // returnValues[1] = 2;
                // returnValues[2] = 3;
                int[] answer = {1, 2, 3};
                return answer;
            } else {
                // returnValues[0] = 3;
                int[] answer = {3};
                return answer;
            }
        } else if (rightCount[1] > rightCount[2]) {
            // returnValues[0] = 2;
            int[] answer = {2};
            return answer;
        } else if (rightCount[1] == rightCount[2]) {
            // returnValues[0] = 2;
            // returnValues[1] = 3;
            int[] answer = {2, 3};
            return answer;
        } else {
            // returnValues[0] = 3;
            int[] answer = {3};
            return answer;
        }
        
        

        
    }
}
