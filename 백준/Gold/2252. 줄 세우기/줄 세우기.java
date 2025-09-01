import java.io.*;
import java.util.*;

/*
 * 위상정렬
 * 
 * - 각자 앞에 서야 할 학생 수 기록
 * - 앞에 설 학생이 없는 학생부터 줄 세운 뒤, 대기 학생 감소 처리
 * 
 * -> 진입 차수가 0인 학생을 뽑아내기 위해 큐로 선입선출
 */

public class Main {
	static int[] indg;
	static ArrayList<Integer>[] gr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		indg = new int[N + 1];
		gr = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++) {
			gr[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			gr[a].add(b);
			indg[b]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (indg[i] == 0) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(' ');
			
			for (int i = 0; i < gr[cur].size(); i++) {
				indg[gr[cur].get(i)]--;
				
				if (indg[gr[cur].get(i)] == 0) {
					q.add(gr[cur].get(i));
				}
			}
		}
		
		System.out.print(sb);
	}
}
