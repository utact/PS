import java.io.*;
import java.util.*;

/*
 * 종료시간 기준으로 오름차순
 * -> 종료시간 같은 경우, 시작시간 기준 오름차순
 * -> 가장 앞 회의를 선택하며 종료시간 갱신 후 다음 회의의 시작시간과 비교 (반복)
 */

public class Main {
	static class Mtg implements Comparable<Mtg> {
		int stt, edt;

		public Mtg(int stt, int edt) {
			super();
			this.stt = stt;
			this.edt = edt;
		}

		@Override
		public int compareTo(Mtg o) {
			if (this.edt != o.edt) {
				return this.edt - o.edt;				
			}
			
			return this.stt - o.stt;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        PriorityQueue<Mtg> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
			pq.add(new Mtg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
        
        int ans = 0;
        int limit = 0;
        
        while (!pq.isEmpty()) {
        	Mtg cur = pq.poll();
        	
        	if (cur.stt >= limit) {
        		ans++;
        		limit = cur.edt;
        	}
        }
        
        System.out.println(ans);
    }
}