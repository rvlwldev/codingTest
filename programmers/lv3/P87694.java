package programmers.lv3;

import java.util.LinkedList;
import java.util.Queue;

class Solution87694 {

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        // Y, X 순서
        int[][] graph = new int[101][101];

        // 1. 2차원 배열로 맵을 만든다
        // 2. 주어진 사각형들보다 -1 작은 맵으로 0 을 채워서 테두리만 남긴다.
        // 3. 위는 2배 크게 만든다

        graph = createGraph(rectangle, graph);

        graph[itemY * 2][itemX * 2] = 7;
        graph[characterY * 2][characterX * 2] = 3;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { characterY * 2, characterX * 2, 0 });

        int[] yList = new int[] { 1, -1, 0, 0 };
        int[] xList = new int[] { 0, 0, 1, -1 };

        while (!q.isEmpty()) {

            int[] info = q.poll();

            int y = info[0];
            int x = info[1];
            int c = info[2];

            for (int i = 0; i < yList.length; i++) {

                if (graph[y + yList[i]][x + xList[i]] == 7) {
                    return (c + 1) / 2;
                }

                if (graph[y + yList[i]][x + xList[i]] == 1) {
                    graph[y + yList[i]][x + xList[i]] = 0;
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

public class P87694 {
    public static void main(String[] args) {

        Solution87694 sol = new Solution87694();

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
