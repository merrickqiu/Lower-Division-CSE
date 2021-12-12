import tester.*;

class Pair {
    int a, b;
    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class ArrayExamples {
    static String joinWith(String[] list, String joinWith) {
        return String.join(joinWith, list);
    }

    static boolean somethingFalse(boolean[] list) {
        for (boolean value: list) {
            if (value == false) {
                return true;
            }
        }
        return false;
    }

    static int countWithinRange(double[] list, double low, double high) {
        int count = 0;
        for (double value : list) {
            if (value >= low && value <= high) {
                count++;
            }
        }
        return count;
    }

    static double[] numsWithinRange(double[] list, double low, double high) {
        double[] listInRange = new double[countWithinRange(list, low, high)];
        int i = 0;
        for (double value : list) {
            if (value >= low && value <= high) {
                listInRange[i++] = value;
            }
        }
        return listInRange;
    }

    static Pair maxmin(int[] list) {
        int min = list[0];
        int max = list[0];  
        for (int value : list) {
            if (value < min) {
                min = value;
            }
            else if (value > max) {
                max = value;
            }
        } 
        return new Pair(min, max);  
    }

    static String earliest(String[] list) {
        String min = list[0];
        for (String value : list) {
            if (value.compareTo(min) < 0) {
                min = value;
            }
        }
        return min;
    }
    void testJoinWith(Tester t){
        String[] example1 = {"a", "b", "c", "d"};
        t.checkExpect(ArrayExamples.joinWith(example1, ""), "abcd");
        String[] example2 = {"a"};
        t.checkExpect(ArrayExamples.joinWith(example2,", "), "a");
        String[] example3 = {};
        t.checkExpect(ArrayExamples.joinWith(example3,", "), "");
      }
    
      void testSomethingFalse(Tester t){
        boolean[] example1 = {true, true};
        t.checkExpect(ArrayExamples.somethingFalse(example1), false);
        boolean[] example2 = {};
        t.checkExpect(ArrayExamples.somethingFalse(example2), false);
        boolean[] example3 = {false};
        t.checkExpect(ArrayExamples.somethingFalse(example3), true);
      }
    
      void testCountWithinRange(Tester t){
        double[] example1 = {0.1, 1.3, 2.6};
        t.checkExpect(ArrayExamples.countWithinRange(example1, 0, 3), 3);
        t.checkExpect(ArrayExamples.countWithinRange(example1, 1.3, 1.3), 1);
        double[] example2 = {};
        t.checkExpect(ArrayExamples.countWithinRange(example2, 0, 3), 0);
      }
    
      void testNumsWithinRange(Tester t){
        double[] example = {0.0, 3.0, 1.4, 1.5, 2.7, 9.1, 2.1};
        double[] expected1 = {0.0, 3.0, 1.4, 1.5, 2.7, 2.1};
        t.checkExpect(ArrayExamples.numsWithinRange(example, -1.1, 3.1), expected1);
        double[] expected2 = {3.0};
        t.checkExpect(ArrayExamples.numsWithinRange(example, 3.0, 3.0), expected2);
        double[] expected3 = {};
        t.checkExpect(ArrayExamples.numsWithinRange(example, 50.0, 100.0), expected3);
      }
    
      void testMaxmin(Tester t){
        int[] example1 = {4, 0, 2, 3, 1};
        t.checkExpect(ArrayExamples.maxmin(example1), new Pair(0, 4));
        int[] example2 = {4};
        t.checkExpect(ArrayExamples.maxmin(example2), new Pair(4, 4));
        int[] example3 = {999, -1000, 999, 40, -1000, 3};
        t.checkExpect(ArrayExamples.maxmin(example3), new Pair(-1000, 999));
      }
    
      void testEarliest(Tester t){
        String[] example1 = {"Aa", "aab", "Abcd", "a"};
        t.checkExpect(ArrayExamples.earliest(example1), "Aa");
        String[] example2 = {"Aa"};
        t.checkExpect(ArrayExamples.earliest(example2), "Aa");
        String[] example3 = {"a", "b", "c", "x", "y"};
        t.checkExpect(ArrayExamples.earliest(example3), "a");
      }
}

class ProvidedArrayExamples {
    void testJoinWith(Tester t){
      String[] example1 = {"a", "b","c"};
      t.checkExpect(ArrayExamples.joinWith(example1, ":"), "a:b:c");
    }
  
    void testSomethingFalse(Tester t){
      boolean[] example1 = {true, false};
      t.checkExpect(ArrayExamples.somethingFalse(example1), true);
    }
  
    void testCountWithinRange(Tester t){
      double[] example = {0.1, 1.3, 2.6};
      t.checkExpect(ArrayExamples.countWithinRange(example, 1.1, 2.2), 1);
    }
  
    void testNumsWithinRange(Tester t){
      double[] example = {0.0, 3.0, 1.4, 1.5, 2.7, 9.1, 2.1};
      double[] expected = {1.4, 1.5, 2.1};
      t.checkExpect(ArrayExamples.numsWithinRange(example, 1.1, 2.2), expected);
    }
  
    void testMaxmin(Tester t){
      int[] example = {4, 5, 2, 3, 1};
      t.checkExpect(ArrayExamples.maxmin(example), new Pair(1, 5));
    }
  
    void testEarliest(Tester t){
      String[] example = {"aa", "aab", "abcd", "a"};
      t.checkExpect(ArrayExamples.earliest(example), "a");
    }
}

