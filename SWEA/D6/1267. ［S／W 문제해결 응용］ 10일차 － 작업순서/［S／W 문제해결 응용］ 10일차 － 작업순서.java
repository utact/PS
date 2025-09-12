import java.io.*;
import java.util.*;

public class Solution {
	static int V, E;
	static ArrayList<Integer>[] adj;
	static int[] indg;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;		
		
		int T = 10;
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc);
			
			// 초기화
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adj = new ArrayList[V + 1];
			for (int i = 0; i < V + 1; i++) {
				adj[i] = new ArrayList<>();
			}
			
			indg = new int[V + 1];
			
			// 간선 입력
			st = new StringTokenizer(br.readLine());
			
			while (st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adj[a].add(b);
				indg[b]++;
			}
			
			// 위상정렬
			Queue<Integer> q = new ArrayDeque<>();
			
			for (int i = 1; i < V + 1; i++) {
				if (indg[i] == 0) {
					q.add(i);
				}
			}
			
			while (!q.isEmpty()) {
				int cur = q.poll();
				sb.append(' ').append(cur);
				
				for (int i = 0; i < adj[cur].size(); i++) {
					int tmp = adj[cur].get(i);
					indg[tmp]--;
					
					if (indg[tmp] == 0) {
						q.add(tmp);
					}
				}
			}
			
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}