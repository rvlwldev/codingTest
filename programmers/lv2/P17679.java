package programmers.lv2;

// 프렌즈 4블록

import java.util.Arrays;

class Solution17679 {
    final char BROKE = 'X';
    final char EMPTY = '*';

    public int solution(int height, int row, String[] gameBoard) {
        int answer = 0;

        boolean[][] breakTargets = getInitBreakBoard(height, row);
        char[][] board = new char[height][row];

        int boardIndex = 0;
        for (String rowBlocks : gameBoard) {
            board[boardIndex] = rowBlocks.toCharArray();
            boardIndex++;
        }

        boolean isBreakable = true;
        while (isBreakable) {
            isBreakable = false;

            for (int y = 0; y < height - 1; y++) {
                for (int x = 0; x < row - 1; x++) {
                    if (checkBreakable(board, y, x)) {
                        isBreakable = true;
                        breakTargets = setBreakableBlocks(breakTargets, y, x);
                    }
                }
            }

            if (isBreakable) {
                board = breakBlocks(board, breakTargets);
                answer += getCountOfBrokenBlocks(breakTargets);
                board = sortBlocksToBelow(board);
            }

            breakTargets = getInitBreakBoard(height, row);
        }

        return answer;
    }

    int getCountOfBrokenBlocks(boolean[][] breakTargets) {
        int broken = 0;

        for (boolean[] row : breakTargets) {
            for (boolean isBroken : row) if (isBroken) broken++;
        }

        return broken;
    }

    char[][] sortBlocksToBelow(char[][] board) {
        char[][] newBoard = new char[board.length][board[0].length];

        for (char[] row : newBoard) Arrays.fill(row, EMPTY);

        for (int x = 0; x < board.length; x++) {
            int newY = board.length - 1;

            for (int y = board.length - 1; y >= 0; y--) {
                char target = board[y][x];

                if (target != BROKE && target != EMPTY) {
                    newBoard[newY][x] = target;
                    newY -= 1;
                }
            }
        }

        return newBoard;
    }

    boolean[][] getInitBreakBoard(int y, int x) {
        return new boolean[y][x];
    }

    boolean[][] setBreakableBlocks(boolean[][] board, int y, int x) {
        board[y][x] = true;
        board[y + 1][x] = true;
        board[y][x + 1] = true;
        board[y + 1][x + 1] = true;

        return board;
    }

    boolean checkBreakable(char[][] board, int y, int x) {
        char target = board[y][x];

        if (target == BROKE || target == EMPTY) return false;

        return board[y + 1][x] == target
                && board[y][x + 1] == target
                && board[y + 1][x + 1] == target;
    }

    char[][] breakBlocks(char[][] board, boolean[][] breakTargets) {
        for (int y = 0; y < breakTargets.length; y++) {
            for (int x = 0; x < breakTargets[y].length; x++) if (breakTargets[y][x]) board[y][x] = BROKE;
        }

        return board;
    }

}

public class P17679 {
    public static void main(String[] args) {
        int m, n;
        String[] board;

        Solution17679 sol = new Solution17679();

        m = 4;
        n = 5;
        board = new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println("answer is " + sol.solution(m, n, board)); // 14

        m = 6;
        n = 6;
        board = new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println("answer is " + sol.solution(m, n, board)); // 15

        m = 4;
        n = 5;
        board = new String[]{"AAAAA", "AUUUA", "AUUAA", "AAAAA"};
        System.out.println("answer is " + sol.solution(m, n, board)); // 14

    }

}
