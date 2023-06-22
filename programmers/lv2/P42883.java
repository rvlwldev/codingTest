package programmers.lv2;

class Solution42883 {

    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int index = 0;

        // 결국 answer 길이는 number.length() - k
        for (int i = 0; i < number.length() - k; i++) {
            int max = 0;

            System.out.println(number.substring(index, (k + i) + 1));
            System.out.println("START INDEX : " + index + " & k+i : " + (k + i));
            // 시작 index 부터 k + i 까지 인덱스 중 가장 큰 수의 인덱스를 찾기
            // k + i 인 이유는 index 가 이전 가장 큰수 다음이며, 다음자릿수(i)와 범위(k)를 확인하기 위해서
            for (int j = index; j <= k + i; j++) {
                int num = number.charAt(j) - '0';
                if (num > max) {
                    max = num;
                    index = j + 1; // 다음 탐색을 위해 index 갱신
                }
            }

            System.out.println("MAX : " + max);
            System.out.println();

//            System.out.println("MAX : " + max);
            answer.append(max);
        }

        return answer.toString();
    }
}

public class P42883 {
    public static void main(String[] args) {
        Solution42883 sol = new Solution42883();

//        System.out.println("answer is " + sol.solution("1924",2)); // 94
//        System.out.println("answer is " + sol.solution("1231234",3)); // 3234

        System.out.println("answer is " + sol.solution("4177252841", 4)); // 775841

//        System.out.println("answer is " + sol.solution("123456789", 4)); // 775841


    }
}
