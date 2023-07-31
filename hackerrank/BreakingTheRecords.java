package hackerrank;

import java.util.ArrayList;
import java.util.List;

public class BreakingTheRecords {
    public static List<Integer> breakingRecords(List<Integer> scores) {
        List<Integer> answer = new ArrayList<>();

        int min = scores.get(0);
        int minCount = 0;
        int max = scores.get(0);
        int maxCount = 0;

        for (int i = 1; i < scores.size(); i++) {
            int score = scores.get(i);

            if (score < min) {
                min = score;
                minCount++;
            }

            if (score > max) {
                max = score;
                maxCount++;
            }

        }

        answer.add(maxCount);
        answer.add(minCount);
        return answer;
    }

}
