import tester.*;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}

class PointCompare implements Comparator<Point> {
    public int compare(Point p, Point q) {
        if (p.y > q.y) { return 1; }
        else if (p.y < q.y) { return -1; }
        else {
            if (p.x > q.x) { return 1; }
            else if (p.x < q.x) { return -1; }
            else {
                return 0;
            }
        }
    }
}

class PointDistanceCompare implements Comparator<Point> {
    double distance(Point p) {
        return Math.sqrt(p.x*p.x + p.y*p.y);
    }

    public int compare(Point p, Point q) {
        double pDistance = distance(p);
        double qDistance = distance(q);
        if (pDistance > qDistance) { return 1; }
        else if (pDistance < qDistance) { return -1; }
        else { return 0; }
    }
}

class StringCompare implements Comparator<String> {
    public int compare(String s1, String s2) {
        return Integer.signum(s1.compareTo(s2));
    }
}

class StringLengthCompare implements Comparator<String> {
    public int compare(String s1, String s2) {
        return Integer.signum(s1.length() - s2.length());
    }
}

class BooleanCompare implements Comparator<Boolean> {
    public int compare(Boolean x, Boolean y) {
        if (x && !y) { return 1; }
        else if(!x && y) {return -1; }
        else {return 0; }
    }
}

class CompareLists<E> {
    public E minimum(List<E> list, Comparator<E> comparator) {
        if (list.size() == 0) {
            return null;
        }
        list.sort(comparator);
        return list.get(0);
    }

    public E minimum(E[] array, Comparator<E> comparator) {
        return minimum(Arrays.asList(array), comparator); 
    }

    public List<E> greaterThan(List<E> list, Comparator<E> comparator, E element) {
        ArrayList<E> greaterElements = new ArrayList<>();
        for(E e : list) {
            if (comparator.compare(e, element) > 0) {
                greaterElements.add(e);
            }
        }
        return greaterElements;
    }

