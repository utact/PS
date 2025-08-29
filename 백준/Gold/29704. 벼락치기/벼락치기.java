import java.io.*;
import java.util.*;

/*
 * 21:13 시작
 */

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] day = new int[N + 1];
        int[] cost = new int[N + 1];

        int[] dp = new int[T + 1];
        int max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            max += c;

            day[i] = d;
            cost[i] = c;
        }

        for (int i = 0; i < N; i++) {
            int d = day[i];
            int c = cost[i];

            for (int j = T; j >= d ; j--) {
                dp[j] = Math.max(dp[j], dp[j-d] + c);
            }
        }

        System.out.println(max - dp[T]);
    }

}
