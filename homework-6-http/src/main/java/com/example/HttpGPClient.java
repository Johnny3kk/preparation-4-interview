package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpGPClient {

    private static class Response {

        private String httpVersion;
        private int statusCode;
        private int contentLength;

        public Response(InputStream inputStream, boolean debug) throws IOException {
            String line;

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                line = reader.readLine();
                if (debug) {
                    System.out.println(line);
                }
                parseStatusLine(line);

                do {
                    line = reader.readLine();
                    parseHeader(line);
                    if (debug) {
                        System.out.println(line);
                    }
                    if (line.isEmpty()) {
                        break;
                    }
                } while (line != null);

                do {
                    line = reader.readLine();
                    if (debug) {
                        System.out.println(line);
                    }
                } while (line != null);
            }
        }

        private void parseStatusLine(String line) {
            String[] tokens = line.split("\\s+");
            httpVersion = tokens[0];
            statusCode = Integer.parseInt(tokens[1]);
        }

        private void parseHeader(String line) {
            if (line.startsWith("Content-Length")) {
                contentLength = Integer.parseInt(line.split("\\s+")[1]);
            }
        }

        public static void sendRequest(String host, int port, String method) throws IOException {
            try (Socket socket = new Socket("localhost", 8189)) {
                StringBuilder output = new StringBuilder();
                output.append("POST /hello HTTP/1.1").append("\r\n");
                output.append("Host: ").append("localhost:8189").append("\r\n");
                output.append("Accept: ").append("text/plain;charset=UTF-8").append("\r\n");
                output.append("Connection: ").append("close").append("\r\n");
                output.append("Content-Type: ").append("text/plain;charset=UTF-8").append("\r\n");
                output.append("\r\n");
                socket.getOutputStream().write(output.toString().getBytes("UTF-8"));
                socket.getOutputStream().flush();

                Response response = new Response(socket.getInputStream(), true);
            }
        }

        public static void main(String[] args) throws IOException {
            sendRequest("http://localhost/homework-6-http", 8189, "POST");
        }
    }
}
