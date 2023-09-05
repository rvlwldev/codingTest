package hackerrank.medium;

public class LibraryFine {
    public static void main(String[] args) {
        System.out.println(libraryFine(9, 6, 2015, 6, 6, 2015)); // 45
    }

    public static int libraryFine(int d1, int m1, int y1, int d2, int m2, int y2) {
        // Write your code here
        // 1 is expected
        // 2 is actual

        int sum1 = y1 * 10000 + m1 * 100 + d1;
        int sum2 = y2 * 10000 + m2 * 100 + d2;

        if (sum1 < sum2) return 0;

        if (y1 > y2) return 10000;
        else if (m1 > m2) return (m1 - m2) * 500;
        else if (d1 > d2) return (d1 - d2) * 15;

        return 0;
    }
}
