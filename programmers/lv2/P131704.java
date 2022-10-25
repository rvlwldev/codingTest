package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution131704 {

    public int solution(int[] order) {

        // 박스 순서
        Queue<Integer> orders = new LinkedList<>();

        // 서브 벨트
        Stack<Integer> sub = new Stack<>();

        for (int i = 0; i < order.length; i++) {
            orders.offer(order[i]); // 주문순서
        }

        int res = 0;

        // 메인벨트
        for (int box = 1; box <= order.length; box++) {
            int chk = orders.peek();

            // 메인 벨트에서 실을 수 있을때
            if (chk == box) {
                orders.poll();
                res++;
            } else if (sub.size() > 0 && sub.peek() == chk) { // 서브벨트에서 실을 수 있을때
                sub.pop();
                orders.poll();
                box--;
                res++;
            } else { // 보조 컨테이너로 옮길때
                sub.push(box);
            }

        }

        while (!sub.isEmpty()) {
            int chk = orders.peek();
            int box = sub.pop();

            if (chk == box) {
                orders.poll();
                res++;
            } else {
                break;
            }

        }

        return res;
    }
}

public class P131704 {
    public static void main(String[] args) {

        Solution131704 sol = new Solution131704();

        // 2
        System.out.println("answer is " + sol.solution(new int[] { 4, 3, 1, 2, 5 }));

        // 5
        System.out.println("answer is " + sol.solution(new int[] { 5, 4, 3, 2, 1 }));
    }
}
