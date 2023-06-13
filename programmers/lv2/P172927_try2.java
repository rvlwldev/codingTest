package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;


// 객체지향적으로
// 미네랄은 도구별로 소모되는 비용을 알 수 있어야한다.
// 또는 도구는 미네랄별로 소모되는 비용을 알 수 있어야한다.

// 광석을 순서대로 5개씩 그룹으로 묶는다.
// 각 그룹을 돌면서 3개의 도구로 각 그룹을 캤을때,
// 피로도가 가장 낮은 도구의 카운트 - 1
// 가장 낮은 피로도를 누적한 뒤 리턴한다.

class Solution172927_2 {

    public static final List<String> TYPE_NAMES = List.of("diamond", "iron", "stone");

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        List<MineralGroup> groups = getGroupList(minerals);
        List<Tool> tools = getToolList(picks);

        System.out.println(groups);
        System.out.println(tools);

        for (MineralGroup group : groups) {

            List<Integer> costs = new ArrayList<>();

            for (Tool tool : tools) {
                if (tool.usable()) costs.add(group.getAllCost(tool));
                else costs.add(Integer.MAX_VALUE);
            }

            int min  = Collections.min(costs);
            int minIndex = costs.indexOf(min);

            tools.get(minIndex).use();

            answer += min;
        }

        return answer;
    }

    private List<MineralGroup> getGroupList(String[] minerals) {
        List<MineralGroup> result = new ArrayList<>();

        List<Mineral> mineralList = Arrays.stream(minerals)
                .map(Mineral::new)
                .collect(Collectors.toList());

        int t = 0;
        while (t < mineralList.size()) {
            List<Mineral> subList = mineralList.subList(t, Integer.min(t + 5, mineralList.size()));
            MineralGroup group = new MineralGroup(subList);
            result.add(group);
            t += 5;
        }

        return result;
    }

    private List<Tool> getToolList(int[] picks) {
        List<Tool> res = new ArrayList<>();
        for (int i = 0; i < picks.length; i++) res.add(new Tool(TYPE_NAMES.get(i), picks[i]));
        return res;
    }
}

class MineralGroup {
    private List<Mineral> group;

    MineralGroup(List<Mineral> list) {
        group = list;
    }

    public int getAllCost(Tool tool) {
        int sum = 0;
        for (Mineral mineral : group) sum += mineral.getCost(tool);
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Mineral m : group) res.append(m.getType()).append(" ");
        return res.toString();
    }
}

class Mineral {
    private String type;

    Mineral(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getCost(Tool tool) {
        String toolType = tool.getType();

        if (toolType.equals(type) || toolType.equals("diamond")) return 1;

        switch (toolType) {
            case "iron":
                if (type.equals("diamond")) return 5;
                else return 1;

            case "stone":
                if (type.equals("diamond")) return 25;
                else if (type.equals("iron")) return 5;
                else return 1;
        }

        return 0;
    }
}

class Tool {
    private final String type;
    private int count;

    Tool(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void use() {
        count--;
    }

    public boolean usable() {
        return this.count > 0;
    }

    @Override
    public String toString() {
        return this.type + "(" + count + ")";
    }
}


public class P172927_try2 {
    public static void main(String[] args) {
        Solution172927_2 sol = new Solution172927_2();

        // 12
//        System.out.println(sol.solution(new int[]{1, 3, 2},
//                new String[]{
//                        "diamond", "diamond", "diamond", "iron", "iron",
//                        "diamond", "iron", "stone"}));

        // 50
        System.out.println(sol.solution(new int[]{0, 1, 1},
                new String[]{
                        "diamond", "diamond", "diamond", "diamond", "diamond",
                        "iron", "iron", "iron", "iron", "iron",
                        "diamond"}));

        // 16
//        System.out.println(sol.solution(new int[]{0, 1, 1},
//                new String[]{
//                        "iron", "iron", "stone", "stone", "stone",
//                        "iron", "iron", "stone"}));
    }
}