package ru.techcoll.news.serial;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Класс, выполняющий десереализацию объектов из XML-документов.
 */
public class XmlSerializer {

    private static Logger log = Logger.getLogger(XmlSerializer.class);

    /**
     * Получение объекта из документа по URL.
     *
     * @param url URL документа.
     * @param klass Класс корневого объекта.
     * @param Item Класс - Item в коллекции items класса klass
     * @param <T> Тип корневого объекта.
     * @return Экземпляр десереализованного объекта.
     * @throws Exception
     */
    public <T> T retrieveFromUrl(String url, Class<T> klass, Object Item) throws Exception {
        return retrieveFromStream(getDocument(url), klass, Item);
    }

    /**
     * Получение объекта из документа в файле.
     *
     * @param name Имя файла документа.
     * @param klass Класс корневого объекта.
     * @param Item Класс - Item в коллекции items класса klass
     * @param <T> Тип корневого объекта.
     * @return Экземпляр десереализованного объекта.
     * @throws Exception
     */
    public <T> T retrieveFromFile(String name, Class<T> klass, Object Item) throws Exception {
        InputStream input = new BufferedInputStream(new FileInputStream(name));
        return retrieveFromStream(input, klass, Item);
    }

    /**
     * Получение объекта из документа в произвольном потоке ввода.
     *
     * @param stream Пток ввода, содержащий документ.
     * @param klass Класс корневого объекта.
     * @param Item Класс - Item в коллекции items класса klass
     * @param <T> Тип корневого объекта.
     * @return Экземпляр десереализованного объекта.
     * @throws Exception
     */
    public <T> T retrieveFromStream(InputStream stream, Class<T> klass, Object Item) throws Exception {
        DocumentFactory factory = new DocumentFactory();
        SAXReader reader = new SAXReader();
        reader.setDocumentFactory(factory);

        Document document = reader.read(stream);
        return parse(document, klass, Item);
    }

    public InputStream getDocument(String url) throws Exception {
        log.info("Retrieving document from " + url);

        URLConnection connection = new URL(url).openConnection();
        return connection.getInputStream();
    }

    protected boolean isSerialNodeSetter(Method method) {
        return method.isAnnotationPresent(SerialNode.class)
                && method.getName().startsWith("set")
                && method.getParameterCount() == 1;
    }

    protected <T> T readProperties(Node node, T instance) throws SerializationException {
        final Class<?> cl = instance.getClass();
        final Method[] methods = cl.getMethods();

        for (final Method method : methods) {
            // проверяем у метода наличие аннотации SerialNode
            if (isSerialNodeSetter(method)) {
                // получаем ее значение
                SerialNode an = method.getAnnotation(SerialNode.class);

                log.debug("Discovered setter " + method.getName() + " mapped to '" + an.value() + "'");

                // считываем значение из XML
                String value = node.valueOf(an.value());

                // устанавливаем занчение свойства
                if (value != null) {
                    try {
                        method.invoke(instance, value.trim());
                    } catch (IllegalAccessException e) {
                        throw new SerializationException("Unable to set property " + method.getName(), e);
                    } catch (InvocationTargetException e) {
                        throw new SerializationException("Unable to set property " + method.getName(), e);
                    }
                }
            }
        }

        return instance;
    }

    protected <T> T parse(Node root, Class<T> klass, Object Item) throws SerializationException, InvocationTargetException {
        // проверяем, есть ли у класса аннотация сериализации
        if (!klass.isAnnotationPresent(SerialNode.class))
            throw new SerializationException("Unknown serial class " + klass.getName());

        SerialNode an = klass.getAnnotation(SerialNode.class);

        log.debug("Deserializing class " + klass.getName() + " mapped to '" + an.value() + "'");

        Node node = root.selectSingleNode(an.value());
        if (node == null)
            throw new SerializationException("Node not found for serial class " + klass.getName());

        try {
            T result = readProperties(node, klass.newInstance());

            return parseItems(node, result, Item);
        } catch (InstantiationException  e) {
            throw new SerializationException("Unable to create instance " + klass.getName(), e);
        } catch (IllegalAccessException e) {
            throw new SerializationException("Unable to create instance " + klass.getName(), e);
        }
    }

    protected <T> T parseItems(Node node, T instance, Object Item) throws SerializationException, InvocationTargetException, IllegalAccessException {
        List<Node> nodes = node.selectNodes("item");

        Class<?> cl = instance.getClass();
        boolean isAnnotationPresentFlag = false;
        for (Method method : cl.getMethods()) {
            if (isSerialAddToItemsSetter(method)) {
                isAnnotationPresentFlag = true;
                for (Node n : nodes)
                    method.invoke(instance, readProperties(n, Item));
            }
        }
        if (!isAnnotationPresentFlag)
            throw new SerializationException("Class don't have method addToItems " + instance.getClass().getName());

        return instance;
    }

    protected boolean isSerialAddToItemsSetter(Method method) {
        return method.isAnnotationPresent(SerialAddToItems.class)
                && method.getName().startsWith("add");
    }


}
