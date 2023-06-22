package programmers.lv3;

import java.util.HashSet;
import java.util.Set;

/*
발견한 문제점

만약 count = 3 이고 n = 3일때
(3 + 3) * 3 = 18
3 + 3 * 3 = 21
괄호에 따라 또는 사칙연산 순서에 따라 결과가 다르기 때문에 경우의 수가 내가 생각했던거 보다 많음

그렇다면 숫자 n 을 4번 사용해서 나오는 값들은
n을 네번 붙인숫자와
n이 2번 사용된 숫자 셋 두개를 서로 사칙연산한것도 포함되고
n이 1번 + n이 3번
n이 3번 + n이 1번 (나누기와 빼기 때문에 순서를 바꿔서)
이 가능함 (별찍기처럼)

하지만 이 코드는
n이 4번 사용되는 모든 경우의 수를
n이 3번 사용되는 모든 경우의 수에 n을 사칙연산 한거 밖에 안되니까
n이 2번 + n이 2번의 합집함을 못만듬...

결론
1 : 1n
2 : (1n,1n)
3 : (1n,2n) (2n,1n)
4 : (2n,2n) (1n,3n) (3n,1n)
5 : (1n,4n) (2n,3n) (3n,2n) (4n,1n)
(1,1,3) 은 성립하지 않음, (1,1)은 이미 2n과 같으니..

또한 초기화만 해준다면 매 반복마다 n을 계산해줄 필요가 없음 ?

...


*/

class Solution42895_2 {

    public int solution_try2(int n, int number) {
        if (n == number) return 1;
        if (number == 1) return 2;

        return checkMinimum(n, number, 1, new HashSet<>());
    }

    private int checkMinimum(int n, int number, int count, Set<Integer> set) {
        if (count > 8) return -1;

        HashSet<Integer> result = new HashSet<>();
        result.add(appendNumberInRow(n, count));

        for (int before : set) {
            result.add(before + n);
            result.add(before * n);

            result.add(before - n);
            result.add(n - before);

            if (before != 0) {
                result.add(before / n);
                result.add(n / before);
            }
        }

        if (result.contains(number)) return count;

        return checkMinimum(n, number, count + 1, result);
    }

    private int appendNumberInRow(int target, int len) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) sb.append(target);

        return Integer.parseInt(sb.toString());
    }
}

public class P42895_try2 {
    public static void main(String[] args) {
        Solution42895_2 sol = new Solution42895_2();

        System.out.println("try2 : " + sol.solution_try2(2, 11));   // 3
        System.out.println("try2 : " + sol.solution_try2(3, 27));   // 3
        System.out.println("try2 : " + sol.solution_try2(3, 4));   // 3
        System.out.println("try2 : " + sol.solution_try2(5, 12));   // 4
        System.out.println("try2 : " + sol.solution_try2(5, 3600)); // 6
        System.out.println("try2 : " + sol.solution_try2(3, 2187)); // 7
        System.out.println("try2 : " + sol.solution_try2(8, 5800)); // 8

    }
}
