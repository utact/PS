import java.io.*;
import java.util.*;
 
/*
 * 반복문으로 구분선 두 개 긋기
 */
 
public class Solution {
    static int[][] flag;
    static int N, M, ans;
 
    public static void main(String[] args) throws Exception {
//      System.out.println((byte) 'W'); 87
//      System.out.println((byte) 'B'); 66
//      System.out.println((byte) 'R'); 82
 
        StringBuilder sb = new StringBuilder();
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
 
            flag = new int[N][M];
            ans = Integer.MAX_VALUE;
 
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    flag[i][j] = line.charAt(j);
                }
            }
             
            for (int i = 1; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    cal(i, j);
                }
            }
 
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
 
        System.out.print(sb);
    }
 
    static void cal(int r1, int r2) {
        int sum = 0;
 
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < M; j++) {
                if (flag[i][j] != 87) {
                    sum++;
                }
            }
        }
 
        for (int i = r1; i < r2; i++) {
            for (int j = 0; j < M; j++) {
                if (flag[i][j] != 66) {
                    sum++;
                }
            }
        }
 
        for (int i = r2; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (flag[i][j] != 82) {
                    sum++;
                }
            }
        }
 
        ans = Math.min(ans, sum);
    }
}