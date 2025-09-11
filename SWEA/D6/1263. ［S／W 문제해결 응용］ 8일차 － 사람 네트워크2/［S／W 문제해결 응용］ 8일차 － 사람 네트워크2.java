import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] dist;
    static final int INF = 1001; 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            
            dist = new int[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Integer.parseInt(st.nextToken());
                    if (i != j && dist[i][j] == 0) {
                        dist[i][j] = INF;
                    }
                }
            }
            
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
            
            long ans = Long.MAX_VALUE;
            
            for (int i = 0; i < N; i++) {
                long sum = 0;
                for (int j = 0; j < N; j++) {
                    sum += dist[i][j];
                }
                ans = Math.min(ans, sum);
            }
            
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        
        System.out.println(sb);
    }
}