package Bob;

import java.util.concurrent.Semaphore;

public class Mesa {
    private Semaphore[] arrSem = new Semaphore[4];
    private double[] duraciones ={3,4,3,4};

    public Mesa(){
        arrSem[0] = new Semaphore(0); //blues
        arrSem[1] = new Semaphore(0); //rock
        arrSem[2] = new Semaphore(0); //country
        arrSem[3] = new Semaphore(0); //folk
    }

    public void ponerEnMesa(String cancion){
        switch (cancion) {
            case "blues":
                arrSem[0].release(); // añade la cancion a la lista de blues
                break;
            case "rock":
                arrSem[1].release(); // añade la cancion a la lista de rock
                break;
            case "country":
                arrSem[2].release(); // añade la cancion a la lista de country
                break;
            default:
                arrSem[3].release(); // añade la cancion a la lista de folk
                break;
        }
    }
    public double sacarCancion(String tipo) throws InterruptedException{
        double dur=-1;
        switch (tipo) {
            case "blues":
                arrSem[0].acquire(); // ve que haya almenos una de blues
                dur = duraciones[0];
                break;
            case "rock":
                arrSem[1].acquire(); // ve que haya almenos una de rock
                dur = duraciones[1];
                break;
            case "country":
                arrSem[2].acquire(); // ve que haya almenos una de country
                dur = duraciones[2];
                break;
            default:
                arrSem[3].acquire(); // ve que haya almenos una de folk
                dur = duraciones[3];
                break;
        }
        return dur;
    }
}
