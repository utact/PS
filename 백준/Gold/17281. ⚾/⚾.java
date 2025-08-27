import java.io.*;
import java.util.*;

/*
 * 20:33 시작
 * 큐 -> 배열로 변경
 */

class Main {
    static int[][] inning;
    static int[] order;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        inning = new int[N][9];
        order = new int[9];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                inning[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(max);
    }

    static void dfs(int depth, int vst) {
        if (depth == 9) {
            play(0, 0, 0);
            return;
        }

        if (depth == 3) {
            order[depth] = 0;
            dfs(depth + 1, vst | (1));
            order[depth] = 0;
            return;
        }

        for (int i = 1; i < 9; i++) {
            if ((vst & (1 << i)) == 0) {
                order[depth] = i;
                dfs(depth + 1, vst | (1 << i));
                order[depth] = 0;
            }
        }
    }

    static void play(int start, int inningNo, int score) {
        if (inningNo > inning.length - 1) {
            max = Math.max(max, score);
            return;
        }

        int[] curIn = inning[inningNo];
        int[] roo = new int[3];
        int out = 0;

        for (int i = start; ; i++) {
            if (out > 2) {
                play(i % 9, inningNo + 1, score);
                return;
            }

            i = i % 9;

            if (curIn[order[i]] == 0) {
                out++;
            } else {
                // 루 털기
                int mvAmt = curIn[order[i]];

                for (int j = 2; j >= 0; j--) {
                    if (j + mvAmt > 2) {
                        if (roo[j] != 0) {
                            roo[j] = 0;
                            score++;
                        }
                    } else {
                        if (roo[j] != 0) {
                            roo[j + mvAmt] = 1;
                            roo[j] = 0;
                        }
                    }
                }

                // 타자 털기
                if (mvAmt == 4) {
                    score++;
                } else {
                    roo[mvAmt - 1] = 1;
                }
            }
        }
    }
}
