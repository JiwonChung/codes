package com.company;

import java.util.Arrays;

public class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int[] basket = new int[moves.length];
        int basketCount = 0;

        for (int move : moves) {

            for (int boardSecondCount = 0; boardSecondCount < board.length; boardSecondCount++) {
                if (move - 1 == boardSecondCount) {
                    int boarderFirstCount = 0;
                    while (true) {
                        if (board[boarderFirstCount][boardSecondCount] != 0) {
                            basket[basketCount] = board[boarderFirstCount][boardSecondCount];
                            board[boarderFirstCount][boardSecondCount] = 0;
                            basketCount++;
                            break;
                        }
                        if (boarderFirstCount == 4) {
                            break;
                        }
                        boarderFirstCount++;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(basket));


        // 찾으면 한법 더 돌리기
        int i = 0;
        while (true) {
            if (basket[i] == basket[i + 1]) {
                answer += 2;
                for (int a = i; a < basket.length - 2; a++) {
                    if (basket[a + 2] == 0) {
                        break;
                    } else {
                        basket[a] = basket[a + 2];
                        basket[a + 2] = 0;
                        i = 0;
                    }
                }

            } else {
                i++;
                if (basket[i] == 0) {
                    break;
                }
            }
        }



        return answer;
    }
}
