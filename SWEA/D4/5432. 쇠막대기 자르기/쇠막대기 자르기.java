import java.io.*;
 
public class Solution {
 
    static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int i = 1; i <= T; i++) {
            String input = br.readLine();
            int ans = 0;
            int cnt = 0;
 
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == '(') {
                    cnt++;
                } else {
                    cnt--;
                    if (input.charAt(j - 1) == '(') {
                        ans += cnt;
                    } else {
                        ans++;
                    }
                }
            }
 
            sb.append('#').append(i).append(' ').append(ans).append('\n');
        }
         
        System.out.print(sb);
    }
 
}