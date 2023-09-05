package hackerrank.easy;

import java.util.HashMap;
import java.util.List;

public class MigratoryBirds {

    public static int migratoryBirds(List<Integer> arr) {
        int answer = 0;
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int b : arr) {
            int count = countMap.getOrDefault(b, 0);
            countMap.put(b, count + 1);
        }

        int max = 0;
        for (int key : countMap.keySet()) {
            int val = countMap.get(key);

            if (val > max) {
                max = val;
                answer = key;
            }
        }

        return answer;
    }
}
