package programmers.lv1;

class Solution72410 {
    public String solution(String new_id) {

        String answer = "";

        // 1. 소문자로 치환
        answer = new_id.toLowerCase();

        // 2. 모든 특수문자 삭제 .제외
        answer = answer.replaceAll("[^\\w-\\.]", "");

        // 3. 여러개의 .을 . 한개로 변경
        answer = answer.replaceAll("[.]{2,}", ".");

        // 4. 마침표가 처음이나 끝에 위차한다면 제거
        if (answer.substring(0, 1).equals(".")) {
            answer = answer.substring(1);
        }

        answer = answer.replaceAll("[.]$", "");

        // 5. 빈문자열이면 a
        if (answer.isEmpty()) {
            answer = "a";
        }

        // 6. 길이 15까지 제한 and .으로 끝나면 제거
        if (answer.length() > 15) {
            answer = answer.substring(0, 15);
            if (answer.substring(answer.length() - 1).equals(".")) {
                answer = answer.substring(0, answer.length() - 1);
            }
        }

        // 7. 2자 이하라면 마지막문자를 3이될때까지 반복해서 붙이기
        while (answer.length() < 3) {
            answer += answer.charAt(answer.length() - 1);
        }

        return answer;
    }
}

public class P72410 {

    public static void main(String[] args) {
        Solution72410 solution = new Solution72410();

        String answer = solution.solution("...!@BaT#*..y.abcdefghijklm");

        System.out.println("Answer is " + answer);
    }
}
