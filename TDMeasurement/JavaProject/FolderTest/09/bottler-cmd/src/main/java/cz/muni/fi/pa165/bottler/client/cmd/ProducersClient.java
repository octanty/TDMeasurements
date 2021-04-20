package cz.muni.fi.pa165.bottler.client.cmd;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpStatus;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
class ProducersClient {

    private ObjectMapper mapper;
    private Client client;
    private WebTarget webTarget;
    private static ResourceBundle rb;
    private String serverUri;


    public ProducersClient(String serverUri) {
        
        this.serverUri = serverUri;

        rb = ResourceBundle.getBundle("strings");
        mapper = new ObjectMapper();

        client = ClientBuilder.newClient();

        String resourceUri = serverUri + "/pa165/rest/producers";
        webTarget = client.target(resourceUri);
    }

    public void addProducers(String filePath) {
        try {
            List<ProducerDto> producers = mapper.readValue(
                    new File(filePath), new TypeReference<List<ProducerDto>>() {
            });
            for (ProducerDto producer : producers) {
                addProducer(producer);
            }

        } catch (JsonParseException ex) {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.json_parse"), new Object[]{filePath}));
        } catch (JsonMappingException ex) {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.json_mapping"), new Object[]{filePath}));
        } catch (IOException ex) {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.ioexception"), new Object[]{filePath}));
        }

    }

    public void deleteProducers(String filePath) {
        try {
            List<ProducerDto> producers = mapper.readValue(
                    new File(filePath), new TypeReference<List<ProducerDto>>() {
            });
            for (ProducerDto producer : producers) {
                deleteProducer(producer);
            }
        } catch (JsonParseException ex) {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.json_parse"), new Object[]{filePath}));
        } catch (JsonMappingException ex) {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.json_mapping"), new Object[]{filePath}));
        } catch (IOException ex) {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.ioexception"), new Object[]{filePath}));
        }

    }

    public void updateProducers(String filePath) {
        try {
            List<ProducerDto> producers = mapper.readValue(
                    new File(filePath), new TypeReference<List<ProducerDto>>() {
            });
            for (ProducerDto producer : producers) {
                updateProducer(producer);
            }

        } catch (JsonParseException ex) {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.json_parse"), new Object[]{filePath}));
        } catch (JsonMappingException ex) {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.json_mapping"), new Object[]{filePath}));
        } catch (IOException ex) {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.ioexception"), new Object[]{filePath}));
        }

    }

    public void addProducer(ProducerDto producer) {
        WebTarget resourceWebTarget = webTarget.path("");

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        String producerJson;
        try {
            producerJson = mapper.writeValueAsString(producer);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Entity<ProducerDto> entity = Entity.entity(producer, MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(entity);
        if (response.getStatus() == HttpStatus.SC_OK) {
            System.out.println(rb.getString("created")
                    + ": \n" + response.readEntity(String.class));
        } else if (response.getStatus() == HttpStatus.SC_BAD_REQUEST) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.bad_request"));
        } else if (response.getStatus() == HttpStatus.SC_NOT_FOUND) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.not_available"));
        } else {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.unable_to_connect"), new Object[]{serverUri}));
        }

    }

    public void deleteProducer(ProducerDto producer) {
        WebTarget resourceWebTarget = webTarget.path("" + producer.getId());

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        Response response = invocationBuilder.delete();
        if (response.getStatus() == HttpStatus.SC_OK) {
            System.out.println(MessageFormat.format(rb.getString("producer.deleted"), producer.getId()));
        } else if (response.getStatus() == HttpStatus.SC_BAD_REQUEST) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.bad_request"));
        } else if (response.getStatus() == HttpStatus.SC_NOT_FOUND) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.not_available"));
        } else if (response.getStatus() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.internal_server_error"));
        } else {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.unable_to_connect"), new Object[]{serverUri}));
        }

    }

    public void updateProducer(ProducerDto producer) {
        WebTarget resourceWebTarget = webTarget.path("" + producer.getId());

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        String producerJson;
        try {
            producerJson = mapper.writeValueAsString(producer);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Entity<ProducerDto> entity = Entity.entity(producer, MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.put(entity);
        if (response.getStatus() == HttpStatus.SC_OK) {
            System.out.println(rb.getString("updated")
                    + ": \n" + response.readEntity(String.class));
        } else if (response.getStatus() == HttpStatus.SC_BAD_REQUEST) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.bad_request"));
        } else if (response.getStatus() == HttpStatus.SC_NOT_FOUND) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.not_available"));
        } else {
            throw new BottlerCmdIllegalArgumentException(MessageFormat.format(rb.getString("error.unable_to_connect"), new Object[]{serverUri}) + "kurva fix do piči");
        }

    }

    public String getProducers() {
        WebTarget resourceWebTarget = webTarget.path("");

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/jsonproducers");

        Response response = invocationBuilder.get();
        if (response.getStatus() == HttpStatus.SC_BAD_REQUEST) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.bad_request"));
        }
        return response.readEntity(String.class).replaceAll("},", "},\n");
    }

    public String getProducer(String id) {
        WebTarget resourceWebTarget = webTarget.path(id);

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        Response response = invocationBuilder.get();
        if (response.getStatus() == HttpStatus.SC_BAD_REQUEST) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.bad_request"));
        } else if (response.getStatus() == HttpStatus.SC_NOT_FOUND) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.not_found"));
        }
        return response.readEntity(String.class);
    }

    private String deleteProducer(String id) {
        WebTarget resourceWebTarget = webTarget.path(id);

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        Response response = invocationBuilder.delete();
        if (response.getStatus() == HttpStatus.SC_BAD_REQUEST) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.bad_request"));
        } else if (response.getStatus() == HttpStatus.SC_NOT_FOUND) {
            throw new BottlerCmdIllegalArgumentException(rb.getString("error.not_found"));
        }
        return response.readEntity(String.class);
    }
}
