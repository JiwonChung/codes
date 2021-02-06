package com.company;

import java.util.Stack;

public class Solution1 {

    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);

        for (int move: moves) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][move - 1] != 0) {
                    if (board[i][move -1] == stack.peek()) {
                        stack.pop();
                        answer += 2;

                    } else {
                        stack.push(board[i][move - 1]);
                    }
                    board[i][move - 1] = 0;
                    break;
                }
            }
        }


        return answer;
    }
}
