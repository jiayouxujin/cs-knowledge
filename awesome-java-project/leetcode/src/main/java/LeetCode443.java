import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LeetCode443 {
    public static void main(String[] args) {
        LeetCode443 leetCode443 = new LeetCode443();
        String[] bank = new String[]{"AAAAAAAA", "AAAAAAAC", "AAAAAACC", "AAAAACCC", "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCCCA"};
        System.out.println(leetCode443.minMutation("AAAAAAAA", "CCCCCCCC", bank));
    }

    public int minMutation(String start, String end, String[] bank) {
        LinkedList<String> startQueue = new LinkedList<>();
        LinkedList<String> endQueue = new LinkedList<>();

        startQueue.add(start);
        endQueue.add(end);

        int res = 0;
        Set<String> dict = new HashSet<>();
        Set<String> mem = new HashSet<>();
        for (String b : bank) {
            dict.add(b);
        }
        if (!dict.contains(end)) {
            return -1;
        }
        while (!startQueue.isEmpty() && !endQueue.isEmpty()) {
            res++;

            LinkedList<String> tmpResQueue = new LinkedList<>();
            if (startQueue.size() > endQueue.size()) {
                LinkedList tmp = startQueue;
                startQueue = endQueue;
                endQueue = tmp;
            }
            int size = startQueue.size();
            for (int i = 0; i < size; i++) {
                String cur = startQueue.poll();
                for (int j = 0; j < bank.length; j++) {
                    if (vertify(cur, bank[j])) {
                        if (endQueue.contains(bank[j])) {
                            return res;
                        }
                        if (!mem.contains(bank[j])) {
                            tmpResQueue.add(bank[j]);
                            mem.add(bank[j]);
                        }
                    }
                }
            }
            if (startQueue.size() <= endQueue.size()) {
                startQueue = tmpResQueue;
            } else {
                endQueue = tmpResQueue;
            }
        }
        return -1;
    }

    public boolean vertify(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
        }
        if (count > 1) {
            return false;
        }
        return true;
    }

}
