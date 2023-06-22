package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P134239 {

    private List<Integer> collatzNumbers = new ArrayList<>();
    private List<Double> AccumulateWidthSumList = new ArrayList<>();

    public double[] solution(int k, int[][] ranges) {
        List<Double> answer = new ArrayList<>();

        getCollatzOrders(k);
        getAccumulateSumOfCollatzNumbers();

        for (int[] range : ranges) {
            answer.add(getRangeWidthSum(range));
        }

        return answer.stream()
                .mapToDouble(value -> value)
                .toArray();
    }

    private void getCollatzOrders(int k) {
        collatzNumbers.add(k);

        if (k == 1) {
            return;
        } else if (isEvenNumber(k)) {
            getCollatzOrders(k / 2);
        } else {
            getCollatzOrders(k * 3 + 1);
        }
    }

    private boolean isEvenNumber(int number) {
        if (number % 2 == 1) {
            return false;
        }

        return true;
    }

    private void getAccumulateSumOfCollatzNumbers() {

        double sum = 0;

        for (int i = 0; i < collatzNumbers.size() - 1; i++) {
            int L = collatzNumbers.get(i);
            int R = collatzNumbers.get(i + 1);

            double max = Math.max(L, R);
            double min = Math.min(L, R);

            sum = min + ((max - min) / 2);

            AccumulateWidthSumList.add(sum);
        }

    }

    private double getRangeWidthSum(int[] range) {

        double sum = 0;

        int start = range[0];
        int end = AccumulateWidthSumList.size() + range[1];

        if (start > end) {
            return -1;
        }

        for (int i = start; i < end; i++) {
            sum += AccumulateWidthSumList.get(i);
        }

        return sum;
    }

    public static void main(String[] args) {
        P134239 sol = new P134239();

        System.out.println("answer is " + Arrays.toString(sol.solution(5,
                new int[][]{{0, 0}, {0, -1}, {2, -3}, {3, -3}})));
    }
}
