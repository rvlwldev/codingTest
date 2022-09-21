package etc;
import java.util.Arrays;

public class recursive {
    public static void main(String[] args) {

        recurExample example = new recurExample();

        // 각 메소드에서 사용하는 변수들 (depth는 0으로 통일)
        int[] arr = new int[] { 1, 2, 3 };
        boolean[] visit = new boolean[arr.length];
        int[] result = new int[arr.length];

        // 중복되지 않는 같은 길이의 모든 조합(swap) (경우의 수 : !n개)
        // example.getAllCases_swap(arr, 0);

        // 중복되지 않는 같은 길이의 모든 조합(visit)
        // example.getAllCases_visit(arr, result, visit, 0);

        // 같은 길이의 모든 조합 (중복을 포함)
        // example.getAllCases_dup(arr, result, 0);

        // 모든 길이의 모든 조합 (중복되지않음)
        for (int i = 1; i <= arr.length; i++) {
            // example.getAllCases_variableLength(arr, result, visit, 0, i);
        }

        // 모든 길이의 모든 조합 (중복가능)
        for (int i = 1; i <= arr.length; i++) {
            // example.getAllCases_dup_variableLength(arr, result, 0, i);
        }
    }

}

class recurExample {
    public void getAllCases_swap(int[] arr, int depth) {

        // 마지막순서까지 다 돌았을때 종료
        if (depth == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = depth; i < arr.length; i++) {

            // 자리를 바꿔준다
            // ex) depth = 0, i = 1;
            // [1,2,3] -> [2,1,3]
            int temp = arr[depth];
            arr[depth] = arr[i];
            arr[i] = temp;

            // 다음자리로 재귀
            getAllCases_swap(arr, depth + 1);

            // 자리 원복
            temp = arr[depth];
            arr[depth] = arr[i];
            arr[i] = temp;

        }

    }

    public void getAllCases_visit(int[] arr, int[] result, boolean[] visit, int depth) {

        if (depth == arr.length) {
            System.out.println(Arrays.toString(result));
            return;
        }

        // swap하는 방식과는 다르게 항상 처음부터
        // 역순으로 조합이 가능하기 때문에 (1,2,3 -> 2,1,3)
        for (int i = 0; i < arr.length; i++) {

            // 방문하지 않았다면
            if (!visit[i]) {

                visit[i] = true;
                result[depth] = arr[i];

                getAllCases_visit(arr, result, visit, depth + 1);

                // 조합시 역순으로 조합이 가능
                // 같은 depth때 depth번째에 arr 각각의 요소가 위치할 수 있게 원복
                visit[i] = false;
            }

        }

    }

    public void getAllCases_dup(int[] arr, int[] result, int depth) {

        if (depth == arr.length) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = 0; i < arr.length; i++) {

            result[depth] = arr[i];
            getAllCases_dup(arr, result, depth + 1);

        }

    }

    public void getAllCases_variableLength(int[] arr, int[] result, boolean[] visit, int depth, int limit) {

        if (depth == limit) {
            System.out.println(Arrays.toString(result));
            return;
        }

        // result를 limit 만큼의 공간이 있는 배열로 초기화
        if (depth == 0) {
            result = new int[limit];
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                result[depth] = arr[i];
                getAllCases_variableLength(arr, result, visit, depth + 1, limit);
                visit[i] = false;
            }
        }
    }

    public void getAllCases_dup_variableLength(int[] arr, int[] result, int depth, int limit) {

        if (depth == limit) {
            System.out.println(Arrays.toString(result));
            return;
        }

        // result를 limit 만큼의 공간이 있는 배열로 초기화
        if (depth == 0) {
            result = new int[limit];
        }

        for (int i = 0; i < arr.length; i++) {
            result[depth] = arr[i];
            getAllCases_dup_variableLength(arr, result, depth + 1, limit);
        }
    }
}
