package com.xy.dp.template;

import java.util.concurrent.TimeUnit;

public class HeidelbergPostOffice extends PostOffice{
    private int port;
    private String address;

    public HeidelbergPostOffice(int port, String address) {
        this.port = port;
        this.address = address;
    }

    @Override
    boolean send(Letter letter) {
        if (letter == null || letter.getDstPort() < 1000 ||
                "".equals(letter.getDstAddress())) {
            System.out.println("<send> dest message not exists.");
            return false;
        }

        if (letter.getSrcPort() < 1000 ||
                "".equals(letter.getSrcAddress())) {
            System.out.println("<send> write source message.");
            letter.setSrcPort(port);
            letter.setSrcAddress(address);
        }

        System.out.println("<send> success.");
        return true;
    }

    @Override
    boolean pack(Letter letter) {
        boolean succ = Math.rint(100) % 2 == 0;
        if (!succ) {
            System.out.println("<pack> fail.");
            return false;
        }

        System.out.println("<pack> success.");
        return true;
    }

    @Override
    boolean trans(Letter letter) {
        System.out.println("<trans> letter trans by train to dest.");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("<trans> success.");
        return true;
    }

    @Override
    boolean recv(Letter letter) {
        System.out.println("<recv> letter got.");
        System.out.printf("<recv> letter content: %s.\n", new String(letter.getContent()));
        return true;
    }
}
