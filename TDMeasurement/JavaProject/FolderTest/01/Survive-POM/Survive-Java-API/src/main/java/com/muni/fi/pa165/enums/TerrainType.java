package com.muni.fi.pa165.enums;

/**
 * Represents different types of terrain.
 *
 * @author Aubrey Oosthuizen
 */
public enum TerrainType {

    OCEANIC, DESERT, SNOW, JUNGLE, SAVANNA, MOUNTAIN;

    /**
     * Gives all possible terrain types separated by comma.
     *
     * @return string with all values in TerrainType
     */
    public static String getList() {
        StringBuilder builder = new StringBuilder();

        for (TerrainType t : values()) {

            builder.append(t.name());
            builder.append(", ");

        }

        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }
}
