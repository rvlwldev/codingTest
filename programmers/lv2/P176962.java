package programmers.lv2;

import java.util.*;

class Subject {

    String name;
    int start;
    int waste;

    public Subject(String[] info) {
        name = info[0];

        String[] time = info[1].split(":");
        start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

        waste = Integer.parseInt(info[2]);
    }
}

class Solution176962 {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();

        PriorityQueue<Subject> queue = new PriorityQueue<>((a, b) -> a.start - b.start);
        Stack<Subject> delay = new Stack<>();

        for (String[] plan : plans) queue.add(new Subject(plan));

        int clock = 0;
        while (!queue.isEmpty() || !delay.isEmpty()) {
            // 새로운 작업이 없을 때, 미룬 작업 바로 진행
            if (queue.isEmpty()) {
                answer.add(delay.pop().name);
            }
            // 미룬 작업이 없을 때, 다음 작업 바로 진행
            else if (delay.isEmpty()) {
                clock = queue.peek().start;
                delay.push(queue.poll());
            }
            // 해야할 작업과 미룬 작업이 있을 때 (시간 비교)
            else {
                // 다음 작업 시작 보다 미룬 작업 끝나는 시간이 늦을 때
                // 미뤄둔일 조금 해놓기
                if (queue.peek().start < clock + delay.peek().waste) {
                    // 다음 작업 시작까지 남은 시간
                    int gap = queue.peek().start - clock;

                    delay.peek().waste -= gap; // 남은 시간 동안 미룬 작업 하기
                    clock = queue.peek().start;

                    delay.push(queue.poll()); // 다음 작업 바로 할 수 있게
                }
                // 다음 작업 전에 미뤘던 작업을 끝낼 수 있을 때
                else {
                    clock += delay.peek().waste;
                    answer.add(delay.pop().name);
                }
            }
        }
        return answer.toArray(new String[plans.length]);
    }
}


public class P176962 {
    public static void main(String[] args) {

        Solution176962 sol = new Solution176962();

        // "korean", "english", "math"
        System.out.println("answer is " + Arrays.toString(sol.solution(new String[][]{{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}})));

        // "science", "history", "computer", "music"
        System.out.println("answer is " + Arrays.toString(sol.solution(new String[][]
                {{"science", "12:40", "50"},
                        {"music", "12:20", "40"},
                        {"history", "14:00", "30"},
                        {"computer", "12:30", "100"}})));

        // BCAD
        System.out.println("answer is " + Arrays.toString(sol.solution(
                new String[][]{
                        {"A", "12:00", "30"},
                        {"B", "12:10", "10"},
                        {"C", "12:30", "10"},
                        {"D", "12:50", "10"}}
        )));


        /*
        현재시간 :

        남은거 :

        다된거 :

         */
    }
}
