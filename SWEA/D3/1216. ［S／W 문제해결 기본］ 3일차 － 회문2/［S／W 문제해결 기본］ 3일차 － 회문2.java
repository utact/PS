/*
 * 22:55
 *
 * 중심점 확장
 */

import java.io.*;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        while (T-- > 0) {
            int tc = Integer.parseInt(br.readLine());
            map = new int[100][100];
            ans = 0;

            for (int i = 0; i < 100; i++) {
                String line = br.readLine();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    getPal(i, j);
                }
            }

            sb.append('#').append(tc).append(' ').append(ans).append("\n");
        }

        System.out.print(sb);
    }

    static void getPal(int r, int c) {
        // 행홀
        int len1 = 1;
        int v1 = 1;
        while (r - v1 >= 0 && r + v1 < 100 && map[r - v1][c] == map[r + v1][c]) {
            len1 += 2;
            v1++;
        }
        ans = Math.max(ans, len1);

        // 행짝
        int len2 = 0;
        int v2 = 0;
        while (r - v2 >= 0 && r + 1 + v2 < 100 && map[r - v2][c] == map[r + 1 + v2][c]) {
            len2 += 2;
            v2++;
        }
        ans = Math.max(ans, len2);

        // 열홀
        int len3 = 1;
        int v3 = 1;
        while (c - v3 >= 0 && c + v3 < 100 && map[r][c - v3] == map[r][c + v3]) {
            len3 += 2;
            v3++;
        }
        ans = Math.max(ans, len3);

        // 열짝
        int len4 = 0;
        int v4 = 0;
        while (c - v4 >= 0 && c + 1 + v4 < 100 && map[r][c - v4] == map[r][c + 1 + v4]) {
            len4 += 2;
            v4++;
        }
        ans = Math.max(ans, len4);
    }
}