import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] inDeg;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDeg = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        } // 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int tmp = Integer.parseInt(st.nextToken()); // 이번 정렬 조건 수
            int from = Integer.parseInt(st.nextToken());

            for (int j = 1; j < tmp; j++) {
                int to = Integer.parseInt(st.nextToken());

                adj.get(from).add(to);
                inDeg[to]++;

                from = to; // 다음 조건을 위한 갱신
            }
        }

        check();
        System.out.print(sb);
    }

    static void check() {
        Queue<Integer> q = new ArrayDeque<>();
        StringBuilder tmp = new StringBuilder();

        int cnt = 0;

        for (int i = 1; i < inDeg.length; i++) {
            if (inDeg[i] == 0) {
                q.add(i);
                tmp.append(i).append('\n');
                cnt++;
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < adj.get(cur).size(); i++) {
                int next = adj.get(cur).get(i);
                inDeg[next]--;

                if (inDeg[next] == 0) {
                    q.add(next);
                    tmp.append(next).append('\n');
                    cnt++;
                }
            }
        }

        if (cnt == N) {
            sb.append(tmp);
        } else {
            sb.append(0);
        }
    }
}