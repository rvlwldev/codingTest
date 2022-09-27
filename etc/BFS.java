package etc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 너비 우선 탐색

// 시작 정점으로부터 가까운 정점을 먼저 방문하고 멀리 떨어져 있는 정점을 나중에 방문하는 순회 방법
// 선입선출 (Queue) 구조를 사용하는 알고리즘이다
// 두 노드 사이의 최단 경로 혹은 임의의 경로를 찾고 싶을 때 사용
// 어떤 노드를 방문했었는지 여부를 반드시 검사 해야 한다는 것이다.
// 이를 검사하지 않을 경우 무한루프에 빠질 위험이 있다.

// 보통 큐를 이용하여 큐가 모두 빌 때까지 반복한다.

// 웹 크롤링, 두 노드 사이의 최단경로 찾기 등에서 사용

// Breadth-First Search
public class BFS {

    public static void main(String[] args) {

        // 8개의 노드와 각각의 노드가 연결된 노드의 정보를 담은 2차원 배열 (ASC 정렬)
        int[][] graph = new int[][] {
                {}, // 노드가 1부터 시작하기 때문에 인덱스와 맞추기위해 0번 인덱스는 제외
                /* 1 */ { 2, 3, 8 },
                /* 2 */ { 1, 7 },
                /* 3 */ { 1, 4, 5 },
                /* 4 */ { 3, 5 },
                /* 5 */ { 3, 4 },
                /* 6 */ { 7 },
                /* 7 */ { 2, 6, 8 },
                /* 8 */ { 1, 7 } };

        ArrayList<Integer> searchOrder = new ArrayList<>();

        // 첫번쨰 시작 노드를 가지고 있어야한다.
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        searchOrder.add(1);

        boolean[] visit = new boolean[graph.length];
        visit[1] = true;

        while (!queue.isEmpty()) {

            int currentNode = queue.poll();

            for (int node : graph[currentNode]) {
                if (visit[node]) {
                    continue;
                }

                queue.offer(node);
                searchOrder.add(node);

                visit[node] = true;
            }

        }

        System.out.println("탐색 순서 : " + searchOrder);

    }

}
