package top.renhongchen;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class KryoEncoder extends MessageToByteEncoder<DTO> {

    @Override
    protected void encode(ChannelHandlerContext ctx, DTO dto, ByteBuf out) throws Exception{
        Serializer.encode(out,dto);
        ctx.flush();
    }
}
