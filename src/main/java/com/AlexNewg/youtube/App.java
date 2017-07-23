package com.AlexNewg.youtube;

import com.AlexNewg.youtube.controller.Controller;
import com.AlexNewg.youtube.view.View;

public class App {

    public static void main(String args[]) {

        View view = new View(Controller.getInstance());
        view.start();
    }
}
