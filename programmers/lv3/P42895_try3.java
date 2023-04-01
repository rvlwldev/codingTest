package programmers.lv3;

import java.util.*;

class Solution42895_3 {

    public int solution_try4(int n, int number) {
        List<HashSet<Integer>> list = new ArrayList<>();

        List<List<int[]>> indexList = initMappingIndexes();

        for (List<int[]> l : indexList) {
            System.out.print(indexList.indexOf(l) + " : ");
            for (int[] arr : l) {
                System.out.print(Arrays.toString(arr) + " ");
            }
            System.out.println();
        }

        for (int index = 1; index < 8; index++) {
            Set<Integer> currentSet = new HashSet<>();


        }


        return -1;
    }

    //
    private List<List<int[]>> initMappingIndexes() {
        List<List<int[]>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        for (int i = 1; i < 8; i++) {
            res.add(new ArrayList<>());

            for (int j = 0; j <= i; j++) {
                for (int k = 1; k <= j; k++) {

                    List<int[]> mapping = res.get(i);

                    if (i == (j + k)) {
                        if (j == k) {
                            System.out.println(i + ": " + j + " + " + k);

                            mapping.add(new int[] {j, k});
                        } else {
                            System.out.println(i + ": " + j + " + " + k);
                            System.out.println(i + ": " + k + " + " + j);

                            mapping.add(new int[] {j, k});
                            mapping.add(new int[] {k, j});
                        }
//                        System.out.println(mapping);
                        res.set(i, mapping);
                    }
                }
            }
//            System.out.println();
        }

        return res;
    }

}

public class P42895_try3 {
    public static void main(String[] args) {
        Solution42895_3 sol = new Solution42895_3();

//        System.out.println("try3 : " + sol.solution_try4(2, 11));   // 3
//        System.out.println("try3 : " + sol.solution_try4(3, 27));   // 3
//        System.out.println("try3 : " + sol.solution_try4(3, 4));    // 3
//        System.out.println("try3 : " + sol.solution_try4(5, 12));   // 4
//        System.out.println("try3 : " + sol.solution_try4(5, 3600)); // 6
//        System.out.println("try3 : " + sol.solution_try4(3, 2187)); // 7
//        System.out.println("try3 : " + sol.solution_try4(8, 5800)); // 8
    }
}
