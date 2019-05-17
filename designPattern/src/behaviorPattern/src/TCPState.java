package behaviorPattern.src;

public interface TCPState {

    void transmit(TCPConnection connection, TCPStream stream);

    void activeOpen(TCPConnection connection);

    void passiveOpen(TCPConnection connection);

    default void close(TCPConnection connection) {
        connection.changeState(TCPClosed.getInstance());
    }

    void synchronize(TCPConnection connection);

    void send(TCPConnection connection);

    void acknowledge(TCPConnection connection);

    default void changeState(TCPConnection connection, TCPState state) {
        connection.changeState(state);
    }

}
