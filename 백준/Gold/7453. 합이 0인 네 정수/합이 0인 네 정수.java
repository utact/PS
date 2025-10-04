import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        // A + B = -(C + D)
        int[] sumAB = new int[n * n];
        int[] sumCD = new int[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumAB[idx] = A[i] + B[j];
                sumCD[idx] = C[i] + D[j];
                idx++;
            }
        }

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        printCnt(n, sumAB, sumCD);
    }

    private static void printCnt(int n, int[] sumAB, int[] sumCD) {
        long cnt = 0;
        int lt = 0; // sumAB 포인터
        int rt = n * n - 1; // sumCD 포인터

        // 합이 0인 쌍 찾기 -> 투포인터
        while (lt < n * n && rt >= 0) {
            int curSum = sumAB[lt] + sumCD[rt];

            if (curSum == 0) {
                long ltCnt = 1;
                long rtCnt = 1;

                // sumAB 중복된 값 개수 세기
                while (lt + 1 < n * n && sumAB[lt] == sumAB[lt + 1]) {
                    ltCnt++;
                    lt++;
                }
                // sumCD 중복된 값 개수 세기
                while (rt - 1 >= 0 && sumCD[rt] == sumCD[rt - 1]) {
                    rtCnt++;
                    rt--;
                }

                cnt += ltCnt * rtCnt;
                lt++;
                rt--;

            } else if (curSum > 0) {
                rt--;
            } else {
                lt++;
            }
        }

        System.out.println(cnt);
    }
}