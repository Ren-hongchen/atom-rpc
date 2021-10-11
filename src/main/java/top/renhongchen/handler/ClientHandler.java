package top.renhongchen.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import top.renhongchen.DTO;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private DTO dto;
    private String message;

    public ClientHandler(DTO dto){
        this.dto = dto;
    }

    public ClientHandler(String message) { this.message = message; }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.printf("Client send dto: " + dto);
        System.out.printf("Client send message: " + message);
        ctx.writeAndFlush(dto);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            String returnValue = (String) msg;
            System.out.printf("Client received:" + returnValue);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
