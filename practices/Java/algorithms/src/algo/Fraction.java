package algo;

public class Fraction {
    private int Numerator;
    private int Denominator;
    public Fraction(int n,int d){
        this.Denominator = d;
        this.Numerator = n;
    }
    public Fraction(){
        this.Numerator = 0;
        this.Denominator = 1;
    }

    public int getNumerator() {
        return Numerator;
    }

    public void setNumerator(int numerator) {
        Numerator = numerator;
    }

    public int getDenominator() {
        return Denominator;
    }

    public void setDenominator(int denominator) {
        Denominator = denominator;
    }
}
