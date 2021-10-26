package com.acme.store.business;

import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

@ApplicationScoped
public class FileManager {

    public FileManager() {}
    
    public JsonArray loadArray(String fileName) {
        InputStream is = getClass().getResourceAsStream(fileName);
        JsonReader jsonReader = Json.createReader(is);
        return jsonReader.readArray();
    }
}
