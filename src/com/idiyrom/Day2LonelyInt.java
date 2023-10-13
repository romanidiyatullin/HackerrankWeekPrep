package com.idiyrom;

import java.util.*;

public class Day2LonelyInt {

    public static int lonelyinteger(List<Integer> a) {
        // Write your code here
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer element:a){
            if(map.containsKey(element))
                map.put(element, map.get(element)+1);
            else
                map.put(element,1);
        }
        return map.keySet().stream().filter( key -> map.get(key)==1).findAny().get();
    }

    public static void main(String[] args) {
        System.out.println(Day2LonelyInt.lonelyinteger(List.of(1,2,1,3,2,4,3)));
    }

}

