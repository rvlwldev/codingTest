package codility;

/*
A non-empty array A consisting of N integers is given.
The consecutive elements of array A represent consecutive cars on a road.

Array A contains only 0s and/or 1s:
- 0 represents a car traveling east
- 1 represents a car traveling west.

The goal is to count passing cars.
We say that a pair of cars (P, Q),
where 0 ≤ P < Q < N, is passing when P is traveling to the east
and Q is traveling to the west.

For example, consider array A such that:
A[0] = 0
A[1] = 1
A[2] = 0
A[3] = 1
A[4] = 1

We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).

Write a function: public int solution(int[] A)
that, given a non-empty array A of N integers, returns the number of pairs of passing cars.
The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.

For example, given:
A[0] = 0
A[1] = 1
A[2] = 0
A[3] = 1
A[4] = 1

the function should return 5, as explained above.

Write an efficient algorithm for the following assumptions:
    - N is an integer within the range [1..100,000];
    - each element of array A is an integer that can have one of the following values: 0, 1.
*/

/*
비어있지 않은 배열 A 정수로 이루어져 있다.
A는 도로의 연속적인 차량을 표현한다.

요소는 0,1 만 들어가며
0은 동쪽, 1은 서쪽으로 이동하는 차량이다.

목표는 지나가는 차를 세는것
We say that a pair of cars (P, Q),
where 0 ≤ P < Q < N, is passing when P is traveling to the east
and Q is traveling to the west.

(P, Q) 로 이루어진 차량의 쌍을 가정
P는 동쪽으로 가는 차
Q는 서졲으로 가는 차
라고 가정할때,
0 ≤ P < Q < N 일때 (P, Q)가 성립된다.

예시)
A[0] = 0
A[1] = 1
A[2] = 0
A[3] = 1
A[4] = 1
일때, 인덱스 별로 (0,1), (0,3), (0,4), (2,3), (2,4) 가 지나가는 차량이며
답은 5개의 쌍이 있음으로 5이다.

*/

public class PassingCar {
    public int solution(int[] A) {
        int eastCount = 0;
        int pairCount = 0;

        for (int direction : A) {
            if (direction == 0) eastCount++;
            else pairCount += eastCount;

            if (pairCount > 1000000000) return -1;
        }

        return pairCount;
    }


    public static void main(String[] args) {
        PassingCar sol = new PassingCar();

        System.out.println(sol.solution(new int[]{0, 1, 0, 1, 1})); // 5
        System.out.println(sol.solution(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0, 1})); // 15
        System.out.println(sol.solution(new int[]{0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1})); // 13

        System.out.println("\n0 below");
        // 0
        System.out.println(sol.solution(new int[]{1}));
        System.out.println(sol.solution(new int[]{0}));
        System.out.println(sol.solution(new int[]{0, 0}));
        System.out.println(sol.solution(new int[]{1, 1}));
        System.out.println(sol.solution(new int[]{1, 1, 0}));

    }
}
