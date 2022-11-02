import java.util.Scanner;

public class FibReeks {
    public static int fibGetal(int n){
        if (n==0)
            return 0;
        if (n==1)
            return 1;
        else {
            return fibGetal(n-1) + fibGetal(n-2);
        }
    }

    public static void main(String[] args) {
        Scanner keyb = new Scanner(System.in);
        System.out.println("Lengte Fibonacci Reeks?");
        int n = Integer.parseInt(keyb.nextLine());
        String reeks = "";
        for (int i=0; i < n; i++)
            reeks = reeks.concat("," + fibGetal(i));
        reeks = reeks.substring(1);
        System.out.println(reeks);
    }
}
