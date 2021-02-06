package GymSuit;


// This solution is right but doesn't work.
public class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        int[] list = new int[n];
        
        // int i = 0;
        // while (true) {
        //     list[reserve[i] - 1] = 1;
        //     i++;
        //     if (!(i < reserve.length)) {
        //         break;
        //     }
        // }
        for (int i: reserve) {
            list[i - 1] = 1;
        }
        for (int i: lost) {
            if (list[i - 1] == 1) {
                list[i - 1] = 0;
            } else {
                list[i - 1] = -1;
            }
        }


        // i = 0;
        // while (true) {
        //     if (list[lost[i] - 1] == 1) {
        //         list[lost[i] - 1] = 0;
        //     } else {
        //         list[lost[i] - 1] = -1;
        //     }
        //     i++;
        //     if (!(i < lost.length)){
        //         break;
        //     }
        // }

        // System.out.println(Arrays.toString(list));

        
        // int i = 0;
        // while (true) {
        //     if (list[i] > -1) {
        //         answer++;
        //         i++;
        //         // System.out.println("1");
        //     } else if (i != 0 && list[i - 1] == 1) {
        //         // System.out.println("2");
        //         answer++;
        //         i++;
        //     } else if (list[i + 1] == 1) {
        //         // System.out.println("3");
        //         answer += 2;
        //         list[i + 1] = 0;
        //         i += 2;

        //     } else {
        //         // System.out.println("4");
        //         i++;
        //     }

        //     if (!(i < list.length)) {
        //         // System.out.println("3");
        //         return answer;
        //     }
        // }

        for (int i = 0; i < list.length; i++) {
            if (list[i] < 0) {
                if (i != list.length - 1 && list[i + 1] == 1) {
                    list[i]++;
                    list[i + 1]--;
                } else if (i != 0 && list[i - 1] == 1) {
                    list[i]++;
                    list[i - 1]--;
                }
            }
        }

        for (int i = 0; i < list.length; i++) {
            if (list[i] >= 0) {
                answer++;
            }
        }
        return answer;
    }
}
