package programmers.lv3;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/43238
public class P43283 {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = 1; // 모든 사람이 1분 심사 받을 때
        long right = (long) times[times.length - 1] * n; // 가장 느린 심사관이 모든 사람을 심사하는 경우
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2; // 심사 시간의 중간값
            long count = 0;

            for (int time : times) {
                count += mid / time; // 중간값 동안 심사 가능한 사람 수 누적
                if (count >= n) break;
            }

            if (count >= n) { // 주어진 시간 내에 심사 가능한 사람 수가 n 이상인 경우
                answer = mid; // 최소 시간 갱신
                right = mid - 1; // 더 짧은 시간으로 시도
            } else { // 주어진 시간 내에 심사 가능한 사람 수가 n 미만인 경우
                left = mid + 1; // 더 긴 시간으로 시도
            }
        }

        return answer;
    }
}
