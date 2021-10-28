package com.example.app_readbook.readbook;

import com.example.app_readbook.chapter.NameChapter;

import java.io.Serializable;
import java.util.ArrayList;

public class ReadbookName implements Serializable {
    private String name ;
    ArrayList<NameChapter> nameChapters;

    public ArrayList<NameChapter> getNameChapters() {
        return nameChapters;
    }

    public void setNameChapters(ArrayList<NameChapter> nameChapters) {
        this.nameChapters = nameChapters;
    }

    public ReadbookName(String name) {
        this.name = name;
        this.nameChapters = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
