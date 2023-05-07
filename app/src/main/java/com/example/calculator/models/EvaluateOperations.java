package com.example.calculator.models;

public class EvaluateOperations {
    public static long evaluate(String txtValue) { // 3 + 43 + 21
        String[] list = txtValue.split(" \\+ ");
        long result = 0;
        for(String e : list){
            result += Long.parseLong(e);
        }
        return result;
    }
}
