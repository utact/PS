import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        Arrays.sort(points, Comparator.comparingInt(p -> p.x));

        Comparator<Point> yComparator = (p1, p2) -> {
            if (p1.y == p2.y) {
                return p1.x - p2.x;
            }
            return p1.y - p2.y;
        };

        TreeSet<Point> candidates = new TreeSet<>(yComparator);

        int minDist = dist(points[0], points[1]);
        candidates.add(points[0]);
        candidates.add(points[1]);

        // 스윕
        int stt = 0;
        for (int i = 2; i < N; i++) {
            Point curPoint = points[i];

            while (stt < i) {
                Point sttPoint = points[stt];
                int dx = curPoint.x - sttPoint.x;

                if (dx * dx > minDist) {
                    candidates.remove(sttPoint);
                    stt++;
                } else {
                    break;
                }
            }

            int d = (int) Math.sqrt((double) minDist) + 1;

            Point lowerBound = new Point(-100001, curPoint.y - d);
            Point upperBound = new Point(100001, curPoint.y + d);

            for (Point candidatePoint : candidates.subSet(lowerBound, upperBound)) {
                minDist = Math.min(minDist, dist(curPoint, candidatePoint));
            }

            candidates.add(curPoint);
        }

        System.out.println(minDist);
    }
}