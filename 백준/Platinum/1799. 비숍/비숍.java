import java.io.*;
import java.util.*;

/*
 * r + c = 짝수 or 홀수
 *
 * 분리시켜서 탐색하기
 * 2^K -> 2^(짝수 개수) + 2^(홀수 개수)
 */

public class Main {
    static int N, maxE, maxO, szE, szO;
    static boolean[] vst1, vst2;

    static ArrayList<int[]> evens = new ArrayList<>();
    static ArrayList<int[]> odds = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 대각선 방문 배열 공유 가능 (겹칠 일 없음)
        vst1 = new boolean[2 * N - 1];
        vst2 = new boolean[2 * N - 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());

                if (v != 1) continue;

                if ((i + j) % 2 == 0) {
                    evens.add(new int[] {i,j});
                } else {
                    odds.add(new int[] {i,j});
                }
            }
        }

        szE = evens.size();
        szO = odds.size();

        dfsEven(0, 0);
        dfsOdd(0, 0);

        System.out.println(maxE + maxO);
    }

    static void dfsEven(int idx, int cnt) {
        if (cnt + (szE - idx) <= maxE) {
            return;
        }

        if (idx == szE) {
            maxE = Math.max(maxE, cnt);
            return;
        }

        int[] cur = evens.get(idx);
        int r = cur[0];
        int c = cur[1];
        
        // 대각선 인덱스 구하기
        int d1 = r - c + (N - 1);// 대각선 (\) -> 음수 인덱스 보정
        int d2 = r + c; // 대각선(/)

        // 비숍 두기
        if (!vst1[d1] && !vst2[d2]) {
            vst1[d1] = true;
            vst2[d2] = true;
            dfsEven(idx + 1, cnt + 1);
            vst1[d1] = false;
            vst2[d2] = false;
        }

        // 비숍 두지 않기
        dfsEven(idx + 1, cnt);
    }

    static void dfsOdd(int idx, int cnt) {
        if (cnt + (szO - idx) <= maxO) {
            return;
        }

        if (idx == szO) {
            maxO = Math.max(maxO, cnt);
            return;
        }

        int[] cur = odds.get(idx);
        int r = cur[0];
        int c = cur[1];

        // 대각선 인덱스 구하기
        int d1 = r - c + (N - 1);// 대각선 (\) -> 음수 인덱스 보정
        int d2 = r + c; // 대각선(/)

        // 비숍 두기
        if (!vst1[d1] && !vst2[d2]) {
            vst1[d1] = true;
            vst2[d2] = true;
            dfsOdd(idx + 1, cnt + 1);
            vst1[d1] = false;
            vst2[d2] = false;
        }

        // 비숍 두지 않기
        dfsOdd(idx + 1, cnt);
    }
}