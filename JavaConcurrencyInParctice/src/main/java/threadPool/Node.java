package threadPool;

import net.jcip.annotations.Immutable;

import java.util.LinkedList;
import java.util.List;

@Immutable
public class Node<P, M> {
    final P pos;
    final M move;
    final Node<P, M> prev;

    public Node(P pos, M move, Node<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }

    List<M> asMoveList() {
        LinkedList<M> linkedList = new LinkedList<>();
        for (Node<P, M> node = this; node.move != null; node = node.prev) {
            linkedList.add(0, node.move);
        }
        return linkedList;
    }
}
