package week01jvm;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 布谷
 */
public class XlassClassLoader extends ClassLoader {

    public static void main(String[] args) {
        String name = "Hello";
        try {
            Class<?> Hello = new XlassClassLoader().findClass(name);
            Object instance = Hello.getDeclaredConstructor().newInstance();
            Method helloMethod = Hello.getDeclaredMethod("hello");
            helloMethod.invoke(instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String prefix = "static/";
        try {
            InputStream dis = this.getClass().getClassLoader().getResourceAsStream(prefix + name + ".xlass");
            byte[] encodeBytes = new byte[dis.available()];
            dis.read(encodeBytes);
            byte[] decoderBytes = decodeBytes(encodeBytes);
            return defineClass(name, decoderBytes, 0, decoderBytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    private byte[] decodeBytes(byte[] encodeBytes) {
        byte[] decodeBytes = new byte[encodeBytes.length];
        for (int i = 0; i < encodeBytes.length; i++) {
            decodeBytes[i] = (byte) (255 - encodeBytes[i]);
        }
        return decodeBytes;
    }
}
