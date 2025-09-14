import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr, rst;
    static boolean[] arrCheck;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        rst = new int[N];
        arrCheck = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        dfs(1, 0);
        System.out.print(sb);
    }

    static void dfs(int stt, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(rst[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 0; i < N; i++) {
            if (arrCheck[i]) {
                continue;
            }
            rst[depth] = arr[i];
            arrCheck[i] = true;
            dfs(i, depth + 1);
            arrCheck[i] = false;
        }
    }
}