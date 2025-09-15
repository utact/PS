import java.io.*;
import java.util.*;

/*
 * N개의 좌표를 입력받고 다각형의 면적 구하기
 * -> 가상의 점 4개 찍어서 사각형 면적 구하고 깎기
 * -> (수정) 불가능한 케이스 있음 -> 신발끈 공식 활용하기
 */

public class Main {

    static class Pos {
        long x, y;

        public Pos(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Pos[] ps = new Pos[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            ps[i] = new Pos(x, y);
        }

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < N; i++) {
            int nxt = (i + 1) % N;
            sum1 += ps[i].x * ps[nxt].y;
            sum2 += ps[i].y * ps[nxt].x;
        }

        System.out.printf("%.1f", Math.abs(sum1 - sum2) / 2.0);
    }
}
