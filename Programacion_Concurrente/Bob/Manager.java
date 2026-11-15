package Bob;

public class Manager implements Runnable{
    private Album album;

    public Manager(Album unAlbum){
        this.album= unAlbum;
    }
    public void run(){
        while (true) {
            try { //este try esta porque el metodo al tener un throws InterruptedException me pide controlar dicha excepcion aca
                album.crearAlbum();
                album.promocionar();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
