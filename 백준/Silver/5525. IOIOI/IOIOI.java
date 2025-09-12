import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int len = Integer.parseInt(br.readLine());
        char[] cs = br.readLine().toCharArray();

        int ans = 0;
        int cnt = 0;

        for (int i = 0; i < len - 2; i++) {
        	if (cs[i] == 'I' && cs[i + 1] == 'O' && cs[i + 2] == 'I') {
        		cnt++;

                if (cnt == N) {
                	// 정답 증가 후, 패턴 카운트 감소
                    ans++;
                    cnt--;
                }
                
                i++;
            }
            else {
                cnt = 0;
            }
        }

        System.out.println(ans);
    }
}