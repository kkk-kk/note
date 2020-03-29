package algo;

import java.util.Scanner;

public class CalofFraction {
    private static Fraction f1 = new Fraction();
    private static Fraction f2 = new Fraction();
    public static void add(){
        System.out.println("First fraction:");
        System.out.print("Numerator = ");
        Scanner scan = new Scanner(System.in);
        f1.setNumerator(string2int(scan.next()));
        System.out.print("Denominator = ");
        f1.setDenominator(string2int(scan.next()));
        System.out.println("Second fraction:");
        System.out.print("Numerator = ");
        f2.setNumerator(string2int(scan.next()));
        System.out.print("Denominator = ");
        f2.setDenominator(string2int(scan.next()));

        System.out.println("Result = " + calAdd(f1,f2));
        scan.close();
    }

    private static String calAdd(Fraction f1,Fraction f2){
        int n = f1.getNumerator()*f2.getDenominator() + f2.getNumerator() * f1.getDenominator();
        int d = f1.getDenominator() * f2.getDenominator();
        if(n < 0){
            if(d < 0)
                return GCD.contract(-1*n,-1*d);
            else
                return "-" + GCD.contract(-1*n,d);
        }
        else{
            if(d < 0)
                return "-" + GCD.contract(n,-1*d);
            else
                return GCD.contract(n,d);
        }

    }

    private static int string2int(String str) {
        // 检查输入，转换失败返回-1
        char num[] = str.toCharArray();
        int result = 0;
        int i = 1,flag = 0;
        char c;
        if(num[0] == '-')
            flag = 1;
        for (int j = num.length - 1; j >= flag; j--) {
            c = num[j];
            if (c < '0' || c > '9') {
                return -1;
            }
            result += i * (c - '0');
            i *= 10;
        }
        if(flag == 0)
            return result;
        else return -1*result;
    }

}
