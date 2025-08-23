import java.io.*;
import java.util.*;

/*
18:18 시작

- 매 초마다 이동
- 이동하는 칸으로 일단 길이를 늘림
- 이동한 칸에 사과가 있다면 사과 제거 (길이 증가)
- 이동한 칸에 사과가 없다면 꼬리 칸 제거 (길이 유지)
- 회전 명령: L은 왼쪽(1), D는 오른쪽(2)로 표시
- 명령 타이밍은 최대 10,000 이하 양의 정수

사과의 위치와 뱀의 이동경로 주어질 경우 게임 종료시간 구하기
 */

public class Main {
    static int[] dr = {0, 1, 0, -1}; // 우하좌상
    static int[] dc = {1, 0, -1, 0}; // 우하좌상
    static int N;
    static int[][] map; // 사과 표시 맵
    static int[][] vst; // 현재 뱀 방문 맵
    static int[] cmdTime = new int[10001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        vst = new int[N][N];

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            String cmd = st.nextToken();
            int dir = (cmd.equals("L")) ? -1 : 1;
            cmdTime[sec] = dir;
        }

        baaam(0, 0, 0);
    }

    static void baaam(int r, int c, int dir) {
        // 시간 및 꼬리칸 체크 목적 큐
        int time = 0;
        Queue<int[]> q = new ArrayDeque<>();

        vst[r][c] = 1;
        q.offer(new int[]{r, c});

        while (true) {
            if (time <= 10000 && cmdTime[time] != 0) {
                // 나머지 보정
                dir = (dir + cmdTime[time]) % 4;
                // 음수일 경우 추가 보정
                if (dir < 0) {
                    dir += 4;
                }
            }

            int nr = r + dr[dir];
            int nc = c + dc[dir];

            // 게임 종료 조건
            if (nr < 0 || nc < 0 || nr >= N || nc >= N || vst[nr][nc] == 1) {
                System.out.println(time + 1);
                return;
            }

            // 대가리 위치 갱신
            r = nr;
            c = nc;

            // 뱀 이동
            vst[r][c] = 1;
            q.offer(new int[]{r, c});

            if (map[r][c] == 1) {
                map[r][c] = 0;
            } else {
                if (!q.isEmpty()) {
                    int[] tail = q.poll();
                    vst[tail[0]][tail[1]] = 0;
                }
            }

            time++;
        }
    }
}
