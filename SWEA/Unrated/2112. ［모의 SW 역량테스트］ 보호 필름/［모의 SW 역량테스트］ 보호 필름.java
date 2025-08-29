import java.io.*;
import java.util.*;
 
/*
행 D, 열 W, 열 연속 K
출력 -> 최소 약품투입 횟수
 
백트래킹
- 최소 약품투입 횟수 MAX_VALUE
- Math.min()
 
각 행에 대한 경우의 수 3가지
원본 행, 0 행, 1 행
 */
 
public class Solution {
    static int D, W, K;
    static int ans;
    static int[] a, b;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            // 대체 목적 행
            a = new int[W];
            b = new int[W];
            Arrays.fill(a, 1);
 
            map = new int[D][W];
            ans = Integer.MAX_VALUE;
 
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
 
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            dfs(0, 0);
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
 
        System.out.println(sb.toString());
    }
 
    private static void dfs(int row, int injection) {
        if (injection > K || injection >= ans) {
            return;
        }
 
        if (row == D) {
            if (isGood()) {
                ans = Math.min(ans, injection);
            }
            return;
        }
 
        // 1. 원본
        dfs(row + 1, injection);
        // 2. a 행으로 대체
        int[] origin = map[row];
        map[row] = a;
        dfs(row + 1, injection + 1);
        // 3. b 행으로 대체
        map[row] = b;
        dfs(row + 1, injection + 1);
        map[row] = origin;
    }
 
    private static boolean isGood() {
        for (int c = 0; c < W; c++) {
            int max = 1;
            int cnt = 1;
 
            for (int r = 1; r < D; r++) {
                if (map[r][c] == map[r - 1][c]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
 
                max = Math.max(max, cnt);
            }
 
            if (max < K) {
                return false;
            }
        }
 
        return true;
    }
}