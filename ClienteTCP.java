package Tarea1_1;

import java.net.*;
import java.io.*;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe la dirección del servidor"); //Solicitamos la direccion del servidor
            String host = br1.readLine();
            System.out.println("\n\nEscribe el puerto: "); //Solicitamos el puerto del servidor
            int pto = Integer.parseInt(br1.readLine());
            Socket cl = new Socket(host, pto); // Crear un socket para conectarse al servidor usando la dirección y puerto proporcionados
            BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream())); //BufferedReader para leer mensajes desde el servidor
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()), true); //PrintWriter para enviar mensajes al servidor

            while (true) { 
                System.out.println("Cliente:"); // Solicita al usuario que ingrese un mensaje
                String mensaje = br1.readLine();
                pw.println(mensaje); // Enviamos el mensaje al servidor a través del PrintWriter

                if (mensaje.equalsIgnoreCase("salir")) { // Si el mensaje del cliente es "salir", salir del bucle
                    break;
                }

                String respuesta = br2.readLine(); // Leer la respuesta del servidor
                System.out.println("Servidor:\n" + respuesta); // Mostrar la respuesta del servidor en la consola del cliente

                if (respuesta.equalsIgnoreCase("salir")) { // Si la respuesta del servidor es "salir", cerrar conexiones y salir del bucle
                    br1.close();
                    br2.close();
                    pw.close();
                    cl.close();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
