package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



class Solution172927_3 {
    public int solution(int[] picks, String[] minerals) {
        /*
         * 피로도를 최소화 하기 위해서는
         * 자원을 5개를 기준으로 묶고, 5개의 비용을 계산한다.
         * 비용이 제일 비싼건 다이아 곡괭이로 캐고, 비용이 제일 저렴한건 돌 곡괭이로 캔다.
         */
        int answer = 0;

        // 그룹 초기화
        int groupSize = minerals.length / 5 + 1;
        List<Minerals>[] group = new ArrayList[groupSize];
        for(int i = 0; i < groupSize; i++) {
            group[i] = new ArrayList<>();
        }

        // 그룹 생성
        // 전체 곡괭이 갯수만큼 그룹 생성이 가능(1곡괭이가 5개를 캘수 있기 때문)
        // 곡괭이 없이 그룹을 만들어도 캘 수 없다.
        // 곡괭이가 없는데 그룹을 생성하면
        // 그룹을 비용 기준으로 내림차순할 때 데이터 정합성이 안맞는다.(캘 수 없는 자원의 비용이 더 낮을 경우...)
        int count = 0;
        int pickSum = Arrays.stream(picks).sum();
        for(int i = 0; i < minerals.length / 5 + 1; i++) {
            // 곡괭이가 0개 이면
            if(pickSum == 0) {
                // 탈출
                break;
            }
            for(int j = count; j < minerals.length; j++) {
                count++;
                String mineral = minerals[j];
                // 자원 별 비용
                int cost = 0;
                if(mineral.equals("diamond")) {
                    cost = 25;
                } else if(mineral.equals("iron")) {
                    cost = 5;
                } else if(mineral.equals("stone")) {
                    cost = 1;
                }

                // 그룹으로 저장
                group[i].add(new Minerals(cost, mineral));

                // 5개씩 저장
                if(count % 5 == 0) {
                    // 곡괭이 1개 감소
                    pickSum--;
                    // 5개면 탈출~
                    break;
                }
            }
        }

        // 비용 저장(Node에 index, cost를 저장)
        List<Node> cost = new ArrayList<>();
        for(int i = 0; i < group.length; i++) {
            int sum = group[i].stream()
                    .mapToInt(g -> g.getCost())
                    .sum();
            cost.add(new Node(i, sum));
        }

        // 비용을 내림차순 정렬(Node에 저장된 index를 이용해서 group에 있는 자원을 캐자)
        Collections.sort(cost, (o1, o2) -> Integer.compare(o2.getTotalCost(), o1.getTotalCost()));

        // 그룹 별로 비용이 비싼것 부터 cost에 저장되어 있다.
        // 다이아 -> 철 -> 돌 순으로 캔다.
        for(int i = 0; i < cost.size(); i++) {
            // 다이아몬드 곡괭이 사용
            if(picks[0] > 0) {
                int node = cost.get(i).getNode();
                // 피로도 계산
                for(Minerals m : group[node]) {
                    answer += 1;
                }
                // 다이아 곡괭이 감소
                picks[0]--;
            }
            // 철 곡괭이 사용
            else if (picks[1] > 0) {
                int node = cost.get(i).getNode();
                // 피로도 계산
                for(Minerals m : group[node]) {
                    if(m.getMineral().equals("diamond")) answer += 5;
                    else answer += 1;
                }
                // 철 곡괭이 감소
                picks[1]--;
            }
            // 돌 곡괭이 사용
            else if (picks[2] > 0) {
                int node = cost.get(i).getNode();
                // 피로도 계산
                for(Minerals m : group[node]) {
                    if(m.getMineral().equals("diamond")) answer += 25;
                    else if(m.getMineral().equals("iron")) answer += 5;
                    else if(m.getMineral().equals("stone")) answer += 1;
                }
                // 돌 곡괭이 감소
                picks[2]--;
            }
            // 곡괭이 없으면 탈출~
            else {
                break;
            }
        }

        return answer;
    }
}

class Minerals {
    private int cost;
    private String mineral;

    public Minerals(int cost, String mineral) {
        this.cost = cost;
        this.mineral = mineral;
    }

    public int getCost() {
        return cost;
    }

    public String getMineral() {
        return mineral;
    }
}

class Node {
    private int node;
    private int totalCost;


    public Node(int node, int totalCost) {
        this.node = node;
        this.totalCost = totalCost;
    }

    public int getNode() {
        return node;
    }

    public int getTotalCost() {
        return totalCost;
    }

}



public class P172927_try3 {
    public static void main(String[] args) {
        Solution172927_3 sol = new Solution172927_3();

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

        // 16
        System.out.println(sol.solution(new int[]{0, 1, 1},
                new String[]{
                        "iron", "iron", "stone", "stone", "stone",
                        "iron", "iron", "stone"}));
    }
}