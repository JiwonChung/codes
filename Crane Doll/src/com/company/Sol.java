package com.company;

import java.util.Arrays;

class Sol {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        int[] stack = new int[moves.length];

        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        int stackCount = 0;
        // moves[0]가 0이면 보더[0]에서 처음으로 나온 0이 아닌 숫자를 stack 에 추가

        for (int movesCount = 0; movesCount < moves.length; movesCount++) {
            for (int firstDimensionalCount = 0; firstDimensionalCount < board.length; firstDimensionalCount++) {
                if (moves[movesCount] -1 == firstDimensionalCount) {
                    int tmp;
                    int count = 0;

                    while (true) {
                        if (board[firstDimensionalCount][count] != 0) {
                            tmp = board[firstDimensionalCount][count];
                            break;
                        }
                        if (count == board.length - 1) {
                            tmp = -1;
                            break;
                        }
                        count++;
                    }
                    if (tmp != -1) {
                        stack[stackCount] = tmp;
                        System.out.println(ANSI_CYAN + moves[movesCount] + ", " + stack[stackCount] + ANSI_RESET);
                        board[firstDimensionalCount][stackCount] = 0;

                        stackCount++;

                    }
                }
            }
//            System.out.println(ANSI_RED + moves[movesCount] + ANSI_RESET);
        }
        System.out.println(Arrays.toString(stack));

        for (int i = 0; i < stack.length - 1; i++) {
            if (stack[i] == stack[i + 1] && stack[i] != 0) {
                i++;
                answer++;
                answer++;
            }
        }


        return answer;
    }
}