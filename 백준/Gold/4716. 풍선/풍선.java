import java.io.*;
import java.util.*;

class Team implements Comparable<Team>{
    int amount;
    int aDist;
    int bDist;
    public Team(int amount, int aDist, int bDist){
        this.amount = amount;
        this.aDist = aDist;
        this.bDist = bDist;
    }

    public int compareTo(Team t){
        int home = Math.abs(aDist - bDist);
        int bottom = Math.abs(t.aDist - t.bDist);
        return bottom - home;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(n == 0 && a == 0 && b == 0) break;

            Team[] teams = new Team[n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                teams[i] = new Team(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(teams);
            int distance = 0;
            for(Team team : teams){
                int calcA = 0;
                int calcB = 0;
                if(team.aDist < team.bDist){
                    if(a < team.amount){
                        calcA = a;
                        calcB = team.amount - a;
                    }
                    else{
                        calcA = team.amount;
                    }
                }
                else{
                    if(b < team.amount){
                        calcB = b;
                        calcA = team.amount - b;
                    }
                    else{
                        calcB = team.amount;
                    }
                }

                a -= calcA;
                b -= calcB;
                distance += (team.aDist * calcA);
                distance += (team.bDist * calcB);
            }
            sb.append(distance).append("\n");
        }

        System.out.println(sb);
    }
}