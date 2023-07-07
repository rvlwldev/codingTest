package programmers.lv1;

import java.util.Arrays;

public class P172928 {

    private String[][] map;

    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];

        map = new String[park.length][park[0].length()];

        for (int i = 0; i < park.length; i++) {
            String[] splited = park[i].split("");

            for (int j = 0; j < splited.length; j++) {
                String s = splited[j];

                map[i][j] = s;

                if (s.equals("S")) {
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }

        // print
//        for (String[] s : map) System.out.println(Arrays.toString(s));

        for (String ops : routes) {
            String[] op = ops.split(" ");

            String arrow = op[0];
            int size = Integer.parseInt(op[1]);

            answer = move(answer, arrow, size);
        }


        return answer;
    }

    private int[] move(int[] now, String arrow, int size) {
        if (arrow.equals("N")) {

            for (int i = now[0]; i >= now[0] - size; i--) {
                if( map[now[0]][i].equals("X") ) return now;
            }

            int[] a  = new int[3];

            Arrays.fill(a, 0);
            int[] b = Arrays.copyOf(a, a.length);

            now[0] -= size;
            
        } else if (arrow.equals("S")) {

            for (int i = now[0]; i < now[0] + size; i++) {
                if( map[i][now[1]].equals("X") ) return now;
            }

            now[0] += size;

        } else if (arrow.equals("W")) {

            for (int i = 0; i < now[1] + size; i++) {
                if( map[now[0]][i].equals("X") ) return now;
            }

            now[1] -= size;


        } else if (arrow.equals("E")) {

            for (int i = 0; i < now[0] + size; i++) {
                if( map[now[0]][i].equals("X") ) return now;
            }

            now[1] += size;
        }

        return now;
    }


    public static void main(String[] args) {
        P172928 sol = new P172928();

        // 2, 1
        System.out.println(Arrays.toString(sol.solution(new String[]{"SOO", "OOO", "OOO"}, new String[]{"E 2", "S 2", "W 1"})));

        // 0, 1
        System.out.println(Arrays.toString(sol.solution(new String[]{"SOO","OXX","OOO"}, new String[]{"E 2","S 2","W 1"})));

//        System.out.println(Arrays.toString(sol.solution(new String[]{"SOO","OXX","OOO"}, new String[]{"S 2"})));

    }
}
