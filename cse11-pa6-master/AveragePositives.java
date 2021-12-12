class AveragePositives {
    public static void main(String[] args) {
        double count = 0.0;
        double total = 0.0;
        for(String arg: args) {
            double value = Double.parseDouble(arg);
            if (value > 0) {
                count++;
                total += value;
            }
        }
        if (count == 0) System.out.println("0.0");
        else System.out.println(total / count);
    }
}