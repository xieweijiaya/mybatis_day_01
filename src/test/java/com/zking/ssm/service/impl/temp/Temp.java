package com.zking.ssm.service.impl.temp;

import java.util.Random;

public class Temp {
    public static void main(String[] args) {
//        for (d i = 0;i< 10;i++){
//        Random rd = new Random();
//        int i = rd.nextInt(10);
//        System.out.println(i);
        int aa = Temp.aa();
        System.out.println(aa);
//        }
    }

    public static int aa(){
        return (int)((Math.random()*9+1)*100000);
    }
}
