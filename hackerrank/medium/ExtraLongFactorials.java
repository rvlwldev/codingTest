package hackerrank.medium;

import java.math.BigInteger;

public class ExtraLongFactorials {
    public static void main(String[] args) {
        extraLongFactorials(30);
    }

    public static void extraLongFactorials(int n) {
        // Write your code here
        // if n is 30, the answer is 265252859812191058636308480000000
        // using BigInteger

        System.out.println(recurFactorial(new BigInteger(String.valueOf(n))));
    }

    private static BigInteger recurFactorial(BigInteger n) {
        if (n.equals(BigInteger.ONE)) return BigInteger.ONE;

        return n.multiply(recurFactorial(n.subtract(BigInteger.ONE)));
    }
}


