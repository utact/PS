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
        int[] cnt = new int[MAX + 1];

        Arrays.fill(time, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        time[N] = 0;
        cnt[N] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            int[] nextPos = {cur - 1, cur + 1, cur * 2};
            for (int next : nextPos) {
                if (next < 0 || next > MAX) continue;

                if (time[next] == -1) {
                    time[next] = time[cur] + 1;
                    cnt[next] = cnt[cur];
                    q.offer(next);
                }
                else if (time[next] == time[cur] + 1) {
                    cnt[next] += cnt[cur];
                }
            }
        }

        System.out.println(time[K]);
        System.out.println(cnt[K]);
    }
}