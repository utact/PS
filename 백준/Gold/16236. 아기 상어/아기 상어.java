import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    static int[][] map;
    static int[][] vst;
    static int N;
    static int t;

    static boolean bool;
    static int eatCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int[] info = new int[]{0, 0, 2, 0};

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());

                if (v == 9) {
                    info[0] = i;
                    info[1] = j;
                    map[i][j] = 0;
                } else {
                    map[i][j] = v;
                }
            }
        }

        bool = true;

        while (bool) {
            vst = new int[N][N];
            info[3] = 0;

            int[] nextInfo = bfs(info);

            if (nextInfo[0] == -1) {
                bool = false;
                break;
            }

            info[0] = nextInfo[0];
            info[1] = nextInfo[1];

            eatCnt++;

            if (eatCnt == info[2]) {
                info[2]++;
                eatCnt = 0;
            }

            t += nextInfo[3];
        }

        System.out.print(t);
    }

    static int[] bfs(int[] info) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{info[0], info[1], info[2], info[3]});

        List<int[]> fish = new ArrayList<>();
        int minDst = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[3] > minDst) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                int nDist = cur[3] + 1;

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || vst[nr][nc] == 1 || map[nr][nc] > info[2]) {
                    continue;
                }

                if (nDist > minDst) {
                    continue;
                }

                vst[nr][nc] = 1;

                if (map[nr][nc] > 0 && map[nr][nc] < info[2]) {
                    fish.add(new int[]{nDist, nr, nc});
                    minDst = nDist;
                } else {
                    q.offer(new int[]{nr, nc, info[2], nDist});
                }
            }
        }

        if (fish.isEmpty()) {
            return new int[]{-1};
        }

        fish.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });

        int[] fin = fish.get(0);
        int fDst = fin[0];
        int fR = fin[1];
        int fC = fin[2];

        map[fR][fC] = 0;

        return new int[]{fR, fC, info[2], fDst};
    }
}