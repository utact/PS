import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        } // 초기화: 자기 자신이 대표

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                union(a, b);
            } else if (cmd == 1) {
                if (find(a) == find(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.print(sb);
    }

    static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent == bParent) return;
        parent[aParent] = bParent;
    }

    static int find(int v) {
        if (parent[v] == v) return v;

        return parent[v] = find(parent[v]);
    }
}