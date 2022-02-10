import java.math.BigDecimal;
import java.util.Scanner;
import java.math.RoundingMode;

public class test {
    public static void main(String[]args) 
    {
        int yourScale = 16;
        Scanner scan = new Scanner(System.in);
        System.out.println("enter value: ");
        String num = scan.nextLine();

        char ch = num.charAt(0);
        if(ch == '-'){
            num = num.replace("-", "");
        }

        int integerPlaces,withDecimal;

        integerPlaces = num.indexOf('.');
        if(integerPlaces == -1){
            integerPlaces = num.length();
            withDecimal = 0;
        }
        else{
            withDecimal = 1;
            yourScale = 16 - integerPlaces;
        }

        if(withDecimal == 0){
            if(num.length()>=16){
                num = num.substring(0, 16);
            }

            if(ch == '-'){
                System.out.println("-" + num);
            }else System.out.println(num);
        }

        BigDecimal bd1 = new BigDecimal(num);
        if(withDecimal==1 && yourScale>0){
            System.out.println("select rounding methods");
            System.out.println("1 - Round towards 0");
            System.out.println("2 - Round Up");
            System.out.println("3 - Round Down");
            System.out.println("4 - Nearest Ties Even");
            int choice = scan.nextInt();
            scan.close();

            switch (choice) {
                case 1:
                    System.out.println(bd1.setScale(yourScale, RoundingMode.DOWN));
                    break;
                case 2:
                    System.out.println(bd1.setScale(yourScale, RoundingMode.CEILING));
                    break;
                case 3:
                    System.out.println(bd1.setScale(yourScale, RoundingMode.FLOOR));
                    break;
                case 4:
                    System.out.println(bd1.setScale(yourScale, RoundingMode.HALF_EVEN));
                    break;
                default:
                System.out.println("invalid input");
                    break;
            }
        }else{
                if(num.length()>=16)
                    num = num.substring(0, 16); 
                if(ch == '-'){
                    System.out.println("-" + num);
                }else System.out.println(num);
            }
        }
}
