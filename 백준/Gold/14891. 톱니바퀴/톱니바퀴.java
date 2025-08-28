import java.io.*;
import java.util.*;

/*
 * 12:59 시작
 */

public class Main {
    static int[][] gears = new int[4][8];
    static int[] index = new int[4];
    static int ans = 0;
    
    static int vst;
    static int[] rotateDir;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = line.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearIdx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            
            // 매 회전마다 변수 초기화
            vst = 0;
            rotateDir = new int[4];

            work(gearIdx, dir);
            
            for (int j = 0; j < 4; j++) {
                if (rotateDir[j] != 0) {
                    index[j] = (index[j] - rotateDir[j] + 8) % 8;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if (gears[i][index[i]] == 1) {
                ans += Math.pow(2, i);
            }
        }

        System.out.println(ans);
    }
    
    static void work(int gearIdx, int dir) {
        if ((vst & (1 << gearIdx)) > 0) return;
        vst |= (1 << gearIdx);
        rotateDir[gearIdx] = dir;

        // 왼쪽 톱니바퀴에 대한 회전 전파
        if (gearIdx > 0 && (vst & (1 << (gearIdx - 1))) == 0) {
            int currentLeft = (index[gearIdx] + 6) % 8;
            int leftRight = (index[gearIdx - 1] + 2) % 8;
            
            if (gears[gearIdx][currentLeft] != gears[gearIdx - 1][leftRight]) {
                work(gearIdx - 1, -dir);
            }
        }

        // 오른쪽 톱니바퀴에 대한 회전 전파
        if (gearIdx < 3 && (vst & (1 << (gearIdx + 1))) == 0) {
            int currentRight = (index[gearIdx] + 2) % 8;
            int rightLeft = (index[gearIdx + 1] + 6) % 8;

            if (gears[gearIdx][currentRight] != gears[gearIdx + 1][rightLeft]) {
                work(gearIdx + 1, -dir);
            }
        }
    }
}
