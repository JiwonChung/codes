package TwoThousandSixteen;

public class Solution {
    public String solution(int a, int b) {
        
        String[] weeks = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        int[] month = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30,31};
        int days = 0; // friday

        for (int i = 0; i < a - 1; i++) {
            days += month[a - 1 - i - 1];
        }
        days += b;
        System.out.println(days);

        return weeks[(days - 1) % 7];
    }
}
