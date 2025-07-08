import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[2][7];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int gen = Integer.parseInt(st.nextToken());
            int gra = Integer.parseInt(st.nextToken());
            map[gen][gra] += 1;
        }

        int rms = 0;

        for (int i = 0; i < 7; i++) {
            if (map[0][i] % K == 0) {
                rms -= 1;
            }
            if (map[1][i] % K == 0) {
                rms -= 1;
            }
            rms += map[0][i] / K + 1;
            rms += map[1][i] / K + 1;
        }

        System.out.print(rms);
    }
}