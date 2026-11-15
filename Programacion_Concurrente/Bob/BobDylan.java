package Bob;
import java.util.Random;
import java.util.random.*;

public class BobDylan implements Runnable {
    private Mesa unaMesa;
    
    public BobDylan(Mesa mesa){
        this.unaMesa = mesa;
    }

    public String crearCancion(){
        String [] genero= {"blues","rock","country","folk"};

        Random random = new Random();
        int decision = random.nextInt(4);

        System.out.println("se creo una cancion de:"+genero[decision]);

        return genero[decision];
    }
    public void run(){
        while (true) {
            String unaCancion =crearCancion();
            unaMesa.ponerEnMesa(unaCancion);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
