package test.java.datastructureproject.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;
import logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.*;
import static org.junit.Assert.*;
import main.java.datastructureproject.init.*;

public class StringListTest {

    StringList sl;
    Random r;

    @Before
    public void setUp() {
        this.sl = new StringList();
        this.r = new Random();
    }
    
    @Test
    public void getString() {
        String s = "sssssss";
        sl.add(s);

        assertEquals(s, sl.get(0));
    }

    @Test
    public void addAllStrings() {
        StringList temp = new StringList();
        StringList temp2 = new StringList();
        StringList test = new StringList();
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < 1204; i++) {
            int j = r.nextInt();
            temp.add(String.valueOf(j));
            temp2.add(String.valueOf(i));
            ans.add(String.valueOf(j));
            ans.add(String.valueOf(i));
        }

        test.addAll(temp);
        test.addAll(temp2);

        List<String> testList = new ArrayList<>();
        for (int i = 0; i < test.size(); i++) {
            testList.add(test.get(i));
        }
        Collections.sort(testList);
        Collections.sort(ans);

        assertEquals(ans, testList);
    }

    @Test
    public void returnStringList() {
        sl.add("ss");
        String[] ans = new String[1000];
        ans[0] = "ss";

        assertEquals(ans, sl.returnStringList());
    }
}
