package server;

public class ServerDatabase implements Database {
    //***************************************************
    //---------------------ATTRIBUTES--------------------
    //***************************************************
    private List<Peer> peers;
    private List<Player> players;
    private Map<Peer, Player> users;
    private List<Game> games;

    //***************************************************
    //---------------------CONSTRUCTORS------------------
    //***************************************************
    public ServerDatabase() {
        this.peers = new ArrayList<Peer>();
        this.players = new ArrayList<Player>();
        this.users = new HashMap<Peer, Player>();
        this.games = new ArrayList<Game>();
    }

    //***************************************************
    //---------------------QUERIES-----------------------
    //***************************************************
    public synchronized Player getPlayer(Peer peer) {
        return this.users.get(peer);
    }

    public synchronized void insertGame(Game game) {
        this.games.add(game);
    }

    public synchronized void removeGame(Game game) {
        this.games.remove(game);
    }

    public synchronized void insertPeer(Peer peer) {
        this.peers.add(peer);
    }

    public synchronized void removePeer(Peer peer) {
        this.peers.remove(peer);
    }

    public synchronized void insertUser(Peer peer, Player player) throws PeerNotFoundException {
        if (!this.peers.contains(peer)) {
            throw new PeerNotFoundException("Peer not found!");
        }
        this.players.add(player);
        this.users.put(peer, player);
    }

    public synchronized void removeUser(Peer peer) {
        this.users.remove(peer);
    }

    public synchronized List<Player> getIdlePlayers() {
        List<Player> idlePlayers = new ArrayList<Player>();

        for (Player player : this.players) {
            if (player.getGame() == null && player.getNickname() != null) {
                idlePlayers.add(player);
            }
        }
        return idlePlayers;
    }

    //***************************************************
    //---------------------GETTERS/SETTERS---------------
    //***************************************************
    public synchronized List<Peer> getPeers() {
        return peers;
    }

    public synchronized void setPeers(List<Peer> peers) {
        this.peers = peers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


