/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kiran
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            int port = 8000;
            ServerSocket server = new ServerSocket(port);
            System.out.println("Connect to port 8000");
            while (true) {
                Socket client = server.accept();
                System.out.println("Get connection from:" + client.getInetAddress().getAddress().toString());
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String line = "";
                while (!(line = reader.readLine()).equalsIgnoreCase("quit")) {
                    // System.out.println(line);
                    PrintStream output = new PrintStream(client.getOutputStream());
                    if (line.equalsIgnoreCase("delete")) {
                        File f = new File("D:/text.txt");
                        f.delete();
                        output.print("File deleted");
                    } else if (line.equalsIgnoreCase("credit")) {
                        BufferedReader r = new BufferedReader(new FileReader(new File("D:/servertext.txt")));
                        String s = "";
                        while ((s = r.readLine()) != null) {
                            output.println(s);
                        }
                    } else {
                        output.println("File not deleted");
                    }

                    output.println(line);
                }

                client.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
