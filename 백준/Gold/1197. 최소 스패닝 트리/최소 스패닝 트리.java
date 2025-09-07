import java.io.*;
import java.util.*;

/*
 * 유니온 파인드를 통한 MST 구현 (크루스칼)
 */

public class Main {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        // 부모 테이블 초기화
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // 모든 간선 정보를 리스트에 저장
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }

        Collections.sort(edges);

        long tw = 0;
        int eCnt = 0;

        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                // 간선을 포함시키고 가중치 반영
                tw += e.cost;
                eCnt++;
            }

            // 간선의 수가 목표치에 도달한 경우 종료
            if (eCnt == V - 1) {
                break;
            }
        }

        System.out.println(tw);
    }

    // 간선 클래스
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int find(int a) {
        // 루트 처리
        if (parent[a] == a) {
            return a;
        }
        
        // 재귀를 통한 경로 압축
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        // 사이클 발생 시 빠져나옴
        if (ra == rb) {
            return false;
        }

        // 더 작은 부모로 합침
        if (ra < rb) {
            parent[rb] = ra;
        } else {
            parent[ra] = rb;
        }

        return true;
    }
}