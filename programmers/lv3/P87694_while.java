package programmers.lv3;

import java.util.LinkedList;
import java.util.Queue;

class Solution87694_while {

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        // Y, X 순서
        // 최대 50이라서 102 까지
        // 인덱스는 0부터 시작해서 + 1
        // 다음 길을 찾을때 에러 때문에 + 1
        int[][] graph = new int[102][102];

        // 1. 2차원 배열로 맵을 만든다
        // 2. 주어진 사각형들보다 -1 작은 맵으로 0 을 채워서 테두리만 남긴다.
        // 3. 위는 2배 크게 만든다
        graph = createGraph(rectangle, graph);

        // 2배 크게 만들었기 때문에
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        graph[itemY][itemX] = 7;
        graph[characterY][characterX] = 3;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { characterY, characterX, 0 }); // 현재 캐릭터 위치

        int[] yList = new int[] { 1, -1, 0, 0 };
        int[] xList = new int[] { 0, 0, 1, -1 };

        // DFS
        while (!q.isEmpty()) {

            int[] info = q.poll();

            int y = info[0];
            int x = info[1];
            int c = info[2]; // 현재 진행한 길 카운트

            for (int i = 0; i < yList.length; i++) {

                // 아이템 주으면
                if (graph[y + yList[i]][x + xList[i]] == 7) {
                    return (c + 1) / 2; // 아이템으로 위치 이동하니까 +1 , 두배 늘려서 계산했으니 / 2
                }

                if (graph[y + yList[i]][x + xList[i]] == 1) {
                    graph[y + yList[i]][x + xList[i]] = 0; // 이미 지나온길은 다시 갈수 없음
                    q.offer(new int[] { y + yList[i], x + xList[i], c + 1 });
                }
            }

        }

        return answer;
    }

    private int[][] createGraph(int[][] rectangle, int[][] graph) {

        for (int i = 0; i < rectangle.length; i++) {
            // 좌측 하단
            int x1 = rectangle[i][0];
            int y1 = rectangle[i][1];

            // 우측 상단
            int x2 = rectangle[i][2];
            int y2 = rectangle[i][3];

            for (int x = x1 * 2; x <= x2 * 2; x++) {
                for (int y = y1 * 2; y <= y2 * 2; y++) {
                    graph[y][x] = 1;
                }
            }
        }

        // 테두리만 남기기 (사이즈를 1 줄이고 0 으로 채우기)
        for (int i = 0; i < rectangle.length; i++) {
            // 좌측 하단
            int x1 = rectangle[i][0];
            int y1 = rectangle[i][1];

            // 우측 상단
            int x2 = rectangle[i][2];
            int y2 = rectangle[i][3];

            for (int x = x1 * 2 + 1; x <= x2 * 2 - 1; x++) {
                for (int y = y1 * 2 + 1; y <= y2 * 2 - 1; y++) {
                    graph[y][x] = 0;
                }
            }

        }

        return graph;
    }

}

public class P87694_while {
    public static void main(String[] args) {

        Solution87694_while sol = new Solution87694_while();

        int[][] rectangle = new int[][] {};

        // 17
        rectangle = new int[][] {
                { 1, 1, 7, 4 },
                { 3, 2, 5, 5 },
                { 4, 3, 6, 9 },
                { 2, 6, 8, 8 } };
        System.out.println("answer is " + sol.solution(rectangle, 1, 3, 7, 8));

    }
}
