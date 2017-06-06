import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mengxin on 27/05/2017.
 */
public class ForeachIteratorDiff {

    List<String> a;

    @Before
    public void setup(){
        a = new ArrayList<String>();
        a.add("1");
        a.add("2");
    }


    @Test
    public void remove1ElementInForEach(){
        for (String temp : a) {
            if ("1".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.print("\nsize: " + a.size());
        System.out.print("\na[0]: " + a.get(0));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void remove2ElementInForEach(){
        // when remove the last element, and go back the loop, will throw an exception
        // because the size of the array has become 1 which less than 2.
        // see this article
        // http://www.cnblogs.com/dolphin0520/p/3933551.html
        for (String temp : a) {
            if ("2".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.print("\nsize: " + a.size());
        System.out.print("\na[0]: " + a.get(0));
    }

    @Test
    public void remove1ElementIterator(){
        Iterator<String> it = a.iterator();
        while (it.hasNext()) {
            String temp = it.next();
            if (temp.equals("1")) {
                it.remove();
            }
        }
        System.out.print("\nsize: " + a.size());
        System.out.print("\na[0]: " + a.get(0));
    }

    @Test
    public void remove2ElementIterator(){
        Iterator<String> it = a.iterator();
        while (it.hasNext()) {
            String temp = it.next();
            if (temp.equals("2")) {
                // use the remove() method of the iterator
                it.remove();
            }
        }
        System.out.print("\nsize: " + a.size());
        System.out.print("\na[0]: " + a.get(0));
    }
}
