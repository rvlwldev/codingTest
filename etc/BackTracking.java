package etc;

import java.util.Arrays;

// DFS 알고리즘으로 해를 찾아가는 도중, 
// 지금의 경로가 해가 될 것 같지 않으면 그 경로를 더 이상 가지 않고 되돌아감 (boolean[] visit 을 활용하는 경우가 많음)
// 이를 가지치기라고 하는데, 불필요한 부분을 쳐내고 최대한 올바른 쪽으로 간다는 의미

// 재귀적으로 문제를 하나씩 풀어나가되 현재 재귀를 통해 확인 중인 상태(노드)가 
// 제한 조건에 위배되는지(유망하지 않은지) 판단하고 
// 그러한 경우 해당 상태(노드)를 제외하고 다음 단계로 나아가는 방식

// 의사 결정, 최적화, 열거하기 문제에 사용됨
// 대표적으로 N-QUEEN 문제
// https://school.programmers.co.kr/learn/courses/30/lessons/12952

/*
사실 백트래킹은 사용 가능한 경우가 많지만 시간복잡도가 보통 Exponential(지수, 2n꼴)이며, 
대부분 Dynamic Programming, 그리디 알고리즘 등으로 더 빠르게 해결할 수 있다.
*/

public class BackTracking {

    public static void main(String[] args) {
        // Example : 1 부터 N 까지의 숫자중 M개의 조합을 구하라
        BackTrackingExample sol = new BackTrackingExample();

        sol.example01(3, 2);
    }

}

class BackTrackingExample {

    public void example01(int N, int M) {

        boolean[] visit = new boolean[N];
        Arrays.fill(visit, false);
        backtracking(N, M, 0, new int[M], visit);

    }

    private void backtracking(int N, int M, int depth, int[] arr, boolean[] visit) {

        if (depth == M) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = 0; i < N; i++) {
            
            // 가지치기
            if (visit[i]) {
                continue;
            }

            // 만약 해당 노드(값)을 방문하지 않았다면?

            visit[i] = true; // 해당 노드를 방문상태로 변경
            arr[depth] = i + 1; // 해당 깊이를 index로 하여 i + 1 값 저장
            backtracking(N, M, depth + 1, arr, visit); // 다음 자식 노드 방문을 위해 depth 1 증가시키면서 재귀호출

            // 자식노드 방문이 끝나고 돌아오면 방문노드를 방문하지 않은 상태로 변경
            visit[i] = false;
        }
        return;
    }

}
