package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    List<String> response = new ArrayList<>();
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                        response.add(string);
                    }
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (response.stream().filter(e -> e.contains("?msg=Hello ")).count() == 1) {
                        output.write("Hello".getBytes());
                    } else if (response.stream().filter(e -> e.contains("?msg=Exit")).count() == 1) {
                        output.write("Завершить работу сервера".getBytes());
                        server.close();
                    } else if (response.stream().filter(e -> e.contains("?msg=Any")).count() == 1) {
                        output.write("What".getBytes());
                    }
                    output.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}