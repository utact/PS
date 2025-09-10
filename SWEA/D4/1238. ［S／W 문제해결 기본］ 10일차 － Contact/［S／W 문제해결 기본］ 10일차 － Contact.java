import java.io.*;
import java.util.*;

public class Solution {
	static int N, sttNode, ans;
	static int[] vst;
	static ArrayList<ArrayList<Integer>> gr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = 10;

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			sttNode = Integer.parseInt(st.nextToken());
			vst = new int[101];
			
			gr = new ArrayList<>();
			for (int i = 0; i < 101; i++) {
				gr.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			
			while (st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				gr.get(from).add(to);
			}
			
			getLastNode();

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}
	
	static void getLastNode() {
		ans = sttNode;
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(sttNode);
		vst[sttNode] = 1;
		
		while (!q.isEmpty()) {
			int rep = q.size();
			int tmpAns = -1;
			
			while (rep-- > 0) {
				int cur = q.poll();
				
				for (int i = 0; i < gr.get(cur).size(); i++) {
					int tmp = gr.get(cur).get(i);
					
					if (vst[tmp] == 1) {
						continue;
					}
					
					q.add(tmp);
					vst[tmp] = 1;
					
					tmpAns = Math.max(tmpAns, tmp);
				}
			}
			
			if (tmpAns != -1) {
				ans = tmpAns;
			}
		}
	}
}