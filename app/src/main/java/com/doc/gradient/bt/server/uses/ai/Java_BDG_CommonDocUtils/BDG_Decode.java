package com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils;

public class BDG_Decode {
    public static String BDG_decode(String s) {
        String invalid = "Invalid Code";
        String ini = "11111111";
        boolean flag = true;

        // Initial check for "11111111"
        for (int i = 0; i < 8; i++) {
            if (ini.charAt(i) != s.charAt(i)) {
                flag = false;
                break;
            }
        }

        // Extract the part after "11111111"
        String val = s.substring(8);

        // Array to store the binary data
        int[][] arr = new int[11101][8];
        int ind1 = -1, ind2 = 0;

        // Split the binary string into 7-bit chunks
        for (int i = 0; i < val.length(); i++) {
            if (i % 7 == 0) {
                ind1++;
                ind2 = 0;
            }
            arr[ind1][ind2++] = val.charAt(i) - '0';
        }

        // Array to hold decoded values
        int[] num = new int[11111];
        int nind = 0;
        int tem, cu;

        // Convert binary chunks into integers
        for (int i = 0; i <= ind1; i++) {
            cu = 0;
            tem = 0;

            for (int j = 6; j >= 0; j--) {
                tem += arr[i][j] * Math.pow(2, cu++);
            }
            num[nind++] = tem;
        }

        // Convert integer values to characters
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < nind; i++) {
            ret.append((char) num[i]);
        }

        // Return the decoded string or "Invalid Code"
        return (val.length() % 7 == 0 && flag) ? ret.toString() : invalid;
    }

    public static void main(String[] args) {
        String input = "111111110110101101000000101101000110"; // Example input
        System.out.println(BDG_decode(input));  // Call and print result
    }
}
