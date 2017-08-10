package com.beiyouwuyanzu.bitmapstest;

import java.util.ArrayList;

/**
 * Created by zp-0001 on 2017/8/10.
 */

public class moban {
    public ArrayList<data> data;
    public ArrayList<Integer> extend;
    int retcode;
    class data{
        ArrayList<numlist> numlist;
        class numlist{
           ArrayList<children> children;
            int id;
            String title;
            int type;
            class children{
                int id;
                String title;
                int type;
                String url;

            }
        }

    }


}
