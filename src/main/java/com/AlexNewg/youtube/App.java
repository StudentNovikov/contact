package com.AlexNewg.youtube;

import com.AlexNewg.youtube.controller.Controller;
import com.AlexNewg.youtube.dao.GroupDaoDOM;
import com.AlexNewg.youtube.dao.XmlValidator;
import com.AlexNewg.youtube.model.Group;
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

//        XmlValidator validator = new XmlValidator();
//        System.out.println(validator.validateGroupXml());
//        System.out.println(validator.validateContactXml());

//        View view = new View(Controller.getInstance(observerOperator));
//        view.start();

        GroupDaoDOM test = new GroupDaoDOM();
        test.initData();
        for (Group group : test.getAllGroups()){
            System.out.println(group.toString());
        }
        test.update("job","work");
        for (Group group : test.getAllGroups()){
            System.out.println(group.toString());
        }
        test.create("fun");
        test.create("superFun");
        test.delete("superFun");
    }
}
