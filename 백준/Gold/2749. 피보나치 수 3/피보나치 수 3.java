import java.io.*;

/*
20:19 시작

- 피보나치 수 n를 어떤 수 m으로 나눈 나머지는 항상 주기적으로 반복됨 (피사노 주기)
- 문제 속 m은 1,000,000이므로 피사노 주기는 1,500,000임
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        int m = 1_000_000;
        int pp = 1_500_000;

        long newN = n % pp;

        int[] fib = new int[pp + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= newN ; i++) {
            fib[i] = (fib[i - 1] + fib[i - 2]) % m;
        }

        System.out.println(fib[(int) newN]);
    }
}
