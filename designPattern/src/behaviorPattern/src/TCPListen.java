package behaviorPattern.src;

public class TCPListen implements TCPState {
    static TCPState getInstance() {
        return new TCPEstablished();
    }

    @Override
    public void transmit(TCPConnection connection, TCPStream stream) {

    }

    @Override
    public void activeOpen(TCPConnection connection) {

    }

    @Override
    public void passiveOpen(TCPConnection connection) {

    }

    @Override
    public void close(TCPConnection connection) {

    }

    @Override
    public void synchronize(TCPConnection connection) {

    }

    @Override
    public void send(TCPConnection connection) {

    }

    @Override
    public void acknowledge(TCPConnection connection) {

    }
}
