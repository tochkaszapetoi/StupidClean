package org.tsz.iq_grower.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tochkaszapetoi on 2026-08-25
 * @project StupidClean
 */
@Config(name = "iq_grower")
public class ModConfig implements ConfigData {
    public boolean enable = true;

    public List<String> targets = createList();
    public String replacement = "";

    private List<String> createList() {
        List<String> strings = new ArrayList<>();
        strings.add("ILYADNEPR");
        return strings;
    }
}