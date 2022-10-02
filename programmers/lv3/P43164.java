package programmers.lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// https://school.programmers.co.kr/learn/courses/30/lessons/43164

/*

BFS로 풀려고 하다가 시간을 너무 날림
간단하게 풀수있었는데...

원래는 한 출발지의 도착지가 여러개면 노드의 깊이가 큰걸로 먼저 가려고햇는데
그러면 너무 어려워짐.....

최대한 단순하고 간단하게 생각해서 하나씩 순차적으로 풀어야겠음.....

 */
class Solution43164 {

    // 모던 경로를 담을 리스트
    static ArrayList<String> route;

    public String[] solution(String[][] tickets) {

        route = new ArrayList<>();

        // 티켓 사용 여부
        boolean[] used = new boolean[tickets.length];

        // 첫시작은 인천
        String from = "ICN";

        recur(tickets, used, from, from, 0);

        Collections.sort(route);

        return route.get(0).split(",");
    }

    void recur(String[][] tickets, boolean[] used, String from, String path, int depth) {

        if (depth == tickets.length) {
            route.add(path);
            return;
        }

        for (int i = 0; i < used.length; i++) {

            if (used[i]) {
                continue;
            }

            // 현재 출발지가 티켓의 출발지가 같다면
            if (from.equals(tickets[i][0])) {

                // 확인
                used[i] = true;

                // path =+ "," + tickets[i][1]; * 주의 : 이렇게 하면 안됨
                // used[i] 를 원복 시키는것과 똑같이
                // 여러개일때 다음 순서의 티켓에 영향이 없어야되는데
                // 변수의 값이 원복되지 않기 때문

                // 출발지를 현재 티켓의 도착지로 바꾸고
                // path에 현재 티켓의 도착지로 추가
                recur(tickets, used, tickets[i][1], path + "," + tickets[i][1], depth + 1);

                // 모든 경우의 수를 위해 다시 원복
                used[i] = false;

            }

        }

    }

}

public class P43164 {
    public static void main(String[] args) {

        Solution43164 sol = new Solution43164();

        String[][] tickets = new String[][] {};

        tickets = new String[][] {
                { "ICN", "AAA" },
                { "ICN", "AAA" },
                { "AAA", "ICN" },
                { "AAA", "CCC" } };
        System.out.println("answer is " + Arrays.toString(sol.solution(tickets)));
        System.out.println("예상 답 : " + "[ICN, AAA, ICN, AAA, CCC]");

        // "ICN", "ATL", "ICN", "SFO", "ATL", "SFO"
        tickets = new String[][] {
                { "ICN", "SFO" },
                { "ICN", "ATL" },
                { "SFO", "ATL" },
                { "ATL", "ICN" },
                { "ATL", "SFO" } };
        System.out.println("answer is " + Arrays.toString(sol.solution(tickets)));
        System.out.println("예상 답 : " + "[ICN, ATL, ICN, SFO, ATL, SFO]");

        // "ICN", "JFK", "HND", "IAD"
        tickets = new String[][] {
                { "ICN", "JFK" },
                { "HND", "IAD" },
                { "JFK", "HND" } };
        System.out.println("answer is " + Arrays.toString(sol.solution(tickets)));
        System.out.println("예상 답 : " + "[ICN, JFK, HND, IAD]");

        tickets = new String[][] {
                { "ICN", "AAA" },
                { "AAA", "BBB" },
                { "AAA", "CCC" },
                { "CCC", "AAA" },
                { "BBB", "DDD" } };
        System.out.println("answer is " + Arrays.toString(sol.solution(tickets)));
        System.out.println("예상 답 : " + "[ICN, AAA, CCC, AAA, BBB, DDD]");

    }
}
