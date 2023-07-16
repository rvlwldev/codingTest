package programmers.lv2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Stack;

public class P150369 {
    public static void main(String[] args) {
        Solution sol = new Solution();

//        System.out.println(sol.solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0})); // 16
        System.out.println(sol.solution1(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0})); // 16

//        System.out.println(sol.solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0})); // 30
        System.out.println(sol.solution1(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0})); // 30

//        System.out.println(sol.solution(4, 2, new int[]{0, 0}, new int[]{0, 0})); // 0
        System.out.println(sol.solution1(4, 2, new int[]{0, 0}, new int[]{0, 0})); // 0


    }
}

class Solution {
    // 스택
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        LocalDateTime start = LocalDateTime.now();

        long answer = 0;

        // 가장 먼곳부터 배달/수거를 한다.
        Stack<Integer> D = new Stack<>();
        Stack<Integer> P = new Stack<>();

        for (int i = 0; i < n; i++) {
            D.push(deliveries[i]);
            P.push(pickups[i]);
        }

        while (!D.isEmpty() || !P.isEmpty()) {
            // 갈 필요가 없으면 제외한다.
            if (!D.isEmpty()) D = replaceEmptyCargo(D);
            if (!P.isEmpty()) P = replaceEmptyCargo(P);

            // 한번 왕복시 갔다가 와야되니까 * 2
            answer += Math.max(D.size(), P.size()) * 2;

            // 1. 가장 많이 배달할 수 있는 만큼 배달한다.
            D = processCargos(D, cap);

            // 2. 돌아올때 최대한 많이 수거한다.
            P = processCargos(P, cap);
        }

        LocalDateTime end = LocalDateTime.now();

        Duration diff = Duration.between(start.toLocalTime(), end.toLocalTime());
        System.out.printf("스택사용 소요 나노초: %d", diff.getNano());

        return answer;
    }

    private Stack<Integer> replaceEmptyCargo(Stack<Integer> stack) {
        while (!stack.isEmpty() && stack.peek() < 1) stack.pop();

        return stack;
    }

    private Stack<Integer> processCargos(Stack<Integer> stack, int maxSize) {
        int cargo = 0;
        while (!stack.isEmpty()) {
            if ((cargo + stack.peek()) <= maxSize) {
                cargo += stack.pop();
            } else {
                stack.push(cargo + stack.pop() - maxSize);
                break;
            }
        }

        return stack;
    }

    // 포인터로 풀어보기
    int Dp;
    int Pp;

    public long solution1(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Dp = n - 1;
        Pp = n - 1;

        while (Dp >= 0 || Pp >= 0) {
            while (Dp >= 0 && deliveries[Dp] == 0) Dp--;
            while (Pp >= 0 && pickups[Pp] == 0) Pp--;

            // 인덱스는 항상 -1 을 가지기 때문에 +1
            answer += (Math.max(Dp, Pp) + 1) * 2L;

            int sum = 0;

            while (Dp >= 0 && sum < cap) {
                sum += deliveries[Dp];
                deliveries[Dp--] = 0; // 전체 반복문 시작 시 삭제 될 수 있게 0처리
            }
            // 최대 개수를 넘었을때, 남은 개수를 설정해주고 포인터 + 1
            if (sum > cap) deliveries[++Dp] = sum - cap;

            sum = 0;
            while (Pp >= 0 && sum < cap) {
                sum += pickups[Pp];
                pickups[Pp--] = 0;
            }
            if (sum > cap) pickups[++Pp] = sum - cap;
        }

        return answer;
    }

    private int[] getLeftCargoCount(int pointer, int[] arr, int maxSize) {
        int sum = 0;

        while (pointer >= 0 && sum < maxSize) {
            sum += arr[pointer];
            arr[pointer--] = 0; // 전체 반복문 시작 시 삭제 될 수 있게 0처리
        }
        // 최대 개수를 넘었을때, 남은 개수를 설정해주고 포인터 + 1
        if (sum > maxSize) arr[++pointer] = sum - maxSize;

        return arr;
    }


}