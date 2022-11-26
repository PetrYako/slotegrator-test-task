package org.slotegrator.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ToStringImpl {

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
