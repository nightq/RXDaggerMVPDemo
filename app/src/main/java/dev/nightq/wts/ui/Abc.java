package dev.nightq.wts.ui;

/**
 * Created by Nightq on 16/9/8.
 */
public class Abc {
    private static Abc ourInstance = new Abc();

    public static Abc getInstance() {
        return ourInstance;
    }

    private Abc() {
    }
}
