package algo;

import java.util.Scanner;

public class SQRT {
    public static void getSQRT() {
        System.out.print("Radicand = ");
        Scanner scan = new Scanner(System.in);
        int radicand = string2int(scan.next());
        if (radicand == -1) {
            System.out.println("Invalid input!");
        } else if (radicand == 0) {
            System.out.println("Result = 0");
        } else if (radicand == 1) {
            System.out.println("Result = 1");
        } else {
            System.out.println("Result = " + sqrt(radicand));
        }
        scan.close();
    }

    private static String sqrt(int radicand) {

        int[] num = new int[50];
        int s = fun(radicand, num, 0);
        int x = 1;
        for (int n : num) {
            if (n == 0)
                break;
            x *= n;
        }
        if (x == 1)
            return "sqrt(" + s + ")";
        if(s == 1)
            return x + "";
        return x + " * sqrt(" + s + ")";
    }

    private static int fun(int s, int[] num, int index) {
        if(s == 1)
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
        // 检查输入,转换失败返回-1

        char[] num = str.toCharArray();
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
}
