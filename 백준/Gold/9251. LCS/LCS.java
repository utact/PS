import java.io.*;

public class Main {
    static String A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();

        solve();
    }

    static void solve() {
        int aLen = A.length(), bLen = B.length();

        int[][] dp = new int[aLen + 1][bLen + 1];

        // 최장 길이 구하기
        for (int i = 0; i < aLen; i++) {
            for (int j = 0; j < bLen; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        System.out.println(dp[aLen][bLen]);
    }
}