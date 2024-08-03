package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;
    private ObjectMapper objectMapper = new ObjectMapper();

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

    public String putId(String git, String newName) {
        //loop thru list find the correct id
        ArrayList<Id> allIdsList = idCtrl.getIds();
        Id idWeNeed = null;
        for (Id mid : allIdsList){
            if (mid.getGithub().equals(git)){
                System.out.println("I found it!");
                idWeNeed = mid;
            }
        }
        //set the name to a new name
//        idWeNeed.setName(newName);
        idCtrl.putId(idWeNeed,newName);

        //sout the name change
        System.out.println("Changed the name to: " + newName + " -> for Github handle: " + git);
        //return the id name
        return newName;
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
