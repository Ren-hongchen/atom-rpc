package top.renhongchen;

public class Client {
    private String host;
    private int port;
    private DTO dto;

    ClientStub clientStub = new ClientStub();

    public Client(String host, int port, DTO dto) {
        this.host = host;
        this.port = port;
        this.dto = dto;
    }

    public void send() throws InterruptedException{
        clientStub.send(host,port,dto);
    }

    public static void main(String[] args) throws Exception {
        DTO message = new DTO();
        message.setOrder("list");
        Client client = new Client("127.0.0.1",8080,message);
        client.send();
    }
}
