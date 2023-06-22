package programmers.lv2;

class SolutionP62048 {

    // 원래 int형 이였는데 long으로 바꿈
    // 더하거나 곱했을시 int형 보다 더 큰 수가 나올수 있는듯 (1억 이하의 자연수니까)
    public long solution(int w, int h) {

        if (Math.min(w, h) < 1) {
            return 0;
        }

        long size = w * h;
        long sum = w + h;

        // 전체 사이즈에서 가로세로를 더한 값에서 최대공약수를 빼줌
        // https://taesan94.tistory.com/55
        return size - (sum - getGDC(w, h));
    }

    // 최대 공약수
    private long getGDC(int a, int b) {
        long res = 0;

        for (int i = 1; i <= Math.min(a, b); i++) {

            if (a % i == 0 && b % i == 0) {
                res = i;
            }

        }

        return res;
    }

}

public class P62048 {
    public static void main(String[] args) {

        SolutionP62048 sol = new SolutionP62048();

        System.out.println("Answer is " + sol.solution(8, 12));

    }
}
