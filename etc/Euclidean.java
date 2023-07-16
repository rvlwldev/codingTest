package etc;

/*
유클리드 알고리즘 (Euclidean algorithm)은 2개의 자연수의 최대공약수(GCD)를 구하는 알고리즘이다.
비교대상인 두 개의 자연수 a와 b에서 (이때, a > b) a를 b로 나눈 나머지를 r 이라고 했을때
GCD(a, b) = GCD(b, r) 이며,

"r이 0이면 그때 b가 최대공약수이다." 라는 원리를 활용한 알고리즘 이다.

만약 r이 0이 아니라면 a에 b값을 다시 넣고, r을 b에 대입 한 후 다시 반복한다
*/
public class Euclidean {
    public static void main(String[] args) {

        int a = 2145232;
        int b = 88888;
        System.out.println(a + " & " + b + " 의 최대공약수 : " + GCD_recur(a, b));
        System.out.println(a + " & " + b + " 의 최소공배수 : " + LCM(a, b));
    }

    // 최대공약수를 구하기
    static int GCD(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    // 최대공약수 - 재귀함수 사용
    static int GCD_recur(int a, int b) {
        System.out.println(a + "를 " + b + "로 나눈 나머지는 " + (a % b) + " 이다.");

        if (a % b == 0) {
            System.out.println("그러므로 최대공약수는 " + b + " 이다.\n");
            return b;
        }

        System.out.println(b + " 와 " + "나머지 값 " + (a % b) + "로 다시 실행한다.\n");
        return GCD_recur(b, a % b);
    }

    // 최소공배수
    /*
    두 자연수 A, B의 최대공약수가 G, 최소공배수가 L 이라고 가정하면
    A = a * G,
    B = b * G (a,b는 서로소) 라고 하면

    L = a * b * G
    A * B = L * G
    */
    static int LCM(int a, int b) {
        // 0이 아닌 두 수의 곱 / 두 수의 최대 공약수
        return a * b / GCD(a, b);
    }
}


