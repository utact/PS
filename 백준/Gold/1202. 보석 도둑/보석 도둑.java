import java.io.*;
import java.util.*;

/*
 * 냅색 문제처럼 보이지만 제약 조건의 크기가 너무 큼
 * 무게로 인한 메모리 초과 + 아이템 개수로 인한 시간 초과 위험 존재
 * 
 * >> 그리디한 접근 필요
 * >> "가벼운 가방부터 가방에 넣을 수 있는 보석 중 가장 비싼 것을 넣는다!"
 * 
 * 1. 보석 클래스 만들어서 '무게', '가치' 속성 주고 배열 만들고 무게 기준으로 Comparable
 * 2. 매 가방에서 가능한 보석을 하나씩 PQ(가치 기준 최대 힙)에 넣어준 뒤 최적 보석 선택
 * -. PQ를 초기화하지 않고 연이어 쓰기 위해 가방은 가장 작은 것부터 시작
 */

public class Main {
	static class Jew implements Comparable<Jew> {
		int w, v;

		public Jew(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}

		@Override
		public int compareTo(Jew o) {
			return this.w - o.w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 보석 (무게, 가격)
		Jew[] jws = new Jew[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jws[i] = new Jew(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(jws);

		// 가방 (최대 무게)
		int[] bgs = new int[K];
		for (int i = 0; i < K; i++) {
			bgs[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bgs);

		// 가치 기준 우선순위 큐
		PriorityQueue<Jew> pq = new PriorityQueue<>((o1, o2) -> o2.v - o1.v);
		int jwIdx = 0;
		long tv = 0;
		for (int bgIdx = 0; bgIdx < K; bgIdx++) {
			while (jwIdx < N && jws[jwIdx].w <= bgs[bgIdx]) {
				pq.add(jws[jwIdx++]);
			}
			
			if (!pq.isEmpty()) {
				tv += pq.poll().v;
			}
		}
		
		// 정답 출력
		System.out.println(tv);
	}
}
