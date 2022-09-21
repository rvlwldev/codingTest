package programmers.lv1;

import java.util.HashMap;
import java.util.List;

class Solution67256 {

    static HashMap<String, List<Integer>> keypadV;
    static HashMap<Integer, List<Integer>> keypadH;

    public String solution(int[] numbers, String hand) {
        String answer = "";

        // 키패드 만들기 (세로기준)
        keypadV = new HashMap<>();
        keypadV.put("L", List.of(1, 4, 7));
        keypadV.put("R", List.of(3, 6, 9));
        keypadV.put("M", List.of(2, 5, 8, 0));

        // 키패드 생성 (가로기준)
        keypadH = new HashMap<>();
        keypadH.put(0, List.of(1, 2, 3));
        keypadH.put(1, List.of(4, 5, 6));
        keypadH.put(2, List.of(7, 8, 9));
        keypadH.put(3, List.of(42, 0, 35)); // 42 : *, 35 : #

        int lt = '*'; // 왼손의 첫위치
        int rt = '#'; // 오른손의 첫위치

        for (int i = 0; i < numbers.length; i++) {

            int num = numbers[i];

            if (keypadV.get("L").contains(num)) {
                answer += 'L';
                lt = num;
            } else if (keypadV.get("R").contains(num)) {
                answer += 'R';
                rt = num;
            } else if (keypadV.get("M").contains(num)) {
                if (lt == num) { // 왼손 그대로 있다면
                    answer += 'L';
                } else if (rt == num) { // 오른손에 그대로 있다면
                    answer += 'R';
                } else {

                    // 거리계산해서 더 가까이 있는 손으로
                    if (getDistance(num, lt) < getDistance(num, rt)) {
                        answer += 'L';
                        lt = num;
                    } else if (getDistance(num, lt) > getDistance(num, rt)) {
                        answer += 'R';
                        rt = num;
                    } else {
                        // 같을때 hand에 가까이 있는 쪽으로
                        if (hand.equals("left")) {
                            answer += 'L';
                            lt = num;
                        } else {
                            answer += 'R';
                            rt = num;
                        }
                    }
                }
            }
        }

        return answer;
    }

    private int getDistance(int target, int loc) {

        int[] from = new int[2]; // 0 : 줄위치, 1 : 칸위치
        int[] to = new int[2];

        // 출발위치 찾기
        for (int i = 0; i < keypadH.keySet().size(); i++) {
            if (keypadH.get(i).contains(loc)) {
                from[0] = i;
                from[1] = keypadH.get(i).indexOf(loc) + 1;
                break;
            }
        }

        // 목표의 위치찾기
        for (int i = 0; i < keypadH.keySet().size(); i++) {
            if (keypadH.get(i).contains(target)) {
                to[0] = i;
                to[1] = keypadH.get(i).indexOf(target) + 1;
                break;
            }
        }

        // 가로, 세로의 각각의 위치의 거리 반환
        return Math.abs(to[0] - from[0]) + Math.abs(to[1] - from[1]);
    }
}

public class P67256 {
    public static void main(String[] args) {
        Solution67256 solution = new Solution67256();

        // LRLLLRLLRRL
        // String answer = solution.solution(new int[] { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5
        // }, "right");

        // LRLLRRLLLRR
        String answer = solution.solution(new int[] { 7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2 }, "left");

        System.out.println("Answer is " + answer);

    }
}
