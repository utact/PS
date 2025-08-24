import java.io.*;
import java.util.*;

/*
13:50 시작

- 빌딩 수 N (높이 같은 빌딩 없음)
- 가장 왼쪽에서 본 빌딩 수 L, 가장 오른쪽에서 본 빌딩 수 R
- 다음 빌딩을 어느 위치에 넣느냐로 점화식 유도 (좌측, 중간, 우측)
 */

public class Main {
    static long[][][] dp = new long[101][101][101]; // dp[N][L][R]
    static final int tmp = 1000000007;

    public static void main(String[] args) throws Exception {
        // N = 1
        dp[1][1][1] = 1;

        // N = 2
        dp[2][2][1] = 1;
        dp[2][1][2] = 1;

        // N > 2
        for (int n = 3; n < 101; n++) {
            for (int l = 1; l < 101; l++) {
                for (int r = 1; r < 101; r++) {
                    dp[n][l][r] = ( (dp[n - 1][l - 1][r]) + (dp[n - 1][l][r - 1]) + (dp[n - 1][l][r] * (n - 2)) ) % tmp;
                }
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long ans = dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
        System.out.println(ans);
    }
}
