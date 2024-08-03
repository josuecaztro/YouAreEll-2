package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.util.JSONPObject;
import models.Id;

public class IdController {
    ServerController sc;
    private static ObjectMapper objectMapper = new ObjectMapper(); //I added this - JC

    private HashMap<String, Id> allIds;

    Id myId;

    public IdController(ServerController shared) {
        sc = shared;
        allIds = new HashMap<String, Id>();
    }

    public ArrayList<Id> getIds() {
        String jsonInput = sc.getIds();
        // convert json to array of Ids
        ObjectMapper mapper = new ObjectMapper();
        List<Id> ids;
        try {
            ids = mapper.readValue(jsonInput, mapper.getTypeFactory().constructCollectionType(List.class, Id.class));

            ArrayList<Id> idList = new ArrayList<>(ids);
            // return array of Ids
            return idList;
        } catch (JsonMappingException e) {
            System.out.println("Error processing JSON from response: " + e.getMessage());
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON from response: " + e.getMessage());
        }
        return null;
    }
//
//    public Id postId(Id id) {
//
////         create json from id
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonId = null;
//        try {
//            jsonId = mapper.writeValueAsString(id);
//        } catch (JsonProcessingException e) {
//            //this is for catching any errors
//            System.out.println("Error processing JSON from response: " + e.getMessage());
////            e.printStackTrace();
//        }
//
//        // call server, get json result Or error
//        sc.sendRequest("/ids", "POST", jsonId);
//
//        // result json to Id obj
//        return null;
//    }

    public Id postId(Id id) {
        try {
            return objectMapper.readValue(sc.sendRequest("/ids", "POST", objectMapper.writeValueAsString(id)), Id.class);
        } catch (JsonProcessingException exception) {
            System.out.println("Id is invalid");
            return null;
        }
    }


    public Id putId(Id id, String update) {
        try {
            id.setName(update);
            System.out.println("You are in the put method on ID controller.");
            sc.sendRequest("/ids","PUT",objectMapper.writeValueAsString(id));
            return id;
//            return objectMapper.readValue(sc.sendRequest("/ids", "PUT", objectMapper.writeValueAsString(id)), Id.class);
        } catch (JsonProcessingException exception) {
            System.out.println("Id is invalid");
            return null;
        }
    }
 
}