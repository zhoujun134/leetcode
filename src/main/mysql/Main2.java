import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: Created by com.zj
 * @Date: 2021/09/14 下午 08:22
 */
public class Main2 {

    public static List<Integer> distance(int N, int Q, List<Integer> K){
        // 创建所有人的位置列表
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        List<Integer> result = new ArrayList<>();
        for (int i : K) {
            List<Integer> s = new ArrayList<>();
            for (int j = 0; j < i+1; j++) {
                s.add(list.get(j) - j);
                int cur = list.get(j) - s.get(j);
                list.set(j, cur);
            }
            list.remove(0);
            int total = 0;
            for (int x : s) {
                total += x;
            }
            result.add(total - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line1 = input.nextLine();
        String line2 = input.nextLine();
        String[] s = line1.split(" ");
        int N = Integer.parseInt(s[0]);
        int Q = Integer.parseInt(s[1]);
        String[] s1 = line2.split(" ");
        List<Integer> K = new ArrayList<>();
        for (String k: s1) {
            K.add(Integer.parseInt(k));
        }
        List<Integer> distance = distance(N, Q, K);
        for (int i = 0; i < distance.size(); i++) {
            System.out.print(i + " ");
        }


    }
}
