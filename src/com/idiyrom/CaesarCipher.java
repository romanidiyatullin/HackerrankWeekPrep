package com.idiyrom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CaesarCipher {

    public static String caesarCipher(String s, int k) {
        // Write your code here
        char[] arr = s.toCharArray();

        int A = 65;
        int Z = 90;

        int a = 97;
        int z = 122;

        for(int i=0; i<arr.length; i++){

            // do not cipher if this is not a letter
            if(arr[i]< A || arr[i]> z || arr[i]> Z && arr[i]< a)
                continue;

            // if original letter was [a..z] we still want to get new cypher letter in [a..z] range:
            if(arr[i]>= a && arr[i]<= z) {
                    cypher(arr, a, z, k, i);
            }
            else {
            // same logic as above but for [A..Z]
                    cypher(arr, A, Z, k, i);
                }
            }

        // construct & return encoded String
        StringBuilder sb = new StringBuilder();
        for(char c: arr)
            sb.append(c);
        return sb.toString();

    }

    public static void cypher(char[] arr, int startLetter, int endLetter, int k, int i){

        int alphabetLength = endLetter-startLetter +1;
        int overfill = arr[i] + k;
        int delta;
        int offset;

        // no need for complicated logic if new cypher letter is still within current [a..z] ASCII range:
        if(overfill>=startLetter && overfill<=endLetter) {
            arr[i] +=k;
            return;
        }

        // otherwise - let's find out how much we are out of endLetter ASCII code:
        delta = overfill-endLetter;

        if(delta<=alphabetLength)
            offset = (startLetter-1) + delta;
        else{
            // most tricky case - if delta incorporates alphabet length w/o remainder of division, use endLetter as new code:
            offset = delta%alphabetLength>0?(startLetter-1) + delta%alphabetLength:endLetter;
        }
        arr[i]=(char)offset;
    }

    public static void main(String[] args) {
        System.out.println(caesarCipher("1X7T4VrCs23k4vv08D6yQ3S19G4rVP188M9ahuxB6j1tMGZs1m10ey7eUj62WV2exLT4C83zl7Q80M",27));
        // expected: 1Y7U4WsDt23l4ww08E6zR3T19H4sWQ188N9bivyC6k1uNHAt1n10fz7fVk62XW2fyMU4D83am7R80N
        // actual:   1Y7U4WsDt23l4ww08E6zR3T19H4sWQ188N9bivyC6k1uNHAt1n10fz7fVk62XW2fyMU4D83am7R80N
    }
}


class CaesarCipherTests {

    @Test
    public void elementaryTest(){
        Assertions.assertEquals("b", CaesarCipher.caesarCipher("a",1));
        Assertions.assertEquals("B", CaesarCipher.caesarCipher("A",1));
    }

    @Test
    public void nonLettersTest(){
        Assertions.assertEquals("1", CaesarCipher.caesarCipher("1",2));
        Assertions.assertEquals("/", CaesarCipher.caesarCipher("/",3));
    }

    @Test
    public void boundaryTest(){
        Assertions.assertEquals("b", CaesarCipher.caesarCipher("a",27));
        Assertions.assertEquals("B", CaesarCipher.caesarCipher("A",27));
        Assertions.assertEquals("a", CaesarCipher.caesarCipher("z",1));
        Assertions.assertEquals("A", CaesarCipher.caesarCipher("Z",1));
        Assertions.assertEquals("k", CaesarCipher.caesarCipher("a",270));
        Assertions.assertEquals("K", CaesarCipher.caesarCipher("A",270));
    }

    @Test
    public void complexTests(){
        String actual = "1X7T4VrCs23k4vv08D6yQ3S19G4rVP188M9ahuxB6j1tMGZs1m10ey7eUj62WV2exLT4C83zl7Q80M";
        String expected = "1Y7U4WsDt23l4ww08E6zR3T19H4sWQ188N9bivyC6k1uNHAt1n10fz7fVk62XW2fyMU4D83am7R80N";
        Assertions.assertEquals(expected, CaesarCipher.caesarCipher(actual,27));
    }

}
