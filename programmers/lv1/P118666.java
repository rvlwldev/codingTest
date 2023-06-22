package programmers.lv1;

import java.util.HashMap;

class Solution118666 {
    public String solution(String[] survey, int[] choices) {

        String[][] type = new String[][] {

                { "R", "T" },
                { "C", "F" },
                { "J", "M" },
                { "A", "N" }

        };

        HashMap<String, Integer> answers = new HashMap<>();

        for (int i = 0; i < survey.length; i++) {

            String L = String.valueOf(survey[i].charAt(0));
            String R = String.valueOf(survey[i].charAt(1));

            // L
            if (choices[i] < 4) {
                answers.put(L, answers.getOrDefault(L, 0) + Math.abs(choices[i] - 4));
            }
            // R
            else if (choices[i] > 4) {
                answers.put(R, answers.getOrDefault(R, 0) + choices[i] - 4);
            }

        }

        // Object[] list = answers.keySet().toArray();

        // for (Object ob : list) {
        // System.out.println(ob + " : " + answers.get(ob));
        // }

        String answer = "";

        for (String[] t : type) {

            int L = answers.getOrDefault(t[0], 0);
            int R = answers.getOrDefault(t[1], 0);

            if (L == R) {
                answer += t[0].compareTo(t[1]) < 1 ? t[0] : t[1];
            } else if (L > R) {
                answer += t[0];
            } else {
                answer += t[1];
            }

        }

        return answer;
    }
}

public class P118666 {
    public static void main(String[] args) {

        Solution118666 solution = new Solution118666();

        String answer = solution.solution(new String[] { "AN", "CF", "MJ", "RT", "NA" }, new int[] { 5, 3, 2, 7, 5 });

        System.out.println("Answer is " + answer);

    }
}
