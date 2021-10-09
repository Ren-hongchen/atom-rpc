package top.renhongchen;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.ByteArrayOutputStream;

public class Serializer {
    public static void encode(ByteBuf byteBuf,DTO dto) {
        Kryo kryo = new Kryo();
        kryo.register(DTO.class);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);

        kryo.writeClassAndObject(output,dto);

        output.flush();
        output.close();
    }

    public static Object decode(ByteBuf byteBuf) {
        if(byteBuf == null) {
            return null;
        }
        Kryo kryo = new Kryo();
        kryo.register(DTO.class);

        Input input = new Input(new ByteBufInputStream(byteBuf));
        return kryo.readClassAndObject(input);
    }
}
