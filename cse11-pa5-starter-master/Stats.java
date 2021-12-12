class Stats {
    public static void main(String[] args) {
        if (args[0].equals("--product")) {
            double product = 1;
            for(int i = 1; i < args.length; i++) {
                product *= Double.parseDouble(args[i]);
            }
            System.out.println(product);
        }
        else if (args[0].equals("--mean")) {
            double total = 0;
            for(int i = 1; i < args.length; i++) {
                total += Double.parseDouble(args[i]);
            }
            System.out.println(total/(args.length - 1));
        }
        else if (args[0].equals("--total")) {
            double total = 0;
            for(int i = 1; i < args.length; i++) {
                total += Double.parseDouble(args[i]);
            }
            System.out.println(total);
        }
        else if (args[0].equals("--max")) {
            double max = Double.parseDouble(args[1]);
            for(int i = 2; i < args.length; i++) {
                double value = Double.parseDouble(args[i]);
                if (value > max) {
                    max = value;
                }
            }
            System.out.println(max);
        }
        else if (args[0].equals("--min")) {
            double min = Double.parseDouble(args[1]);
            for(int i = 2; i < args.length; i++) {
                double value = Double.parseDouble(args[i]);
                if (value < min) {
                    min = value;
                }
            }
            System.out.println(min);
        }
        else {
            System.out.println("Bad option " + args[0]);
        }
    }
}