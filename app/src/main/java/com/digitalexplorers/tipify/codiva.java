package com.digitalexplorers.tipify;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.math.*;
import java.util.function.IntPredicate;
import java.util.regex.*;
import java.util.Scanner;


class codiva {

    static int valueofRow(String val){
        char[] stringToCharArray = val.toCharArray();
        int res =0;

        for (char output : stringToCharArray) {
            res+=output - '0';
        }

        return res;
    }
    static int frequency(int a[],
                         int n, int x)
    {
        int count = 0;
        for (int i=0; i < n; i++)
            if (a[i] == x)
                count++;
        return count;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String solution(int U, int L, int[] c){
        String str1="", str0="", str = "";
        long count1=0, count0=0;
        int countof2 = frequency(c, c.length, 2);
        int remU = U-countof2;

        for (int i = 0; i<c.length; i++){
            count0 = str0.chars().filter(new IntPredicate() {
                @Override
                public boolean test(int ch) {
                    return ch == '1';
                }
            }).count();
            count1 = str1.chars().filter(new IntPredicate() {
                @Override
                public boolean test(int ch) {
                    return ch == '1';
                }
            }).count();
            if(c[i]==2){

                str1 += "1";
                str0 += "1";

            }
            else if(c[i]==1){
                if(remU==0){
                    str1 += "1";
                    str0 += "0";
                }
                else if(remU >0 ){
                    str1 += "0";
                    str0 += "1";
                    remU= remU-1;
                }

            }

            else if(c[i]==0){
                str1 += "0";
                str0 += "0";
            }


        }



        if((U ==  valueofRow(str0)) && (L ==valueofRow(str1))){
            return str0+","+ str1;
        }else{
            return "IMPOSSIBLE";
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {

        System.out.println("Hello Codiva");

        int[] array = new int[] {0,0,1,1,2};
        int[] array2 = new int[] {2,1,1,0,1};
        int[] array3 = new int[] {2,0,0,0,1};
        System.out.println(solution(3,2,array2));
        System.out.println(solution(2,3,array));
        System.out.println(solution(2,1,array3));



    }
}