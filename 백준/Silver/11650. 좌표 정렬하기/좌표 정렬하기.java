import java.util.*;

class Point implements Comparable<Point> {
    int x;
    int y;

    @Override
    public int compareTo(Point o) {
        if(this.x - o.x == 0){
            return (this.y - o.y);
        }
        return (this.x - o.x);
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Point[] points = new Point[n];
        for(int i = 0; i < n; i++){
            points[i] = new Point();
            points[i].x = sc.nextInt();
            points[i].y = sc.nextInt();
        }
        Arrays.sort(points);
        for(Point p : points){
            System.out.println(p);
        }
    }
}
