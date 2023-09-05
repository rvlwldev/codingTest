package hackerrank.medium;

public class SherlockAndSquares {
    public static void main(String[] args) {
        System.out.println(squares(24, 49)); // 3
        System.out.println(squares(3, 9)); // 2
        System.out.println(squares(17, 24)); // 0
    }

    public static int squares(int a, int b) {
        // Write your code here
        int stt = (int) Math.sqrt(a);
        if (Math.sqrt(a) > stt) stt ++;

        int end = (int) Math.sqrt(b) + 1;

        return end - stt;
    }
}
