import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        int pt = 0;
        List<Integer> tmp = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && visited[i][j] == 0) {
                    tmp.add(bfs(new int[] {i, j}));
                    pt++;
                }
            }
        }

        Collections.sort(tmp);
        sb.append(pt).append("\n");
        for (int i = 0; i < pt; i++) {
            sb.append(tmp.get(i)).append("\n");
        }

        System.out.print(sb);
    }

    static int bfs(int[] st) {
        int ct = 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(st);
        visited[st[0]][st[1]] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nR = r + dr[i];
                int nC = c + dc[i];

                if (nR >= 0 && nR < N && nC >= 0 && nC < N &&
                        map[nR][nC] == 1 && visited[nR][nC] == 0) {
                    q.offer(new int[] {nR, nC});
                    visited[nR][nC] = 1;
                    ct++;
                }
            }
        }

        return ct;
    }
}