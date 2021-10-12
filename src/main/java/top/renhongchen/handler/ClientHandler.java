package top.renhongchen.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import top.renhongchen.DTO;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private DTO dto;

    public ClientHandler(DTO dto){
        this.dto = dto;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("Client send dto: " + dto);
        ctx.writeAndFlush(dto);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            DTO returnValue = (DTO) msg;
            System.out.println("Client received:" + returnValue);
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
