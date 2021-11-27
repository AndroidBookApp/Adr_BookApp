package com.example.app_readbook.View.readbook;

import java.io.Serializable;

public class ReadbookName implements Serializable {
    private String name ;

    public ReadbookName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
