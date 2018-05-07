package de.demmer.dennis.lyricswebapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class ModifiedObjectInputStream extends ObjectInputStream {

    public ModifiedObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass resultClassDescriptor = super.readClassDescriptor();

            if (resultClassDescriptor.getName().equals("de.demmer.dennis.Song"))
                resultClassDescriptor = ObjectStreamClass.lookup(de.demmer.dennis.lyricswebapp.Song.class);

        return resultClassDescriptor;


    }


}


