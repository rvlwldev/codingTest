package toss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test02 {
    public static void main(String[] args) {
        P p = new P();

        System.out.println(p.sol(new int[][]
                {
                        {1, 2},
                        {2, 3},
                        {2, 6},
                        {3, 4},
                        {4, 5}
                }, 2, 3)
        ); // 37

        System.out.println(p.sol(new int[][]
                {
                        {1, 2},
                        {2, 3},
                        {2, 6},
                        {3, 4},
                        {4, 5}
                }, 1, 2)
        ); // 27


    }

    private static class P {

        private HashMap<Integer, List<Integer>> map;

        public int sol(int[][] relationships, int target, int limit) {
            map = toRelationshipMap(relationships);

            int originCount = map.get(target).size();
            int allCount = getAllFriendCount(map.get(target), 0, limit);

            int newFriendsCount = allCount - originCount;
            int pay = originCount * 5 + newFriendsCount * 10;

            return newFriendsCount + pay;
        }

        private int getAllFriendCount(List<Integer> list, int depth, int limit) {
            if (depth >= limit - 1) {
                return (int) list.stream()
                        .distinct()
                        .count() - 1; // 자기자신 제외
            }

            List<Integer> friendsList = new ArrayList<>(list);

            for (int friend : list) friendsList.addAll(map.get(friend));

            return getAllFriendCount(friendsList, depth + 1, limit);
        }

        private HashMap<Integer, List<Integer>> toRelationshipMap(int[][] relationships) {
            HashMap<Integer, List<Integer>> result = new HashMap<>();

            for (int[] rel : relationships) {
                int a = rel[0];
                int b = rel[1];

                List<Integer> aList = result.getOrDefault(a, new ArrayList<>());
                List<Integer> bList = result.getOrDefault(b, new ArrayList<>());

                aList.add(b);
                bList.add(a);

                result.put(a, aList);
                result.put(b, bList);
            }

            return result;
        }
    }
}
