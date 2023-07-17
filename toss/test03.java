package toss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class test03 {
    public static void main(String[] args) {
        P p = new P();
        String[] input;

        input = new String[]{
                "토스커피사일로&베이커리",
                "토스커피사일로 베이커리",
                "토스커피사일로 베이커",
                "토스커피사일로 베이",
                "토스커피사일"
        };
        System.out.println(Arrays.toString(p.sol(input))); // 답 : [토스커피사일로&베이커리]

        input = new String[]{
                "토스커피사일로&베이커리",
                "토스커피사일로 베이커리",
                "토스커피사일로 베이커",
                "토스커피사일로 베이",
                "토스커피사일",
                "비바리퍼블리카 식당",
                "비바리퍼블리카식",
                "비바리퍼블리"
        };
        System.out.println(Arrays.toString(p.sol(input))); // 답 : [토스커피사일로&베이커리, 비바리퍼블리카 식당]

        input = new String[]{
                "망고&토마토",
                "망고, 토마토",
                "비,비",
                "비비.."
        };
        System.out.println(Arrays.toString(p.sol(input)));
    }

    private static class P {

        List<String> SPECIAL_SYMBOLS = List.of("&", "(", ")", ".", ",", "-");

        public String[] sol(String[] names) {
            List<String> result = new ArrayList<>();

            int order = 0;
            HashMap<Integer, String> map = new HashMap<>() {
                {
                    put(0, names[0]);
                }
            };

            for (int i = 1; i < names.length; i++) {
                String befName = names[i - 1];
                String curName = names[i];

                if (!isEqual(befName, curName)) map.put(++order, curName);
            }

            for (int key : map.keySet()) result.add(map.get(key));

            return result.toArray(new String[0]);
        }

        private boolean isEqual(String a, String b) {
            a = format(a);
            b = format(b);

            return a.contains(b) || b.contains(a);
        }

        private String format(String s) {
            s = removeBlank(s);
            s = removeSymbols(s);

            return s;
        }

        private String removeBlank(String s) {
            return String.join("", s.split(" "));
        }

        private String removeSymbols(String s) {
            return Arrays.stream(s.split(""))
                    .filter(v -> !SPECIAL_SYMBOLS.contains(v))
                    .collect(Collectors.joining());
        }

    }
}
