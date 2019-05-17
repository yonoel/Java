package behaviorPattern.src;

public class TCPConnection {
    public TCPConnection() {
        state = TCPClosed.getInstance();
    }

    protected void changeState(TCPState state) {
        this.state = state;
    }

    private TCPState state;

    public void activeOpen() {
        state.activeOpen(this);
    }

    public void passiveOpen() {
        state.passiveOpen(this);
    }

    public void close() {
        state.close(this);
    }

    public void send() {
        state.send(this);
    }

    public void acknowledge() {
        state.acknowledge(this);
    }

    public void synchronize() {
        state.synchronize(this);
    }
}
