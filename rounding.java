import java.math.BigDecimal;
import java.util.Scanner;
import java.math.RoundingMode;
public class Rounding {
    private int yourScale = 16;
    private String num;

    public Rounding(String num){
        this.num = num;
    }

    String round(){
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
                return "-" + num;
            }else return num;
        }

        BigDecimal bd1 = new BigDecimal(num);
        if(withDecimal==1){
            System.out.println("select rounding methods");
            System.out.println("1 - Round towards 0");
            System.out.println("2 - Round Up");
            System.out.println("3 - Round Down");
            System.out.println("4 - Nearest Ties Even");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            scan.close();

            switch (choice) {
                case 1:
                    System.out.println(bd1.setScale(yourScale, RoundingMode.DOWN));
                    bd1 = bd1.setScale(yourScale, RoundingMode.DOWN);
                    num = bd1.toString();
                    return num;
                case 2:
                    System.out.println(bd1.setScale(yourScale, RoundingMode.CEILING));
                    bd1 = bd1.setScale(yourScale, RoundingMode.CEILING);
                    num = bd1.toString();
                    return num;
                case 3:
                    System.out.println(bd1.setScale(yourScale, RoundingMode.FLOOR));
                    bd1 = bd1.setScale(yourScale, RoundingMode.FLOOR);
                    num = bd1.toString();
                    return num;
                case 4:
                    System.out.println(bd1.setScale(yourScale, RoundingMode.HALF_EVEN));
                    bd1 = bd1.setScale(yourScale, RoundingMode.HALF_EVEN);
                    num = bd1.toString();
                    return num;
                default:
                return "invalid input";

            }
        }else{
                if(num.length()>=16)
                    num = num.substring(0, 16); 
                if(ch == '-'){
                    return "-" + num;
                }else return num;
            }
        }
    }
