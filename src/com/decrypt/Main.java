package com.decrypt;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {

        String texto = "Hola, soy un texto sin cifrar";
        SecretKey secretKey = Claus.keygenKeyGeneration(256);
        byte[] cifrado = Claus.encryptData(secretKey, texto.getBytes());
        byte[] descifrado = Claus.decryptData(secretKey, cifrado);

// Ejercicio 1.1, he encriptado la variable texto con el algoritmo AES
        System.out.println(cifrado);
        System.out.println(secretKey.getAlgorithm());
        System.out.println(secretKey.getEncoded());
        System.out.println(secretKey.getFormat());

// He desencriptado el texto previamente encriptado
        System.out.println(new String(descifrado));


//Ejercicio 1.2

        String textToPassword = "Texto con contraseña, uuuuuu";

        SecretKey skp = ClausPswd.passwordKeyGeneration("Xopowo", 256);

        byte[] cifradopswd = Claus.encryptData(skp, textToPassword.getBytes());
        byte[] descifradopswd = Claus.decryptData(skp, cifradopswd);

        System.out.println(new String(descifradopswd));



// Ejercicio 2

        Path textocifrado = Paths.get("/home/dam2a/textamagat");
        Path claves = Paths.get("/home/dam2a/claves.txt");


        byte[] textoenbytes = Files.readAllBytes(textocifrado);
        List<String> clausenstring = Files.readAllLines(claves);

        int i = 0;

        boolean correcto = false;

        while (!correcto){

            try {
                if(clausenstring.size() < i) return;
                SecretKey cp = ClausPswd.passwordKeyGeneration(clausenstring.get(i),128);
                ClausPswd.decryptData(cp,textoenbytes);
                correcto = true;
                System.out.println(clausenstring.get(i));
                System.out.println(new String(ClausPswd.decryptData(cp,textoenbytes)));

            }catch (Exception ex){
                System.out.println("Contraseña incorrecta");
                i++;
            }



        }







    }

}
