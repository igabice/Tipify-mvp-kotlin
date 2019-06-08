package com.digitalexplorers.tipify;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.math.*;
import java.util.function.IntPredicate;
import java.util.regex.*;
import java.util.Scanner;


class Solution {

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
    public static String solutions(int U, int L, int[] c){
        StringBuilder stringU= new StringBuilder();
        StringBuilder stringL= new StringBuilder();
        String str = "";

        int count = c.length, countU = U, countL=L;

        for(int i = 0; i< count; i++){
            int nthValue = c[i];
            if(nthValue == 2){
                stringU.append(1);
                stringL.append(1);
                countL -= 1;
                countU -= 1;
            }else if(nthValue == 1){
                if(countL > 0){
                    stringL.append(1);
                    stringU.append(0);
                    countL -= 1;
                }else if(countU > 0) {
                    stringU.append(1);
                    stringL.append(0);
                    countU -= 1;
                }
                }else{
                stringU.append(0);
                stringL.append(0);
            }
        }

        if((frequency(stringL) ==  L) && (frequency(stringU) == U)){
            str = stringU+", "+stringL;
        }else{
            str = "IMPOSSIBLE";
        }
        return str;
    }
    public static  int frequency(StringBuilder stringBuilder){
         int count = 0;
        for(int i = 0; i < stringBuilder.length(); i++){
            count += Character.getNumericValue(stringBuilder.charAt(i)) ;
        }

//        System.out.println(stringBuilder);
//        System.out.println(stringBuilder.length());
//        System.out.println(count);
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
        System.out.println(solutions(3,2,array2));
        System.out.println(solutions(2,3,array));
        System.out.println(solutions(2,1,array3));

    }
}