package Tarea1_1;

import java.net.*;
import java.io.*;

public class ServidorTCP {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(1234); //Creamos un socket de servidor que escuche en el puerto 1234
            System.out.println("Esperando Cliente..."); //Esperamos a que un cliente se conecte
            BufferedReader brRespuesta = new BufferedReader(new InputStreamReader(System.in)); //Este BufferedReader nos ayudara a leer las respuestas desde la consola del servidor
            while (true) {
                Socket cl = s.accept(); //Aceptamos la conexion de un cliente
                System.out.println("Conexión establecida desde " + cl.getInetAddress() + ":" + cl.getPort());

                BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream())); // BufferedReader para leer mensajes desde el cliente
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()), true); // PrintWriter para enviar respuestas al cliente
                String mensaje;
                do { //Este bucle se ejecutará al menos una vez y seguirá ejecutándose mientras el mensaje del cliente no sea "salir"
                    mensaje = br.readLine(); // Leer un mensaje desde el cliente
                    System.out.println("Cliente:\n" + mensaje); //Imprimira en consola el mensaje que envio el cliente

                    if (!mensaje.equalsIgnoreCase("salir")) { //Mientas mensaje sea diferente a salir
                        System.out.println("Servidor:");                        
                        String respuesta = brRespuesta.readLine(); // Leer una respuesta desde la consola del servidor
                        pw.println(respuesta);

                        if (respuesta.equalsIgnoreCase("salir")) {// Si la respuesta es "salir", cerrar la conexión
                            br.close();
                            pw.close();
                            cl.close();
                            break;
                        }
                    }
                } while (!mensaje.equalsIgnoreCase("salir"));//El ciclo se cierra cuando mensaje es salir
                System.out.println("Esperando Cliente..."); //Una vez cerrado el socket del cliente nuestro socket servidor se vuelve a poner en espera.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
