package com.muni.fi.pa165.enums;

/**
 * Represents different classes of Monsters.
 *
 * @author Aubrey Oosthuizen
 */
public enum MonsterClass {

    ZOMBIE, ROBOT, MUTANT;

    /**
     * Gives all possible monster classes separated by comma.
     *
     * @return string with all values in MonsterClass
     */
    public static String getList() {
        StringBuilder builder = new StringBuilder();

        for (MonsterClass t : values()) {

            builder.append(t.name());
            builder.append(", ");

        }

        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }
}
