package algo;

import java.util.Scanner;

public class GCD {
    private static int gcd(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a < b) {
            int c = a;
            a = b;
            b = c;
        }
        int sub = a - b;
        return gcd(sub, b);
    }

    public static void getGCD() {
        int a, b;
        System.out.print("a = ");
        Scanner scan = new Scanner(System.in);
        a = string2int(scan.next());
        System.out.print("b = ");
        b = string2int(scan.next());
        if (a == -1 || b == -1 || a == 0 || b == 0) {
            System.out.println("Invalid input");
        } else {
            System.out.println("GCD = " + gcd(a, b));
        }
        scan.close();
    }


    private static int string2int(String str) {
        // 检查输入，转换失败返回-1
        char num[] = str.toCharArray();
        int result = 0;
        int i = 1;
        char c;
        for (int j = num.length - 1; j >= 0; j--) {
            c = num[j];
            if (c < '0' || c > '9') {
                return -1;
            }
            result += i * (c - '0');
            i *= 10;
        }
        return result;
    }

    // 约分
    public static void getContract() {
        int a, b;
        System.out.print("Numerator = ");
        Scanner scan = new Scanner(System.in);
        a = string2int(scan.next());
        System.out.print("Denominator = ");
        b = string2int(scan.next());
        if (a == -1 || b == -1 || b == 0) {
            System.out.println("Invalid input");
        } else if (a == 0) {
            System.out.println("Result = 0");
        } else {
            System.out.println("Result = " + contract(a, b));
        }

    }

    protected static String contract(int a, int b) {
        int gcd = gcd(a, b);
        int n = a / gcd;
        int d = b / gcd;
        if (d == 1) {
            return n + "";
        }
        return n + "/" + d;
    }
}
