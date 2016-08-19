package d1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

class Player {
}

/**
 * trying to reproduce ConcurrentModificationException
 * */
public class Tournament {
    private List<Player> players = new LinkedList<>();

    private synchronized void addPlayer(Player p) {
        players.add(p);
    }

    private synchronized Iterator<Player> getPlayerIterator() {
        return players.iterator();
    }

    public static void main(String[] args) throws InterruptedException {
        Tournament tournament = new Tournament();
        tournament.addPlayer(new Player());
        tournament.addPlayer(new Player());
        tournament.addPlayer(new Player());

        Thread t2 = new Thread(() -> IntStream.range(1, 10).forEach((i) -> tournament.addPlayer(new Player())));

        Thread t1 = new Thread(() -> {
            Iterator<Player> it = tournament.getPlayerIterator();
            while (it.hasNext()) System.out.println(it.next());
        });

        t2.start();
        t1.start();
    }
}
