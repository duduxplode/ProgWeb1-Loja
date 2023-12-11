package br.ueg.loja.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Base64Converter{

    public static String toBase64(File arquivo) throws IOException {
        //    File f = new File("Image Location");
        FileInputStream fin = new FileInputStream(arquivo);
        byte imagebytearray[] = new byte[(int) arquivo.length()];
        fin.read(imagebytearray);
        String imagetobase64 = Base64.encodeBase64String(imagebytearray);
        return  imagetobase64;
    }
}

