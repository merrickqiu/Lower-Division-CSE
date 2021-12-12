import tester.*;

class Pair {
    int a, b;
    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class ExamplesArrays {
    double averageWithoutLowest(double[] array) {
        if (array.length <= 1) {
            return 0.0;
        }
        double total = 0;
        double lowest = array[0];
        for (double value: array) {
            total += value;
            if (value < lowest) {
                lowest = value;
            }
        }
        return (total - lowest)/(array.length - 1);
    }
    //Result of calling averageWithoutLowest() with array {5, 3, 1, 2}
    // value    total-start    total-end    lowest-start    lowest-end
    //  5          0             5             5                5
    //  3          5             8             5                3
    //  1          8             9             3                1
    //  2          9             11            1                1



    int[] sumOfPairs(Pair[] pairArray) {
        int[] sumArray = new int[pairArray.length];
        for(int i = 0; i < pairArray.length; i++) {
            sumArray[i] = pairArray[i].a + pairArray[i].b;
        }
        return sumArray;
    }

    Region[] regionsWithPoint(Region[] regionArray, Point p) {
        int count = 0;
        for (Region r : regionArray) {
            if (r.contains(p)) {
                count++;
            }
        }

        Region[] withPoint = new Region[count];
        int i = 0;
        for (Region r : regionArray) {
            if (r.contains(p)) {
                withPoint[i] = r;
                i++;
            }
        }

        return withPoint;
    }

    void testAverageWithoutLowest(Tester t) {
		double[] unique = {1.0,2.0,3.0};
		t.checkExpect(averageWithoutLowest(unique), 2.5);
        double[] unique1 = {};
		t.checkExpect(averageWithoutLowest(unique1), 0.0);
        double[] unique2 = {1.0};
		t.checkExpect(averageWithoutLowest(unique2), 0.0);
        double[] unique3 = {1.0,2.0,3.0, 1.0};
		t.checkExpect(averageWithoutLowest(unique3), 2.0);
	};
	void testRegionsWithPoint(Tester t) {
		Region[] regions = {new CircleRegion(new Point(0, 0), 5), new CircleRegion(new Point(0, 0), 10)};
		Region[] result = {new CircleRegion(new Point(0, 0), 10)};
		t.checkExpect(regionsWithPoint(regions, new Point(9, 0)), result);
	};
	void testSumOfPairs(Tester t) {
		Pair[] pairs = {new Pair(1, 2), new Pair(3, 4)};
		int[] result = {3, 7};
		t.checkExpect(sumOfPairs(pairs), result);
	};
}