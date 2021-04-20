package com.muni.fi.pa165.enums;

/**
 * Represents different classes of Weapons.
 *
 * @author Aubrey Oosthuizen
 */
public enum WeaponClass {

    RANGED, MELEE;

    /**
     * Gives all possible weapon classes separated by comma.
     *
     * @return string with all values in WeaponClass
     */
    public static String getList() {
        StringBuilder builder = new StringBuilder();

        for (WeaponClass t : values()) {

            builder.append(t.name());
            builder.append(", ");

        }

        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }
}
