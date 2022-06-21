package com.xy.dp.builder;

public class NetEquipment {
    private String name;
    private String ip;
    private String mask;
    private String mac;
    private Long RX;
    private Long TX;

    public NetEquipment() {

    }

    public NetEquipment(String name, String ip, String mask, String mac, Long RX, Long TX) {
        this.name = name;
        this.ip = ip;
        this.mask = mask;
        this.mac = mac;
        this.RX = RX;
        this.TX = TX;
    }

    public static NetEquipmentBuilder builder() {
        return new NetEquipmentBuilder();
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

    public String toString() {
        return String.format("%s:\n\t\tIp: %s; Mask: %s; Mac: %s\n\t\tRX: %d\n\t\tTX: %d\n",
                this.name, this.ip, this.mask, this.mac, this.RX, this.TX);
    }
}
