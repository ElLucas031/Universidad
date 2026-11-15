package Bob;

public class main {
    public static void main (String[]args){
        Mesa mesa = new Mesa();
        Album album = new Album();
        Thread bob = new Thread(new BobDylan(mesa));
        bob.start();

        for(int i =0; i<3;i++){
            new Thread( new Productor(mesa, album, "blues")).start();;
        }
        for(int i =0; i<2;i++){
            new Thread( new Productor(mesa, album, "rock")).start();;
        }
        for(int i =0; i<4;i++){
            new Thread( new Productor(mesa, album, "country")).start();;
        }
        for(int i =0; i<1;i++){
            new Thread( new Productor(mesa, album, "folk")).start();;
        }

        new Thread(new Manager(album)).start();
    }
}
