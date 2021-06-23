
```java
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
```

>  `javap -c -verbose Hello` 编译Hello.class

```shell script
# 类路径
Classfile /Users/dizent/IdeaProjects/javaCourseCodes/src/main/java/cn/dizent/javaCourseCodes/week01jvm/Hello.class # 最近修改时间，文件大小
  Last modified 2021-6-23; size 1160 bytes
# MD5 文件校验数
  MD5 checksum 7b5718d09c692023d520c9041ce181eb
# 编译源文件
  Compiled from "Hello.java"
# 编译的类的项目路径
public class cn.dizent.javaCourseCodes.week01jvm.Hello
# 编译的jdk小版本
  minor version: 0
# 编译的jdk大版本：jdk 1.8
  major version: 52
# 类标记，public类型，有继承；自object
  flags: ACC_PUBLIC, ACC_SUPER
# 常量池
Constant pool:
# 常量池的格式
   #编号 = 常量类型         常量值或者常量的引用  引用的值：Object.init返回void
   #1 = Methodref          #24.#37        // java/lang/Object."<init>":()V
   #2 = Long               16777216l
   #4 = Float              2.43f
   #5 = Double             3.141592653d
   #7 = Long               8l
   #9 = Fieldref           #38.#39        // java/lang/System.out:Ljava/io/PrintStream;
  #10 = String             #40            // 进入if判断
  #11 = Methodref          #41.#42        // java/io/PrintStream.println:(Ljava/lang/String;)V
  #12 = String             #43            // 进入else分支
  #13 = String             #44            // 进入for循环
  #14 = Class              #45            // cn/dizent/javaCourseCodes/week01jvm/Hello
  #15 = Methodref          #14.#37        // cn/dizent/javaCourseCodes/week01jvm/Hello."<init>":()V
  #16 = String             #46            // < - 这是 i 的值
  #17 = Methodref          #14.#47        // cn/dizent/javaCourseCodes/week01jvm/Hello.print:(ILjava/lang/String;)Ljava/lang/String;
  #18 = Class              #48            // java/lang/StringBuilder
  #19 = Methodref          #18.#37        // java/lang/StringBuilder."<init>":()V
  #20 = Methodref          #18.#49        // java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
  #21 = Methodref          #18.#50        // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
  #22 = Methodref          #18.#51        // java/lang/StringBuilder.toString:()Ljava/lang/String;
  #23 = String             #52            // 打印方法
  #24 = Class              #53            // java/lang/Object
  #25 = Utf8               <init>
  #26 = Utf8               ()V
  #27 = Utf8               Code
  #28 = Utf8               LineNumberTable
  #29 = Utf8               main
  #30 = Utf8               ([Ljava/lang/String;)V
  #31 = Utf8               StackMapTable
  #32 = Class              #54            // "[Ljava/lang/String;"
  #33 = Utf8               print
  #34 = Utf8               (ILjava/lang/String;)Ljava/lang/String;
  #35 = Utf8               SourceFile
  #36 = Utf8               Hello.java
  #37 = NameAndType        #25:#26        // "<init>":()V
  #38 = Class              #55            // java/lang/System
  #39 = NameAndType        #56:#57        // out:Ljava/io/PrintStream;
  #40 = Utf8               进入if判断
  #41 = Class              #58            // java/io/PrintStream
  #42 = NameAndType        #59:#60        // println:(Ljava/lang/String;)V
  #43 = Utf8               进入else分支
  #44 = Utf8               进入for循环
  #45 = Utf8               cn/dizent/javaCourseCodes/week01jvm/Hello
  #46 = Utf8               < - 这是 i 的值
  #47 = NameAndType        #33:#34        // print:(ILjava/lang/String;)Ljava/lang/String;
  #48 = Utf8               java/lang/StringBuilder
  #49 = NameAndType        #61:#62        // append:(I)Ljava/lang/StringBuilder;
  #50 = NameAndType        #61:#63        // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
  #51 = NameAndType        #64:#65        // toString:()Ljava/lang/String;
  #52 = Utf8               打印方法
  #53 = Utf8               java/lang/Object
  #54 = Utf8               [Ljava/lang/String;
  #55 = Utf8               java/lang/System
  #56 = Utf8               out
  #57 = Utf8               Ljava/io/PrintStream;
  #58 = Utf8               java/io/PrintStream
  #59 = Utf8               println
  #60 = Utf8               (Ljava/lang/String;)V
  #61 = Utf8               append
  #62 = Utf8               (I)Ljava/lang/StringBuilder;
  #63 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
  #64 = Utf8               toString
  #65 = Utf8               ()Ljava/lang/String;
### 代码的字节码
{
# 隐式的无参数构造方法；在代码中没写，但是在字节码中会生成
  public cn.dizent.javaCourseCodes.week01jvm.Hello();
# 解释：()中为参数，此方法表示无参；V表示返回值，此方法为void
    descriptor: ()V # 标记：public方法
    flags: ACC_PUBLIC
# 代码：
    Code:
# 堆栈深度= 1，本地变量数= 1，参数数量 = 1
      stack=1, locals=1, args_size=1
# 偏移量 0：将第一个引用类型本地变量推送至栈顶
         0: aload_0 # 偏移量 1：调用父类Object的构造方法
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
# 偏移量 4：结束方法并return
         4: return # 偏移量与代码对应行数关系
      LineNumberTable: # 源代码第六行：对应偏移量 0 
        line 6: 0
# 本地变量表；javac时需要加 -g 参数
     LocalVariableTable:
# 变量位置  长度  槽位  名字  类型
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcn/dizent/javaCourseCodes/week01jvm/Hello;


# Hello类的main方法编译的字节码
  public static void main(java.lang.String[]);
# 解释：(LString)代表参数为字符串数组，V：方法为void
    descriptor: ([Ljava/lang/String;)V # 标记：方法为public并且static方法
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
# 栈高度=4，本地变量数=12，方法参数数量= 1
      stack=4, locals=12, args_size=1
# 偏移0:将int型2推送至栈顶；
         0: iconst_2 # 偏移1：将栈顶int型数值存入指定本地变量
         1: istore_1
# 偏移2:将int型2推送至栈顶
         2: iconst_2
# 偏移3：将栈顶int型数值存入指定本地变量
         3: istore_2
# 偏移4:将int型1推送至栈顶；
         4: iconst_1
# 偏移3：将栈顶int型数值存入指定本地变量
         5: istore_3
# 偏移6：将long或double型常量值从常量池中推送至栈顶（宽索引）编号#2
         6: ldc2_w        #2                  // long 16777216l
# 偏移9：将栈顶long类型数值存入指定本地变量 槽位：4
         9: lstore        4
# 偏移量11：将int, float或String型常量值从常量池中推送至栈顶 编号#4
        11: ldc           #4                  // float 2.43f
# 偏移量13：将栈顶float型数值存入本地变量 槽位：6
        13: fstore        6
# 偏移量15：将long或double型常量值从常量池中推送至栈顶（宽索引）编号#5
        15: ldc2_w        #5                  // double 3.141592653d
# 偏移量18：将栈顶double型数值存入本地变量 槽位：7
        18: dstore        7
# 偏移量20：将int型1推送至栈顶
        20: iconst_1
# 偏移量21：将栈顶int型数值存入本地变量 槽位：9 
        21: istore        9
# 偏移量23：将单字节的常量值 99 推送至栈顶
        23: bipush        99 # 偏移量25：将栈顶int型数值存入指定本地变量 槽位：10
        25: istore        10
# 偏移量27：将单字节的常量值 16 推至栈顶
        27: bipush        16 # 偏移量29：将栈顶int行数值存入本地变量 槽位：1
        29: istore_1
# 偏移量30：将单字节的常量值 16 推送至栈顶
        30: bipush        16
# 偏移量32：将栈顶int行数值存入本地变量 槽位：2
        32: istore_2
# 偏移量33：将一个短整型常量 16384 推送至栈顶
        33: sipush        16384
# 偏移量36：将栈顶int型数值存入本地变量 槽位：3
        36: istore_3
# 偏移量37：将long或double型常量值从常量池中推送至栈顶（宽索引）编号#7
        37: ldc2_w        #7                  // long 8L
# 偏移量40：将栈顶long型数值存入本地变量 槽位：4
        40: lstore        4
# 偏移量42：将指定的double型本地变量复制到栈顶 槽位：7
        42: dload         7
# 偏移量44：将指定的float型本地变量复制到栈顶 槽位：6
        44: fload         6
# 偏移量46：将栈顶的float型数据转换为double
        46: f2d
# 偏移量47：比较栈顶两double型数值大小，并将结果（1，0，-1）压入栈顶；当其中一个数值为NaN时，将-1压入栈顶
        47: dcmpl
# 偏移量48：当栈顶int型数值小于0时跳转到 59 
        48: iflt          59
# 偏移量51：获取指定类的静态域，并将其值压入栈顶；获取static资源：System.out.printStream
        51: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
# 偏移量54：将int, float或String型常量值从常量池中推送至栈顶 编号#10
        54: ldc           #10                 // String 进入if判断
# 偏移量56：调用实例方法println()参数为String，void
        56: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
# 偏移量59：将指定的int型变量复制到栈顶 槽位：3
        59: iload_3
# 偏移量60：将栈顶的int型数据转换为long型
        60: i2l
# 偏移量61: 将指定的long型变量复制到栈顶 槽位：4
        61: lload         4
# 偏移量63：比较栈顶的两个long类型变量，并将结果（1，0，-1）压入栈顶
        63: lcmp
# 偏移量 64：当栈顶long型数值大于0时跳转到 70
        64: ifge          70
# 偏移量 67：无条件跳转到 70
        67: goto          78
# 下面是打印 #12 号常量字符串
        70: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        73: ldc           #12                 // String 进入else分支
        75: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
# 将int型0推送至栈顶
        78: iconst_0
# 将栈顶的int型存入本地变量，槽位：11
        79: istore        11
# 将指定int型数据复制到栈顶 槽位：11（循环开始）
        81: iload         11
# 将指定的int型数据复制到栈顶 槽位：2
        83: iload_2
# 比较栈顶的两个int数据，结果大于等于0时，跳转到偏移：106
        84: if_icmpge     106
        # 将指定的int型复制到栈顶 槽位 3
        87: iload_3
        # 将指定的int型复制到栈顶 槽位 11
        88: iload         11
        # 将栈顶的两个int型数据相加并将结果压入栈顶
        90: iadd
        # 将栈顶的int型数据，存入本地变量 槽位 3
        91: istore_3
        # 打印
        92: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        95: ldc           #13                 // String 进入for循环
        97: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        # 将指定int型变量增加指定值（i++, i–, i+=2）槽位11本地变量，+ 1
       100: iinc          11, 1
       # 无条件跳转到 偏移81.即循环开始处
       103: goto          81
       # 循环结束
       # 创建一个对象，并将其引用值压入栈顶
       106: new           #14                 // class cn/dizent/javaCourseCodes/week01jvm/Hello
       # 复制栈顶数值并将复制值压入栈顶
       109: dup
       # 调用超类构造方法，实例初始化方法，私有方法
       110: invokespecial #15                 // Method "<init>":()V
       # 将指定int型数据压入栈顶
       113: iload_3
       # 将指定long，String类型数据压入栈顶
       114: ldc           #16                 // String < - 这是 i 的值
       # 调用自定义方法 Hello.print
       116: invokevirtual #17                 // Method print:(ILjava/lang/String;)Ljava/lang/String;
       # 将栈顶数值弹出 (数值不能是long或do le类型的)
       119: pop
       # 从当前方法返回void
       120: return
      LineNumberTable:
        line 9: 0
        line 10: 2
        line 11: 4
        line 12: 6
        line 14: 11
        line 15: 15
        line 17: 20
        line 19: 23
        line 21: 27
        line 22: 30
        line 23: 33
        line 24: 37
        line 26: 42
        line 27: 51
        line 30: 59
        line 33: 70
        line 36: 78
        line 37: 87
        line 38: 92
        line 36: 100
        line 40: 106
        line 42: 120
     LocalVariableTable:
        Start  Length  Slot  Name   Signature
           81      25    11   num   I
            0     121     0  args   [Ljava/lang/String;
            2     119     1    by   B
            4     117     2     s   S
            6     115     3     i   I
           11     110     4     l   J
           15     106     6     f   F
           20     101     7     d   D
           23      98     9     b   Z
           27      94    10     c   C
# 栈图：jdk8之后，字节码文件中出现；
# number_of_entries:表示entries表的成员数量。entries表中的所有成员都是一个stack_map_frame结构。
      StackMapTable: number_of_entries = 5
      # frame_type 
        frame_type = 255 /* full_frame */
          offset_delta = 59
          locals = [ class "[Ljava/lang/String;", int, int, int, long, float, double, int, int ]
          stack = []
        frame_type = 10 /* same */
        frame_type = 7 /* same */
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]
        frame_type = 250 /* chop */
          offset_delta = 24

# 这是一个额外定义的public方法print
  public java.lang.String print(int, java.lang.String);
# 解释：()参数I->int，LString->字符串，返回值String
    descriptor: (ILjava/lang/String;)Ljava/lang/String;
# flags：public
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=3, args_size=3
# 获取指定类的静态域，并将其值压入栈顶；获取static资源：
         0: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
# 创建一个对象，并将其引用值压入栈顶
         3: new           #18                 // class java/lang/StringBuilder
# 复制栈顶数值并将复制值压入栈顶
         6: dup
# 调用超类的构造方法
         7: invokespecial #19                 // Method java/lang/StringBuilder."<init>":()V
# 将第二个int本地变量推到栈顶
        10: iload_1
# 调用实例方法
        11: invokevirtual #20                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
# 将第二个引用类型本地变量推送至栈顶
        14: aload_2
#调用实例方法append
        15: invokevirtual #21                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
# 调用实例方法tostring
        18: invokevirtual #22                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
# 调用实例方法println
        21: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
# 将int, float或String型常量值从常量池中推送至栈顶
        24: ldc           #23                 // String 打印方法
# 从当前方法返回对象引用
        26: areturn
      LineNumberTable:
        line 45: 0
        line 46: 24
}
SourceFile: "Hello.java"
```