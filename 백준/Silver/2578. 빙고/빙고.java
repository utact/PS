import java.io.*;
import java.util.*;

/*
 * 22:02 시작
 *
 * 빙고 체크 12개
 */

class Main {
    static Point[] pos = new Point[26];
    static int[] rows = new int[5];
    static int[] cols = new int[5];
    static int sp1 = 0;
    static int sp2 = 0;
    static int bg = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 5; j++) {
                int idx = Integer.parseInt(st.nextToken());
                pos[idx] = new Point(i, j);
            }
        }

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 5; j++) {
                int idx = Integer.parseInt(st.nextToken());
                Point cur = pos[idx];

                int tmpRow = cur.row;
                int tmpCol = cur.col;

                if (++rows[tmpRow] == 5) bg++;
                if (++cols[tmpCol] == 5) bg++;
                if (tmpRow == tmpCol) {
                    if (++sp1 == 5) bg++;
                }
                if (tmpRow + tmpCol == 4) {
                    if (++sp2 == 5) bg++;
                }

                if (bg >= 3) {
                    System.out.println(i * 5 + j + 1);
                    return;
                }
            }
        }
    }

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
