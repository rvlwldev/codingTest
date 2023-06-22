package etc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQ {

    public static void main(String[] args) {

        // (우선순위가 낮은 숫자 순)
        PriorityQueue<Integer> priorityQueueASC = new PriorityQueue<>();

        // (우선순위가 높은 숫자 순)
        // PriorityQueue<Integer> priorityQueueDESC = new PriorityQueue<>(Collections.reverseOrder());

        priorityQueueASC.add(3);
        priorityQueueASC.add(1);
        priorityQueueASC.add(2);

        // 우선 순위가 낮은 숫자부터니까
        // 1이 반환됨
        // System.out.println(priorityQueueASC.poll());

        ////////////////////////////////////////////////////////////////////////

        // 우선순위를 직접 설정할 수 있다.
        // 보통 Comparator 클래스의 compare 메소드를 오버라이드 한다.
        // add또는 offer 될때 즉시 compare 메소드로 정렬된다.
        PriorityQueue<Integer> exampleQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 숫자 5에 더 가까운 숫자부터 반환

                // 예) 3 , 5 일때 2 와 0 이 됨
                // 2 - 0 은 양수니까 자리를 바꾸지 않음

                /*
                 * 주의
                 * 5번을 넣어도 큐 자체를 터미널에서 찍어 봤을때 이 로직대로 정렬되어있지 않음
                 * poll 또는 추가 할때 마다 마지막 값과 정렬함
                 * 그러므로 실제 큐에 목록과
                 * 모든 값을 poll했을대 순서가 다름
                 */

                int o1Compare = Math.abs(5 - o1);
                int o2Compare = Math.abs(5 - o2);

                if (o1Compare == o2Compare) {
                    // 비교값이 같으면 큰수가 먼저오게
                    return o2 - o1;
                }

                return Math.abs(5 - o1) - Math.abs(5 - o2);
            }
        });

        exampleQ.offer(5);
        exampleQ.offer(7);
        exampleQ.offer(9);
        exampleQ.offer(1);
        exampleQ.offer(3);

        System.out.println(exampleQ);
        System.out.print(exampleQ.poll() + " ");
        System.out.print(exampleQ.poll() + " ");
        System.out.print(exampleQ.poll() + " ");
        System.out.print(exampleQ.poll() + " ");
        System.out.print(exampleQ.poll() + " ");

    }

}
