package com.beiyouwuyanzu.bitmapstest;

import java.util.ArrayList;

/**
 * Created by zp-0001 on 2017/8/10.
 */

public class moban {
    public ArrayList<numdata> data;
    public ArrayList<Integer> extend;
    int retcode;
    class numdata{
        ArrayList<newslist> children;
        int id;
        String title;
        int type;

        class newslist{

                int id;
                String title;
                int type;
                String url;

            }
        }

    }



