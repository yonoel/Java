package threadSafe;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {
    private StatelessFactorizer instance = null;

    public StatelessFactorizer getInstance(){
        if (instance == null)
            instance = new StatelessFactorizer();
        return instance;
    }
}
