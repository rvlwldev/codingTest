package etc;

public class Greedy {
// 탐욕법 알고리즘
// 선택의 순간마다 당장 눈앞에 보이는 최적의 상황만을 쫓아 최종적인 해답에 도달
// 때문에 부분적으로는 최적의 해결법이라고 할 수 있지만 전체적으로는 최적의 방법이라고 보장할 수 없다.
// 예로, 최소갯수의 거스름돈 등의 문제가 있다.

    public static void main(String[] args) {

        // 거스름돈 동전 갯수의 최소 갯수 구하는 간단한 예시
        // 당장의 가장 큰 단위의 거스름돈을 선택하는 로직을 반복한다.
        Matroid matroid = new Matroid();

        int result = matroid.getMinimumCoinCount(10000, 9940);
        System.out.println("Result : " + result);

    }
}

class Matroid {

    // 1. 선택 절차(Selection Procedure) : 현재 상태에서의 최적의 해답을 선택한다.
    // 2. 적절성 검사(Feasibility Check) : 선택된 해가 문제아의 조건을 만족했는지 검사한다.
    // 3. 해답 검사(Solution Check) : 원래의 문제가 해결되었는지 검사한 뒤, 그렇지 않다면 선택절차로 돌아가 반복한다.

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