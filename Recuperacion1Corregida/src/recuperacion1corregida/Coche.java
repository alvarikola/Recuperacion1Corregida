/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recuperacion1corregida;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Coche implements Runnable{
    private final GestorAparcamiento gestorAparcamiento;
    private final String matricula;
    private final int tiempoCircular;
    private final Random random = new Random();

    public Coche(GestorAparcamiento gestorAparcamiento, String matricula, int tiempoCircular) {
        this.gestorAparcamiento = gestorAparcamiento;
        this.matricula = matricula;
        this.tiempoCircular = tiempoCircular;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getTiempoCircular() {
        return tiempoCircular;
    }

    
    
    
    
    
    @Override
    public void run() {
        
        final int plaza = gestorAparcamiento.aparcar(matricula);
        
        // Aparcar
        //gestorAparcamiento.aparcar(matricula);

        /*J Tiempo aparcado. */
        try {
            Thread.sleep(2000+ new Random().nextInt(10000));
        } catch (InterruptedException e) {
        }
        
        // Devolverlos.
        gestorAparcamiento.liberar(plaza, matricula);
    
          
        
    }
}

