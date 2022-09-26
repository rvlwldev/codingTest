package etc;

import java.util.ArrayList;
import java.util.Arrays;

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

        // 방문할 노드의 목록
        boolean[] visit = new boolean[node1.size()];
        Arrays.fill(visit, false);

        // DFS Example
        example(node1, 0, visit, new int[node1.size()]);

    }

    static void example(Node node, int depth, boolean[] visit, int[] result) {

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
            example(node, depth + 1, visit, result);

            // 반복문에서 depth의 들어갈수있는 노드의 모든 경우의 수를 위해서 다시 원복
            visit[i] = false;
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