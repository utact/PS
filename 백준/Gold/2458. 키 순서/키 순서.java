import java.io.*;
import java.util.*;

/*
 * 자신의 키가 몇 번째인지 알 수 있는 학생의 수
 * -> 언듯 보기에 위상정렬 같지만 위상정렬은 '가능한 순서 중 하나'를 찾는 알고리즘
 * -> '자신의 순서가 유일하게 확정되는가'를 알기 위해서는 플로이드-워셜 알고리즘 필요
 *
 * >> 자기 자신을 경유지로 두고, 앞/뒤 가능한 학생 수를 더해서 n명 이라면 순서 확정 가능한 것
 * >> 플로이드-워셜은 '가중치 그래프의 최단 거리'뿐 아니라, '가중치가 없는 그래프의 도달 가능성'도 찾을 수 있음
 */

public class Main {
    static final int INF = 987654321; // 적당히 큰 수로 설정 (오버플로우 방지)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1; // 키 순서 직접 비교 가능
        }

        for (int tmp = 1; tmp <= n; tmp++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (dist[a][tmp] == INF || dist[tmp][b] == INF) continue;
                    dist[a][b] = Math.min(dist[a][b], dist[a][tmp] + dist[tmp][b]);
                }
            }
        }

        int ans = 0;

        for (int a = 1; a <= n ; a++) {
            int cnt = 0;

            for (int b = 1; b <= n; b++) {
                if (dist[a][b] == INF && dist[b][a] == INF) continue;
                cnt++;
            }

            if (cnt == n) {
                ans++;
            }
        }

        System.out.print(ans);
    }
}