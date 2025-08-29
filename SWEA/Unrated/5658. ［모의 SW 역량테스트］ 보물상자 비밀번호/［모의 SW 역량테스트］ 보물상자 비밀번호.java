import java.io.*;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
         
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int len = N / 4;
             
            String code = br.readLine();
            String newCode = code + code.substring(0, len - 1);
                     
            HashSet<String> set = new HashSet<>();
             
            for (int i = len - 1; i < newCode.length(); i++) {
                set.add(newCode.substring(i - (len - 1), i + 1));
            }
             
            ArrayList<Integer> arr = new ArrayList<>();
             
            for (String v : set) {
                arr.add(Integer.parseInt(v, 16));
            }
             
            Collections.sort(arr);
             
            sb.append('#').append(tc).append(' ').append(arr.get(arr.size() - K)).append('\n');
        }
         
        System.out.print(sb);
    }
 
}