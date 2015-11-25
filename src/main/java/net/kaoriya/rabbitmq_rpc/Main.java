package net.kaoriya.rabbitmq_rpc;

public class Main {
    public static void main(String[] args) {
        if (args.length >= 1 && "-s".equals(args[0])) {
            Server.main(new String[0]);
            return;
        }
        Client.main(args);
    }
}
