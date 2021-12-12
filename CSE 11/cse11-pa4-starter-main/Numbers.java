import tester.*;

interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    String toText();
    double toDouble();
}

class WholeNumber implements Number {
    int n;

    WholeNumber (int n) {
        this.n = n;
    }

    public int numerator() {
        return this.n;
    }

    public int denominator() {
        return 1;
    } 

    public Number add(Number other) {
        return new Fraction(other.numerator() + n*other.denominator(), other.denominator());
    }

    public Number multiply(Number other) {
        return new Fraction(n*other.numerator(), other.denominator());
    }

    public String toText() {
        return String.valueOf(this.n);
    }

    public double toDouble() {
        return Double.valueOf(this.n);
    }
}

class Fraction implements Number {
    int n;
    int d;

    Fraction (int n, int d) {
        this.n = n;
        this.d = d;
    }

    public int numerator() {
        return this.n;
    }

    public int denominator() {
        return this.d;
    }

    public Number add(Number other) {
        return new Fraction(this.n*other.denominator() + this.d*other.numerator(), this.d*other.denominator());
    }

    public Number multiply(Number other) {
        return new Fraction(this.n*other.numerator(), this.d*other.denominator());
    }

    public String toText() {
        return n + "/" + d;
    }

    public double toDouble() {
        return Double.valueOf(this.n)/Double.valueOf(this.d);
    }
}

class ExamplesNumbers {
    Number n1 = new WholeNumber(5);
    Number n2 = new WholeNumber(7);
    Number n3 = new Fraction(7, 2);
    Number n4 = new Fraction(1, 2);

    void testAdd(Tester t) {
        t.checkExpect(this.n1.add(this.n2).toDouble(), 12.0);
        t.checkExpect(this.n1.add(this.n3).toDouble(), 5 + 7.0/2.0);
        t.checkExpect(this.n3.add(this.n3).toDouble(), 7.0);
    }

    void testMultiply(Tester t) {
        t.checkExpect(this.n1.multiply(this.n4).toDouble(), 2.5);
        t.checkExpect(this.n3.multiply(this.n4).toDouble(), 7.0/4.0);
    }

    void testNumDem(Tester t) {
        t.checkExpect(this.n3.numerator(), 7);
        t.checkExpect(this.n1.numerator(), 5);
        t.checkExpect(this.n4.denominator(), 2);
        t.checkExpect(this.n2.denominator(), 1);
    }

    void testToString(Tester t) {
        t.checkExpect(this.n4.toText(), "1/2");
        t.checkExpect(this.n3.toText(), "7/2");
        t.checkExpect(this.n2.toText(), "7");
    }

    //Exploration
    Number oneTenth = new Fraction(1, 10);
    Number twoTenths = new Fraction(2, 10);
    Number threeTenths = new Fraction(3, 10);
    double test1 = 0.1 + 0.2 + 0.3;
    double test2 = 0.1 + (0.2 + 0.3);
    String test3 = oneTenth.add(twoTenths).add(threeTenths).toText();
    String test4 = oneTenth.add(twoTenths.add(threeTenths)).toText();
}