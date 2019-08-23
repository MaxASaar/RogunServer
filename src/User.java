import java.nio.ByteBuffer;

public class User {
    int id;
    String name;

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public User getUserFromBytes(byte[] bytes){
        ByteBuffer wrapped = ByteBuffer.wrap(bytes); // big-endian by default
        int id = wrapped.getInt(); // 1
        String name = wrapped.toString();

        return new User(id, name);
    }
}
