package programmers.lv2;

//https://school.programmers.co.kr/learn/courses/30/lessons/155651


// 입실시간 순으로 정렬 (같다면 퇴실시간까지)
// 종료시간이 담긴 배열을 하나만든다.
// 예약을 순회하며 종료시간 이후로 들어갈 수 있는 방이 없다면 방을 추가한다.
// 들어갈 수 있는 방이 있다면 종료시간을 업데이트 해준다.
// 방의 사이즈를 리턴한다. (실패)

// 누적합
// 24 * 60 + 10(청소시간) 길이의 [분]배열을 만든다.
// [입실시간] 인덱스에 +1, [퇴실시간 + 청소시간에 + 1] 인덱스에 -1
// [퇴실시간 + 청소시간에 + 1] 인덱스에 -1 의 이유는 바로이전값은 1일텐데 바로 이전이 끝이기 때문에....
// 단 한번만 하루 분의 반복을 하면서 최대값을 가려낸다.
public class P155651 {

    private final int CLEAN_MINUTES = 10;
    private final int MINUTES_BY_DAY = 24 * 60 + CLEAN_MINUTES;

    public int solution(String[][] book_time) {
        int maxRoomCount = 0;

        int[] minutes = new int[MINUTES_BY_DAY];

        for (String[] bookInfo : book_time) {
            int checkIn = parseToMinutes(bookInfo[0]);
            int checkOut = parseToMinutes(bookInfo[1]);

            minutes[checkIn] += 1;
            minutes[checkOut + CLEAN_MINUTES] += -1;
        }

        for (int i = 1; i < MINUTES_BY_DAY; i++) {
            minutes[i] += minutes[i - 1];
            maxRoomCount = Math.max(maxRoomCount, minutes[i]);
        }

        return maxRoomCount;
    }

    private int parseToMinutes(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int mins = Integer.parseInt(split[1]);

        return hour * 60 + mins;
    }


    public static void main(String[] args) {
        P155651 sol = new P155651();

        // 3
        System.out.println("answer is " + sol.solution(new String[][]{
                {"15:00", "17:00"},
                {"16:40", "18:20"},
                {"14:20", "15:20"},
                {"14:10", "19:20"},
                {"18:20", "21:50"}
        }));

        // 1
        System.out.println("answer is " + sol.solution(new String[][]{
                {"09:10", "10:10"},
                {"10:20", "12:20"}
        }));

        // 3
        System.out.println("answer is " + sol.solution(new String[][]{
                {"10:20", "12:30"},
                {"10:20", "12:30"},
                {"10:20", "12:30"}
        }));

        // 2
        System.out.println("answer is " + sol.solution(new String[][]{
                {"09:10", "10:10"},
                {"09:10", "10:10"},
                {"10:20", "12:20"},
                {"10:20", "12:20"}
        }));


    }
}
