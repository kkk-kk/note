package algo;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Equation {
    public static void calculate() {
        int a, b, c;
        System.out.println("Ax^ + Bx + C = 0");
        System.out.print("A = ");
        Scanner scan = new Scanner(System.in);
        a = string2int(scan.next());
        System.out.print("B = ");
        b = string2int(scan.next());
        System.out.print("C = ");
        c = string2int(scan.next());


        System.out.println("\nResult:");
        System.out.println(calEqua(a, b, c));
        scan.close();
    }

    private static String solution1(int a,int b,int c){
        double sqrt = Math.sqrt(b * b - 4 * a * c);
        double x1 = (-1*b + sqrt)/(2*a);
        double x2 = (-1*b - sqrt)/(2*a);
        DecimalFormat df = new DecimalFormat("0.000");
        return "x1 = " + df.format(x1) + "\nx2 = " + df.format(x2);
    }
    private static String calEqua(int a, int b, int c) {
        double x = b * b - 4 * a * c;
        if (x > 0)
            return solution1(a,b,c) + "\n\n" + solution2(a, b, c);
        if (x == 0)
            return "x1 = x2 = " + b / (-2 * a);
        return "No solution.";
    }

    private static String solution2(int a, int b, int c) {
        int x1 = 0, x2 = 0;
        int s = b * b - 4 * a * c;

        int[] sqr = new int[2];
        sqrt(s, sqr);
        int[] fra1 = new int[2];
        contract(b, -2 * a, fra1);
        int[] fra2 = new int[2];
        contract(sqr[0], 2 * a, fra2);
        return result(fra1, fra2, sqr[1]);
    }

    private static String result(int[] fra1, int[] fra2, int sqr) {
        int[] x1 = new int[2];
        int[] x2 = new int[2];
        if (sqr == 1) {  // 没有根号
            int n1 = fra1[0] * fra2[1] + fra1[1] * fra2[0];
            int n2 = fra1[0] * fra2[1] - fra1[1] * fra2[0];
            int d = fra1[1] * fra2[1];
            if(n1 != 0)
                contract(n1, d, x1);
            else {
                contract(n2, d, x2);
                return "x1 = 0" + "\n" +
                        "x2 = " + printFra(x2[0],x2[1]);
            }
            if(n2!=0)
                contract(n2, d, x2);
            else{
                contract(n1, d, x1);
                return "x1 = 0" + "\n" +
                        "x2 = " + printFra(x1[0],x1[1]);
            }
            return "x1 = " + printFra(x1[0],x1[1]) + "\n" +
                    "x2 = " + printFra(x2[0],x2[1]);
        } else {
            return "x1 = " + printFra(fra1[0],fra1[1]) + " - " + printSqr(fra2[0],fra2[1],sqr) + "\n" +
                    "x2 = " + printFra(fra1[0],fra1[1]) + " + " + printSqr(fra2[0],fra2[1],sqr);

        }
    }
    private static String printSqr(int n,int m,int s){
        if(n == 1){
            if(m == 1)
                return "sqrt("+s+")";
            else return "sqrt(" + s + ")/" + Math.abs(m);
        }
        else{
            if(m == 1)
                return n + "*sqrt("+s+")";
            else return n + "*sqrt(" + s + ")/" + Math.abs(m);
        }
    }
    private static String printFra(int n,int m){
        String s;
        if(n * m < 0) {
            if (m == -1 || m == 1)
                s = "-" + Math.abs(n);
            else
                s = "-" + Math.abs(n) + "/" + Math.abs(m);
        }
        else{
            if (m == -1 || m == 1)
                s = Math.abs(n) + "";
            else
                s = Math.abs(n) + "/" + Math.abs(m);
        }
        return s;

    }

    private static void contract(int a, int b, int[] fra) {
        int gcd = gcd(Math.abs(a), Math.abs(b));
        fra[0] = a / gcd;
        fra[1] = b / gcd;
        // System.out.println(fra[0] + "/" + fra[1]);
    }

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

    private static void sqrt(int radicand, int[] sqr) {
        int[] num = new int[20];
        int s = fun(radicand, num, 0);
        int x = 1;
        for (int n : num) {
            if (n == 0)
                break;
            x *= n;
        }
        sqr[0] = x;
        sqr[1] = s;

    }

    private static int fun(int s, int[] num, int index) {
        if (s == 1)
            return s;
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};
        // radicand < 2809 prime <= 47 index < 15 more: {, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97}
        for (int p : prime) {
            if (s == p) {
                return s;
            } else if (s % (p * p) == 0) {
                s /= (p * p);
                num[index++] = p;
                return fun(s, num, index);
            }
        }
        return s;
    }

    private static int string2int(String str) {
        char num[] = str.toCharArray();
        int result = 0;
        int i = 1, flag = 0;
        char c;
        if (num[0] == '-')
            flag = 1;
        for (int j = num.length - 1; j >= flag; j--) {
            c = num[j];
            if (c < '0' || c > '9') {
                return -1;
            }
            result += i * (c - '0');
            i *= 10;
        }
        if (flag == 0)
            return result;
        else return -1 * result;
    }
}
