package test.test;

import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * @author junzhou
 * @date 2022/5/3 17:12
 * @since 1.8
 */
public class CustomClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            byte[] result = getClassFromCustomPath(name);
            return defineClass(name, result, 0, result.length);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        throw new ClassNotFoundException();
    }

    private byte[] getClassFromCustomPath(String name){
        // 从自定义路径中加载指定类
        return "".getBytes();
    }

    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            Class<?> encodeTest = Class.forName("EncodeTest", true, customClassLoader);
            Object obj = encodeTest.newInstance();
            System.out.println(obj.getClass().getClassLoader());

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
