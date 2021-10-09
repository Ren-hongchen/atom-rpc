package top.renhongchen;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.HashMap;
import java.util.Map;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            DTO dto = new DTO();
            dto.setName("test");
            Map<Integer,Object> map = new HashMap<>();
            String answer = "测试结果";
            map.put(1,answer);
            dto.setParameters(map);

            System.out.printf("server return msg" + dto);
            ChannelFuture channelFuture = ctx.writeAndFlush(dto);
            channelFuture.addListener(ChannelFutureListener.CLOSE);
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
