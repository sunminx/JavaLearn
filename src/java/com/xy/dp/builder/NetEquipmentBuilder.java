package com.xy.dp.builder;

import org.springframework.util.StringUtils;

public class NetEquipmentBuilder {
    private String name;
    private String ip;
    private String mask;
    private String mac;
    private Long RX;
    private Long TX;

    public NetEquipmentBuilder() {

    }

    public NetEquipmentBuilder(String name) {
        this.name = name;
    }

    public NetEquipment build(String name, NetEquipmentBuilderOPt[] opts) {
        if (StringUtils.isEmpty(name))
            return null;
        this.setName(name);

        // default val;
        this.setRX(65535L);
        this.setTX(65535L);

        // run opt
        for (NetEquipmentBuilderOPt opt : opts) {
            opt.opt(this);
        }

        return new NetEquipment(this.name, this.ip,
                                this.mask, this.mac,
                                this.RX, this.TX);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Long getRX() {
        return RX;
    }

    public void setRX(Long RX) {
        this.RX = RX;
    }

    public Long getTX() {
        return TX;
    }

    public void setTX(Long TX) {
        this.TX = TX;
    }
}
