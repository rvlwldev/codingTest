package programmers.lv1;

import java.util.Arrays;
import java.util.HashMap;

public class P160586 {
    public static void main(String[] args) {
        Solution160586 sol = new Solution160586();

        System.out.println(Arrays.toString(sol.solution(new String[]{"ABACD", "BCEFD"}, new String[]{"ABCD", "AABB"}))); // [9, 4]
        System.out.println(Arrays.toString(sol.solution(new String[]{"AA"}, new String[]{"B"}))); // [-1]
        System.out.println(Arrays.toString(sol.solution(new String[]{"AGZ", "BSSS"}, new String[]{"ASA", "BGZ"}))); // [4, 6]
    }
}


class Solution160586 {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        HashMap<Character, Integer> hashMap = getHashMapByKeymap(keymap);

        for (int i = 0; i < targets.length; i++) {
            int sum = 0;
            char[] arr = targets[i].toCharArray();

            for (char c : arr) {
                if (!hashMap.containsKey(c)) {
                    sum = -1;
                    break;
                }
                sum += hashMap.get(c);
            }
            answer[i] = sum;
        }


        return answer;
    }

    private HashMap<Character, Integer> getHashMapByKeymap(String[] keymap) {
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (String str : keymap) {
            char[] arr = str.toCharArray();

            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                int val = hashMap.getOrDefault(c, Integer.MAX_VALUE);

                hashMap.put(c, Math.min(val, i + 1));
            }
        }

        return hashMap;

    }

}