/*
 * Copyright 2012 Faculty of Informatics - Masaryk University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.muni.fi.pa165.survive.rest.client.utils;

import com.muni.fi.pa165.enums.TerrainType;
import com.muni.fi.pa165.enums.WeaponClass;
import com.muni.fi.pa165.enums.WeaponType;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

/**
 * This class represent a way how we obtain built {@link org.apache.commons.cli.Options}.
 *
 * @author Michal
 */
public class OptionsProvider {

    private static Options options;

    private OptionsProvider() {
    }

    private static class OptionsProviderHolder {

        public static final OptionsProvider INSTANCE = new OptionsProvider();
    }

    public static OptionsProvider getInstance() {
        return OptionsProviderHolder.INSTANCE;
    }

    public Options getOptions() {
        if (options != null) {
            return OptionsProvider.options;
        }

        options = new Options();

        Option help = new Option("h", "Print help");

        Option operation = OptionBuilder.withArgName("operation")
                .hasArg().withDescription("operation, C, R, U, D, A")
                .create("o");

        Option id = OptionBuilder.withArgName("id")
                .hasArg()
                .withDescription("Entity ID")
                .create("i");

        Option name = OptionBuilder.withArgName("name")
                .hasArg()
                .withDescription("name of an entity of a choosen mode")
                .create("n");

        // WEAPON
        Option weapon = OptionBuilder.withDescription("Weapon Management").create("w");

        Option caliber = OptionBuilder.withArgName("caliber")
                .hasArg()
                .withDescription("Weapon ammo caliber (mm)")
                .create("m");
        Option range = OptionBuilder.withArgName("range")
                .hasArg()
                .withDescription("Weapon range in meters")
                .create("g");
        Option rounds = OptionBuilder.withArgName("rounds")
                .hasArg()
                .withDescription("Number of rounds a weapon holds")
                .create("r");
        Option weaponType = OptionBuilder.withArgName("Weapon Type")
                .hasArg()
                .withDescription("Weapon Type: " + WeaponType.getList())
                .create("t");

        Option weaponClass = OptionBuilder.withArgName("Weapon Class")
                .hasArg()
                .withDescription("Weapon Class: " + WeaponClass.getList())
                .create("c");

        //AREA
        Option area = OptionBuilder.withDescription("Area Management").create("a");

        Option description = OptionBuilder.withArgName("Description")
                .hasArg()
                .withDescription("Area description")
                .create("d");

        Option terrain = OptionBuilder.withArgName("Area Terrain")
                .hasArg()
                .withDescription("Terrain Type: " + TerrainType.getList())
                .create("q");

        options.addOption(help);
        options.addOption(weaponType);
        options.addOption(weaponClass);
        options.addOption(rounds);
        options.addOption(weapon);
        options.addOption(area);
        options.addOption(operation);
        options.addOption(id);
        options.addOption(name);
        options.addOption(caliber);
        options.addOption(range);
        options.addOption(description);
        options.addOption(terrain);

        return options;

    }
}
