package com.xy.dp.template;

public class Letter {
    private int srcPort;
    private String srcAddress;
    private int dstPort;
    private String dstAddress;
    private byte[] content;

    public Letter(byte[] content, int port, String address) {
        this(0, null, port, address, content);
    }

    public Letter(int srcPort, String srcAddress, int dstPort, String dstAddress, byte[] content) {
        this.srcPort = srcPort;
        this.srcAddress = srcAddress;
        this.dstPort = dstPort;
        this.dstAddress = dstAddress;
        this.content = content;
    }

    public int getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(int srcPort) {
        this.srcPort = srcPort;
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public void setSrcAddress(String srcAddress) {
        this.srcAddress = srcAddress;
    }

    public int getDstPort() {
        return dstPort;
    }

    public void setDstPort(int dstPort) {
        this.dstPort = dstPort;
    }

    public String getDstAddress() {
        return dstAddress;
    }

    public void setDstAddress(String dstAddress) {
        this.dstAddress = dstAddress;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
