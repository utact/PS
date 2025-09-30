import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j] -> i 행렬부터 j 행렬까지 곱의 최소 연산 횟수
        int[][] dp = new int[N][N];

        // len -> 곱하는 행렬의 개수 - 1
        for (int len = 1; len < N; len++) {
            for (int i = 0; i < N - len; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                // k -> i, j 사이를 나누는 기준점
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + map[i][0] * map[k][1] * map[j][1];
                    if (dp[i][j] > cost) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        System.out.println(dp[0][N - 1]);
    }
}