package com.epam.araksa.recursion;

public class Palindrom {

    public static void main(String [] args){

        String input="malayalam";
        input="mama";

        System.out.println(isPalindrom(input, 0, input.length()-1));




    }

    public static boolean isPalindrom(String input, int l, int r)
    {
        if(l>r)
            return true;
        if(input.charAt(l)==input.charAt(r)){
            return isPalindrom(input, l+1, r-1);
        }else
            return false;
    }

}
