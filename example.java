class Solution {

    static int answer;

    public int solution(int n, int k) {
        answer = 0;

        String str = Integer.toString(n, k);

        for (int i = 0; i < str.length(); i++) {
            recur(str, i, 0);
        }

        return answer;
    }

    private void recur(String str, int start, int len) {

        System.out.println("RECUR : " + str.substring(start, start + len));

        if (str.length() <= start + len) {
            return;
        }

        if (check(str, start, len)) {
            // System.out.println("TRUE : " + str.substring(start, start + len));
            answer++;
        }

        recur(str, start, len + 1);

    }

    private boolean check(String str, int start, int len) {

        // condition

        if (start > 0) {
            if (str.charAt(start - 1) != '0') {
                return false;
            }
        }

        if (len + start < str.length()) {
            if (str.charAt(len + start) != '0') {
                return false;
            }
        }

        String target = str.substring(start, start + len);
        if (target.length() < 1) {
            return false;
        }

        System.out.println("t : " + target);

        // isPrime
        long num = Long.parseLong(str.substring(start, start + len));

        if (num < 3) {
            return false;
        }

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}

public class example {

    public static void main(String[] args) {

        Solution sol = new Solution();

        // 3
        // System.out.println("\nANSWER : " + sol.solution(437674, 3));

        // 2
        System.out.println("\nANSWER : " + sol.solution(110011, 10));

    }

}