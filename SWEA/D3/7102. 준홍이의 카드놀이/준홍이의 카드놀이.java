/*
 * 00:42
 *
 * 합 배열 선언하고 카운팅
 */

import java.io.*;
import java.util.*;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static int[] sum;
    static int max;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            sum = new int[N + M + 1];
            max = 0;
            ans.clear();

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    sum[i + j]++;
                }
            }

            for (int i = 0; i < sum.length; i++) {
                max = Math.max(max, sum[i]);
            }

            for (int i = 0; i < sum.length; i++) {
                if (sum[i] == max) {
                    ans.add(i);
                }
            }

            Collections.sort(ans);

            sb.append('#').append(tc).append(' ');
            for (int i = 0; i < ans.size(); i++) {
                sb.append(ans.get(i)).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}