import java.util.*;
 
public class Solution {
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] children;
    static int[] parent;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
            int V = sc.nextInt(); // 정점 수
            int E = sc.nextInt(); // 간선 수
            int tgA = sc.nextInt(); // 타겟 1
            int tgB = sc.nextInt(); // 타겟 2
 
            parent = new int[V + 1];
            children = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                children[i] = new ArrayList<>();
            }
 
            for (int i = 0; i < E; i++) {
                int p = sc.nextInt();
                int c = sc.nextInt();
 
                parent[c] = p;
                children[p].add(c);
            }
 
            Set<Integer> anc = new HashSet<>();
            int cur = tgA;
            while (cur != 0) {
                anc.add(cur);
                cur = parent[cur];
            }
 
            int lca = 0;
            cur = tgB;
            while (cur != 0) {
                if (anc.contains(cur)) {
                    lca = cur;
                    break;
                }
                cur = parent[cur];
            }
 
            sb.append('#').append(tc).append(' ').append(lca).append(' ').append(getSize(lca)).append('\n');
        }
 
        System.out.println(sb);
    }
 
    private static int getSize(int start) {
        if (start == 0) {
            return 0;
        }
 
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
 
        int count = 0;
 
        while (!q.isEmpty()) {
            int cur = q.poll();
            count++;
 
            for (int ch : children[cur]) {
                q.add(ch);
            }
        }
 
        return count;
    }
 
}