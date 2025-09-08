import java.io.*;
import java.util.*;

/*
 * https://www.acmicpc.net/problem/13911
 * 멀티 소스 다익스트라 연습
 * 
 * >> 모든 집 노드(최대 V개)에서 각각 다익스트라를 실행하면 -> O(V * E log V)로 시간 초과
 *
 * >> 다익스트라의 실행 횟수를 V번(집 개수) -> K번(카테고리 개수, 2번)으로 줄이기
 * >> "모든 노드 i"에 대해 필요한 2가지 정보(최근접 맥도날드 거리, 최근접 스타벅스 거리)를 미리 계산하기
 *
 * 1. [다익 1/2] 모든 "맥도날드" 노드를 출발점으로 -> long[] dist_m
 * 2. [다익 2/2] 모든 "스타벅스" 노드를 출발점으로 -> long[] dist_s
 * 3. [최종 결과 취합] 1번 ~ V번 노드까지 단일 반복문 순회 -> minSum = Math.min(minSum, dist_mc[i] + dist_sb[i])
 * 4. minSum이 초기값(INF) 그대로이면 -1, 아니면 minSum 출력
 */

public class Main {
	static long INF = Long.MAX_VALUE;

	static int V, E;
    static ArrayList<ArrayList<Node>> graph;
    
    static class Node implements Comparable<Node> {
        int idx;
        long cost;

        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // ㅊㄱㅎ
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        // ㅇㅈㄹㅅㅌ
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        // ㅁㄷㄴㄷ (tg1/2)
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        long lmt1 = Long.parseLong(st.nextToken());

        ArrayList<Integer> tg1 = new ArrayList<>();
        boolean[] isTg = new boolean[V + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int node = Integer.parseInt(st.nextToken());
            tg1.add(node);
            isTg[node] = true;
        }

        // ㅅㅌㅂㅅ (tg2/2)
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        long lmt2 = Long.parseLong(st.nextToken());

        ArrayList<Integer> tg2 = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int node = Integer.parseInt(st.nextToken());
            tg2.add(node);
            isTg[node] = true;
        }

        // ㅁㅌ-ㅅㅅ ㄷㅇㅅㅌㄹ
        long[] dist_m = msd(tg1);
        long[] dist_s = msd(tg2);

        // ㅈㄷ ㄱㅅ
        long minSum = INF;

        for (int i = 1; i <= V; i++) {
            if (isTg[i]) {
                continue;
            }

            long mcCost = dist_m[i];
            long sbCost = dist_s[i];
            
            if (mcCost != INF && sbCost != INF && mcCost <= lmt1 && sbCost <= lmt2) {
                minSum = Math.min(minSum, mcCost + sbCost);
            }
        }

        // ㅊㄹ
        if (minSum == INF) {
            System.out.println(-1);
        } else {
            System.out.println(minSum);
        }
    }

    static long[] msd(ArrayList<Integer> sttNodes) {
        long[] dist = new long[V + 1];
        Arrays.fill(dist, INF);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int node : sttNodes) {
            dist[node] = 0;
            pq.add(new Node(node, 0));
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int tmpNode = current.idx;
            long tmpCost = current.cost;

            if (tmpCost > dist[tmpNode]) {
                continue;
            }

            for (Node nb : graph.get(tmpNode)) {
                long nc = tmpCost + nb.cost;

                if (nc < dist[nb.idx]) {
                    dist[nb.idx] = nc;
                    pq.add(new Node(nb.idx, nc));
                }
            }
        }

        return dist;
    }
}