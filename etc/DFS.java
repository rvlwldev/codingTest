package etc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
    // 깊이 우선 탐색 (Depth First Search)
    // 넓게 보기 전에 끝까지 파고 들어가봄
    // 단점 : 해가 없는 경우에 빠져 시간이 많이 소요될수 있음

    // 인접한 노드를 모두 방문하면서 하나의 노드를 방문하고 넘어갈때,
    // 더 이상 방문할 노드가 없을때까지 인접한 노드를 방문한다.
    // 1번 노드를 방문하고 2번노드를 방문했을때 1번의 인접한 노드를 방문하기 전에
    // 2번의 모든 노드를 방문한다.

    public static void main(String[] args) {

        Node node1 = new Node();

        // 3개의 노드를 추가 ()
        node1.addNode();
        node1.addNode();
        node1.addNode();

        // 방문된 1차원 노드의 목록
        boolean[] visit = new boolean[node1.size()];
        Arrays.fill(visit, false);

        // DFS Example
        System.out.println("1, 2, 3 의 노드를 DFS로 순환 시 경우의 수");
        example01(node1, 0, visit, new int[node1.size()]);
        System.out.println();

        // 8개의 노드와 각각의 노드가 연결된 노드의 정보를 담은 2차원 배열 (ASC 정렬)
        int[][] graph = new int[][] {
                {}, // 노드가 1부터 시작하기 때문에 인덱스와 맞추기위해 0번 인덱스는 제외
                { 2, 3, 8 },
                { 1, 7 },
                { 1, 4, 5 },
                { 3, 5 },
                { 3, 4 },
                { 7 },
                { 2, 6, 8 },
                { 1, 7 } };

        // 방문된 1차원 노드의 목록
        visit = new boolean[graph.length];
        Arrays.fill(visit, false);

        // 1 -> 2 -> 7 -> 6 -> 8 -> 3 -> 4 -> 5
        System.out.println("8개의 노드와 각각의 노드가 연결된 노드의 정보를 담은 2차원 배열로\n노드를 탐색할 경우");
        example02(graph, 1, visit, new ArrayList<>());

        System.out.println();
        System.out.println("8개의 노드와 각각의 노드가 연결된 노드의 정보를 담은 2차원 배열로\n스택을 이용해 노드를 탐색할 경우");
        Arrays.fill(visit, false);
        example03(graph, 1, visit);

    }

    static void example01(Node node, int depth, boolean[] visit, int[] result) {

        // 존재하는 모든 노드의 갯수를 넘으면 종료
        if (node.size() == depth) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = 0; i < node.size(); i++) {
            // 이미 방문했다면 탐색하지 않음
            if (visit[i]) {
                continue;
            }

            // 현재 depth에 해당노드를 기록
            // depth가 곧 탐색의 순서가됨
            result[depth] = node.getNode(i);

            // 다음 재귀때 중복을 피하기 위해서
            visit[i] = true;

            // 재귀 시작
            // 현재의 정보와 depth + 1 로 탐색의 순서를 진행시킴
            example01(node, depth + 1, visit, result);

            // 반복문에서 depth의 들어갈수있는 노드의 모든 경우의 수를 위해서 다시 원복
            visit[i] = false;
        }
    }

    static void example02(int[][] graph, int start, boolean[] visit, ArrayList<Integer> result) {
        // 현재 노드 방문처리
        visit[start] = true;

        // 탐색순서결과에 현재 노드를 추가
        result.add(start);
        System.out.println("(example02) 현재 탐색 순서 : " + result);

        // 현재 노드와 인접한 노드들을 확인한다.
        for (int node : graph[start]) {
            // 방문되지 않았다면 재귀로 인접한 노드를 대상으로 확인한다.
            if (!visit[node]) {
                example02(graph, node, visit, result);
            }
        }

    }

    static void example03(int[][] graph, int start, boolean[] visit) {

        // 스택 생성 (스택은 후입 선출)
        Stack<Integer> stack = new Stack<>();

        // 스택에 현재 노드의 인접 노드들을 추가해준다.
        for (int node : graph[start]) {
            stack.push(node);
        }
        System.out.println();
        System.out.println("현재 확인 노드 : " + start + "  남은 스택 목록 : " + stack);

        // 인접한 노드가 추가되지 않을때까지 반복
        while (!stack.empty()) {

            // 현재 노드 (후입 선출)
            int now = stack.pop();

            System.out.println("현재 확인 노드 : " + now + "  남은 스택 목록 : " + stack);

            // 방문하지 않은 노드라면
            if (!visit[now]) {
                // 방문하지 않은 노드의 인접노드들을 체크함
                System.out.print("추가 되는 노드 목록 : ");
                for (int i : graph[now]) {
                    // 인접한 노드도 방문하지 않았다면
                    if (!visit[i]) {
                        stack.push(i); // 인접한 노드로 추가
                        System.out.print(i + " ");

                        visit[i] = true; // 방문 처리
                    }
                }
                System.out.println();
            }
        }
    }
}

// 노드 클래스 생성
class Node {

    private ArrayList<Integer> nodeList = new ArrayList<>();

    void addNode() {
        this.nodeList.add(nodeList.size() + 1);
    }

    int getNode(int n) {
        return nodeList.get(n);
    }

    int size() {
        return nodeList.size();
    }

    void print() {
        System.out.println();

        System.out.println(this.nodeList);

        System.out.println();
    }

}