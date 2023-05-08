package com.example.calculator.models;

import java.util.Arrays;

public class EvaluateOperations {
    public static double evaluate(String txtValue) { // 3 + 43 - 21
        if(txtValue.length() == 0) return 0;

        String[] list = txtValue.split(" ");
        double result = Double.parseDouble(list[0]);

        for (int i = 1; i < list.length-1; i++) {
            if(list[i].equals("-")){
                result-= Double.parseDouble(list[i+1]);
            }
            if(list[i].equals("+")){
                result+= Double.parseDouble(list[i+1]);
            }
        }
        return result;
    }

    public static double evaluateWithPriority(String txtValue){ // 3 + 1 / 5 * 4 - 2
        if(txtValue.endsWith(" ")) // 3 + 1
            txtValue = txtValue.substring(0, txtValue.length()-3);
        return evaluatePriority(txtValue);

        /*

        3 + 1 / 5 * 4 - 2 => [3,+,1,/,5,*,4,-,2] => "3 + " (0.2) " * 4 - 2"
        3 + 0.2 * 4 - 2
        3 + 0.8 - 2
        1.8
        */
    }

    private static double evaluatePriority(String txtValue) {
        String[] elements = txtValue.split(" ");
        for (int i = 1; i < elements.length-1; i++) {
            if(elements[i].equals("*")){
                String result = joinList(elements,0,i-1)+ " "+ (Double.parseDouble(elements[i-1])*Double.parseDouble(elements[i+1])) + " " + joinList(elements,i+2,elements.length);
                if(result.startsWith(" ")) result = result.substring(1);
                if(result.endsWith(" ")) result = result.substring(0, result.length()-1);
                return evaluatePriority(result);
            }
            if(elements[i].equals("/")){
                String result = joinList(elements,0,i-1)+ " "+ (Double.parseDouble(elements[i-1])/Double.parseDouble(elements[i+1])) + " " + joinList(elements,i+2,elements.length);
                if(result.startsWith(" ")) result = result.substring(1);
                if(result.endsWith(" ")) result = result.substring(0, result.length()-1);
                return evaluatePriority(result);
            }
        }
        return evaluate(txtValue);
    }

    private static String joinList(String[] elements, int i, int j) {
        return String.join(" ", Arrays.asList(elements).subList(i,j));
    }
}
