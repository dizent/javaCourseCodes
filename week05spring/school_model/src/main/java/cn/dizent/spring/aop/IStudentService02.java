package cn.dizent.spring.aop;

import org.springframework.stereotype.Service;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 14:10
 * @Description:
 */
@Service
public class IStudentService02 implements IStudent{


    @Override
    public void read() {
        System.out.println("IStudentService02 read");
    }
}
