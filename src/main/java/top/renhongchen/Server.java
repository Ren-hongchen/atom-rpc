package top.renhongchen;

public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws Exception{
        ServerStub serverStub = new ServerStub();
        serverStub.run(port);
    }

    public static void main(String[] args) throws Exception {

        int port = 8080;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        Server server = new Server(port);
        server.run();
    }
}
