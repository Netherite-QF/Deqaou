package com.netheriteqf.deqaou.api.element;

import net.minecraft.nbt.NbtCompound;

public class ElementManager {
    public int angrium;
    public int sadantium;
    public int hapoundium;
    public int pitockium;

    public void readNbt(NbtCompound nbt) {
        angrium = nbt.getInt("deqaou.angrium_amount");
        sadantium = nbt.getInt("deqaou.sadantium_amount");
        hapoundium = nbt.getInt("deqaou.hapoundium_amount");
        pitockium = nbt.getInt("deqaou.pitockium_amount");
    }

    public void  writeNbt(NbtCompound nbt) {
        nbt.putInt("deqaou.angrium_amount", angrium);
        nbt.putInt("deqaou.sadantium_amount", sadantium);
        nbt.putInt("deqaou.hapoundium_amount", hapoundium);
        nbt.putInt("deqaou.pitockium_amount", pitockium);
    }
}
