package cz.muni.fi.pa165.bottler.client.cmd;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import org.apache.commons.cli.*;
import java.util.ResourceBundle;
import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
import java.io.IOException;
import java.net.ConnectException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpStatus;

/**
 * @author Josef Ludvicek <374268@mail.muni.cz>
 */
public class Main {
    
    private static final String DEFAULT_SERVER_URI = "http://localhost:8080";

    /**
     * option definition
     */
    public static final Option help;
    public static final Option addProducers;
    public static final Option getProducers;
    public static final Option getProducer;
    public static final Option deleteProducers;
    public static final Option updateProducers;
    public static final Option addStores;
    public static final Option getStores;
    public static final Option getStore;
    public static final Option deleteStores;
    public static final Option updateStores;
    public static final Option serverUri;
    /**
     * list of defined options
     */
    public static final Options cmdOptions;
    /**
     * resource bundle containing strings
     */
    private static ResourceBundle rb;

    static {

        rb = ResourceBundle.getBundle("strings");

        help = OptionBuilder
                .withDescription(rb.getString("help.help_description"))
                .withLongOpt("help")
                .create("h");

        addProducers = OptionBuilder
                .withArgName("file")
                .hasArg()
                .withDescription(rb.getString("help.add-producers"))
                .withLongOpt("add-producers")
                .create("a");
        getProducers = OptionBuilder
                .withDescription(rb.getString("help.get-producers"))
                .withLongOpt("get-producers")
                .create("g");
        getProducer = OptionBuilder
                .withArgName("id")
                .hasArg()
                .withDescription(rb.getString("help.get-producer"))
                .withLongOpt("get-producer")
                .create("p");
        deleteProducers = OptionBuilder
                .withArgName("file")
                .hasArg()
                .withDescription(rb.getString("help.delete-producers"))
                .withLongOpt("delete-producers")
                .create("d");
        updateProducers = OptionBuilder
                .withArgName("file")
                .hasArg()
                .withDescription(rb.getString("help.update-producers"))
                .withLongOpt("update-producers")
                .create("u");

        addStores = OptionBuilder
                .withArgName("file")
                .hasArg()
                .withDescription(rb.getString("help.add-stores"))
                .withLongOpt("add-stores")
                .create("b");
        getStores = OptionBuilder
                .withDescription(rb.getString("help.get-stores"))
                .withLongOpt("get-stores")
                .create("c");
        getStore = OptionBuilder
                .withArgName("id")
                .hasArg()
                .withDescription(rb.getString("help.get-store"))
                .withLongOpt("get-store")
                .create("e");
        deleteStores = OptionBuilder
                .withArgName("file")
                .hasArg()
                .withDescription(rb.getString("help.delete-stores"))
                .withLongOpt("delete-stores")
                .create("f");
        updateStores = OptionBuilder
                .withArgName("file")
                .hasArg()
                .withDescription(rb.getString("help.update-stores"))
                .withLongOpt("update-stores")
                .create("v");
        serverUri = OptionBuilder
                .withArgName("uri")
                .hasArg()
                .withDescription(rb.getString("help.server-uri"))
                .withLongOpt("server-uri")
                .create("s");

        cmdOptions = new Options();
        cmdOptions.addOption(help);

        cmdOptions.addOption(addProducers);
        cmdOptions.addOption(getProducers);
        cmdOptions.addOption(deleteProducers);
        cmdOptions.addOption(updateProducers);
        cmdOptions.addOption(getProducer);

        cmdOptions.addOption(addStores);
        cmdOptions.addOption(getStores);
        cmdOptions.addOption(deleteStores);
        cmdOptions.addOption(updateStores);
        cmdOptions.addOption(getStore);
        cmdOptions.addOption(serverUri);

    }

    public static void main(String[] args) {
//        use POSIX like standard according to http://commons.apache.org/proper/commons-cli/
        CommandLineParser clp = new GnuParser();

        // http://stackoverflow.com/questions/4345911/switching-off-jersey-logging-programmatically
        Logger.getLogger("").setLevel(Level.OFF);

        String serverUriString = DEFAULT_SERVER_URI;

        try {
            CommandLine cmd = clp.parse(cmdOptions, args);

            if (cmd.hasOption(serverUri.getOpt())) {
                serverUriString = cmd.getOptionValue(serverUri.getOpt());
            }
            ProducersClient producersClient = new ProducersClient(serverUriString);
            StoresClient storesClient = new StoresClient(serverUriString);

            if (cmd.hasOption(help.getOpt())) {
                printHelp();
            } else if (cmd.hasOption(addProducers.getOpt())) {
                String filePath = cmd.getOptionValue(addProducers.getOpt());
                producersClient.addProducers(filePath);
            } else if (cmd.hasOption(deleteProducers.getOpt())) {
                String filePath = cmd.getOptionValue(deleteProducers.getOpt());
                producersClient.deleteProducers(filePath);
            } else if (cmd.hasOption(updateProducers.getOpt())) {
                String filePath = cmd.getOptionValue(updateProducers.getOpt());
                producersClient.updateProducers(filePath);
            } else if (cmd.hasOption(getProducers.getOpt())) {
                System.out.println(producersClient.getProducers());
            } else if (cmd.hasOption(getProducer.getOpt())) {
                String id = cmd.getOptionValue(getProducer.getOpt());
                System.out.println(producersClient.getProducer(id));
            } else if (cmd.hasOption(addStores.getOpt())) {
                String filePath = cmd.getOptionValue(addStores.getOpt());
                storesClient.addStores(filePath);
            } else if (cmd.hasOption(deleteStores.getOpt())) {
                String filePath = cmd.getOptionValue(deleteStores.getOpt());
                storesClient.deleteStores(filePath);
            } else if (cmd.hasOption(updateStores.getOpt())) {
                String filePath = cmd.getOptionValue(updateStores.getOpt());
                storesClient.updateStores(filePath);
            } else if (cmd.hasOption(getStores.getOpt())) {
                System.out.println(storesClient.getStores());
            } else if (cmd.hasOption(getStore.getOpt())) {
                String id = cmd.getOptionValue(getStore.getOpt());
                System.out.println(storesClient.getStore(id));
            }

        } catch (ParseException e) {
            System.err.println(rb.getString("error") + " " + e.getLocalizedMessage());
            printHelp();
            System.exit(1);
        } catch (BottlerCmdIllegalArgumentException e) {
            System.err.println(rb.getString("error") + " " + e.getLocalizedMessage());
            System.exit(1);
        } catch (ProcessingException e) {
            System.err.println(rb.getString("error") + " " + MessageFormat.format(rb.getString("error.unable_to_connect"), new Object[]{serverUriString}));
            System.exit(1);
        }

    }

    public static void printHelp() {
        HelpFormatter hf = new HelpFormatter();
        Boolean autoUsage = true;
        hf.printHelp(
                rb.getString("help.usage"),
                rb.getString("help.header") + "\n" + rb.getString("help.sample_json") + "\n",
                cmdOptions,
                rb.getString("help.footer"),
                autoUsage);
    }
}
