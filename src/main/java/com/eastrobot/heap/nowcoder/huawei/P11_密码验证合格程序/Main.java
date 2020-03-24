/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.nowcoder.huawei.P11_密码验证合格程序;

import java.util.Scanner;

/**
 * S^fc8G5-78hlgV3X#96C5+5$b7K4^!#(B39
 * I)S)1p0485$-ot4N7A9Mc*N3#46^Hd)9)-^Z!J**
 * F04bA$y6$1CAD4n)x1$9&
 * 80-~8$~%x-F12P
 * // @!7479Y^V^1$l*26x#2S9%Nx0H!dY78*w4Zl
 * 16@%^nju+4d04U4*)1l!5+g!^k+P#vq*r#&v2059PsJ
 * 4@M$68(Oh%!n%~9&08&Z@#+dN0&Z
 * D*09R~G1-$7GB&()$b1h^A^1b
 * 80!2b^*t411699+^&4y@
 * xZ44332BZ-Ck0+ko~19(w!%
 * )3y@GT!(5V9G(8VeT+(tM3k4
 * O1r#K$C804E8-41J8*&%#13603
 * 7v0T+6s!(7*)C4RX8*IB85yk+6&~#v6)q$+W3&8-8+
 * jJ8~7125d@DGmk~^~m637B20!61M5NdY^9CU9%R$1&h53iO+
 * 831(l)8^$O+3T
 *
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 21:05
 */
public class Main {

    public static final String verify(String passwd){

        int uppercaseCount = 0;
        int lowercaseCount = 0;
        int numCount = 0;
        int symbolCount = 0;

        int maxLen = 8;

        if (passwd.length()<maxLen){
            return "NG";
        }
        char[] chars = passwd.toCharArray();
        for (char c : chars){
            if (c>='a' && c<='z'){
                lowercaseCount=1;
            }else if (c>='A' && c<='Z'){
                uppercaseCount=1;
            }else if (c>='0' && c<='9'){
                numCount=1;
            }else{
                symbolCount=1;
            }
        }
//        if ((uppercaseCount>0 && lowercaseCount>0 && numCount>0)
//        || (uppercaseCount>0 && lowercaseCount>0 && symbolCount>0)
//        || (lowercaseCount>0 && numCount>0 && symbolCount>0)){
//
//        }else{
//            return "NG";
//        }
        if (lowercaseCount+uppercaseCount+numCount+symbolCount<3){
            return "NG";
        }

        // 子串重复出现 一组或多组长度超过2的子符串
        for (int i=0;i<chars.length;i++){
            if (i+2==chars.length){
                break;
            }
            String sub = chars[i] + "" + chars[i+1] + "" + chars[i+2];
//            System.out.println(sub);
            if (passwd.substring(i+3).contains(sub)){
                return "NG";
            }
        }

        return "OK";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            System.out.println(verify(s));
        }
    }
}
