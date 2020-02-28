package com.aaa.test;

/**
 * @ClassName IntTest
 * @Author Adam
 * @Date Create in 2020/2/28  17:59
 * @Description TODO
 *
 *      使用jclasslib插件可以看到字节码文件执行的过程
 *
 *      int i = 1;
 *          0 iconst_1 将int类型常量压入栈（操作数栈）也可以这样说 将int类型常量1压入栈顶
 *          1 istore_1 将栈顶的的值存入（赋值）给本地变量表（局部变量表）中下标为1的变量
 *      i = i++;
 *          3 iload_1  从局部变量表中的值加载到栈中(操作数栈）
 *          4 iinc 1 by 1  局部变量表中 int类型值是1 开始自增+1（这步操作是在局部变量表中完成的）不是在栈中完成的
 *                  ++i 和 i++ 的区别就是   ++i会先在局部变量表中自增  然后再把 结果  压入栈中
 *          5 istore_1
 *          sout("i"+i)
 *         return i=1
 *
 */
public class IntTest {
    public static void main(String[] args) {
        int i = 1;
        int t = 3;
        i = ++i;
        i = t++;
        int j = i++;
        int k = i + ++i*i++;
        System.out.println("i ="+ i);
        System.out.println("j ="+ j);
        System.out.println("k ="+ k);
    }

}
