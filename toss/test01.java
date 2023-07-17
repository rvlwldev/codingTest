package toss;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        P p = new P();

        System.out.println(p.sol("1451232125", 2)); // 21
        System.out.println(p.sol("313253123", 3)); // 312
        System.out.println(p.sol("12412415", 4)); // -1

    }

    private static class P {
        public int sol(String s, int N) {
            int answer = -1;

            for (int i = 0; i < s.length() - N; i++) {
                int part = Integer.parseInt(s.substring(i, i + N));
                if (check(part, N)) answer = Math.max(part, answer);
            }

            return answer;
        }

        private boolean check(int num, int N) {
            return Arrays.stream(String.valueOf(num)
                    .split(""))
                    .filter(v -> Integer.parseInt(v) <= N)
                    .distinct()
                    .count() == N;
        }

    }

}

