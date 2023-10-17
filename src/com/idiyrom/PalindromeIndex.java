package com.idiyrom;

public class PalindromeIndex {
    public static int palindromeIndex(String s) {
        // Write your code here
        if(s == null || new StringBuilder(s).reverse().toString().equals(s))
            return -1;

        char[] arr = s.toCharArray();
        int n = arr.length-1;
        int removedLetterIndex = 0;
        int i=0;
        int j=n;
        while(removedLetterIndex<=n){
            for(; i<j; i++, j--){
                if(i == removedLetterIndex){
                    j++;
                    continue;
                }
                if(j == removedLetterIndex){
                    i--;
                    continue;
                }
                if(arr[i]==arr[j])
                    continue;
                else
                    break;
            }
            if(i>=j)
                return removedLetterIndex;
            else{
                removedLetterIndex++;
                i=0;
                j=n;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(palindromeIndex("fvyqxqxynewuebtcuqdwyetyqqisappmunmnldmkttkmdlnmnumppasiqyteywdquctbeuwenyxqxqyvf"));
    }
}
