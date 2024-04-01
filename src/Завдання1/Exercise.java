package src.Завдання1;
import java.util.Random;
import java.lang.Math;

public class Exercise {
        static boolean IsSimple(int ANum) {
          if (ANum < 2){
            return false;
          }
          double s = Math.sqrt(ANum);
          for (int i = 2; i <= s; i++) {
            if (ANum % i == 0)
              return false;
          }
          return true;
        }
        public static void main(String[] args) {
            Random rand = new Random();
            int[] Array = new int[10];
            for (int i = 0; i < 10; i++) {
                while(true){
                    int num = rand.nextInt(100);
                    if(IsSimple(num) == true){
                        Array[i] = num;
                        break;
                    }
                }
                System.out.print(Array[i] + " ");
            }
        }
    }