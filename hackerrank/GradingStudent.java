package hackerrank;

import java.io.*;
import java.util.List;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/grading/problem?isFullScreen=true
public class GradingStudent {
    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        return grades.stream()
                .map(v -> {
                    if (v < 38) return v;

                    int d = v % 10;

                    int r = v - d + 5;
                    if (d > 5) r += 5;

                    if (r - v < 3) v = r;

                    return v;
                })
                .collect(Collectors.toList());

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int gradesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> grades = IntStream.range(0, gradesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = GradingStudent.gradingStudents(grades);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
