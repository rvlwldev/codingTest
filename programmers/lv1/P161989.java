package programmers.lv1;

class Solution161989 {
    public int solution(int n, int m, int[] section) {
        int count = 0;
        int[] wall = new int[n];

        for (int point : section) wall[point - 1] = 1;

        while (!check(wall)) {
            count++;

            int index = getFirstIndex(wall);

            for (int i = index; i < Math.min(index + m, n); i++) {
                wall[i]--;
            }
        }

        return count;
    }

    private boolean check(int[] wall) {
        for (int n : wall) {
            if (n > 0) return false;
        }

        return true;
    }

    private int getFirstIndex(int[] wall) {
        for (int i = 0; i < wall.length; i++) {
            if (wall[i] > 0) return i;
        }

        return -1;
    }
}

public class P161989 {
    public static void main(String[] args) {
        Solution161989 sol = new Solution161989();

        System.out.println(sol.solution(8, 4, new int[]{2, 3, 6})); // 2
        System.out.println(sol.solution(5, 4, new int[]{1, 3})); // 1
        System.out.println(sol.solution(4, 1, new int[]{1, 2, 3, 4})); // 4

    }


}
