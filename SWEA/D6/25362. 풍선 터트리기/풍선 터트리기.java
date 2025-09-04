import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] b = new int[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N][N];

            for (int len = 1; len <= N; len++) {
                for (int i = 0; i <= N - len; i++) {
                    int j = i + len - 1;
                    for (int k = i; k <= j; k++) {
                        int left = (k == i) ? 0 : dp[i][k - 1];
                        int right = (k == j) ? 0 : dp[k + 1][j];
                        
                        int score;
                        if (i == 0 && j == N - 1) {
                            score = b[k];
                        } 
                        else if (i == 0) {
                            score = b[j + 1];
                        } 
                        else if (j == N - 1) {
                            score = b[i - 1];
                        } 
                        else {
                            score = b[i - 1] * b[j + 1];
                        }
                        
                        dp[i][j] = Math.max(dp[i][j], left + right + score);
                    }
                }
            }
            
            sb.append('#').append(tc).append(' ').append(dp[0][N - 1]).append('\n');
        }
        
        System.out.print(sb);
    }
}