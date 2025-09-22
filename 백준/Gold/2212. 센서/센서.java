import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] ss;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        if (K >= N) {
            System.out.println(0);
            return;
        }

        ss = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ss[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(ss);

        int[] gp = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            gp[i] = ss[i + 1] - ss[i];
        }

        Arrays.sort(gp);

        int mtl = 0;
        for (int i = 0; i < N - K; i++) {
            mtl += gp[i];
        }

        System.out.println(mtl);
    }
}