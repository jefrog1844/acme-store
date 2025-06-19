package com.acme.store.business;

import java.io.InputStream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

@ApplicationScoped
public class FileManager {

    public FileManager() {}
    
    public JsonArray loadArray(String fileName) {
        InputStream is = getClass().getResourceAsStream(fileName);
        JsonReader jsonReader = Json.createReader(is);
        return jsonReader.readArray();
    }
}
