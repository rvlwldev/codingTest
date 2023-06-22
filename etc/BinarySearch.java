package etc;

// 이분 탐색

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
        int target = 990;
        int[] array = new int[10000];





    }





}
