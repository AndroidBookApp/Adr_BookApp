package com.example.app_readbook.listreabook;

import java.io.Serializable;

public class name implements Serializable {
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public name(String name) {
        this.name = name;
    }
}
