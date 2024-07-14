package com.zj.java.base;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author zhoujun07 <zhoujun07@kuaishou.com>
 * Created on 2021-09-14
 */
public class 类下集合中元素的修改集合值更改 {

    public static void main(String[] args) {

        Test1 test1 = create();

        System.out.println("create: " + test1);

        test1.getList()
                .stream()
                .filter(test2 -> test2.getTestData2().equals("test22"))
                .findFirst()
                .ifPresent(test22 -> test22.setTestData2("test23"));

        System.out.println("2: " + test1);
        Test2 test3 = new Test2("test33",  false);
        test1.getList().add(test3);
        test3.setTestData2("test34");
        System.out.println("3: " + test1);

    }

    private static Test1 create() {
        List<Test2> test2List = new ArrayList<>();
        test2List.add(new Test2("test21", true));
        test2List.add(new Test2("test22", false));
        return new Test1()
                .setTestData1("test1")
                .setList(test2List);

    }

}

@Data
@ToString
@Accessors(chain = true)
class Test1 {

    private String testData1;

    private List<Test2> list;
}

@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
class Test2 {

    private String testData2;

    private Boolean isDelete;

}