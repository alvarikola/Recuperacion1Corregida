package recuperacion1corregida;

import java.util.Random;

public class Recuperacion1Corregida {

    
    public static void main(String[] args) {
        
        // CONSTANTES
        final int TIEMPO_MINIMO = 5;
        final int TIEMPO_MAXIMO = 30;
        final int NUMERO_PLAZAS = 5;       
        
        GestorAparcamiento gestorAparcamientos = new GestorAparcamiento(NUMERO_PLAZAS);
        
        String matricula = "";
        int tiempoCircular = 0;
        
        //Bucle infinito creando coches
        int numeroCoche = 0;
        while (true) {
            
            numeroCoche++;
            matricula = "LZ " + numeroCoche; 
            tiempoCircular = numeroAleatorio(TIEMPO_MINIMO, TIEMPO_MAXIMO);
            System.out.printf("*** El coche con matricula %s solicita aparcamiento despues de haber circulado %d ms. \n",
                        matricula, tiempoCircular);
            Thread hiloCoche = new Thread(new Coche(gestorAparcamientos, matricula, tiempoCircular));
            try {
                Thread.sleep(tiempoCircular * 100);
            } catch (InterruptedException e) {
            }
            hiloCoche.start();
        }

    }
    public static int numeroAleatorio(int valorMinimo, int valorMaximo) {
        Random r = new Random();
        return valorMinimo + r.nextInt(valorMaximo - valorMinimo + 1);
    }
}
    

