package com.promeme.model;

import java.io.File;
import java.util.ArrayList;

public class Library {
    private ArrayList<File> images;

    public Library(){
        images = new ArrayList<File>();
    }

    public ArrayList<File> getImages() {
        return images;
    }

    public void setImages(ArrayList<File> images) {
        this.images = images;
    }
}
