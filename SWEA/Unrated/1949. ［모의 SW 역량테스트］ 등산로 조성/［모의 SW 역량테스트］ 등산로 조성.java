import java.io.*;
import java.util.*;
 
/*
 * 등산로 N * N (지형의 높이 정보)
 *
 * DFS
 * 어차피 공사 가능한 위치는 한 곳으로 제한되니 -> boolean
 * K값은 상수로 관리
 */
 
public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
 
    static int N;
    static int K;
    static int[][] map;
    static int[][] vst;
 
    static StringBuilder sb = new StringBuilder();
    static int ans;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            ans = 0; // 정답 초기화
 
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            vst = new int[N][N];
 
            int max = 0; // 봉우리 초기화
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    map[i][j] = v;
                    max = Math.max(max, v);
                }
            } // 배열 입력받기
 
            List<int[]> arr = new ArrayList<>();
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == max) {
                        arr.add(new int[] {i, j});
                    }
                }
            } // 가장 높은 값 저장
 
            for (int i = 0; i < arr.size(); i++) {
                dfs(arr.get(i), true, 1);
            } // 탐색하기
 
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        } // 테스트케이스
 
        System.out.print(sb);
    } // 메인
 
    static void dfs(int[] cur, boolean isPos, int depth) {
        ans = Math.max(ans, depth);
 
        int r = cur[0];
        int c = cur[1];
 
        vst[r][c] = 1;
 
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
 
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && vst[nr][nc] == 0) {
                if (map[r][c] > map[nr][nc]) {
                    dfs(new int[] {nr, nc}, isPos, depth + 1);
                } else {
                    if (isPos) {
                        if (map[nr][nc] - map[r][c] < K) {
                            int origH = map[nr][nc];
 
                            map[nr][nc] = map[r][c] - 1;
                            dfs(new int[] {nr, nc}, false, depth + 1);
 
                            map[nr][nc] = origH;
                        }
                    }
                }
            }
        }
 
        vst[r][c] = 0;
    }
}