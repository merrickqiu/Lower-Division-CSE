import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.NoSuchElementException;
import tester.*;

class ComparatorLookupTable<K, V> {
    List<K> keys;
    List<V> values;
    Comparator<K> comparator;

    ComparatorLookupTable(List<K> keys, List<V> values, Comparator<K> comparator) {
        this.keys = keys;
        this.values = values;
        this.comparator = comparator;
    }
    boolean contains(K key) {
        for (K current: keys) {
            if (comparator.compare(current, key) == 0) {
                return true;
            }
        }
        return false;
    }
    void add(K key, V value) {
        if(this.contains(key)) {
            throw new IllegalArgumentException();
        }
        keys.add(key);
        values.add(value);
    }
    V find(K key) {
        for (int i = 0; i< keys.size(); i++) {
            if (comparator.compare(keys.get(i), key) == 0) {
                return values.get(i);
            }
        }
        throw new NoSuchElementException(); // This line on the stack
    }
    void update(K key, V value) {
        for (int i = 0; i< keys.size(); i++) {
            if (comparator.compare(keys.get(i), key) == 0) {
                values.set(i, value);
                return;
            }
        }
        throw new NoSuchElementException();
    }
}

class StringComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
      return s1.compareTo(s2);
    }
}

class ComparatorLookupTableExamples {
    List<String> strs = new ArrayList<>(Arrays.asList("a", "b", "c"));
    List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
    ComparatorLookupTable<String, Integer> ctl = new ComparatorLookupTable<>(strs, nums, new StringComparator());
    void testUpdate(Tester t) {
        t.checkExpect(ctl.contains("a"), true);
        ctl.update("a", 9);
        t.checkExpect(ctl.find("a"), 9);
        ctl.add("z", 10);
        t.checkExpect(ctl.keys, Arrays.asList("a", "b", "c", "z"));
        t.checkExpect(ctl.values, Arrays.asList(9, 2, 3, 10));
        t.checkException(new IllegalArgumentException(), ctl, "add", "z", 5);
        t.checkException(new NoSuchElementException(), ctl, "find", "y");

        ctl.find("This is not in the table."); // This line of stack
    }
}
/*
class                           method      this reference      other variables
ComparatorLookupTableExamples   testUpdate      <ignore>          None
ComparatorLookupTable           find            :4                  key
*/