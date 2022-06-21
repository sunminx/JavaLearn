package com.xy.dp.template;

public abstract class PostOffice {

    public void  process(Letter letter) {
        boolean ok = true;
        if (ok)
            ok = send(letter);

        if (ok)
            ok = pack(letter);
        else
            System.out.println("[office] send pack failed, quit.");

        if (ok)
            ok = trans(letter);
        else
            System.out.println("[office] pack letter failed, quit.");
        if (ok)
            recv(letter);
        else
            System.out.println("[office] trans letter failed, quit.");
    }

    abstract boolean send(Letter letter);

    abstract boolean pack(Letter letter);

    abstract boolean trans(Letter letter);

    abstract boolean recv(Letter letter);
}
