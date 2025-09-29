import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] map;
    static int[][] vstW, vstS; // 물, 고슴도치

    static int[] S, D; // S: 고슴도치, D: 비버의 굴
    static ArrayList<int[]> W; // 물 범람 시작 위치 리스트

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        vstW = new int[R][C];
        vstS = new int[R][C];
        W = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                int v = line.charAt(j);

                if (v == 'S') S = new int[] {i, j};
                else if (v == 'D') D = new int[] {i, j};
                else if (v == '*') W.add(new int[] {i, j});

                map[i][j] = v;
            }
        }

        bfs();
    }

    static void bfs() {
        int ans = 0;

        Queue<int[]> wq = new ArrayDeque<>();
        for (int i = 0; i < W.size(); i++) {
            wq.add(W.get(i));
            vstW[W.get(i)[0]][W.get(i)[1]] = 1;
        }

        Queue<int[]> sq = new ArrayDeque<>();
        sq.add(S);
        vstS[S[0]][S[1]] = 1;

        while (!sq.isEmpty()) {
            int wRep = wq.size();

            while (wRep-- > 0) {
                int[] cur = wq.poll();
                int r = cur[0];
                int c = cur[1];

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C || vstW[nr][nc] == 1 || map[nr][nc] != '.') {
                        continue;
                    }

                    wq.add(new int[] {nr, nc});
                    vstW[nr][nc] = 1;
                }
            }

            int sRep = sq.size();

            while (sRep-- > 0) {
                int[] cur = sq.poll();
                int r = cur[0];
                int c = cur[1];

                if (r == D[0] && c == D[1]) {
                    System.out.println(ans);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C || vstW[nr][nc] == 1 || vstS[nr][nc] == 1 || (map[nr][nc] != '.' && map[nr][nc] != 'D')) {
                        continue;
                    }

                    sq.add(new int[] {nr, nc});
                    vstS[nr][nc] = 1;
                }
            }

            ans++;
        }

        System.out.println("KAKTUS");
    }
}