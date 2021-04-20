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

import com.muni.fi.pa165.dto.AreaDto;
import com.muni.fi.pa165.dto.WeaponDto;
import com.muni.fi.pa165.enums.TerrainType;
import com.muni.fi.pa165.enums.WeaponClass;
import com.muni.fi.pa165.enums.WeaponType;
import org.apache.commons.cli.CommandLine;

/**
 * This class is providing an easy way how to get DTO object from the values we entered on the command line.
 *
 * @author Michal
 */
public class DtoBuilder {

    public static WeaponDto getWeaponDto(CommandLine line) {
        WeaponDto dto = new WeaponDto();
        //name
        if (line.hasOption("n")) {
            dto.setName(line.getOptionValue("n"));
        }
        //caliber
        if (line.hasOption("m")) {
            dto.setCaliber(new Double(line.getOptionValue("m")));
        }
        //range
        if (line.hasOption("g")) {
            dto.setRange(new Integer(line.getOptionValue("g")));
        }
        //rounds
        if (line.hasOption("r")) {
            dto.setRounds(new Integer(line.getOptionValue("r")));
        }
        //index
        if (line.hasOption("i")) {
            dto.setId(new Long(line.getOptionValue("i")));
        }
        //type
        if (line.hasOption("t")) {
            dto.setWeaponType(WeaponType.valueOf(line.getOptionValue("t").toUpperCase()));
        }
        //class
        if (line.hasOption("c")) {
            dto.setWeaponClass(WeaponClass.valueOf(line.getOptionValue("c").toUpperCase()));
        }
         //desc
        if (line.hasOption("d")) {
            dto.setDescription(line.getOptionValue("d"));
        }
        return dto;

    }

    public static AreaDto getAreaDto(CommandLine line) {
        AreaDto dto = new AreaDto();
        if (line.hasOption("n")) {
            dto.setName(line.getOptionValue("n"));
        }
        if (line.hasOption("d")) {
            dto.setDescription(line.getOptionValue("d"));
        }
        if (line.hasOption("q")) {
            dto.setTerrain(TerrainType.valueOf(line.getOptionValue("q").toUpperCase()));
        }
        if (line.hasOption("i")) {
            dto.setId(new Long(line.getOptionValue("i")));
        }
        return dto;

    }
}
