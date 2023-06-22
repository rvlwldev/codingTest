package programmers.lv3;

import java.util.ArrayList;

class Solution87694_recur {

    static int[] yList;
    static int[] xList;
    static int[][] graph;
    static ArrayList<Integer> res;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        yList = new int[] { 1, -1, 0, 0 };
        xList = new int[] { 0, 0, 1, -1 };

        res = new ArrayList<>();

        // 2차원 배열로 맵을 만든다 (2배 크게)
        // 주어진 사각형들보다 -1 작은 맵으로 0 을 채워서 테두리만 남긴다.

        // 최대 50이라서 102 까지
        // 인덱스는 0부터 시작해서 + 1
        // 다음 길을 찾을때 에러 때문에 + 1
        graph = createGraph(rectangle, new int[102][102]);

        // 2배 크게 만들었기 때문에
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        graph[itemY][itemX] = 7; // 아이템(7) 위치 설정

        recur(characterY, characterX, 0);

        // 어차피 아이템까지 도달하려면 2개의 길밖에 없음
        return Math.min(res.get(0), res.get(1));
    }

    private void recur(int y, int x, int count) {

        if (graph[y][x] == 7) {
            res.add(count / 2);
            return;
        }

        // 현재 길은 다시는 갈수없게
        graph[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int next = graph[y + yList[i]][x + xList[i]];
            if (next == 1 || next == 7) {
                recur(y + yList[i], x + xList[i], count + 1);
            }
        }

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

public class P87694_recur {
    public static void main(String[] args) {

        Solution87694_recur sol = new Solution87694_recur();

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
