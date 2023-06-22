package programmers.lv2;

import java.util.Arrays;

class Solution17686 {

    public String[] solution(String[] files) {

        // 리턴이 음수면 자리를 바꾼다
        Arrays.sort(files, (r, l) -> {

            // 숫자를 나누기위한 위치
            int rFirstNumberIndex = getFirstNumberIndex(r);
            int lFirstNumberIndex = getFirstNumberIndex(l);

            // 숫자의 마지막인덱스
            int rLastNumberIndex = getLastNumberIndex(r, rFirstNumberIndex);
            int lLastNumberIndex = getLastNumberIndex(l, lFirstNumberIndex);

            // 대소문자를 구분하지 않으므로
            String rHead = r.substring(0, rFirstNumberIndex).toLowerCase();
            String lHead = l.substring(0, lFirstNumberIndex).toLowerCase();

            int rNum = Integer.parseInt(r.substring(rFirstNumberIndex, rLastNumberIndex));
            int lNum = Integer.parseInt(l.substring(lFirstNumberIndex, lLastNumberIndex));

            // 꼬리부분을 구할수는 있지만 굳이 구할 필요가 없음........
            // String rTail = r.replace(rHead, "").replace(String.valueOf(rNum), "");
            // String lTail = r.replace(lHead, "").replace(String.valueOf(lNum), "");

            // 헤드부분이 같으면 숫자로 정렬
            if (rHead.equals(lHead)) {
                return rNum - lNum;
            }

            // 대소문자가 통일된 문자의 사전순 비교
            return rHead.compareTo(lHead);

        });

        return files;
    }

    private int getFirstNumberIndex(String str) {

        String[] arr = str.split("");

        for (int i = 0; i < arr.length; i++) {
            try {
                Integer.parseInt(arr[i]);
                return i;
            } catch (Exception e) {
                continue;
            }
        }

        return -1;
    }

    private int getLastNumberIndex(String str, int firstIndex) {
        String[] arr = str.split("");

        for (int i = firstIndex; i < arr.length; i++) {
            try {
                Integer.parseInt(arr[i]);

                if (i == arr.length - 1) {
                    return i + 1;
                }

            } catch (Exception e) {
                return i;
            }
        }

        return -1;
    }

}

public class P17686 {
    public static void main(String[] args) {

        Solution17686 sol = new Solution17686();

        String[] answer = sol.solution(sol.solution(new String[] { "img12.png",
        "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG", "aaaa2" }));

        System.out.println("\nAnswer is " + Arrays.toString(answer));

        // System.out.println("Answer is " + sol.solution(new String[] { "aaaa1",
        // "aaaa2" }));
    }
}
