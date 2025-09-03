import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[] tree;
    static long ans;
    
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            tree = new int[N];
            ans = 0;

            int max = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                tree[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, tree[i]);
            }

            long one = 0, two = 0; 
            for (int i = 0; i < N; i++) {
                int diff = max - tree[i];
                if (diff > 0) {
                    two += diff / 2;
                    one += diff % 2;
                }
            }

            long minVal = Math.min(one, two);
            ans = minVal * 2;
            one -= minVal;
            two -= minVal;

            if (one > 0) {
                ans += one * 2 - 1;
            } else if (two > 0) {
                ans += (two / 3) * 4;
                if (two % 3 == 1) ans += 2;
                else if (two % 3 == 2) ans += 3;
            }
            
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }

        System.out.print(sb);
    }
}