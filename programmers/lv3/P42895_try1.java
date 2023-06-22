package programmers.lv3;

/*

https://school.programmers.co.kr/learn/courses/30/lessons/42895

불필요한 계산을 줄이고, 효율적으로 최적해를 찾아야만 풀리는 문제
동적계획법 (Dynamic Programming)

문제
파라미터 숫자 N이 주어지면 N을 사칙연산(+-/*)으로 파라미터 number 와 같은 값을 도출해낼 수 있는 최소 N의 개수 구하기
최소 N의 개수가 8보다 크면 -1 반환

[분석]
number 1이라면 n/n으로 2, 두 파라미터가 같다면 1
number 가 n보다 항상 크다고 안했으니 위치 바꿔서 잘 빼고 나눠야댐
문자열을 붙여서 22, 222 등의 숫자를 반환하는 기능을 만들어야 한다.

어차피 8보다 크면 -1 이니까
8번 반복하며 한번의 반복마다 모든 경우의 수를 반복한다고 하면 구현방식은 아래와 같다

[try 1]
n이 한개일때는
n 1개

n이 두개일때는
nn
n + n
n * n
n - n
n / n
5개

n이 세개일때는
nnn
nn + n
nn * n
nn - n
n - nn
nn / n
n / nn
n + n + n
n + n * n
n - n + n ...
여기서 곱하기, 나누기에 괄호까지 조합하면 너무 많은데...

내가 알아낸 패턴
n이 3개라면 nnn과 n이 2개일때 나온 모든 값에 각각의 사칙연산을 적용해서 모든 경우의 수를 찾을 수 있다.

try1 의 문제점
n이 1이고 4개의 1을 조합할때,
1111
2 + 1 = 3
2 * 1 = 2
2 - 1 = 1
1 - 2 = -1
2 / 1 = 2
1 / 2 = 0
3 + 1 = 4
3 * 1 = 3
3 - 1 = 2
1 - 3 = -2
3 / 1 = 3
1 / 3 = 0 ...
이런식으로 도출되기 때문에 1이 4개가 아님.... 4개 보다 적게 (2,3개) 조합했을때 답이 나왔다면 그떄 끝났어야댐
불필요한 계산도 하고 있음
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution42895_1 {

    // 이 코드는 9개의 테스트케이스에서 5~8 실패
    public int solution_try1(int n, int number) {
        if (n == number) return 1;
        if (number == 1) return 2;
//        if (n == 1 && number < 8) return number; // 이건 한참 나중에 생각난거임

        // 갯수별 모든 사칙연산의 결과 목록
        List<Set<Integer>> calList = new ArrayList<>();

        Set<Integer> initSet = new HashSet<>();
        initSet.add(n);

        calList.add(initSet);

//        System.out.println(sumList);

        for (int i = 1; i <= 8; i++) {
            Set<Integer> set = getAllSum(calList.get(i - 1), n, i);

            if (set.contains(number)) {
//                System.out.println("답 : " + (i + 1) + "에서 " + number + "를 찾음");
//                System.out.println(set);
//                System.out.println("총 : " + set.size());
                return i + 1;
            }

            calList.add(set);
        }

        // 답이 최솟값이 8보다 크면 -1을 return
        return -1;
    }

    private Set<Integer> getAllSum(Set<Integer> preList, int n, int index) {
//        System.out.println("숫자가 " + (index + 1) + "개 일때");


        Set<Integer> set = new HashSet<>();

        int appendedNumber = appendNumberInRow(n, index + 1);
        set.add(appendedNumber);
//        System.out.println("이어붙인값 : " + appendedNumber);

        if (preList != null) {
//            set.addAll(preList);
            for (int preSum : preList) {
//                System.out.println(preSum + " + " + n + " = " + (preSum + n));
//                System.out.println(preSum + " * " + n + " = " + preSum * n);
//                System.out.println(preSum + " - " + n + " = " + (preSum - n));
//                System.out.println(n + " - " + preSum + " = " + (n - preSum));
//                System.out.println(preSum + " / " + n + " = " + (preSum / n));
//                System.out.println(n + " / " + preSum + " = " + (n / preSum));

                set.add(preSum + n);
                set.add(preSum * n);

                set.add(preSum - n);
                set.add(n - preSum);

                if (preSum != 0) {
                    set.add(preSum / n);
                    set.add(n / preSum);
                }
            }
        }

        // number 1 이상이니까 0 빼기
//        set = set.stream()
//                .filter(num -> num != n)
//                .filter(num -> !preList.contains(num))
//                .collect(Collectors.toSet());

        return set;
    }

    private int appendNumberInRow(int target, int len) {
        // 길이만큼 숫자를 붙이는 메소드
        return Integer.parseInt(String.valueOf(target).repeat(len));
    }
}


public class P42895_try1 {
    public static void main(String[] args) {
        Solution42895_1 sol = new Solution42895_1();

        System.out.println("try1 : " + sol.solution_try1(2, 11)); // 3
        System.out.println("try1 : " + sol.solution_try1(3, 27)); // 3
        System.out.println("try1 : " + sol.solution_try1(5, 12)); // 4
        System.out.println("try1 : " + sol.solution_try1(5, 3600)); // 6
        System.out.println("try1 : " + sol.solution_try1(3, 2187)); // 7
        System.out.println("try1 : " + sol.solution_try1(8, 5800)); // 8

    }
}



