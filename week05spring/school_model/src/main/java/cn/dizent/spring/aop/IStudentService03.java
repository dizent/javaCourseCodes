package cn.dizent.spring.aop;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 14:28
 * @Description:
 */
public class IStudentService03 implements IStudent{


    @Override
    public void read() {
        System.out.println("IStudentService03 read");
    }
}
