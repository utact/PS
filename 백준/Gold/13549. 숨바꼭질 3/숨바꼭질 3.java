import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] time = new int[MAX + 1];

        Arrays.fill(time, -1);

        // 0-1 BFS
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(N);
        time[N] = 0;

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            int tp = cur * 2;
            if (tp <= MAX && time[tp] == -1) {
                time[tp] = time[cur];
                dq.addFirst(tp);
            }

            int[] wks = {cur - 1, cur + 1};
            for (int wk : wks) {
                if (wk >= 0 && wk <= MAX && time[wk] == -1) {
                    time[wk] = time[cur] + 1;
                    dq.addLast(wk);
                }
            }
        }

        System.out.println(time[K]);
    }
}