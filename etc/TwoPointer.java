package etc;

// https://code-lab1.tistory.com/276

/*
투 포인터 알고리즘이란?
리스트에 순차적으로 접근해야 할 때 [두 개의 점]의 위치를 기록하면서 처리하는 알고리즘
*/

import java.util.Arrays;

public class TwoPointer {
    public static void main(String[] args) {
        SumOfRange range = new SumOfRange();

        // start = 0, end = 0 으로 시작
        // 가장 작은 수열의 사이즈부터 찾는다.
        range.getSumOfSmallestRange();
    }

}

// 범위 합 구하기
class SumOfRange {
    /* 가장작은 범위의 합 구하기

    1. 시작점과 끝점이 첫번째 원소의 인덱스를 가리키도록 한다. (start = 0, end = 0)
    2. 현재 부분 합(start ~ end)이 sum 과 같다면 올바른 범위이다.
    3. 현재 부분 합(start ~ end)이 sum 보다 작다면 end 를 1 증가시킨다.
    4. 현재 부분 합(start ~ end)이 sum 보다 크거나 같다면 start 를 1 증가시킨다.
    5. 모든 경우를 확인할 때까지 2-4번 과정을 반복한다.

    예를 들어
    [1,2,3,2,1] 의 배열이 있고 목표 합이 { 3 } 인 부분 수열을 찾는다면
    start = 0, end = 0 로 초기화한다.

    1. 현재 리스트의 부분합 (인덱스 0,0) : 1
    부분합 1이 목표합 3보다 작으므로 end 값을 증가시킨다.

    2. 현재 리스트의 부분합 (인덱스 0,1) : 3
    목표합 3과 같으므로 인덱스 (0,1)은 올바른 부분수열이다.
    이후의 부분 수열도 찾기 위해 start, end 값을 증가시킨다.

    3. 현재 범위를 인덱스 2,3 이라고 가정 : 5
    목표합 3보다 크므로 start 값을 증가 시킨다.
    (3,3) 부터 탐색을 시작하며 위 과정을 반복한다.

    반복 조건은 start 값이 범위를 벗어날 때 까지이다.
    */
    public void getSumOfSmallestRange() {
        int target = 3; // 찾고자 하는 값
        int[] arr = {1, 2, 3, 2, 1}; // 수열

        System.out.println("찾고자 하는 수열 : " + Arrays.toString(arr) + "\n찾고자 하는 합의 값 : " + target + "\n");

        int stt = 0;  // 시작 인덱스 포인터
        int end = 0;  // 끝나는 인덱스 포인터
        int sum = arr[0]; // 합을 미리 (0,0) 으로 초기화

        while (stt < arr.length) {
            // 현재 합이 찾고자 하는 값보다 작으면 end 포인터를 오른쪽으로 이동하고 합에 값을 추가
            if (sum < target) {
                end++;
                if (end < arr.length) sum += arr[end];
                    // 배열의 길이를 초과한다면 반복 멈추기
                    // 즉 stt, end 둘다 범위를 하나라도 벗어나면 탐색을 멈추게 된다.
                else break;
            } else if (sum > target) {
                // 현재 합이 찾고자 하는 값보다 크면 stt 포인터를 오른쪽으로 이동하고 합에서 값을 제거
                sum -= arr[stt];
                stt++;
            } else {
                System.out.println("부분 수열 발견! (" + stt + " ~ " + end + ")");
                System.out.print("(합이 " + target + "인 부분수열 값) : ");
                for (int i = stt; i <= end; i++) System.out.print(arr[i] + " ");
                System.out.println("\n");

                // 합이 찾고자 하는 값과 동일한 경우 결과 출력 및 stt 포인터를 오른쪽으로 이동하고 합에서 값을 제거
                sum -= arr[stt];
                stt++;
            }
        }

    }

}
