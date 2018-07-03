package com.saltwater2233.reflection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getClass1()==getClass2()){
            Log.d("getClass","getClass1()==getClass2()");
        }

        if (getClass1() == getClass3()){
            Log.d("getClass","getClass1() == getClass3()");
        }

        getConstructor();
        getFields();
        getMethod();
    }

    /**第一种获取class的方法,通过对象获取
     * @return
     */
    private Class getClass1(){
        Student student = new Student();
        Class stuClass = student.getClass();
        return stuClass;
    }

    /**第二种获取class的方法，通过反射，但是需要导入类的包，依赖太强
     * @return
     */
    private Class getClass2(){
        Class stuClass = Student.class;
        return stuClass;
    }

    /**第三种获取class的方法，通过反射，可以使用一个字符串可以传入也可写在配置文件中等多种方法
     * @return
     */
    private Class getClass3(){
        try {
            Class stuClass = Class.forName("com.saltwater2233.reflection.Student");
            return stuClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取构造方法
     */
    private void getConstructor(){
        Class clazz = getClass3();
        Constructor[] constructors;

        //获取所有的构造方法（包括：私有、受保护、默认、公有）
        constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor:constructors){
            Log.d("getClass","获取所有的构造方法"+constructor.toString());
        }

        //获取公有构造方法
        constructors = clazz.getConstructors();
        for (Constructor constructor:constructors){
            Log.d("getClass","获取公有构造方法"+constructor.toString());
        }

        try {
            //获取公有、无参的构造方法（因为无参的构造方法只有一个，所以不用数组）
            Constructor constructor = clazz.getConstructor(null);
            Log.d("getClass","获取公有、无参的构造方法"+constructor.toString());

            //调用构造方法
            Student student = (Student) constructor.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            //获取私有构造方法
            Constructor constructor = clazz.getConstructor(char.class);
            Log.d("getClass","获取私有构造方法"+constructor.toString());

            //调用私有构造方法
            constructor.setAccessible(true);//暴力访问，忽略访问修饰符
            Student student = (Student) constructor.newInstance(0);
            student.show();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 获取成员变量
     */
    private void getFields(){
        Class stuClass = getClass3();
        //获取所有公共字段
        Field[] fields = stuClass.getFields();
        for (Field field:fields){
            Log.d("getClass","获取私有构造方法"+field.toString());
        }

        //获取所有的字段(包括私有、受保护、默认的)
        fields = stuClass.getDeclaredFields();
        for (Field field:fields){
            Log.d("getClass","获取私有构造方法"+field.toString());
        }

        try {
            //获取指定字段
            Field field = stuClass.getField("name");
            Object object = stuClass.getConstructor().newInstance();
            //为字段设置值
            field.set(object,"测试");
            Student student = (Student)object;
            Log.d("getClass","为字段设置值"+student.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取成员方法
     */
    private void getMethod(){
        Class stuClass = getClass3();

        //获取所有的”公有“方法
        Method[] methodArray = stuClass.getMethods();
        for(Method m : methodArray){
            Log.d("getClass","获取所有的”公有“方法"+m.toString());

        }

        //获取所有的方法，包括私有
        methodArray = stuClass.getDeclaredMethods();
        for(Method m : methodArray){
            Log.d("getClass","获取所有的方法，包括私有"+m.toString());
        }

        //调用方法
        try {
            Method method = stuClass.getMethod("setAge",int.class);
            Object object = stuClass.getConstructor().newInstance();
            method.invoke(object,20);

            Student student = (Student)object;
            Log.d("getClass","调用方法"+student.getAge());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
