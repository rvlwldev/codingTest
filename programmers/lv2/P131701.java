package programmers.lv2;

import java.util.HashSet;

class Solution131701 {

    public int solution(int[] elements) {
        HashSet<Integer> answer = new HashSet<>();

        int len = 1;

        while (len <= elements.length) {

            for (int i = 0; i < elements.length; i++) {
                int res = 0;
                for (int j = i; j < i + len; j++) {
                    res += elements[j % elements.length];
                }
                answer.add(res);
            }

            len++;
        }

        return answer.size();
    }


}

public class P131701 {
    public static void main(String[] args) {

        Solution131701 sol = new Solution131701();

        // 18
        System.out.println("answer is " + sol.solution(new int[] { 7, 9, 1, 1, 4 }));

    }
}