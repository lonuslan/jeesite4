package com.lonus.utilstest.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListUtils extends com.jeesite.common.collect.ListUtils {

    private List<String> listString;
    private List<Object> listObject;
    private Integer number;


    /**
     * 准备测试用的数据集
     */
    @Before
    public void setUp(){

        listString = new ArrayList<>();
        listObject = new LinkedList<>();
        number = 2;

        listString.add("字符串1");
        listString.add(8+"");
        listString.add("89");
        listString.add("a");
        listString.add("8");

        Object object = new Object();
        listObject.add(object);
        listObject.add(object);
        listObject.add(object);
        listObject.add(number);
    }

    @Test
    public void test(){
        String str = new String(" 8 ");
        String str1 = "8";
        System.out.println(com.jeesite.common.collect.ListUtils.inString(str, listString));
        System.out.println(com.jeesite.common.collect.ListUtils.inString(str1, listString));
//        boolean b = listUtils.inString(str, listString);
    }

    @Test
    public void test1(){

    }
}
