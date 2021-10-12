package top.renhongchen.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import top.renhongchen.DTO;
import top.renhongchen.ServerMapper;

import java.util.List;
import java.util.Map;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            DTO request = (DTO) msg;
            System.out.println("Server received: " + request);
            ServerMapper serverMapper = new ServerMapper(request);
            if("list".equals(request.getOrder())) {
                List<Map<String,String>> methods = serverMapper.list();
                ChannelFuture channelFuture = ctx.writeAndFlush(methods);
                System.out.println("Server return: " + methods);
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            } else {
                Object returnValue = serverMapper.invoke(request);
                ChannelFuture channelFuture = ctx.writeAndFlush(returnValue);
                System.out.println("Server return: " + returnValue);
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
