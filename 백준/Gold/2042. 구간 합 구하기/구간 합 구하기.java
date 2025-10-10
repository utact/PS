import java.io.*;
import java.util.*;

public class Main {
	static long[] raw, tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); // update count
		int K = Integer.parseInt(st.nextToken()); // query count
		
		raw = new long[N + 1];
		for (int i = 1; i < N + 1; i++) {
			raw[i] = Long.parseLong(br.readLine());
		}
		
		tree = new long[N * 4];
		init(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // command
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken()); // 인덱스 또는 입력값
			
			if (a == 1) { // update
				long dif = c - raw[b];
				raw[b] = c;
				update(1, 1, N, b, dif);
			}
			else if (a == 2) { // query
				sb.append(query(1, 1, N, b, (int) c)).append('\n');
			}
		}
		
		System.out.print(sb);
	}
	
	// 초기 배열의 값으로 세그먼트 트리 구축
	static long init(int node, int st, int ed) {
		if (st == ed) {
			return tree[node] = raw[st];
		}
		int mid = (st + ed) / 2;
		return tree[node] = init(node * 2, st, mid) + init(node * 2 + 1, mid + 1, ed);
	}
	
	// 특정 구간의 합
	static long query(int node, int st, int ed, int lt, int rt) {
		// 범위 밖일 경우
        if (lt > ed || rt < st) {
            return 0;
        }
        // 범위 안에 완전히 포함될 경우
        if (lt <= st && ed <= rt) {
            return tree[node];
        }
        // 걸쳐있는 경우
        int mid = (st + ed) / 2;
        return query(node * 2, st, mid, lt, rt) + query(node * 2 + 1, mid + 1, ed, lt, rt);
    }
	
	// 특정 인덱스의 값 변경 후 트리 노드 갱신
	static void update(int node, int st, int ed, int idx, long dif) {
		// 범위 밖일 경우
        if (idx < st || idx > ed) {
            return;
        }
        // 범위 안일 경우
        tree[node] += dif;
        if (st != ed) {
            int mid = (st + ed) / 2;
            update(node * 2, st, mid, idx, dif);
            update(node * 2 + 1, mid + 1, ed, idx, dif);
        }
	}
}
