import java.io.*;
import java.util.*;
 
public class Solution {
    // 상0 하1 좌2 우3
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
 
    static int[][] blocks = {
            { 1, 3, 0, 2 }, // 하우상좌
            { 3, 0, 1, 2 }, // 우상하좌
            { 2, 0, 3, 1 }, // 좌상우하
            { 1, 2, 3, 0 }, // 하좌우상
            { 1, 0, 3, 2 }  // 하상우좌
    };
     
    static class Ball {
        int r;
        int c;
        int dir;
         
        Ball(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
         
        void set(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
 
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] map = new int[N + 2][N + 2];
             
            int maxCnt = 0;
             
            // 5번 블록으로 초기화
            for (int r = 0; r < map.length; r++) {
                for (int c = 0; c < map.length; c++) {
                    map[r][c] = 5;
                }
            }
 
            // 맵 입력
            for (int r = 1; r <= N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
 
                for (int c = 1; c <= N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }
             
            // 구슬 쏘기
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (map[r][c] == 0) {
                        maxCnt = Math.max(maxCnt, move(map, new int[] {r, c}));
                    }
                }
            }
             
            sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
        } // TC
         
        System.out.println(sb);
    }
 
    static int move(int[][] map, int[] start) {
        int maxCnt = 0;
         
        for (int d = 0; d < 4; d++) {
            int cnt = 0;
             
            int r = start[0];
            int c = start[1];
             
            Ball ball = new Ball(r, c, d);
             
            while (true) {
                int nr = ball.r + dr[ball.dir];
                int nc = ball.c + dc[ball.dir];
                 
                // 종료 조건
                if ((nr == start[0] && nc == start[1]) || (map[nr][nc] == -1)) {
                    maxCnt = Math.max(maxCnt, cnt);
                     
                    break;
                }
                 
                // 이동
                if (map[nr][nc] == 0) {
                    ball.set(nr, nc, ball.dir);
                }
                // 블록 만남
                else if (map[nr][nc] > 0 && map[nr][nc] < 6) {
                    ball.set(nr, nc, blocks[map[nr][nc] - 1][ball.dir]);
                    cnt++;
                }
                // 웜홀 만남
                else if (map[nr][nc] > 5 && map[nr][nc] < 11) {
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map.length; j++) {
                            if (map[i][j] == map[nr][nc] && (i != nr || j != nc)) {
                                ball.set(i, j, ball.dir); 
                            }
                        }
                    }
                }
            }
        }
         
        return maxCnt;
    }
}