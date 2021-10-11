package top.renhongchen;

public class Client {
    private String host;
    private int port;
    private DTO dto;
    private String message;

    ClientStub clientStub = new ClientStub();

    public Client(String host, int port, DTO dto) {
        this.host = host;
        this.port = port;
        this.dto = dto;
    }

    public Client(String host, int port, String message) {
        this.host = host;
        this.port = port;
        this.message = message;
    }

    public void send() throws InterruptedException{
        clientStub.send(host,port,dto);
    }

    public void list() throws InterruptedException {
        clientStub.list(host,port,message);
    }
}
