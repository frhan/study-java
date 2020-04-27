package me.farhan.ocp;

import java.util.ArrayList;
import java.util.List;

 class Shape {
     void draw(){}
}

class Circle extends Shape {

    @Override
    void draw() {

    }
}
class Triangle extends Circle {

    @Override
    void draw() {

    }
}

class ShapeTest {

    void drawTest(){
        List<? extends Circle> l = new ArrayList<>();

       // l.add(new Circle());
    }

    void drawAll(List<? extends Shape> shapes){

    }
}