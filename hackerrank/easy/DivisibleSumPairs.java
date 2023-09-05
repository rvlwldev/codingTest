package hackerrank.easy;

import java.util.List;

public class DivisibleSumPairs {



    public static int divisibleSumPairs(int n, int k, List<Integer> ar) {
        int answer = 0;

        for (int i = 0; i < ar.size(); i++) {
            int a = ar.get(i);
            int b;
            for (int j = i + 1; j < ar.size(); j++) {
                b = ar.get(j);

                if ((a + b) % k == 0) answer++;
            }

        }

        return answer;
    }




}
