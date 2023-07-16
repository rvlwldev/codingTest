package etc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfEratosthenes {

    public static void main(String[] args) {
        Eratosthenes e = new Eratosthenes();
        e.printAllPrimeNumbers(10);
        System.out.println();
        System.out.println(Arrays.toString(e.getPrimeArray(2, 15)));

    }
}

class Eratosthenes {

    /*
    범위 내의 소수를 찾는 알고리즘

    N = 2 이면 2 를 제외한 2의 배수를 모두 지워주고,
    N = 3 이면 3 을 제외한 3의 배수를 모두 지워주고,
    (4는 이미 N = 2 에서 제외되어 넘어간다.)
    N = 5 이면 5 를 제외한 5의 배수를 모두 지워주며 최대범위 까지 반복한다.
    */

    // N (limit) 이하의 모든 소수 찾기
    public void printAllPrimeNumbers(int limit) {
        /*
		소수가 아닌 index = false;
		소수인 index = true;
		선언 시 모든 요소가 false 로 초기화 된다.
		인덱스가 소수인 경우에 true 설정
		*/
        boolean[] primeArr = new boolean[limit + 1];    // 0 ~ N

        // 2 미만의 N 을 입력받으면 소수는 판별할 필요 없으므로 바로 return
        if (limit < 2) return;

        // 2부터 시작.
        for (int i = 2; i < primeArr.length; i++) {
            if (isPrime(i)) {
                primeArr[i] = true;
                System.out.print(i + " "); // 출력
            }
        }
    }

    // 정해진 범위에서 소수 배열 반환
    public int[] getPrimeArray(int stt, int end) {
        List<Integer> list = new ArrayList<>();

        for (int i = stt; i <= end; i++) {
            if (isPrime(i)) list.add(i);
        }

        return list.stream()
                .mapToInt(v -> v)
                .toArray();
    }


    // 소수 판별 알고리즘
    public boolean isPrime(int num) {
        // 0 또는 1은 소수가 아님
        if (num < 2) return false;

        // 2는 소수 (어차피 반복문을 돌지 않기에 기술 안해도 상관없음)
        // if (num == 2) return true;

        // 그외의 수(루트이하의 수로 나눠보기)
        for (int i = 2; i <= Math.sqrt(num); i++) {
            // 루트이하 수에서 나눠지는 수가 있으면 소수가 아님
            // 루트보다 작은 값에서 1보다 큰 어떤 값을 곱하면, 무조껀 커지기 때문에
            if (num % i == 0) return false;
        }

        // 루트가 아니라 제곱까지 해도 되지만 위의 방법이 약간 더 효율적이긴 하다.
        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) return false;
        }

        // 안나왔으면 소수
        return true;
    }
}


