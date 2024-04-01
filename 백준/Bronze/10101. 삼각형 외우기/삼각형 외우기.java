import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        short[] angles = new short[3];

        for(int i = 0; i < 3; i++){
            angles[i] = sc.nextShort();
        }

        if(angles[0] + angles[1] + angles[2] == 180){
            if(angles[0] == 60 && angles[1] == 60){
                System.out.println("Equilateral");
            }
            else if(angles[0] == angles[1] || angles[1] == angles[2] || angles[0] == angles[2]){
                System.out.println("Isosceles");
            }
            else{
                System.out.println("Scalene");
            }
        }
        else{
            System.out.println("Error");
        }


    }
}
