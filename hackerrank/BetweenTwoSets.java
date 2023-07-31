package hackerrank;

import java.io.IOException;
import java.util.List;

public class BetweenTwoSets {

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        int answer = 0;

        int n = 0;
        boolean flag;

        int limit = b.get(0);
        while (n <= limit) {
            n++;
            flag = true;

            // check factors
            for (int i : a) {
                if (n % i != 0) {
                    flag = false;
                    break;
                }
            }

            if (!flag) continue;

            // check multiples
            for (int i : b) {
                if (i % n != 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) answer++;
        }


        return answer;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("answer : " + getTotalX(List.of(2, 4), List.of(16, 32, 96))); // 3
        System.out.println("answer : " + getTotalX(List.of(3, 4), List.of(24, 48))); // 2
        System.out.println("answer : " + getTotalX(List.of(2, 6), List.of(24, 36))); // 2
        System.out.println("answer : " + getTotalX(List.of(2), List.of(20, 30, 12))); // 1
        System.out.println("answer : " + getTotalX(List.of(1), List.of(100))); // 9
    }
}
