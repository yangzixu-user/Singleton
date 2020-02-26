package com.aaa.Singleton;

/**
 * @ClassName HolderEnum
 * @Author Adam
 * @Date Create in 2020/2/26  23:37
 * @Description TODO
 *      holder+枚举模式
 */
public class HolderEnum {
    /**
     * 1.构造方法私有化
     */
    private HolderEnum(){}

    /**
     * 2.创建内部私有枚举类
     */
    private enum EnumHolder{
        //4.定义一个枚举---->是为了方便获取EnumHolder对象--->这是个获取的形式也是个枚举，这种模式称之为双枚举形式
        INSTANCE;
        //6.定义变量
        private HolderEnum instance;
        //5.通过构造方法来进行初始化对象
         EnumHolder(){
            this.instance= new HolderEnum();
        }
        /**
         * 3.定义返回HolderEnum对象的方法
         */
        private HolderEnum getInstance(){
            return instance;
        }

    }
    /**
     * 7.真正给外部提供获取对象的方发
     */
    public static HolderEnum getInstance(){
        return EnumHolder.INSTANCE.instance;
    }
    /**
     * 分析holder+enum
     *  安全  安全  使用的是enum的方式实现的单例模式
     *  懒加载  不是懒加载  因为枚举就想一个静态常亮一样  在类被加载的时候就随着类加载进内存了
     *  效率：高  枚举是java自带的  效率高
     */

}
