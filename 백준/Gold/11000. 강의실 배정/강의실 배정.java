import java.io.*;
import java.util.*;

public class Main {
    static class Lec implements Comparable<Lec> {
        int stt, edt;

        public Lec(int stt, int edt) {
            this.stt = stt;
            this.edt = edt;
        }

        @Override
        public int compareTo(Lec o) {
            if (this.stt == o.stt) {
                return Integer.compare(this.edt, o.edt);
            }
            return Integer.compare(this.stt, o.stt);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Lec[] lecs = new Lec[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int stt = Integer.parseInt(st.nextToken());
            int edt = Integer.parseInt(st.nextToken());
            lecs[i] = new Lec(stt, edt);
        }

        // 시작 시간 기준 정렬
        Arrays.sort(lecs);

        // 우선순위 큐는 종료 시간만 기록
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(lecs[0].edt);

        for (int i = 1; i < N; i++) {
            if (pq.peek() <= lecs[i].stt) {
                pq.poll();
            }
            pq.offer(lecs[i].edt);
        }

        System.out.println(pq.size());
    }
}