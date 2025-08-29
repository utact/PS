import java.io.*;
import java.util.*;
 
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static Map<Character, Character> map = new HashMap<>();
 
    public static void main(String[] args) throws Exception {
        int T = 10;
 
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        map.put('>', '<');
 
        for (int i = 1; i <= T; i++) {
            sb.append('#').append(i).append(' ');
            isPair();
        }
 
        System.out.print(sb);
    }
     
    static void isPair() throws Exception {
        Stack<Character> stk = new Stack<>();
        int N = Integer.parseInt(br.readLine());
        String line = br.readLine();
 
        for (int j = 0; j < N; j++) {
            if (line.charAt(j) == '(' || line.charAt(j) == '[' || line.charAt(j) == '{' || line.charAt(j) == '<') {
                stk.add(line.charAt(j));
            } else {
                if (!(stk.pop() == map.get(line.charAt(j)))) {
                    sb.append(0).append('\n');
                    return;
                }
            }
        }
         
        if (stk.isEmpty()) {
            sb.append(1).append('\n');
        } else {
            sb.append(0).append('\n');
        }
    }
 
}