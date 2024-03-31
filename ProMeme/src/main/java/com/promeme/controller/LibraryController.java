package com.promeme.controller;

import com.promeme.model.Library;
import com.promeme.view.LibraryView;

import java.io.File;
import java.util.ArrayList;

public class LibraryController {
    LibraryView libraryView;
    Library library;

    public LibraryController(LibraryView libraryView) {
        library = new Library();
        this.libraryView = libraryView;
    }

    public void getData(){
        File directory = new File("MemeImage");
        File[] files = directory.listFiles();
        for(File file : files){
            library.getImages().add(file);
        }
    }

    public LibraryView getLibraryView() {
        return libraryView;
    }

    public void setLibraryView(LibraryView libraryView) {
        this.libraryView = libraryView;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
