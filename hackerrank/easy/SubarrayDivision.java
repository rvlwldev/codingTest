package hackerrank.easy;

import java.util.List;

public class SubarrayDivision {
    public static int birthday(List<Integer> s, int d, int m) {
        int answer = 0;

        for (int i = 0; i <= s.size() - m; i++) {
            List<Integer> copy = s.subList(i, i + m);

            if (getSum(copy) == d) answer++;
        }

        return answer;
    }

    private static int getSum(List<Integer> list){
        return list.stream().reduce(0, Integer::sum);
    }

    public static void main(String[] args) {

    }
}
