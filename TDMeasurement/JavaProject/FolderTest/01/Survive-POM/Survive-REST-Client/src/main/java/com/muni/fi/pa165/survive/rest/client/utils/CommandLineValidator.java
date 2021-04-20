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
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;

/**
 * This class validates the command line. It checks if everything necessary in order to execute a request is present.
 *
 * @author Michal
 */
public class CommandLineValidator {

    /**
     * Validate a command line.
     *
     * @param line command line to validate
     * @return List<String> errors - the list of errors, empty if OK 
     */
    public static List<String> validate(CommandLine line) {

        List<String> errors = new ArrayList<>();

        // WEAPON
        if (line.hasOption("w")) {
            if (!line.hasOption("o")) {
                errors.add("Missing mandatory parameter '-o'.");
                return errors;
            }
            String operation = line.getOptionValue("o");
            switch (operation) {
                case "C":
                    //name
                    if (!line.hasOption("n")) {
                        errors.add("Missing mandatory parameter '-n' for the operation Create.");
                    } else {
                        if (line.getOptionValue("n").length() > 255) {
                            errors.add("The parameter '-n' must not be longer than 255 characters.");
                        }
                    }
                    //index
                    if (line.hasOption("i")) {
                        errors.add("Parameter '-i' must not be present for operation Create.");
                    }
                    //caliber
                    if (line.hasOption("m")) {
                        try {
                            double parseDouble = Double.parseDouble(line.getOptionValue("m"));

                            if (parseDouble < 0 || parseDouble > 50) {
                                errors.add("Parameter '-m' must be lying at an interval <0.0; 50.0>.");
                            }
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-m' must be of a type Double.");
                        }
                    }
                    //range
                    if (line.hasOption("g")) {
                        try {
                            int parseInt = Integer.parseInt(line.getOptionValue("g"));

                            if (parseInt < 0 || parseInt > 10000) {
                                errors.add("Parameter '-g' must be lying at an interval <0; 10000>.");
                            }
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-g' must be of a type Integer.");
                        }
                    }
                    //rounds
                    if (line.hasOption("r")) {
                        try {
                            int parseInt = Integer.parseInt(line.getOptionValue("r"));

                            if (parseInt < 0 || parseInt > 10000) {
                                errors.add("Parameter '-r' must be lying at an interval <0; 10000>.");
                            }

                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-r' must be of a type Integer.");
                        }
                    }
                    //type
                    if (line.hasOption("t")) {
                        try {
                            WeaponType.valueOf(line.getOptionValue("t").toUpperCase());
                        } catch (IllegalArgumentException e) {
                            errors.add("Parameter '-t' must be one of the following: " + WeaponType.getList());
                        }
                    }
                    //class
                    if (line.hasOption("c")) {
                        try {
                            WeaponClass.valueOf(line.getOptionValue("c").toUpperCase());
                        } catch (IllegalArgumentException e) {
                            errors.add("Parameter '-c' must be one of the following: " + WeaponClass.getList());
                        }
                    }
                    //desc
                    if (line.hasOption("d")) {
                        if (line.getOptionValue("d").length() > 255) {
                            errors.add("The parameter '-d' must not be longer than 255 characters.");
                        }
                    }

                    break;
                case "R":
                    if (!line.hasOption("i")) {
                        errors.add("Missing mandatory parameter '-i' for the operation Read.");
                    } else {
                        try {
                            Long.parseLong(line.getOptionValue("i"));
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-i' must be of a type Long.");
                        }
                    }

                    break;
                case "U":
                    if (!line.hasOption("i")) {
                        errors.add("Missing mandatory parameter '-i' for the operation Update.");
                    } else {
                        try {
                            Long.parseLong(line.getOptionValue("i"));
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-i' must be of a type Long.");
                        }
                    }
                    //name
                    if (line.hasOption("n")) {
                        if (line.getOptionValue("n").length() > 255) {
                            errors.add("The parameter '-n' must not be longer than 255 characters.");
                        }
                    }
                    //caliber
                    if (line.hasOption("m")) {
                        try {
                            double parseDouble = Double.parseDouble(line.getOptionValue("m"));

                            if (parseDouble < 0 || parseDouble > 50) {
                                errors.add("Parameter '-m' must be lying at an interval <0.0; 50.0>.");
                            }
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-m' must be of a type Double.");
                        }
                    }
                    //range
                    if (line.hasOption("g")) {
                        try {
                            int parseInt = Integer.parseInt(line.getOptionValue("g"));

                            if (parseInt < 0 || parseInt > 10000) {
                                errors.add("Parameter '-g' must be lying at an interval <0; 10000>.");
                            }
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-g' must be of a type Integer.");
                        }
                    }
                    //rounds
                    if (line.hasOption("r")) {
                        try {
                            int parseInt = Integer.parseInt(line.getOptionValue("r"));

                            if (parseInt < 0 || parseInt > 10000) {
                                errors.add("Parameter '-r' must be lying at an interval <0; 10000>.");
                            }

                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-r' must be of a type Integer.");
                        }
                    }
                    //type
                    if (line.hasOption("t")) {
                        try {
                            WeaponType.valueOf(line.getOptionValue("t").toUpperCase());
                        } catch (IllegalArgumentException e) {
                            errors.add("Parameter '-t' must be one of the following: " + WeaponType.getList());
                        }
                    }
                    //class
                    if (line.hasOption("c")) {
                        try {
                            WeaponClass.valueOf(line.getOptionValue("c").toUpperCase());
                        } catch (IllegalArgumentException e) {
                            errors.add("Parameter '-c' must be one of the following: " + WeaponClass.getList());
                        }
                    }
                    //desc
                    if (line.hasOption("d")) {
                        if (line.getOptionValue("d").length() > 255) {
                            errors.add("The parameter '-d' must not be longer than 255 characters.");
                        }
                    }

                    break;
                case "D":
                    if (!line.hasOption("i")) {
                        errors.add("Missing mandatory parameter '-i' for the operation Delete.");
                    } else {
                        try {
                            Long.parseLong(line.getOptionValue("i"));
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-i' must be of a type Long.");
                        }
                    }

                    break;
                case "A":
                    break;
                default:
                    // nothing like C, R, U, D, A 
                    errors.add("Detected unknown operation : " + operation + ". Valid operations: C, R, U, D, A.");
                    return errors;
            }
        }

        // AREA
        if (line.hasOption("a")) {
            if (!line.hasOption("o")) {
                errors.add("Missing mandatory parameter '-o'.");
                return errors;
            }
            String operation = line.getOptionValue("o");
            switch (operation) {
                case "C":
                    //name
                    if (!line.hasOption("n")) {
                        errors.add("Missing mandatory parameter '-n' for the operation Create.");
                    } else {
                        if (line.getOptionValue("n").length() > 255) {
                            errors.add("The parameter '-n' must not be longer than 255 characters.");
                        }
                    }
                    //index
                    if (line.hasOption("i")) {
                        errors.add("Parameter '-i' must not be present for operation Create.");
                    }
                    //desc
                    if (line.hasOption("d")) {
                        if (line.getOptionValue("d").length() > 255) {
                            errors.add("The parameter '-d' must not be longer than 255 characters.");
                        }
                    }
                    //terrain
                    if (line.hasOption("q")) {
                        try {
                            TerrainType.valueOf(line.getOptionValue("q").toUpperCase());
                        } catch (IllegalArgumentException e) {
                            errors.add("Parameter '-q' must be one of the following: " +  TerrainType.getList());
                        }

                    }
                    break;
                case "R":
                    if (!line.hasOption("i")) {
                        errors.add("Missing mandatory parameter '-i' for the operation Read.");
                    } else {
                        try {
                            Long.parseLong(line.getOptionValue("i"));
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-i' must be of a type Long.");
                        }
                    }
                    break;
                case "U":
                    if (!line.hasOption("i")) {
                        errors.add("Missing mandatory parameter '-i' for the operation Update.");
                    } else {
                        try {
                            Long.parseLong(line.getOptionValue("i"));
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-i' must be of a type Long.");
                        }
                    }
                    //name
                    if (line.hasOption("n")) {
                        if (line.getOptionValue("n").length() > 255) {
                            errors.add("The parameter '-n' must not be longer than 255 characters.");
                        }
                    }

                    //desc
                    if (line.hasOption("d")) {
                        if (line.getOptionValue("d").length() > 255) {
                            errors.add("The parameter '-d' must not be longer than 255 characters.");
                        }
                    }
                   //terrain
                    if (line.hasOption("q")) {
                        try {
                            TerrainType.valueOf(line.getOptionValue("q").toUpperCase());
                        } catch (IllegalArgumentException e) {
                            errors.add("Parameter '-q' must be one of the following: " +  TerrainType.getList());
                        }

                    }
                    break;
                case "D":
                    if (!line.hasOption("i")) {
                        errors.add("Missing mandatory parameter '-i' for the operation Delete.");
                    } else {
                        try {
                            Long.parseLong(line.getOptionValue("i"));
                        } catch (NumberFormatException ex) {
                            errors.add("Parameter '-i' must be of a type Long.");
                        }
                    }
                    break;
                case "A":
                    break;

                default:
                    // nothing like C, R, U, D, A 
                    errors.add("Detected unknown operation : " + operation + ". Valid operations: C, R, U, D, A.");
                    return errors;
            }
        }
        return errors;
    }
}
