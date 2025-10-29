import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int width = 2 * N - 1;
        map = new char[N][width];

        // 배열 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], ' ');
        }

        draw(0, N - 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map[i]).append('\n');
        }
        
        System.out.print(sb);
    }

    static void draw(int r, int c, int n) {
        if (n == 3) {
            map[r][c] = '*';

            map[r + 1][c - 1] = '*';
            map[r + 1][c + 1] = '*';

            map[r + 2][c - 2] = '*';
            map[r + 2][c - 1] = '*';
            map[r + 2][c] = '*';
            map[r + 2][c + 1] = '*';
            map[r + 2][c + 2] = '*';
            return;
        }

        int halfN = n / 2;

        // 위쪽 삼각형
        draw(r, c, halfN);
        // 왼쪽 아래 삼각형
        draw(r + halfN, c - halfN, halfN);
        // 오른쪽 아래 삼각형
        draw(r + halfN, c + halfN, halfN);
    }
}