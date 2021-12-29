package com.example.app_readbook.Class;

public class TachText {
    public String tach(String text){
        String st = null;
        String kt = " ";
        int max = 130;
        int kitucuoi = 0,kitu = 0;
        while (kitu <= max && kitu < text.length()){
            if(text.substring(kitu,kitu+1).equals(kt)){
                kitucuoi = kitu;
            }
            if(text.charAt(kitu) == '\n')
                max-=45;
            kitu++;
        }
        if(kitu != kitucuoi){
            return text.substring(0,kitucuoi)+"...";
        }
        return text.substring(0,kitu)+"...";
    }
}
