import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, ans;
    static boolean[] vst;
    static ArrayList<ArrayList<Integer>> graph;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            solve();

            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }

        System.out.print(sb);
    }

    static void solve() {
    	// 초기화
        Queue<int[]> q = new ArrayDeque<>();
        vst = new boolean[N + 1];
        ans = 0;

        // {번호, 깊이}
        q.add(new int[]{1, 0});
        vst[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curNo = cur[0];
            int depth = cur[1];
            
            if (depth >= 2) {
                continue;
            }

            for (int no : graph.get(curNo)) {
                if (!vst[no]) {
                    vst[no] = true;
                    ans++;
                    q.add(new int[]{no, depth + 1});
                }
            }
        }
    }
}