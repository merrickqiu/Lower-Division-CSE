import tester.*;

class Pair {
    int a, b;
    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class PairSelect {
    int[] getAs(Pair[] pairs) {
        int[] intArray = new int[pairs.length];
        for(int i = 0; i < pairs.length; i++) {
            intArray[i] = pairs[i].a;
        }
        return intArray;
    }

    void testGetAs(Tester t){
        Pair pair1 = new Pair(1, 2);
        Pair pair2 = new Pair(3, 4);
        Pair pair3 = new Pair(-60, 150);
        Pair pair4 = new Pair(0, 0);
        Pair[] pairs1 = {};
        Pair[] pairs2 = {pair1};
        Pair[] pairs3 = {pair3, pair4};
        Pair[] pairs4 = {pair1, pair2, pair3, pair4};
        int[] As1 = {};
        int[] As2 = {1};
        int[] As3 = {-60, 0};
        int[] As4 = {1, 3, -60, 0};
        t.checkExpect(getAs(pairs1), As1);
        t.checkExpect(getAs(pairs2), As2);
        t.checkExpect(getAs(pairs3), As3);
        t.checkExpect(getAs(pairs4), As4);
    }
}
