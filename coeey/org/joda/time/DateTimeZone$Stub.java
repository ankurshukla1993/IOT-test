package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

final class DateTimeZone$Stub implements Serializable {
    private static final long serialVersionUID = -6471952376487863581L;
    private transient String iID;

    DateTimeZone$Stub(String str) {
        this.iID = str;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeUTF(this.iID);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        this.iID = objectInputStream.readUTF();
    }

    private Object readResolve() throws ObjectStreamException {
        return DateTimeZone.forID(this.iID);
    }
}
