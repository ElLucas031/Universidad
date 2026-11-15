package Bob;

public class Productor implements Runnable {
    private Mesa mesa;
    private Album album;
    private String genero;

    public Productor(Mesa unaMesa,Album unAlbum,String gen){
        this.mesa= unaMesa;
        this.album = unAlbum;
        this.genero = gen;
    }

    public void run(){
        double dur =0;
        while(true){
            try { //este try esta porque el metodo al tener un throws InterruptedException me pide controlar dicha excepcion aca
                dur = mesa.sacarCancion(genero);
                album.agregarCancion(dur);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
