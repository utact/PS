import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Integer>[] adj;
    static int[] subtrSize;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 초기화
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        subtrSize = new int[N + 1];

        // 인접리스트
        for (int i = 0; i < N - 1; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // 서브트리 계산
        dfs(1, 0);

        long totalDiv = 0;
        long totalPairs = (long) N * (N - 1) / 2;

        // 기어도 계산
        for (int i = 2; i <= N; i++) {
            long s = subtrSize[i];
            long restCnt = N - s;

            // 해당 간선을 사용하지 않는 경로의 수
            long restPairs = restCnt * (restCnt - 1) / 2;
            
            // 해당 간선의 기여도 = (전체 경로 수) - (사용하지 않는 경로 수)
            totalDiv += totalPairs - restPairs;
        }

        System.out.println(totalDiv);
    }

    public static void dfs(int cur, int par) {
        subtrSize[cur] = 1;

        for (int nb : adj[cur]) {
            if (nb != par) {
                dfs(nb, cur);
                subtrSize[cur] += subtrSize[nb];
            }
        }
    }
}