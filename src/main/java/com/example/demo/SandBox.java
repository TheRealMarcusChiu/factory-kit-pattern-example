package com.example.demo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class SandBox {
    public static void main(String[] args) {
        Integer integers1 = 1;
        Number numbers = integers1; // oops!
        List<? super B> integers = new ArrayList<>();
        integers.add(new C());
        integers.add(new D());
        C i1 = (C) integers.get(0); // oh dear
        D i2 = (D) integers.get(1); // oh dear
        System.out.println(i1);
        System.out.println(i2);
    }

    @Data
    public static class A {

    }

    @Data
    public static class B extends A {

    }

    @Data
    public static class C extends B {

    }

    @Data
    public static class D extends B {

    }
}
