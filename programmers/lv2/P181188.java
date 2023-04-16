package programmers.lv2;

import java.util.Arrays;

public class P181188 {
    class Solution {

        public int solution(int[][] targets) {
            Arrays.sort(targets, (a, b) -> a[1] - b[1]);

            int answer = 1;
            int tail = targets[0][1];

            for (int[] target : targets) {
                if(target[0] >= tail) {
                    answer++;
                    tail = target[1];
                }
            }

            return answer;
        }
    }
}
