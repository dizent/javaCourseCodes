### 使用不同参数运行GCLogAnalysis.java的GC情况分析

#### SerialGC 分析

1. `-Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+UseSerialGC`

```shell script
0.252: [GC (Allocation Failure) 0.252: [DefNew: 279616K->34944K(314560K), 0.0449118 secs] 279616K->83919K(1013632K), 0.0449409 secs] [Times: user=0.03 sys=0.01, real=0.05 secs] 
0.337: [GC (Allocation Failure) 0.337: [DefNew: 314560K->34943K(314560K), 0.0608446 secs] 363535K->164112K(1013632K), 0.0608726 secs] [Times: user=0.03 sys=0.02, real=0.06 secs] 
0.432: [GC (Allocation Failure) 0.432: [DefNew: 314378K->34943K(314560K), 0.0493286 secs] 443546K->241114K(1013632K), 0.0493529 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
0.517: [GC (Allocation Failure) 0.517: [DefNew: 314559K->34943K(314560K), 0.0512692 secs] 520730K->322058K(1013632K), 0.0512947 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
0.602: [GC (Allocation Failure) 0.602: [DefNew: 314559K->34943K(314560K), 0.0544799 secs] 601674K->407508K(1013632K), 0.0545067 secs] [Times: user=0.04 sys=0.03, real=0.06 secs] 
0.698: [GC (Allocation Failure) 0.698: [DefNew: 314559K->34943K(314560K), 0.0524634 secs] 687124K->489741K(1013632K), 0.0524891 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
0.785: [GC (Allocation Failure) 0.785: [DefNew: 314559K->34943K(314560K), 0.0467529 secs] 769357K->565446K(1013632K), 0.0467810 secs] [Times: user=0.03 sys=0.02, real=0.04 secs] 
0.872: [GC (Allocation Failure) 0.872: [DefNew: 314559K->34943K(314560K), 0.0490821 secs] 845062K->645493K(1013632K), 0.0491212 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
0.962: [GC (Allocation Failure) 0.962: [DefNew: 314559K->314559K(314560K), 0.0000134 secs]0.962: [Tenured: 610550K->369458K(699072K), 0.0663020 secs] 925109K->369458K(1013632K), [Metaspace: 3368K->3368K(1056768K)], 0.0663596 secs] [Times: user=0.06 sys=0.00, real=0.07 secs] 
1.069: [GC (Allocation Failure) 1.069: [DefNew: 279483K->34943K(314560K), 0.0157115 secs] 648941K->457786K(1013632K), 0.0157447 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
执行结束!共生成对象次数:11138
Heap
 def new generation   total 314560K, used 189670K [0x0000000780000000, 0x0000000795550000, 0x0000000795550000)
  eden space 279616K,  55% used [0x0000000780000000, 0x0000000789719aa0, 0x0000000791110000)
  from space 34944K,  99% used [0x0000000793330000, 0x000000079554fff8, 0x0000000795550000)
  to   space 34944K,   0% used [0x0000000791110000, 0x0000000791110000, 0x0000000793330000)
 tenured generation   total 699072K, used 422842K [0x0000000795550000, 0x00000007c0000000, 0x00000007c0000000)
   the space 699072K,  60% used [0x0000000795550000, 0x00000007af23eb50, 0x00000007af23ec00, 0x00000007c0000000)
 Metaspace       used 3374K, capacity 4500K, committed 4864K, reserved 1056768K
  class space    used 374K, capacity 388K, committed 512K, reserved 1048576K   
```
 
 **分析：**
 
>  1. 一共进行了9次YoungGc，1次FullGc
>  2. 年轻代一共占用314560K空间，Eden区279616K，from和to区都为34944K
>  3. 
>