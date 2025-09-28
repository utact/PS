import java.io.*;
import java.util.*;

// 플로이드 워셜 O(N^3)

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static int n, m;
    static int[][] dist;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        } // INF 초기화 -> 자기 자신으로 가는 최단 비용은 0

        StringTokenizer st;

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(dist[a][b], c);
        } // 초기 경로 입력 -> 더 저렴한 노선으로 갱신

        for (int tmp = 1; tmp <= n; tmp++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (dist[a][tmp] == INF || dist[tmp][b] == INF) continue;
                    dist[a][b] = Math.min(dist[a][b], dist[a][tmp] + dist[tmp][b]);
                }
            }
        } // a 도시에서 b 도시로 가되, tmp 경유한다면 어떤지 비교

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) {
                    sb.append("0 ");
                } else {
                    sb.append(dist[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}