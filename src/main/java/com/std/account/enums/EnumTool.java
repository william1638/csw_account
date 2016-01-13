package com.std.account.enums;

public class EnumTool {

    /**
     * 获取枚举类
     * @param clazz
     * @param ordinal
     * @return 
     * @create: 2015年3月21日 下午9:06:07 chenyun
     * @history:
     */
    public static <T extends Enum<T>> T valueof(Class<T> clazz, int ordinal) {
        return (T) clazz.getEnumConstants()[ordinal];
    }

    /**
     * 
     * @param clazz
     * @param name
     * @return 
     * @create: 2015年3月21日 下午9:06:12 chenyun
     * @history:
     */
    public static <T extends Enum<T>> T valueof(Class<T> clazz, String name) {
        return (T) Enum.valueOf(clazz, name);
    }

    /**
     * 获取所有枚举成员
     * @param clazz
     * @return 
     * @create: 2015年3月21日 下午9:09:33 chenyun
     * @history:
     */
    public static <T extends Enum<T>> T[] getAllvalueOf(Class<T> clazz) {
        return clazz.getEnumConstants();

    }

    /**
     * 判断包含关系
     * 前提------------直接使用枚举名字作为分类标志&&数据类型用String
     * @param clazz
     * @param str
     * @return 
     * @create: 2015年3月21日 下午10:02:07 chenyun
     * @history:
     */
    public static <T extends Enum<T>> boolean isContain(Class<T> clazz,
            String str) {
        T[] array = clazz.getEnumConstants();
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            String target = array[i].toString();
            if (target.equals(str)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        EDirection[] array = EnumTool.getAllvalueOf(EDirection.class);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].getCode());
        }
    }
}
