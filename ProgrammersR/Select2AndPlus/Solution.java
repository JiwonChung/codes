package Select2AndPlus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};

        HashMap<Integer, Integer> hm= new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (hm.containsKey(numbers[i] + numbers[j])) {
                    hm.replace(numbers[i] + numbers[j], hm.get(numbers[i] + numbers[j]) + 1);
                } else {
                    hm.put(numbers[i] + numbers[j], 1);
                }
            }
        }

        int count = 0;
        answer = new int[hm.size()];
        for (Map.Entry<Integer, Integer> entry: hm.entrySet()) {
            answer[count] = entry.getKey();
            count++;
        }
        answer = sort(answer);
        return answer;
    }

    public int[] sort(int[] arr) {
        if (arr.length < 2) return arr;

        int mid = arr.length / 2;
        int[] low_arr = sort(Arrays.copyOfRange(arr, 0, mid));
        int[] high_arr = sort(Arrays.copyOfRange(arr, mid, arr.length));

        int[] mergedArr = new int[arr.length];
        int m = 0, l = 0, h = 0;
        while (l < low_arr.length && h < high_arr.length) {
            if (low_arr[l] < high_arr[h])
                mergedArr[m++] = low_arr[l++];
            else
                mergedArr[m++] = high_arr[h++];
        }
        while (l < low_arr.length) {
            mergedArr[m++] = low_arr[l++];
        }
        while (h < high_arr.length) {
            mergedArr[m++] = high_arr[h++];
        }
        return mergedArr;
    }

}
