import java.io.*;

public class Main {
    static long[][] adj = {
            {0, 1, 1, 0, 0, 0, 0, 0}, // 정보과학관
            {1, 0, 1, 1, 0, 0, 0, 0}, // 전산관
            {1, 1, 0, 1, 1, 0, 0, 0}, // 미래관
            {0, 1, 1, 0, 1, 1, 0, 0}, // 신양관
            {0, 0, 1, 1, 0, 1, 0, 1}, // 한경직기념관
            {0, 0, 0, 1, 1, 0, 1, 0}, // 진리관
            {0, 0, 0, 0, 0, 1, 0, 1}, // 형남공학관
            {0, 0, 0, 0, 1, 0, 1, 0}, // 학생회관
    };
    static final int SIZE = 8;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int D = Integer.parseInt(br.readLine());

        long[][] rst = pwr(adj, D);

        System.out.println(rst[0][0]);
    }

    // 행렬 D 제곱
    public static long[][] pwr(long[][] base, int exp) {
        long[][] rst = new long[SIZE][SIZE];
        // 결과 행렬 -> 단위 행렬 초기화
        for (int i = 0; i < SIZE; i++) {
            rst[i][i] = 1;
        }

        long[][] a = base;

        while (exp > 0) {
            // 지수 홀수 -> 결과 행렬에 * base
            if (exp % 2 == 1) {
                rst = mtp(rst, a);
            }
            // base 제곱
            a = mtp(a, a);
            // 지수 나누기
            exp /= 2;
        }

        return rst;
    }

    // 행렬 간의 곱
    public static long[][] mtp(long[][] m1, long[][] m2) {
        long[][] rst = new long[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    rst[i][j] += m1[i][k] * m2[k][j];
                    rst[i][j] %= MOD;
                }
            }
        }

        return rst;
    }
}