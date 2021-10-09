package top.renhongchen;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    private String host;
    private int port;
    private DTO dto;

    public Client(String host,int port,DTO dto) {
        this.host = host;
        this.port = port;
        this.dto = dto;
    }

    public void send() throws Exception {
        EventLoopGroup workgroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workgroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("encoder",new KryoEncoder());
                            socketChannel.pipeline().addLast("decoder",new KryoDecoder());
                            socketChannel.pipeline().addLast(new ClientHandler(dto));
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            workgroup.shutdownGracefully();
        }
    }
}
