package GymSuit;

import java.util.HashMap;


/**
 * Solution
 */
public class Solution2 {

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        HashMap<Integer, Integer> students = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            if (contains(lost, i)) {
                if (contains(reserve, i)) {
                    students.put(i, 1);
                } else {
                    students.put(i, 0);
                }
            } else if (contains(reserve, i)) {
                students.put(i, 2);
            } else {
                students.put(i, 1);
            }
        }
        System.out.println(students);

        int i = 1;
        while (true) {
            if (students.get(i) == 1) {
                answer++;
                System.out.println(answer + "Plussed one!");
                i++;
            } else if (students.get(i) == 0) {
                if (i == 1) {
                    if (students.get(i + 1) == 2) {
                        students.replace(i + 1, 1);
                        students.replace(i, 1);
                        System.out.println(answer + "Plussed two!");
                        answer += 2;
                        i += 2;
                    } else {
                        i++;
                    }
                } else if (students.get(i - 1) == 2) {
                    students.replace(i - 1, 1);
                    students.replace(i, 1);
                    answer += 1;
                    System.out.println(answer + "Plussed one!!");
                    i++;
                } else if (students.get(i + 1) == 2) {
                    students.replace(i + 1, 1);
                    students.replace(i, 1);
                    answer += 2;
                    System.out.println(answer + "Plused two!!");
                    i += 2;
                } else {
                    // no friend to borrow
                    i++;
                }
            } else {
                // case of students.get(i) == 2
                System.out.println(answer + "Plussed two!!!");
                answer++;
                i++;
            }

            if (!students.containsKey(i)) {
                break;
            }
        }
        System.out.println(students);

        return answer;
        
    }
    public boolean contains(int[] array, int key) {
        for (int n: array){
            if (key == n) {
                return true;
            }
        }
        return false;
    }
}