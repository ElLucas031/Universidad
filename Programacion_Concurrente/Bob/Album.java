package Bob;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Album {
    private double duracion =0;
    private double maxDuracion = 30;
    private boolean hayAlbum = false;
    private boolean albumLleno = false;

    private ReentrantLock lock = new ReentrantLock();
    private Condition productores = lock.newCondition();
    private Condition Manager = lock.newCondition();

    public void agregarCancion(double dur) throws InterruptedException{
        lock.lock();
        boolean agregada = false;
        try {
            while(!agregada){ //mientras la cancion no sea agregada, esto esta para no perder la cancion cuando no se puede agregar al alcanzar la duracion
                while(!hayAlbum || albumLleno){ //verifica que se pueda agregar una cancion, si el album esta lleno, no puede, si no hay album tampoco puede
                    productores.await();
                }
                if((duracion+dur)<=maxDuracion){ //si la duracion no exece el maximo
                    System.out.println("---------------------------------------------");
                    System.out.println("se agrego una cancion al album!! : "+dur);
                    this.duracion = duracion + dur;
                    agregada = true;
                }
                else{ // si lo excede
                    albumLleno = true;  //indica que el album esta lleno
                    Manager.signal();   // se lo notifica al manager
                    productores.await();  // se duerme, no puede agregarla
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    //se promociona el album
    public void promocionar(){
        lock.lock();
        try {
            while (!albumLleno) { //mientras el album no este lleno se va a dormir
                Manager.await();
            }
            System.out.println("---------------------------------------------");
            System.out.println("se esta promocionando el album");
            hayAlbum = false;  //"se lleva el album"
            Thread.sleep(4000); //promociona y piensa el nombre del siguiente album
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    //luego de la promocion o al inicio se crea el album nuevo
    public void crearAlbum() throws InterruptedException{
        lock.lock();
        try {
            System.out.println("---------------------------------------------");
            System.out.println("se creo un nuevo album");
            duracion =0;  //inicia la duracion en 0
            hayAlbum = true;   //dice que hay un album
            albumLleno = false;   //y que el album no esta lleno
            productores.signalAll();   //despierta a los productores para que añadan canciones
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
}
