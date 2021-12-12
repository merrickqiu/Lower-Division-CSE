// EXAM INSTRUCTIONS:
// All of your code for Task 3 goes in this file.

class WordFilter{
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide at least one command-line argument");
            return;
        }
        String subString = args[0];

        boolean zeroWords = true;
        for(int i = 1; i < args.length; i++) {
            if (args[i].contains(subString)){
                System.out.println(args[i]);
                zeroWords = false;
            }
        }

        if (zeroWords) {
            System.out.println("0 words contained \"" + subString + "\"");
        }
    }
}