package com.example.gitapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class GitApiApplicationTests {


    @Test
    void contextLoads() {
        ZZZ zzz = new ZZZ("Piri");
        foo(zzz);
        System.out.println(zzz.getFirstName());

    }

    private void foo(ZZZ zzz) {
        zzz.setFirstName("golshan");
        zzz = new ZZZ("hamedi");
        zzz.setFirstName("jabari");
        ArrayList arrayList = new ArrayList();
        arrayList.add("sdfsdf");
        arrayList.add(234);
        char z='z';
        System.out.println((int) z);
        System.out.println(arrayList);
        String[] strings={"aa","bb","cc","dd","gt","re"};
        System.out.println(Arrays.stream(strings).anyMatch("gt"::equals));
    }

}

class ZZZ {
    String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ZZZ(String firstName) {
        this.firstName = firstName;
    }
}
