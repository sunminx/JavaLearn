package com.xy.dp.chain;

/**
 * 责任链设计模式
 * 每个处理handler串成一个处理链
 */
public class ChainTest {

    public static void main(String[] args) {
        // init chain
        GuarderHandler guarderHandler = new GuarderHandler(null);
        KeeperHandler keeperHandler = new KeeperHandler(guarderHandler);
        ManagerHandler managerHandler = new ManagerHandler(keeperHandler);
        LeaderHandler leaderHandler = new LeaderHandler(managerHandler);

        Context context = new Context("tom");
        leaderHandler.process(context);
    }
}
