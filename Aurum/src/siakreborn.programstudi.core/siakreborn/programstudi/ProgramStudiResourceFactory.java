package siakreborn.programstudi;

import siakreborn.programstudi.core.ProgramStudiResource;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class ProgramStudiResourceFactory{
    private static final Logger LOGGER = Logger.getLogger(ProgramStudiFactory.class.getName());

    public ProgramStudiResourceFactory()
    {

    }

    public static ProgramStudiResource createProgramStudiResource(String fullyQualifiedName, Object ... base)
    {
        ProgramStudiResource record = null;
        if (true) {
            try {
                Class<?> clz = Class.forName(fullyQualifiedName);
                Constructor<?> constructor = clz.getDeclaredConstructors()[0];
                System.out.println(constructor.toString());
                record = (ProgramStudiResource) constructor.newInstance(base);
            } 
            catch (IllegalArgumentException e)
            {
                LOGGER.severe("Failed to create instance of ProgramStudi.");
                LOGGER.severe("Given FQN: " + fullyQualifiedName);
                LOGGER.severe("Failed to run: Check your constructor argument");
                System.exit(20);
            }
            catch (ClassCastException e)
            {   LOGGER.severe("Failed to create instance of ProgramStudi.");
                LOGGER.severe("Given FQN: " + fullyQualifiedName);
                LOGGER.severe("Failed to cast the object");
                System.exit(30);
            }
            catch (ClassNotFoundException e)
            {
                LOGGER.severe("Failed to create instance of ProgramStudi.");
                LOGGER.severe("Given FQN: " + fullyQualifiedName);
                LOGGER.severe("Decorator can't be applied to the object");
                System.exit(40);
            }
            catch (Exception e)
            {
                LOGGER.severe("Failed to create instance of ProgramStudi.");
                LOGGER.severe("Given FQN: " + fullyQualifiedName);
                System.exit(50);
            }
        } else {
            System.out.println("Config Fail");
            System.exit(10);
        }
        return record;
    }

}
