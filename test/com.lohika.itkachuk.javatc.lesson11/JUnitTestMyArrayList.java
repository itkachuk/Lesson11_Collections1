package com.lohika.itkachuk.javatc.lesson11;

// : c15:JUnitDemo.java
//Simple use of JUnit to test ArrayList
//{Depends: junit.jar}
//From 'Thinking in Java, 3rd ed.' (c) Bruce Eckel 2002
//www.BruceEckel.com. See copyright notice in CopyRight.t

import junit.framework.JUnit4TestAdapter;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: itkachuk
 * Date: 4/5/13 5:27 PM
 */

//So we can see the list objects being created,
//and keep track of when they are cleaned up:
class CountedList extends MyArrayList {
    private static int counter = 0;

    private int id = counter++;

    public CountedList() {
        System.out.println("CountedList #" + id);
    }

    public int getId() {
        return id;
    }
}

public class JUnitTestMyArrayList {

    private CountedList list = new CountedList();

    public JUnitTestMyArrayList() {
        System.out.println("Set up for " + list.getId());
        for (int i = 0; i < 3; i++)
            list.add("" + i);
    }



    // All tests have method names beginning with "test":
    @Test
    public void testInsert() {
        System.out.println("Running testInsert()");
        assertEquals(list.size(), 3);
        list.add(1, "Insert");
        assertEquals(list.size(), 4);
        assertEquals(list.get(1), "Insert");
    }

    @Test
    public void testReplace() {
        System.out.println("Running testReplace()");
        assertEquals(list.size(), 3);
        list.set(1, "Replace");
        assertEquals(list.size(), 3);
        assertEquals(list.get(1), "Replace");
    }

    // A "helper" method to reduce code duplication. As long
    // as the name doesn't start with "test," it will not
    // be automatically executed by JUnit.
    private void compare(MyArrayList lst, String[] strs) {
        Object[] array = lst.toArray();
        assertTrue("Arrays not the same length", array.length == strs.length);
        for (int i = 0; i < array.length; i++)
            assertEquals(strs[i], (String) array[i]);
    }

    @Test
    public void testOrder() {
        System.out.println("Running testOrder()");
        compare(list, new String[] { "0", "1", "2" });
    }

    @Test
    public void testRemove() {
        System.out.println("Running testRemove()");
        assertEquals(list.size(), 3);
        list.remove(1);
        assertEquals(list.size(), 2);
        compare(list, new String[] { "0", "2" });
    }

    @Test
    public void testAddAll() {
        System.out.println("Running testAddAll()");
        list.addAll(Arrays.asList(new Object[]{"An", "African", "Swallow"}));
        assertEquals(list.size(), 6);
        compare(list,
                new String[] { "0", "1", "2", "An", "African", "Swallow" });
    }

    public static junit.framework.Test suite(){
        return new JUnit4TestAdapter(JUnitTestMyArrayList.class);
    }
}
