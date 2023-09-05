package hackerrank.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/apple-and-orange/problem?isFullScreen=true&h_r=next-challenge&h_v=zen
public class AppleAndOrange {

    public static void countApplesAndOranges(int s, int t, int appleTree, int orangeTree, List<Integer> apples, List<Integer> oranges) {
        // Write your code here
        int[] answer = new int[2];
        house house = new house(s, t);

        apples.stream()
                .map(v -> v + appleTree)
                .forEach(v -> {
                    if (house.isFall(v)) answer[0]++;
                });

        oranges.stream()
                .map(v -> v + orangeTree)
                .forEach(v -> {
                    if (house.isFall(v)) answer[1]++;
                });

        for (int a : answer) System.out.println(a);
    }

    static class house {
        int l;
        int r;

        house(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public boolean isFall(int f) {
            return l <= f && f <= r;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int s = Integer.parseInt(firstMultipleInput[0]);

        int t = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int a = Integer.parseInt(secondMultipleInput[0]);

        int b = Integer.parseInt(secondMultipleInput[1]);

        String[] thirdMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(thirdMultipleInput[0]);

        int n = Integer.parseInt(thirdMultipleInput[1]);

        List<Integer> apples = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> oranges = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        AppleAndOrange.countApplesAndOranges(s, t, a, b, apples, oranges);

        bufferedReader.close();
    }
}
