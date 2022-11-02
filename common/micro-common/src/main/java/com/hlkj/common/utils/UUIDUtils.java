package com.hlkj.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UUIDUtils {

    public static String generateUUID() {
        String id = UUID.randomUUID().toString();
        id = id.substring(0, 8) + id.substring(9, 13) + id.substring(14, 18) + id.substring(19, 23) + id.substring(24);
//        System.out.println(id);

        return id;
    }

    public static List<Integer> getPage(int count,int pageNo, int pageSize) {
        List<Integer> list = new ArrayList<>();
        int fromIndex = 0;
        int toIndex = 0;
        if(count > 0) {
            fromIndex = (pageNo - 1) * pageSize;
            toIndex = pageNo * pageSize;
            if (toIndex > count){
                toIndex = count;
            }
        }
        list.add(fromIndex);
        list.add(toIndex);
        return list;
    }

}