package programmers.lv2;

class Solution92335 {

    /*
     * 조건에 나와있는 0P0 조건 설명은 split하라는 말을 길게 풀어 쓴거였음
     * 문자열로 만들어 0으로 나눈다음 소수판별
     */
    public int solution(int n, int k) {

        int answer = 0;

        String str = Integer.toString(n, k);

        String[] arr = str.split("0");

        for (String t : arr) {
            if (t.length() > 0 && isPrime(t)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isPrime(String x) {
        long num = Long.parseLong(x);

        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}

public class P92335 {
    public static void main(String[] args) {

        Solution92335 sol = new Solution92335();

        // 3
        System.out.println("answer is " + sol.solution(437674, 3));

        // 2
        System.out.println("answer is " + sol.solution(110011, 10));

        // System.out.println("answer is " + sol.solution(123456, 10));

    }
}
