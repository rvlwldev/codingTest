package programmers.lv2;

public class P154539 {
    class Solution {
        public int[] solution(int[] numbers) {
            int[] answer = new int[numbers.length];
            answer[numbers.length - 1] = -1;

            for (int i = numbers.length - 2; i >= 0; i--) {
                for (int j = i + 1; j < numbers.length; j++) {
                    // 바로 뒤에 있는 수 비교기
                    if(numbers[i] < numbers[j]) {
                        answer[i] = numbers[j];
                        break;
                    }

                    // 큰 값이 어차피 없음
                    if (answer[j] == -1) {
                        answer[i] = -1;
                        break;
                    }

                    // answer[j]는 어차피 최대값이니까 그대로 앞에도 똑같이
                    // 뒤에 있는 수 중에 최대값이 저장됨
                    if (numbers[i] < answer[j]) {
                        answer[i] = answer[j];
                        break;
                    }
                }
            }

            return answer;
        }
    }
}
