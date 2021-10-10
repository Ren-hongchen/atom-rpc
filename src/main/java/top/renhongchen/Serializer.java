package top.renhongchen;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class Serializer {

    final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.register(HashMap.class);
            kryo.register(DTO.class);
            return kryo;
        }
    };

    private Kryo getKryo() {
        return kryoLocal.get();
    }



    public byte[] encode(Object dto) {
        Kryo kryo = getKryo();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);

        kryo.writeClassAndObject(output,dto);

        output.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public Object decode(byte[] bytes) {
        if(bytes == null) {
            return null;
        }
        Kryo kryo = getKryo();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        return kryo.readClassAndObject(input);
    }
}