/*
 * 16:23 시작
 *
 * 42인 공간만 이동 또는 확장 가능
 * 예비 위험 지역을 수연이 이동 후보에서 제거
 */

import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, ans;
    static int[][] map;
    static int[][] vst1, vst2;
    static int[] dv, pl, goal;

    static Queue<int[]> devil, player;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 큐 초기화
            devil = new ArrayDeque<>();
            player = new ArrayDeque<>();

            // 배열 및 정답 초기화
            map = new int[N][M];
            vst1 = new int[N][M];
            vst2 = new int[N][M];
            ans = 0;

            // 좌표 초기화
            dv = null;
            pl = null;
            goal = new int[] {-1, -1};

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    int v = line.charAt(j);

                    if (v == 'S') {
                        player.add(new int[]{i, j});
                        vst2[i][j] = 1;
                    } else if (v == 'D') {
                        goal = new int[]{i, j};
                    } else if (v == '*') {
                        devil.add(new int[]{i, j});
                        vst1[i][j] = 1;
                    }

                    map[i][j] = v;
                }
            }

            movePl();

            if (ans == 0) {
                sb.append('#').append(tc).append(' ').append("GAME OVER").append('\n');
            } else {
                sb.append('#').append(tc).append(' ').append(ans).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void movePl() {
        int time = 0;

        while (!player.isEmpty()) {
            int rep = player.size();
            renewMap();

            while (rep-- > 0) {
                int[] tmp = player.poll();

                // 뽑은 좌표가 도착지라면 타임 갱신 후 종료
                if(tmp == null) continue;
                if (tmp[0] == goal[0] && tmp[1] == goal[1]) {
                    ans = time;
                    return;
                }

                int r = tmp[0];
                int c = tmp[1];

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || vst2[nr][nc] == 1 || map[nr][nc] == 'X' || map[nr][nc] == '*') {
                        continue;
                    }

                    player.add(new int[] { nr, nc });
                    vst2[nr][nc] = 1;
                }
            }

            time++;
        }
    }

    // 손아귀 적용 메서드
    static void renewMap() {
        int rep = devil.size();

        while (rep-- > 0) {
            int[] tmp = devil.poll();

            if(tmp == null) continue;
            int r = tmp[0];
            int c = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || vst1[nr][nc] == 1 || map[nr][nc] != '.') {
                    continue;
                }

                devil.add(new int[] { nr, nc });
                vst1[nr][nc] = 1;
                map[nr][nc] = 'X';
            }
        }
    }
}