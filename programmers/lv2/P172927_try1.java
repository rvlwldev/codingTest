package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/172927

// 광물을 5개씩 자른 배열을 만들고
// 각 배열에 하나씩 대입해서 최소값을 만들고
// 최소값을 뽑아낸 picks의 인덱스에 -1 적용한다.
// 최소값을 누적해서 가장 작은 값을 리턴한다.
class Solution172927 {

    private int answer = 0;
    private List<Integer> tool;
    private final String[] toolNames = {"diamond", "iron", "stone"};

    public int solution(int[] picks, String[] minerals) {
        // 리스트로 변환
        tool = Arrays.stream(picks)
                .boxed()
                .collect(Collectors.toList());

        // 5개씩 나눈 광물배열 리스트
        List<String[]> list = new ArrayList<>();

        int t = 0;
        while (t < minerals.length) {
            list.add(Arrays.copyOfRange(minerals, t, t + 5));
            t = t + 5;
        }

        for (String[] arr : list) mining(arr);

        return answer;
    }

    // 5개씩 광물을 캤을때, 도구별 피로도의 최소값 누적
    private void mining(String[] minerals) {
        List<Integer> tiredList = new ArrayList<>();

        for (int i = 0; i < tool.size(); i++) {
            // 곡괭이가 없으면 스킵
            if (tool.get(i) < 1) tiredList.add(Integer.MAX_VALUE);
            else tiredList.add(getTired(toolNames[i], minerals));
        }

        int min = tiredList.stream()
                .min(Comparator.comparingInt(integer -> integer))
                .orElse(0);

        if (min < Integer.MAX_VALUE) {
            answer += min;
            useTool(tiredList, min);
        }

    }

    // 도구별 피로도 계산
    private int getTired(String tool, String[] minerals) {
        int tired = 0;

        for (String mineral : minerals) {
            if (mineral == null) continue;

            if (mineral.equals(tool) || tool.equals("diamond")) tired += 1;

            else if (tool.equals("iron")) {
                if (mineral.equals("diamond")) tired += 5;
                else tired += 1;
            } else if (tool.equals("stone")) {
                if (mineral.equals("diamond")) tired += 25;
                else if (mineral.equals("iron")) tired += 5;
            }

        }

        return tired;
    }

    // 최소값을 도출한 도구의 개수를 -1
    private void useTool(List<Integer> list, int minValue) {
        int index = list.indexOf(minValue);
        tool.set(index, tool.get(index) - 1);
    }

}


public class P172927_try1 {
    public static void main(String[] args) {
        Solution172927 sol = new Solution172927();

        // 12
        System.out.println(sol.solution(new int[]{1, 3, 2},
                new String[]{
                        "diamond", "diamond", "diamond", "iron", "iron",
                        "diamond", "iron", "stone"}));

        // 50
        System.out.println(sol.solution(new int[]{0, 1, 1},
                new String[]{
                        "diamond", "diamond", "diamond", "diamond", "diamond",
                        "iron", "iron", "iron", "iron", "iron",
                        "diamond"}));
    }
}

