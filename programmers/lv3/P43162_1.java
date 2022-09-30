package programmers.lv3;

import java.util.Stack;

class Solution43162_1 {

    public int solution(int n, int[][] computers) {
        int answer = 0;

        // 하나의 네트워크로 확인된 인덱스 배열
        boolean[] checked = new boolean[n];

        for (int i = 0; i < computers.length; i++) {

            // 이미 확인한 컴퓨터라면 확인하지 않음
            if (checked[i]) {
                continue;
            }

            // i 번째의 연결정보
            int[] list = computers[i];

            // 확인처리
            checked[i] = true;

            // 재귀적으로 연결된 컴퓨터 인덱스를 담을 스택
            Stack<Integer> stack = new Stack<>();

            // 일단 첫번째로 현재의 연결된 컴퓨터의 인덱스를 스택에 담는다
            for (int c = 0; c < list.length; c++) {
                if (!checked[c] && list[c] == 1) {
                    stack.push(c);
                }
            }

            // DFS (후입선출)
            while (!stack.isEmpty()) {

                // 연결된 컴퓨터를 하나씩 확인한다.
                int p = stack.pop();

                // 확인되지 않은 컴퓨터라면
                if (!checked[p]) {

                    // 연결된 모든 컴퓨터를 확인처리
                    checked[p] = true;

                    for (int c = 0; c < computers[p].length; c++) {

                        // 확인되지 않은 컴퓨터의 '모든' 연결정보(인덱스)를 스택에 담는다.
                        // p : 확인대상의 컴퓨터
                        // c : p의 연결된 모든 컴퓨터
                        if (!checked[c] && computers[p][c] == 1) {
                            stack.push(c);
                        }
                    }

                }

            }

            // 반복문 최상단에 확인 되었다면 여기까지 진행되지 않음으로
            // 연결정보가 없을경우 여기까지 닿는다.

            // 즉, 하나의 네트워크가 확인되었다면
            // 그 네트워크의 모든 컴퓨터들은 반복문이 진행되지 않는다.
            answer++;

        }

        return answer;
    }

}

public class P43162_1 {
    public static void main(String[] args) {

        Solution43162_1 sol = new Solution43162_1();

        int[][] computers;

        // 1
        computers = new int[][] { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } };
        System.out.println("answer is " + sol.solution(3, computers));

        // 2
        computers = new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
        System.out.println("answer is " + sol.solution(3, computers));

        // 3
        computers = new int[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
        System.out.println("answer is " + sol.solution(3, computers));

        // 1
        computers = new int[][] { { 1, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 0, 1, 1 }, { 1, 0, 1, 1 } };
        System.out.println("answer is " + sol.solution(4, computers));

    }
}
