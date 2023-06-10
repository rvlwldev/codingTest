package etc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Greedy {
// 탐욕법 알고리즘
// 선택의 순간마다 당장 눈앞에 보이는 최적의 상황만을 쫓아 최종적인 해답에 도달
// 때문에 부분적으로는 최적의 해결법이라고 할 수 있지만 전체적으로는 최적의 방법이라고 보장할 수 없다.
// 예로, 최소갯수의 거스름돈 등의 문제가 있다.

// 해결방법
// 1. 선택 절차(Selection Procedure) : 현재 상태에서의 최적의 해답을 선택한다.
// 2. 적절성 검사(Feasibility Check) : 선택된 해가 문제아의 조건을 만족했는지 검사한다.
// 3. 해답 검사(Solution Check) : 원래의 문제가 해결되었는지 검사한 뒤, 그렇지 않다면 선택절차로 돌아가 반복한다.

// 적절한지 검사하는 로직에, 해답검사가 필요없도록 구현하는 방법이 가장 효율적인 방법으로 구현될 수 있다.


    public static void main(String[] args) {

        // 거스름돈 동전 갯수의 최소 갯수 구하는 간단한 예시
        // 당장의 가장 큰 단위의 거스름돈을 선택하는 로직을 반복한다.
        Matroid matroid = new Matroid();

        int result = matroid.getMinimumCoinCount(10000, 9940);
        System.out.println("거스름돈 결과 : " + result);


        Camera camera = new Camera();
        int[][] routes = new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        System.out.println("카메라 최소대수 계산 결과 : " + camera.getInstallCameraCount(routes));
    }
}

class Camera {
// https://school.programmers.co.kr/learn/courses/30/lessons/42884
// 고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때,
// 모든 차량이 한 번은 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는지?

    /* 제한사항 */
// 차량의 대수는 1대 이상 10,000대 이하
// 차량의 진입 지점, 진출 지점 은 -30,000 이상 30,000 이하
// routes[i][0]에는 i번째 차량이 고속도로에 진입
// routes[i][1]에는 i번째 차량이 고속도로에서 나간 지점

    /* 추론 */
// 진입지점의 오름차순으로 정렬한다.
// 고속도로루트에서 해결 여부를 확인하고 미해결시 카메라를 추가하고 마지막 카메라 위치를 수정한다.
// 진입시점 이후에 카메라가 위치해 있다면, 카메라 위치를 수정할 필요가 없다.

    public int getInstallCameraCount(int[][] routes) {
        int count = 0;
        int position = Integer.MIN_VALUE;

        // 선택절차로써 진입시점순으로 정렬
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));

        for (int[] route : routes) {

            // 진입시점보다 카메라가 뒤에 있다면 찍히지 않는다.
            if(route[0] > position) {
                count++;

                // 진출시점에 카메라를 설치하여 카메라에 찍힌다.
                position = route[1];
            }

        }

        return count;
    }

}

class Matroid {

    // 선택절차 배열
    private int[] COIN_TYPE = new int[]{10000, 5000, 1000, 500, 100, 50, 10, 1};

    public int getMinimumCoinCount(int totalPrice, int payAmount) {

        // 거스름돈 금액 판별 및 정합성 확인
        int change = totalPrice - payAmount;
        if (change < 1) return 0;

        int coinCount = 0;
        int index = 0;

        while (change > 0 && index < COIN_TYPE.length) {
            int coin = COIN_TYPE[index];

            // 적절성 검사 이후 거스름돈 처리
            if (isFeasible(change, coin)) {
                int count = change / coin;
                coinCount += count;
                change -= coin * count;
            }

            index++;

            if (isSolution(change)) return coinCount;
        }

        return 0;
    }

    // 적절성 검사: 선택된 동전이 거스름돈보다 작거나 같은지 확인
    private boolean isFeasible(int change, int coin) {
        return coin <= change;
    }

    // 해답 검사: 거스름돈이 모두 나누어 떨어지는지 확인
    private boolean isSolution(int change) {
        return change == 0;
    }

}