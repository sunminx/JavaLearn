package com.xy.dp.builder;

public class MaskBuilderOpt implements NetEquipmentBuilderOPt{
    private String mask;

    public MaskBuilderOpt(String mask) {
        this.mask = mask;
    }

    @Override
    public void opt(NetEquipmentBuilder builder) {
        builder.setMask(mask);
    }
}
