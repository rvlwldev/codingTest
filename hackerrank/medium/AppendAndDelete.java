package hackerrank.medium;

public class AppendAndDelete {
    public static void main(String[] args) {
//        System.out.println(appendAndDelete("hackerhappy", "hackerrank", 9)); // Yes
//        System.out.println(appendAndDelete("aba", "aba", 7)); // Yes
//        System.out.println(appendAndDelete("ashley", "ash", 2)); // No


//        System.out.println(appendAndDelete(
//                "uoiauwrebgiwrhgiuawheirhwebvjforidkslweufgrhvjqasw",
//                "vgftrheydkoslwezxcvdsqjkfhrydjwvogfheksockelsnbkeq",
//                100)); // Yes


        System.out.println(appendAndDelete(
                "asdfqwertyuighjkzxcvasdfqwertyuighjkzxcvasdfqwertyuighjkzxcvasdfqwertyuighjkzxcvasdfqwertyuighjkzxcv",
                "bsdfqwertyuighjkzxcvasdfqwertyuighjkzxcvasdfqwertyuighjkzxcvasdfqwertyuighjkzxcvasdfqwertyuighjkzxcv",
                100)
        ); // No


    }

    public static String appendAndDelete(String s, String t, int k) {
        int count = 0;
        int len = s.length() + t.length();

        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) == t.charAt(i)) count++;
            else break;
        }

        if (len <= count * 2 + k
        && (len % 2 == k % 2 || len < k)) return "Yes";
        else return "No";
    }
}
