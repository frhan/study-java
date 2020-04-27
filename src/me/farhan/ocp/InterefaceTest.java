package me.farhan.ocp;

import java.util.ArrayList;
import java.util.List;

interface Bar{

}
interface Foo extends Bar{}

public class InterefaceTest {

    void tets(){

        List<? extends Bar> bars = new ArrayList<Foo>();
    }
}
