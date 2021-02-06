package GymSuit;

public class Main {

    public static void main(String[] args) {
        // Solution2 s = new Solution2();
        Solution s1 = new Solution();
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};
        System.out.println(s1.solution(5, lost, reserve));
        
    }
}
