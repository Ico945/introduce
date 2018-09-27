package com.company.introduce.service;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i=0; i<10; i++)
            list.add(i);
        System.out.println(list.subList(0, 3));
    }
}
