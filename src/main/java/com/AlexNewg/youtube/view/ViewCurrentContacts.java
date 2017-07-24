package com.AlexNewg.youtube.view;

import com.AlexNewg.youtube.model.Contact;
import com.AlexNewg.youtube.model.ContactLogic;

import java.io.BufferedWriter;
import java.io.FileWriter;



public class ViewCurrentContacts implements Observer{

    private ContactLogic contactLogic = new ContactLogic();
    private static int observerIDTracker = 0;



    public ViewCurrentContacts(Subject observerOperator){

        int observerID = ++observerIDTracker;

        System.out.println("New Observer " + observerID);

        observerOperator.register(this);

    }


    public void update(){

        String filePath = "1.txt";
        writeInFile(filePath,allContactsToString());

        Runtime rt = Runtime.getRuntime();
        try{
            rt.exec("notepad " + filePath);}
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

   private void writeInFile(String filePath, String message){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            String updatedText = message.replaceAll("\n", System.lineSeparator());
           writer.write(updatedText);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String allContactsToString(){
        String result = "All contacts:";
        for (Contact contact : contactLogic.getAllContacts()) {
            result += "\n" + contact.toStringDetailed();
        }
        return result;
    }

}
