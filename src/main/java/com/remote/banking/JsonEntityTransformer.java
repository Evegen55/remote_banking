package com.remote.banking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonEntityTransformer {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     *
     * @param o
     * @return
     */
    public static String toJson(Object o) {
        return GSON.toJson(o);
    }

}
