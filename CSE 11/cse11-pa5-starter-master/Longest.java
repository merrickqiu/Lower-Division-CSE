class Longest {
    public static void main(String[] args) {
        String longest = "";
        for(String str : args) {
            if (str.length() > longest.length()) {
                longest = str;
            }
        }
        System.out.println(longest);
    }
}