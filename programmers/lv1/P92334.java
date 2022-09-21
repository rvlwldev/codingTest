package programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Solution92334 {

    public int[] solution(String[] id_list, String[] reportDup, int k) {
        int[] answer = new int[id_list.length];
        Arrays.fill(answer, 0);

        // 중복제거
        Set<String> set = new HashSet<String>(Arrays.asList(reportDup));
        ArrayList<String> report = new ArrayList<String>(set);

        HashMap<String, ArrayList<String>> reportMap = new HashMap<>();
        HashMap<String, Integer> reportCnt = new HashMap<>();

        for (String rep : report) {
            String key = rep.split(" ")[0];
            String val = rep.split(" ")[1];

            // 누가 누구를 신고했는지 정리
            if (reportMap.containsKey(key)) {
                ArrayList<String> newVal = reportMap.get(key);
                newVal.add(val);
                reportMap.put(key, newVal);
            } else {
                ArrayList<String> newVal = new ArrayList<>();
                newVal.add(val);
                reportMap.put(key, newVal);
            }

            // 신고횟수 누적
            int count = reportCnt.getOrDefault(val, 0);
            reportCnt.put(val, count + 1);
        }

        for (int i = 0; i < id_list.length; i++) {

            ArrayList<String> reportList = reportMap.get(id_list[i]);

            if (reportList == null)
                continue;

            for (int j = 0; j < reportList.size(); j++) {

                if (reportCnt.get(reportList.get(j)) >= k)
                    answer[i]++;

            }

        }

        return answer;
    }

}

public class P92334 {

    public static void main(String[] args) {

        Solution92334 solution = new Solution92334();

        int[] answer = solution.solution(
                new String[] { "muzi", "frodo", "apeach", "neo" },
                new String[] { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" }, 2);

        System.out.println("Answer is " + Arrays.toString(answer));

    }
}
