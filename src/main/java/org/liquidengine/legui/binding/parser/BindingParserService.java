package org.liquidengine.legui.binding.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.liquidengine.legui.binding.model.AbstractClassBinding;
import org.liquidengine.legui.binding.model.BindingCreationException;
import org.liquidengine.legui.binding.model.ClassBinding;
import org.xml.sax.SAXException;

/**
 * Parser for bindings.
 *
 * @author ShchAlexander.
 */
public class BindingParserService {

    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * Private constructor.
     */
    private BindingParserService() {
    }

    /**
     * Returns instance of binding service.
     *
     * @return binding service.
     */
    public static BindingParserService getInstance() {
        return BindingParserHolder.INSTANCE;
    }

    /**
     * Used to parse binding list.
     *
     * @param listPath binding list location.
     *
     * @return created class bindings map.
     */
    public Map<Class, AbstractClassBinding> parseList(String listPath) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            BindingListParser listParser = new BindingListParser();
            InputStream inputStream = getInputStream(listPath);
            if (inputStream == null) {
                throw new FileNotFoundException("Binding list: " + listPath);
            }
            saxParserFactory.newSAXParser().parse(inputStream, listParser);
            return listParser.getBindings();
        } catch (SAXException | IOException | ParserConfigurationException | BindingCreationException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Used to parse class binding from binding path.
     *
     * @param bindingPath class binding path.
     *
     * @return parsed class binding or null.
     */
    public ClassBinding parseBinding(String bindingPath) {
        return this.parseBinding(bindingPath, null);
    }

    /**
     * Used to parse class binding from binding path.
     *
     * @param bindingPath class binding path.
     * @param parentPath path to parent binding.
     *
     * @return parsed class binding or null.
     */
    protected ClassBinding parseBinding(String bindingPath, String parentPath) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            BindingParser bindingParser = new BindingParser(bindingPath, parentPath);
            InputStream inputStream = getInputStream(bindingPath);

            if (inputStream == null) {
                LOGGER.warn(
                    "Can't parse binding cause binding not found: " + bindingPath + ((parentPath != null ? "    \t-> (for binding " + parentPath + ")" : "")));
            }
//            if (inputStream == null) {
//                throw new FileNotFoundException();
//            }
            saxParserFactory.newSAXParser().parse(inputStream, bindingParser);
            return bindingParser.getBinding();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Used to get input stream of file.
     *
     * @param filePath file path.
     *
     * @return input stream of file.
     *
     * @throws FileNotFoundException in case if file not found.
     */
    private InputStream getInputStream(String filePath) throws FileNotFoundException {
        InputStream stream;
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            stream = new FileInputStream(file);
        } else {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        }
        return stream;
    }

    /**
     * BindingParserService singleton implementation "on demand holder".
     */
    private static class BindingParserHolder {

        /**
         * Service instance.
         */
        private static final BindingParserService INSTANCE = new BindingParserService();
    }
}
