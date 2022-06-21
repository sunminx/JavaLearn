package com.xy.dp.builder;

public class IPBuilderOpt implements NetEquipmentBuilderOPt{
    private String ip;

    public IPBuilderOpt(String ip) {
        this.ip = ip;
    }

    @Override
    public void opt(NetEquipmentBuilder builder) {
        builder.setIp(ip);
    }
}
