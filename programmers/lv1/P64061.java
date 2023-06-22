package programmers.lv1;

import java.util.ArrayList;

class Solution64061 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        // 바구니
        ArrayList<Integer> basket = new ArrayList<>();

        // 움직임 구현
        for (int i = 0; i < moves.length; i++) {

            // 인덱스 구현으로 -1
            int index = moves[i] - 1;

            for (int j = 0; j < board.length; j++) {
                if (board[j][index] != 0) {
                    basket.add(board[j][index]);
                    board[j][index] = 0;
                    break;
                }
            }

        }

        // getReturn 함수로 터트릴수있는 인형이 있는지 확인
        while (getReturn(basket)) {

            int index = getIndex(basket);

            // 짝지어서 터지니까 2번
            basket.remove(index);
            basket.remove(index);

            // 터지면 2개씩 터지니까 +2
            answer += 2;

        }

        return answer;
    }

    // 같은 모양이 짝지어져 있는지 확인
    private boolean getReturn(ArrayList<Integer> basket) {

        for (int i = 0; i < basket.size() - 1; i++) {
            if (basket.get(i) == basket.get(i + 1)) {
                return true;
            }
        }

        return false;
    }

    // 짝지어진 위치 찾기
    private int getIndex(ArrayList<Integer> basket) {

        for (int i = 0; i < basket.size() - 1; i++) {
            if (basket.get(i) == basket.get(i + 1)) {
                return i;
            }
        }

        return -1;
    }
}

public class P64061 {
    public static void main(String[] args) {
        Solution64061 sol = new Solution64061();

        int[][] board = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 3 },
                { 0, 2, 5, 0, 1 },
                { 4, 2, 4, 4, 2 },
                { 3, 5, 1, 3, 1 } };

        int[] moves = new int[] { 1, 5, 3, 5, 1, 2, 1, 4 };

        int answer = sol.solution(board, moves);

        System.out.println("Answer is " + answer);

    }

}
