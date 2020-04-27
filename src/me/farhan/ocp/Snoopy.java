package me.farhan.ocp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

interface Comic<C>{
    void draw(C c);
}

class ComicClass<C> implements Comic<C> {
    @Override
    public void draw(C c) {

    }
}

class SnoopyClass implements Comic<Snoopy> {

    @Override
    public void draw(Snoopy snoopy) {

    }
}

class SnoopyComic implements Comic<Snoopy>{
    @Override
    public void draw(Snoopy snoopy) {

    }
}
public class Snoopy {
    public static void main(String [] args){

        LinkedList<String> list = new LinkedList<>();


        //Consumer<Comic> tConsumer = c -> System.out.println(c);
    }

}
