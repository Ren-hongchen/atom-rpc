package top.renhongchen;

import java.util.ArrayList;
import java.util.List;

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
        message.setName("add");
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        message.setCall_parameters(list);
        Client client = new Client("127.0.0.1",8080,message);
        client.send();
    }
}
