package com.AlexNewg.youtube;

import com.AlexNewg.youtube.controller.Controller;
import com.AlexNewg.youtube.view.ObserverOperator;
import com.AlexNewg.youtube.view.View;
import com.AlexNewg.youtube.view.ViewCurrentContacts;

/**
 * to make this application work properly make sure
 * you have 1.txt file in project folder
 */

public class App {

    public static void main(String args[]) {

        ObserverOperator observerOperator = new ObserverOperator();
        ViewCurrentContacts viewCurrentContacts = new ViewCurrentContacts(observerOperator);
        View view = new View(Controller.getInstance(observerOperator));
        view.start();

    }
}
