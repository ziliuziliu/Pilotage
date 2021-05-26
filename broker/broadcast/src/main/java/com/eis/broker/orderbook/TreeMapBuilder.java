package com.eis.broker.orderbook;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;

public class TreeMapBuilder implements Serializable {

    private static class DescendComparator implements Comparator<Integer>, Serializable {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 < o2) return 1;
            else if (o1.equals(o2)) return 0;
            else return -1;
        }
    }

    private static class AscendComparator implements Comparator<Integer>, Serializable {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 < o2) return -1;
            else if (o1.equals(o2)) return 0;
            else return 1;
        }
    }

    public static TreeMap<Integer, LinkedList<Order>> decreaseMap1() {
        return new TreeMap<>(new DescendComparator());
    }

    public static TreeMap<Integer, LinkedList<Order>> increaseMap1() {
        return new TreeMap<>(new AscendComparator());
    }

    public static TreeMap<Integer, Integer> decreaseMap2() {
        return new TreeMap<>(new DescendComparator());
    }

    public static TreeMap<Integer, Integer> increaseMap2() {
        return new TreeMap<>(new AscendComparator());
    }

}
