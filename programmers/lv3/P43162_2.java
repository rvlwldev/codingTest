package programmers.lv3;

import java.util.Stack;

class Solution43162_2 {

    static int answer;
    static boolean[] visit;

    public int solution(int n, int[][] computers) {

        answer = 0;
        visit = new boolean[n];

        // 각각의 컴퓨터 하나씩 확인
        // DFS
        for (int i = 0; i < computers.length; i++) {
            recur(computers, i);
        }

        return answer;
    }

    private void recur(int[][] computers, int index) {

        // 확인되었다면 종료
        if (visit[index]) {
            return;
        } else {
            // 연결 확인이 안되었으면 +1
            // 하나의 네트워크를 확인했으면 모든 컴퓨터가 확인되기 때문에....
            answer++;
        }

        // 아래는 확인되지 않은 컴퓨터 인덱스 대상으로

        int[] list = computers[index];

        // 현재 확인되지 않은 컴퓨터의 연결 컴퓨터 인덱스들을 스택에 담아준다
        Stack<Integer> stack = addStack(list, new Stack<>());

        while (!stack.isEmpty()) {
            int p = stack.pop();

            /*
             * 현재 인덱스의 컴퓨터와
             * 확인되지 않은 컴퓨터 사이의
             * 같은 네트워크의 컴퓨터가 존재할 수 있음)
             */

            // 확인되지않은 컴퓨터라면
            if (!visit[p]) {
                visit[p] = true;
                stack = addStack(computers[p], stack);
            }
        }

    }

    // visit변수로 확인되지 않은 모든 연결을 스택에 담아 리턴한다.
    private Stack<Integer> addStack(int[] arr, Stack<Integer> stack) {

        for (int i = 0; i < arr.length; i++) {
            if (!visit[i] && arr[i] == 1) {
                stack.push(i);
            }
        }

        return stack;
    }

}

public class P43162_2 {
    public static void main(String[] args) {

        Solution43162_2 sol = new Solution43162_2();

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
