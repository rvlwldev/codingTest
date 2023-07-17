package toss;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class test04 {
    public static void main(String[] args) {
        P p = new P();

//        System.out.println(Arrays.toString(p.sol(new String[]{"1", "2", "3", "4", "3"}, 3))); // [3, 4, 2]
//        System.out.println(Arrays.toString(p.sol(new String[]{"1", "2", "3", "4", "5"}, 3))); // [5, 4, 3]
//        System.out.println(Arrays.toString(p.sol(new String[]{"B", "F"}, 3))); // []
        System.out.println(Arrays.toString(p.sol(new String[]{"1", "2", "B", "F"}, 3))); // [2, 1]
//        System.out.println(Arrays.toString(p.sol(new String[]{"1", "2", "B", "B", "3"}, 3))); // [3, 1, 2]

    }

    private static class P {
        public String[] sol(String[] action, int maxSize) {
            History history = new History(maxSize);

            for (String input : action) history.action(input);

            return history.getResultAsArray();
        }

        class History {
            final int size;
            LinkedList<String> lasts;
            LinkedList<String> backList;
            LinkedList<String> frontList;

            History(int size) {
                this.size = size;

                lasts = new LinkedList<>();
                backList = new LinkedList<>();
                frontList = new LinkedList<>();
            }

            public String[] getResultAsArray() {
                return lasts.toArray(new String[0]);
            }

            public void action(String input) {
                if (input.length() < 1) return;

                try {
                    goPage(Integer.parseInt(input));
                } catch (NumberFormatException e) {

                    if (input.equals("B")) goBack();
                    else if (input.equals("F")) goFront();

                } finally {
                    resizeAndDistinct();

                    System.out.println("input  : " + input);
                    System.out.println("recent : " + lasts);
                    System.out.println("back   : " + backList);
                    System.out.println("front  : " + frontList);
                    System.out.println();
                }
            }

            private void goPage(int input) {
                if (!lasts.isEmpty()) backList.add(lasts.getFirst());

                String page = String.valueOf(input);

                lasts.remove(page);
                lasts.addFirst(page);
            }

            private void goBack() {
                if (backList.isEmpty()) return;

                String page = backList.removeFirst();

                lasts.addFirst(page);
                frontList.addFirst(page);
            }

            private void goFront() {
                if (frontList.isEmpty()) return;

                String page = frontList.removeFirst();

                lasts.addFirst(page);
                backList.addFirst(page);
            }

            private void resizeAndDistinct() {
                if (lasts.size() > size) lasts = new LinkedList<>(lasts.subList(0, size));

                LinkedList<String> temp = new LinkedList<>();
                HashSet<String> set = new HashSet<>();

                for (String p : lasts) {
                    if (!set.contains(p)) {
                        set.add(p);
                        temp.add(p);
                    }
                }

                lasts = temp;
            }
        }


    }
}
