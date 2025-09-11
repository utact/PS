import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] connected;
    static int[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        connected = new int[n + 1][n + 1];
        visited = new int[n + 1];

        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connected[a][b] = 1;
            connected[b][a] = 1;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (visited[cur] == 0) {
                count++;
            }

            for (int i = 0; i <= n; i++) {
                if (connected[cur][i] == 1 && visited[i] == 0) {
                    q.offer(i);
                }
            }
            visited[cur] = 1;
        }

        System.out.print(count - 1);
    }
}