package top.renhongchen.serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class KryoDecoder extends ByteToMessageDecoder {
    private static final int HEAD_LENGTH = 4;
    private Serializer serializer = new Serializer();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if(in.readableBytes() < HEAD_LENGTH) {
            return;
        }

        in.markReaderIndex();

        int dataLength = in.readInt();
        if(dataLength <= 0){
            ctx.close();
        }
        if(in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[dataLength];
        in.readBytes(bytes);
        Object obj = serializer.decode(bytes);
        out.add(obj);
    }
}
