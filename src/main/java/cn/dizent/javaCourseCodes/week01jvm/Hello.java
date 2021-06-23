package cn.dizent.javaCourseCodes.week01jvm;

/**
 * @author 布谷
 */
public class Hello {

    public static void main(String[] args) {
        byte by = 2;
        short s = 2;
        int i = 1;
        long l = 1 << 24;

        float f = 2.43f;
        double d = 3.141592653d;

        boolean b = true;

        char c = 'c';

        by = 8 + 8;
        s = 32 - 16;
        i = 128 * 128;
        l = 64 / 8;

        if (d >= f) {
            System.out.println("进入if判断");
        }

        if (i < l) {

        } else {
            System.out.println("进入else分支");
        }

        for (int num = 0; num < s; num++) {
            i += num;
            System.out.println("进入for循环");
        }
        new Hello().print(i,"< - 这是 i 的值");

    }

    public String print(int a,String b){
        System.out.println(a + b);
        return "打印方法";
    }
}
