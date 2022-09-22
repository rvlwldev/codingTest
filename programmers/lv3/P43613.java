package programmers.lv3;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/43163

class Solution43163 {

    static int answer;
    static boolean changable;

    public int solution(String begin, String target, String[] words) {

        answer = Integer.MAX_VALUE;
        changable = false; // 바꿀수있는지 없는지, 재귀가 끝까지 갔을때 바꿀수있음

        boolean[] visit = new boolean[words.length];
        Arrays.fill(visit, false);

        recur(begin, target, words, visit, true, 0);

        if (!changable) {
            return 0;
        }

        return answer;
    }

    private void recur(String begin, String target, String[] words, boolean[] visit, boolean continuable, int count) {

        // 계속 바꿀수 있는지 여부
        if (!continuable) {
            return;
        }

        // 계속 바꿔보다가 바뀐 단어가 타겟과 같다면 답 대입
        if (begin.equals(target)) {
            changable = true;
            answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < words.length; i++) {

            // 바꿨으면 확인안하기
            if (visit[i]) {
                continue;
            }

            String word = words[i];

            // 문자열 길이에서 몇개가 같은지
            int spellCount = getSpellCount(word, begin);

            // 하나빼고 같다면 바꾸기
            visit[i] = spellCount == word.length() - 1;
            recur(word, target, words, visit, visit[i], count + 1);
            visit[i] = false;

        }

    }

    // 문자 각각 스펠링중 몇개가 같은지
    private int getSpellCount(String a, String b) {

        int res = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i))
                res++;
        }

        return res;

    }

}

public class P43613 {
    public static void main(String[] args) {

        Solution43163 sol = new Solution43163();

        System.out.println(
                "Answer is " + sol.solution("hit", "cog",
                        new String[] { "hot", "dot", "dog", "lot", "log", "cog" }));

    }
}