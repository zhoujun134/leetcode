import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Created by com.zj
 * @Date: 2021/09/14 下午 07:51
 */
public class Main {


    public static List<String> getCurrentDataList(int row, int col){
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= col; i++) {
            String data = (i * row) + "";
            result.add(new StringBuilder(data).reverse().toString());
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int K = input.nextInt();
        List<String> currentDataList = getCurrentDataList(N, K);
        int max = currentDataList.stream()
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0);
        System.out.println(max);
    }
}
