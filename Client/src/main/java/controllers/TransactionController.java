package controllers;

import models.Id;
import models.Message;

import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {
        msgCtrl = m;
        idCtrl = j;
    }

    public List<Id> getIds() {
        return idCtrl.getIds();
    }

    public String getId(String idToGet) {
        ArrayList<Id> allIdsList = idCtrl.getIds();
        for (Id mid : allIdsList){
            if (mid.getGithub().equals(idToGet)){
                System.out.println("I found it! Name: " + mid.getName() + " and GitHub: " + mid.getGithub() );
                return mid.getName();
            }
        }
        return "No id found by that github user. Sorry!";
    }

    public String putId(String id) {
        return null;
    }

    public String deleteId(String id) {
        return null;
    }

    public String postId(String idToRegister, String name, String githubName) {
         Id tid = new Id(idToRegister, name, githubName);
         idCtrl.postId(tid);
         return ("Id registered.");
//        return null;
    }

    public List<Message> getMessages() {
        return msgCtrl.getMessages();
    }
}