    public boolean inOrder(List<E> list, Comparator<E> comparator) {
        for(int i = 0; i < list.size()-1; i++) {
            E element1 = list.get(i);
            E element2 = list.get(i+1);
            if (element1 == null || element2 == null) {
                throw new IllegalArgumentException("null value in array");
            }
            if (comparator.compare(element1, element2) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean inOrder(E[] array, Comparator<E> comparator) {
        return inOrder(Arrays.asList(array), comparator); 
    }

    public List<E> merge(List<E> list1, List<E> list2, Comparator<E> comparator) {
        ArrayList<E> output = new ArrayList<>();
        int i = 0;
        int j = 0;
        for(int n = 0; n < list1.size() + list2.size(); n++) {
            if (i != list1.size() && list1.get(i) == null) { throw new IllegalArgumentException("null value in first list"); }
            if (j != list2.size() && list2.get(j) == null) { throw new IllegalArgumentException("null value in second list"); }
            if (j == list2.size()) {
                output.add(list1.get(i++));
                continue;
            }
            if (i == list1.size()) {
                output.add(list2.get(j++));
                continue;
            }

            if (comparator.compare(list1.get(i), list2.get(j)) < 0) { output.add(list1.get(i++)); }
            else { output.add(list2.get(j++)); }
        }

        return output;
    }

    public static void main(String[] args) {
        CompareLists<Boolean> booleanCompare= new CompareLists<>();
        BooleanCompare c3 = new BooleanCompare();
        List<Boolean> booleanList2 = Arrays.asList(false, true, null);
        // THROWS EXCEPTION
        booleanCompare.inOrder(booleanList2, c3);
    }
}
class testCompareLists {
    void testMinimum(Tester t) {
        CompareLists<String> stringCompare= new CompareLists<>();
        CompareLists<Boolean> booleanCompare= new CompareLists<>();
        StringCompare c1 = new StringCompare();
        StringLengthCompare c2 = new StringLengthCompare();
        BooleanCompare c3 = new BooleanCompare();
        List<String> stringList = Arrays.asList("alpha", "beta", "prunning", "zebras");
        List<Boolean> booleanList = Arrays.asList(false, true, false, true);
        t.checkExpect(stringCompare.minimum(stringList, c1), "alpha");
        t.checkExpect(stringCompare.minimum(stringList, c2), "beta");
        t.checkExpect(booleanCompare.minimum(booleanList, c3), false);
    }

    void testMinimumOverload(Tester t) {
        CompareLists<String> stringCompare= new CompareLists<>();
        CompareLists<Boolean> booleanCompare= new CompareLists<>();
        StringCompare c1 = new StringCompare();
        StringLengthCompare c2 = new StringLengthCompare();
        BooleanCompare c3 = new BooleanCompare();
        String[] stringList = {"alpha", "beta", "prunning", "zebras"};
        Boolean[] booleanList = {false, true, false, true};
        t.checkExpect(stringCompare.minimum(stringList, c1), "alpha");
        t.checkExpect(stringCompare.minimum(stringList, c2), "beta");
        t.checkExpect(booleanCompare.minimum(booleanList, c3), false);
    }

    void testGreaterThan(Tester t) {
        CompareLists<String> stringCompare= new CompareLists<>();
        CompareLists<Boolean> booleanCompare= new CompareLists<>();
        StringCompare c1 = new StringCompare();
        StringLengthCompare c2 = new StringLengthCompare();
        BooleanCompare c3 = new BooleanCompare();
        List<String> stringList = Arrays.asList("alpha", "beta", "prunning", "zebras");
        List<Boolean> booleanList = Arrays.asList(false, true, false, true);
        t.checkExpect(stringCompare.greaterThan(stringList, c1, "california"), Arrays.asList("prunning", "zebras"));
        t.checkExpect(stringCompare.greaterThan(stringList, c2, "alpha"), Arrays.asList("prunning", "zebras"));
        t.checkExpect(booleanCompare.greaterThan(booleanList, c3, false), Arrays.asList(true, true));
    }

    void testInOrder(Tester t) {
        CompareLists<String> stringCompare= new CompareLists<>();
        CompareLists<Boolean> booleanCompare= new CompareLists<>();
        StringCompare c1 = new StringCompare();
        StringLengthCompare c2 = new StringLengthCompare();
        BooleanCompare c3 = new BooleanCompare();
        List<String> stringList = Arrays.asList("alpha", "beta", "prunning", "zebras");
        List<Boolean> booleanList = Arrays.asList(false, true, false, true);
        List<Boolean> booleanList2 = Arrays.asList(false, true, null);
        t.checkExpect(stringCompare.inOrder(stringList, c1), true);
        t.checkExpect(stringCompare.inOrder(stringList, c2), false);
        t.checkExpect(booleanCompare.inOrder(booleanList, c3), false);
        t.checkException(new IllegalArgumentException("null value in array"), booleanCompare, "inOrder", booleanList2, c3);
    }

    void testInOrderOverload(Tester t) {
        CompareLists<String> stringCompare= new CompareLists<>();
        CompareLists<Boolean> booleanCompare= new CompareLists<>();
        StringCompare c1 = new StringCompare();
        StringLengthCompare c2 = new StringLengthCompare();
        BooleanCompare c3 = new BooleanCompare();
        String[] stringList = {"alpha", "beta", "prunning", "zebras"};
        Boolean[] booleanList = {false, true, false, true};
        Boolean[] booleanList2 = {false, true, null};
        t.checkExpect(stringCompare.inOrder(stringList, c1), true);
        t.checkExpect(stringCompare.inOrder(stringList, c2), false);
        t.checkExpect(booleanCompare.inOrder(booleanList, c3), false);
        t.checkException(new IllegalArgumentException("null value in array"), booleanCompare, "inOrder", booleanList2, c3);
    }

    void testMerge(Tester t) {
        CompareLists<String> stringCompare= new CompareLists<>();
        CompareLists<Boolean> booleanCompare= new CompareLists<>();
        StringCompare c1 = new StringCompare();
        StringLengthCompare c2 = new StringLengthCompare();
        BooleanCompare c3 = new BooleanCompare();
        List<String> stringList1 = Arrays.asList("alpha", "beta", "prunning", "zebras");
        List<String> stringList2 = Arrays.asList("chicken", "reduction", "xylophones");
        List<String> stringList3 = Arrays.asList("won", "chicken", "photosynthesis");
        List<Boolean> booleanList1 = Arrays.asList(false, false, false, true);
        List<Boolean> booleanList2 = Arrays.asList(true);
        List<Boolean> booleanList3 = Arrays.asList(false, true, null);
        t.checkExpect(stringCompare.merge(stringList1, stringList2, c1), Arrays.asList("alpha", "beta", "chicken", "prunning", "reduction", "xylophones", "zebras"));
        t.checkExpect(stringCompare.merge(stringList2, stringList3, c2), Arrays.asList("won", "chicken", "chicken", "reduction", "xylophones", "photosynthesis"));
        t.checkExpect(booleanCompare.merge(booleanList1, booleanList2, c3), Arrays.asList(false, false, false, true, true));
        t.checkException(new IllegalArgumentException("null value in first list"), booleanCompare, "merge", booleanList3, booleanList1, c3);
        t.checkException(new IllegalArgumentException("null value in second list"), booleanCompare, "merge", booleanList1, booleanList3, c3);
    }
    void testPointCompare(Tester t) {
        Point one = new Point(3, 4);
        Point two = new Point(3, 4);
        Point three = new Point(-1, 5);
        Point four = new Point(5, -1);
        Point five = new Point(0, 4);
        PointCompare c = new PointCompare();
        t.checkExpect(0, c.compare(one, two));
        t.checkExpect(-1, c.compare(one, three));
        t.checkExpect(1, c.compare(one, four));
        t.checkExpect(1, c.compare(one, five));
    }

    void testPointDistanceCompare(Tester t) {
        Point one = new Point(0, 0);
        Point two = new Point(3, 4);
        Point three = new Point(-5, 11);
        Point four = new Point(5, -11);
        PointDistanceCompare c = new PointDistanceCompare();
        t.checkExpect(c.compare(one, two), -1);
        t.checkExpect(c.compare(three, two), 1);
        t.checkExpect(c.compare(three, four), 0);
        t.checkExpect(c.compare(four, one), 1);
    }

    void testStringCompare(Tester t) {
        StringCompare c = new StringCompare();
        t.checkExpect(c.compare("AbCd", "AbCd"), 0);
        t.checkExpect(c.compare("aBcD", "AbCd"), 1);
        t.checkExpect(c.compare("applesauce", "zebra"), -1);
        t.checkExpect(c.compare("alphabet", "alpha"), 1);
    }

    void testStringLengthCompare(Tester t) {
        StringLengthCompare c = new StringLengthCompare();
        t.checkExpect(c.compare("z", "applesauce"), -1);
        t.checkExpect(c.compare("apple", "zebra"), 0);
        t.checkExpect(c.compare("zebra", "as"), 1);
        t.checkExpect(c.compare("nuance", "pig"), 1);
    }

    void testBooleanCompare(Tester t) {
        BooleanCompare c = new BooleanCompare();
        t.checkExpect(c.compare(true, true), 0);
        t.checkExpect(c.compare(true, false), 1);
        t.checkExpect(c.compare(false, true), -1);
        t.checkExpect(c.compare(false, false), 0);
    }
}