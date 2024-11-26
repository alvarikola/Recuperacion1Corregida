/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recuperacion1corregida;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;



public class GestorAparcamiento {
    private final String[] plazas;
    private int tiempoAparcado;
    private final Random random = new Random();
    private int plazasLibres;

    
    public GestorAparcamiento(int numeroPlazas) {
        this.plazas = new String[numeroPlazas];
        this.tiempoAparcado = 1000 + random.nextInt(2000);
        for (int i = 0; i < plazas.length; i++) {
            plazas[i] = "Libre";
        }
        this.plazasLibres = numeroPlazas;
    }
      
    // Método sincronizado para intentar aparcar un coche
/*
    public synchronized int aparcar(String matricula) {
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == "Libre") {
                System.out.println("Aparcado en la plaza " + i);
                plazas[i] = matricula;
                System.out.printf(">>> El coche con matrícula %s entra al aparcamiento, ocupa la plaza %d y permanecera durante %d s. %s. \n", matricula, i, tiempoAparcado, Arrays.toString(plazas));
                return i;
            }
        }
        System.out.printf("--- El coche con matrícula %s debe esperar a que haya plazas de aparcamiento disponibles.\n", matricula);
        return -1;   
    }
*/
    public synchronized int aparcar(String matricula) {
        int plaza = 0;
        while (plazasLibres <= 0) {
            System.out.printf("--- El coche con matrícula %s debe esperar a que haya plazas de aparcamiento disponibles.\n", matricula);
            try {
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == "Libre") {
                //System.out.println("Aparcado en la plaza " + i);
                plazas[i] = matricula;
                plaza = i;
                plazasLibres--;
                System.out.printf(">>> El coche con matrícula %s entra al aparcamiento, ocupa la plaza %d y permanecera durante %d s. %s. \n", matricula, plaza, tiempoAparcado, Arrays.toString(plazas));
                break;
            }
        }
        return plaza;
        
    }

    // Método sincronizado para liberar una plaza
    public synchronized void liberar(int plaza, String matricula) {
        if (plaza >= 0 && plaza < plazas.length && plazas[plaza] != null) {
            plazas[plaza] = "Libre";
            plazasLibres++;
            System.out.printf("<<< El coche con matrícula %s sale del aparcamiento, deja libre la plaza %d.\n", matricula, plaza);
            notifyAll(); // Notificar a otros coches que una plaza está libre
        }
    }
}
