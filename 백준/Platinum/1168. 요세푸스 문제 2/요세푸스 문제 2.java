import java.io.*;
import java.util.*;

/*
 * 남아있는 사람들 중 k번째 사람을 빠르게 찾고 제거하기 O(logN)
 * -> ArrayList 구현 시 O(n^2)
 * 
 * 세그 트리의 각 리프 노드는 한 사람을 의미
 * 사람이 존재하면 1, 제거되면 0
 */

public class Main {
	static int[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		tree = new int[N * 4];
		init(1, 1, N);
		
		StringBuilder sb = new StringBuilder("<");
	    int idx = 1;
	    
	    for (int i = 0; i < N; i++) {
	        int remainCnt = N - i;
	        
	        // 다음 제거 대상 계산
	        idx = (idx + K - 1 - 1) % remainCnt + 1;

	        int rmvNo = query(1, 1, N, idx);
	        update(1, 1, N, rmvNo);
	        
	        sb.append(rmvNo);
	        if (i < N - 1) {
	            sb.append(", ");
	        }
	    }
	    
	    sb.append(">");
	    System.out.println(sb);
	}
	
	// 1. 트리 초기화
	static void init(int node, int stt, int end) {
		if (stt == end) {
			tree[node] = 1;
			return;
		}
		
		int mid = (stt + end) / 2;
		
		init(node * 2, stt, mid);
		init(node * 2 + 1, mid + 1, end);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	// 2. 트리 업데이트 (사람 제거)
	static void update(int node, int stt, int end, int idx) {
	    if (idx < stt || idx > end) {
	        return;
	    }
	    
	    // 현재 구간 생존자 제거
	    tree[node]--;
	    
	    if (stt == end) {
	        return;
	    }
	    
	    int mid = (stt + end) / 2;
	    
	    update(node * 2, stt, mid, idx);
	    update(node * 2 + 1, mid + 1, end, idx);
	}
	
	// k번째 사람 찾기
	static int query(int node, int stt, int end, int k) {
		// 리프 노드 도달 시 리턴
	    if (stt == end) {
	        return stt;
	    }
	    
	    int mid = (stt + end) / 2;
	    int leftCnt = tree[node * 2];
	    
	    if (k <= leftCnt) {
	        // k가 좌측 구간합 이하 시, k번째는 왼쪽에 위치
	        return query(node * 2, stt, mid, k);
	    } else {
	        // k가 좌측 구간합 초과 시, k번째는 오른쪽에 위치
	        return query(node * 2 + 1, mid + 1, end, k - leftCnt);
	    }
	}
}
