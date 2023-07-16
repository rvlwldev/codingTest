package etc;

// 이분 탐색

import java.util.Arrays;

/*
이분 탐색(Binary Search)은 결정 문제(Decision Problem)의 답이 이분적일 때 사용할 수 있는 탐색 기법이다.

이때 결정 문제란 답이 Yes or No인 문제를 의미하며 (이분 탐색 문제에서는)
보통 1개의 parameter  가진다.

특정 리스트나 배열을 오름차 순으로 정렬한 뒤
길이(size or length)를 low - mid - high 로 인덱스 값을 분할하고 초기화 한다.

이후 원하는 값을 찾을 때 까지 탐색 범위를 재조정 하며 비교한다.
1. mid 인덱스의 값이 찾고자 하는 값과 동일할 때까지 반복한다.
2. mid 값이 찾고자 하는 값보다 작다면, 찾고자 하는 값은 mid 값보다 크므로
   탐색 범위를 mid + 1부터 high 까지로 조정한다.
3. mid 값이 찾고자 하는 값보다 크다면 찾고자 하는 값은 mid 값보다 작으므로
   탐색 범위를 low 부터 mid - 1 까지로 조정한다.
*/
public class BinarySearch {

    public static void main(String[] args) {
        example e = new example();
//        e.example();

        // 예시문제 테스트 코드

        // 200
        System.out.print(e.solution(11, 4, new long[]{802, 743, 457, 539})); // 200
        System.out.println(" : " + (e.solution(11, 4, new long[]{802, 743, 457, 539}) == 200));

        // 2147483647
        System.out.print(e.solution(1, 1, new long[]{2147483647}));
        System.out.println(" : " + (e.solution(1, 1, new long[]{2147483647}) == 2147483647));

        // 100
        System.out.print(e.solution(1, 1, new long[]{100}));
        System.out.println(" : " + (e.solution(1, 1, new long[]{100}) == 100));

        // 1
        System.out.print(e.solution(3, 2, new long[]{3, 2}));
        System.out.println(" : " + (e.solution(3, 2, new long[]{3, 2}) == 1));

        // 1
        System.out.print(e.solution(5, 5, new long[]{1, 1, 1, 1, 1}));
        System.out.println(" : " + (e.solution(5, 5, new long[]{1, 1, 1, 1, 1}) == 1));

        // 1
        System.out.print(e.solution(4, 4, new long[]{1, 2, 1, 2}));
        System.out.println(" : " + (e.solution(4, 4, new long[]{1, 2, 1, 2}) == 1));

        // 10
        System.out.print(e.solution(6, 3, new long[]{40, 20, 1}));
        System.out.println(" : " + (e.solution(6, 3, new long[]{40, 20, 1}) == 10));

        // 1
        System.out.print(e.solution(2, 1, new long[]{1,1}));
        System.out.println(" : " + (e.solution(2, 1, new long[]{1, 1}) == 1));




    }

    private static class example {
        /*
        예시 문제 (랜선자르기)
        https://www.acmicpc.net/problem/1654

        집에서 시간을 보내던 오영식은 박성원의 부름을 받고 급히 달려왔다.
        박성원이 캠프 때 쓸 N개의 랜선을 만들어야 하는데 너무 바빠서 영식이에게 도움을 청했다.

        이미 오영식은 자체적으로 K개의 랜선을 가지고 있다. 그러나 K개의 랜선은 길이가 제각각이다.
        박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다.
        예를 들어 300cm 짜리 랜선에서 140cm 짜리 랜선을 두 개 잘라내면 20cm는 버려야 한다. (이미 자른 랜선은 붙일 수 없다.)

        편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며,
        기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자.
        그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자.
        N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
        이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.

        [입력]
        필요한 랜선의 개수 N, 오영식이 이미 가지고 있는 랜선의 개수 K, 그리고 길이가 각각 다른 K개의 랜선 길이가 입력된다.
        - K는 1이상 10,000이하의 정수이고,
        - N은 1이상 1,000,000이하의 정수이다.
        - 그리고 항상 K ≦ N 이다.
        그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다.
        각각 랜선의 길이는 2^31 - 1 보다 작거나 같은 자연수이다.
        */

        long solution(int N, int K, long[] cables) {
            Arrays.sort(cables); // 정렬

//            System.out.println(K + "개의 랜선을 잘라 " + N + "개를 만들어야된다.");

            // 변수 초기화
            long min = 1;
            long max = cables[cables.length - 1];
            long mid;


            /*
            min <= max 으로 하는 이유는
            10 == 10 일 경우 return 해주는 max 의 값을 갱신해주기 위해서이다.
            */
            while (min <= max) {
                // 중간값
                mid = (min + max) / 2;

                int sum = 0;
                for (long length : cables) {
//                    System.out.println(length + " 길이를 " + mid + " 만큼 잘라내면 " + (length / mid) + "개를 만들 수 있다.");
                    sum += (length / mid);
                }
//                System.out.println("총 : " + sum + "개를 만든다.");

//                if (sum == N) return mid;

                // N 보다 크다면 너무 크게 잘랐으니
                // 더 작게 자르기 위해 MAX 값을 줄인다.
                if (sum < N) max = mid - 1;
                // N 보다 적다면 너무 크게 작게
                // 더 크게 자르기 위해 MIN 값을 늘린다.
                else min = mid + 1;

//                System.out.println("MIN : " + min + ", " + "MAX : " + max + "\n");
            }

//            System.out.println("답 : "+ max);
            return max;
        }

        void example() {
            int target = 990; // 찾고자 하는 값

            // 초기화
            // 배열이 정렬되어 있어야 이분탐색이 가능하다.
            int[] array = new int[10000 + 1];
            for (int i = 0; i < 10000; i++) array[i] = i;

            int low = 0;
            int high = array.length - 1;

            int count = 0;
            while (low <= high) {
                count++;

                int mid = (low + high) / 2;

                System.out.println(mid);
                if (array[mid] == target) {
                    System.out.println(count + "번 만에 찾음\nmid : " + mid);
                    break;
                }

                // 중간값 보다 타겟이 작으면 왼쪽으로 탐색범위 이동
                else if (array[mid] > target) high = mid - 1;
                else low = mid + 1;

                System.out.println("현재 범위 : " + low + " ~ " + high);
            }
        }
    }

}


