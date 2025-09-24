import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static Integer[] dp;
    static int[] values;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new Integer[n + 1];
        values = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        dp[0] = 0;
        dp[1] = values[1];
        if (n >= 2) {
            dp[2] = values[1] + values[2];
        }

        System.out.println(find(n));
    }

    private static int find(int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(find(n - 2), find(n - 3) + values[n - 1]) + values[n];
        }
        return dp[n];
    }
}