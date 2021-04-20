/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165.enums;

/**
 *
 * @author Auron
 */
public enum UserStatus {
    DISABLED(0), ENABLED(1);
    
    private int value;

        private UserStatus(int value) {
                this.value = value;
        }
};
