package programmers.lv3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution42895 {

    public int solution(int n, int number) {
        if (n == number) return 1;
        if (number == 1) return 2;

        List<Set<Integer>> list = new ArrayList<>();
        list.add(new HashSet<>()); // 0
        list.add(Set.of(n));       // 1

        for (int count = 2; count < 9; count++) {
            list.add(new HashSet<>());

            for (int index = 1; index < count; index++) {
                Set<Integer> setA = list.get(count - index);
                Set<Integer> setB = list.get(index);

                Set<Integer> unionSet = unionSet(setA, setB);

                list.get(count).addAll(unionSet);
                list.get(count).add(appendNumberInRow(n, count));

                if (list.get(count).contains(number)) return count;
            }
        }

        return -1;
    }

    private Set<Integer> unionSet(Set<Integer> aSet, Set<Integer> bSet) {
        Set<Integer> res = new HashSet<>();

        for (int a : aSet) {
            for (int b : bSet) {
                res.add(a + b);
                res.add(a * b);

                res.add(a - b);
                res.add(b - a);

                if (a != 0 && b != 0) {
                    res.add(a / b);
                    res.add(b / a);
                }
            }
        }

        return res;
    }

    private int appendNumberInRow(int target, int len) {
        String appended = String.valueOf(target).repeat(len);
        return Integer.parseInt(appended);
    }
}

public class P42895 {
    public static void main(String[] args) {
        Solution42895 sol = new Solution42895();

        System.out.println(sol.solution(2, 11));   // 3
        System.out.println(sol.solution(3, 27));   // 3
        System.out.println(sol.solution(3, 4));    // 3
        System.out.println(sol.solution(5, 12));   // 4
        System.out.println(sol.solution(5, 3600)); // 6
        System.out.println(sol.solution(3, 2187)); // 7
        System.out.println(sol.solution(8, 5800)); // 8

        System.out.println("answer is " + (sol.solution(8, 5800) == 8));
    }
}
