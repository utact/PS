import java.io.*;
import java.util.*;

/*
 * MST -> 크루스칼 구현해서 풀기
 * 
 * 출력 값: E * L^2
 * >> 소수 첫째 자리에서 반올림한 정수 형태일 것
 */

public class Solution {
	static double ans;
	static int N, edgeCnt;
	static double E;
	static int[] xs, ys, arr;
	static ArrayList<Edge> edges;
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		long cost;

		public Edge(int from, int to, long cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			edgeCnt = 0;
			N = Integer.parseInt(br.readLine());
			
			// 초기화
			xs = new int[N];
			ys = new int[N];
			arr = new int[N + 1];
			for (int i = 0; i < N; i++) {
				arr[i] = i;
			}

			// x 좌표 쭉 입력받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				xs[i] = Integer.parseInt(st.nextToken());
			}
			
			// y 좌표 쭉 입력받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				ys[i] = Integer.parseInt(st.nextToken());
			}
			
			// 엣지 배열 만들어서 정렬하고 최적 간선 준비하기
			edges = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					long dist = (long) (Math.pow((xs[i] - xs[j]), 2)) + (long) (Math.pow((ys[i] - ys[j]), 2));
					edges.add(new Edge(i, j, dist));
				}
			}			
			Collections.sort(edges);
			
			// 사이클 체크하며 최적 간선 확정하기
			for (int i = 0; i < edges.size(); i++) {
				union(edges.get(i).from, edges.get(i).to, edges.get(i).cost);
				
				if (edgeCnt == N -1) {
					break;
				}
			}
			
			E = Double.parseDouble(br.readLine());
			ans *= E;
			
			sb.append('#').append(tc).append(' ').append(Math.round(ans)).append('\n');
		}

		System.out.print(sb);
	}
	
	static int find(int a) {
		if (arr[a] == a) {
			return a;
		}
		
		return arr[a] = find(arr[a]);
	}

	static void union(int a, int b, double w) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA != rootB) {
			arr[rootB] = rootA;
			ans += w;
			edgeCnt++;
		}
	}
}