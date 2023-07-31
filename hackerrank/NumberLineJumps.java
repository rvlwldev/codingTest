package hackerrank;

import java.io.*;

public class NumberLineJumps {

    public static String kangaroo(int x1, int v1, int x2, int v2) {
        if (v1 <= v2) return "NO";
        if (x1 == x2) return "YES";

        for (int i = 0; i < 10000; i++) {
            x1 += v1;
            x2 += v2;

            if (x1 == x2) return "YES";
        }

        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int x1 = Integer.parseInt(firstMultipleInput[0]);

        int v1 = Integer.parseInt(firstMultipleInput[1]);

        int x2 = Integer.parseInt(firstMultipleInput[2]);

        int v2 = Integer.parseInt(firstMultipleInput[3]);

        String result = NumberLineJumps.kangaroo(x1, v1, x2, v2);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
