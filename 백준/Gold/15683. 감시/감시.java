import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static List<int[]> cctvPoint = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // 방향
    static int[][] cam1 = {{0}, {1}, {2}, {3}};
    static int[][] cam2 = {{0, 2}, {1, 3}};
    static int[][] cam3 = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
    static int[][] cam4 = {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}};
    static int[][] cam5 = {{0, 1, 2, 3}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvPoint.add(new int[] {i, j, map[i][j]});
                }
            }
        }

        int result = minBlindSpots(0);
        System.out.print(result);
    }

    static int minBlindSpots(int index) {
        if (index == cctvPoint.size()) {
            return countBlindSpots();
        }

        int[] cctv = cctvPoint.get(index);
        int x = cctv[0];
        int y = cctv[1];
        int type = cctv[2];
        int[][] directions = getDirections(type);

        int minSpots = Integer.MAX_VALUE;
        for (int[] dir : directions) {
            int[][] backupMap = backupMapState();
            for (int d : dir) {
                see(x, y, d);
            }
            minSpots = Math.min(minSpots, minBlindSpots(index + 1));
            restoreMapState(backupMap);
        }
        return minSpots;
    }

    static int[][] getDirections(int type) {
        switch (type) {
            case 1: return cam1;
            case 2: return cam2;
            case 3: return cam3;
            case 4: return cam4;
            case 5: return cam5;
            default: return new int[0][0];
        }
    }

    static void see(int x, int y, int dir) {
        int nx = x, ny = y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 6) {
                break;
            }
            if (map[nx][ny] == 0) {
                map[nx][ny] = -1;
            }
        }
    }

    static int[][] backupMapState() {
        int[][] backup = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, backup[i], 0, M);
        }
        return backup;
    }

    static void restoreMapState(int[][] backup) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(backup[i], 0, map[i], 0, M);
        }
    }

    static int countBlindSpots() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}