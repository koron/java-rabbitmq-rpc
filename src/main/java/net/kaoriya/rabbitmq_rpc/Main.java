package net.kaoriya.rabbitmq_rpc;

public class Main {
    public static final String HOST = "127.0.0.1";
    public static final String SERVER_QUEUE = "server-queue";
    public static final String DIRECT_QUEUE = "amq.rabbitmq.reply-to";

    public static void main(String[] args) throws Exception {
        if (args.length >= 1 && "-s".equals(args[0])) {
            Server.main(new String[0]);
            return;
        }
        Client.main(args);
    }
}
