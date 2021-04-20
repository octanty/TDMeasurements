/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165.survive.rest.client.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Auron
 */
public class BaseRestService {

    protected Client client = ClientBuilder.newClient();
    protected WebTarget webTarget = client.target("http://localhost:8080/pa165/rest");

    protected static final String HEADER_JSON = "application/json";
    protected static final String HEADER_XML = "application/XML";
    protected static final String HEADER_TEXT = "application/Plain";
}
