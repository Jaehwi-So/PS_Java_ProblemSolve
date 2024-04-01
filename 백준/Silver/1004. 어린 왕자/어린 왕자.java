import java.util.*;

class Point{
    int x;
    int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Planet{
    int radios;
    Point p;
    public Planet(int radios, Point p){
        this.radios = radios;
        this.p = p;
    }

    public int calcInherit(Point a, Point b){
        //거리계산
        int x2 = (a.x - p.x) * (a.x - p.x);
        int y2 = (a.y - p.y) * (a.y - p.y);
        int r2 = radios * radios;

        int xb2 = (b.x - p.x) * (b.x - p.x);
        int yb2 = (b.y - p.y) * (b.y - p.y);


        // 두 점 모두 행성 안에 있는 경우
        if(x2 + y2 < r2 && xb2 + yb2 < r2){
            return 0;
        }
        else{
            // 한 점이 행성 안에 있는 경우
            if(x2 + y2 < r2 || xb2 + yb2 < r2){
                return 1;
            }
            else{
                return 0;
            }
        }

    }
}



public class Main {
    static int calc(Point start, Point end, List<Planet> planets){
        int result = 0;
        for(Planet p : planets){
            result += p.calcInherit(start, end);
        }
        return result;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int castNumber = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < castNumber; i++){
            String[] s = sc.nextLine().split(" ");
            int x1 = Integer.parseInt(s[0]);
            int y1 = Integer.parseInt(s[1]);
            int x2 = Integer.parseInt(s[2]);
            int y2 = Integer.parseInt(s[3]);
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            int planetNum = Integer.parseInt(sc.nextLine());
            List<Planet> planets = new ArrayList<>();
            for(int j = 0; j < planetNum; j++){
                String[] s2 = sc.nextLine().split(" ");
                int x = Integer.parseInt(s2[0]);
                int y = Integer.parseInt(s2[1]);
                int rad = Integer.parseInt(s2[2]);
                Point point = new Point(x, y);
                Planet planet = new Planet(rad, point);
                planets.add(planet);
            }
            int res = calc(start, end, planets);
            System.out.println(res);
        }
    }
}
