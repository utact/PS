import java.io.*;
import java.util.*;
 
/*
 * 11:00
 * 칼로리 L 이하인 최대 만족도 점수 -> 냅색 DP
 */
 
public class Solution {
     
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
             
             
            int[] scores = new int[N];
            int[] kcals = new int[N];
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                kcals[i] = Integer.parseInt(st.nextToken());
            }
 
             
            int[] dp = new int[L + 1];
 
            for (int i = 0; i < N; i++) {
                for (int j = L; j >= kcals[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - kcals[i]] + scores[i]);
                }
            }
 
            sb.append('#').append(tc).append(' ').append(dp[L]).append('\n');
        }
         
        System.out.print(sb);
    }
     
}