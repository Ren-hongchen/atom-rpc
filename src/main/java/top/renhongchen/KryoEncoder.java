package top.renhongchen;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class KryoEncoder extends MessageToByteEncoder<DTO> {
    private Serializer serializer = new Serializer();


    @Override
    protected void encode(ChannelHandlerContext ctx, DTO dto, ByteBuf out) throws Exception{
        byte[] bytes = serializer.encode(dto);
        int datalength = bytes.length;
        out.writeInt(datalength);
        out.writeBytes(bytes);
    }
}
