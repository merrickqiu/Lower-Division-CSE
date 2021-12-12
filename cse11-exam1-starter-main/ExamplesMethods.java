class ExamplesMethods {
    double ringArea(double r1, double r2) {
        return Math.PI*(r1*r1-r2*r2);
    }

    String rotate(String str, int n) {
        if (str.length() < n) {
            return str;
        }
        return str.substring(n, str.length()) + str.substring(0, n);
    }

    double area1 = ringArea(2.0, 1.0); // ~9.4
    double area2 = ringArea(10.0, 5.0); // ~235.5
    String rotate1 = rotate("aaa", 69); // "aaa"
    String rotate2 = rotate("Hello", 3); // "loHel"
}
