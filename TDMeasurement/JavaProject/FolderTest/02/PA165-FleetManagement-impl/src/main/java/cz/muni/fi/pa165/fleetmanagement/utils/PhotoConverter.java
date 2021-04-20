package cz.muni.fi.pa165.fleetmanagement.utils;

import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author Peter Pavlik (418125)
 * @version 1.0
 */
public class PhotoConverter {

    public Byte[] convertToByte(String path) {
        File file = new File(path);

        if (!file.exists()) {
            throw new IllegalArgumentException(path + " not exist");
        }

        byte[] byteFile = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(byteFile);
            fileInputStream.close();
        } catch (Exception e) {
            throw new ExceptionInInitializerError();
        }
        return convertByteToByte(byteFile);
    }

    private Byte[] convertByteToByte(byte[] myByte) {
        Byte[] newByte = new Byte[myByte.length];
        for (int i = 0; i < myByte.length; i++) {
            newByte[i] = Byte.valueOf(myByte[i]);
        }
        return newByte;
    }
}
