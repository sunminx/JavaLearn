package com.xy.dp.builder;

public class MacBuilderOpt implements NetEquipmentBuilderOPt{
    private String mac;

    public MacBuilderOpt(String mac) {
        this.mac = mac;
    }

    @Override
    public void opt(NetEquipmentBuilder builder) {
        builder.setMac(mac);
    }
}
