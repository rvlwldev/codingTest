package programmers.lv2;

/*
비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다.
부분 수열의 합은 k 입니다.

합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다.
길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾습니다.
수열을 나타내는 정수 배열 Sequence 와 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때,

위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아
return 하는 solution 함수를 완성해주세요.
이때 수열의 인덱스는 0부터 시작합니다.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 연속된 부분 수열의 합
// 투포인터로 풀어보기
public class P178870 {
    public int[] solution(int[] sequence, int k) {
        ArrayList<int[]> list = new ArrayList<>();

        int stt = 0;
        int end = 0;
        int sum = sequence[0];

        while (stt < sequence.length) {
            if (sum < k) {
                end++;
                if (end < sequence.length) sum += sequence[end];
                else break;
            } else if (sum > k) {
                sum -= sequence[stt];
                stt++;
            } else {
                list.add(new int[]{stt, end});
                sum -= sequence[stt];
                stt++;
            }
        }

        list.sort((o1, o2) -> {
            int diff1 = o1[1] - o1[0];
            int diff2 = o2[1] - o2[0];

            if (diff1 == diff2) return o1[0] - o2[0];
            else return diff1 - diff2;
        });

//        print(list);

        return list.get(0);
    }

    private void print(List<int[]> arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        P178870 sol = new P178870();

        // 2,3
        System.out.println("answer is " + Arrays.toString(sol.solution(new int[]{1, 2, 3, 4, 5}, 7)));
        // 6,6
        System.out.println("answer is " + Arrays.toString(sol.solution(new int[]{1, 1, 1, 2, 3, 4, 5}, 5)));
        // 0,2
        System.out.println("answer is " + Arrays.toString(sol.solution(new int[]{2, 2, 2, 2, 2}, 6)));
        // 2,2
        System.out.println("answer is " + Arrays.toString(sol.solution(new int[]{1, 2, 3, 2, 1}, 3)));
    }
}
