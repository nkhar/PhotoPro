package com.droiddwarf.photomodifix.data;

import java.io.Serializable;

public class PhotoHolder implements Serializable {

    public Photo[] photos;

    public PhotoHolder() {
    }

    public PhotoHolder(Photo[] photos) {
        this.photos = photos;
    }

}
