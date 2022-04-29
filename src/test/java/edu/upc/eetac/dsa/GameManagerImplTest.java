package edu.upc.eetac.dsa;
import edu.upc.eetac.dsa.models.User;
import edu.upc.eetac.dsa.models.Objeto;
import org.apache.log4j.Logger;
//Junit 4.13
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class  GameManagerImplTest {
    // THE QUICK REMINDER: Remember to name the test class public smh
    //Log4j Logger initialization
    private static Logger logger = Logger.getLogger(GameManagerImplTest.class);
    //GameManager
    public GameManager manager = null;
    //Estructura de datos
    User user;
    List<Objeto> listGameObjects;
    //Metodo SetUp
    @Before
    public void setUp() {
        //Configuring Log4j
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger.debug("Debug Test Message!");
        logger.info("Info Test Message!");
        logger.warn("Warning Test Message!");
        logger.error("Error Test Message!");
        //Instancing GameManager Implementation
        manager = GameManagerImpl.getInstance();
       //Initializing Object List
        listGameObjects =  new LinkedList<Objeto>();
        //Initialzing Test User
        user = new User("xyz", "Pablo", "Ramos", "20Diciembre","pabloramos@gmail.com","44445AS" );
        //Appending data to Object List
        listGameObjects.add(new Objeto("001", "Description"));
        listGameObjects.add(new Objeto("002", "Coins"));
        //Adding Objects list to Game Manager
        manager.addGameObjects(listGameObjects);


    }
    //Tests
    //Metodo Test para añadir un usuario en el sistema y verificar el número de usuarios
    @Test
    public void addUserTest(){
        //Initial Test, initial users in game Zero!
        Assert.assertEquals(0, this.manager.numUsers());
        //Adding a user to the GameManager
        manager.addUser(user.getId(),user.getName(),user.getSurname(), user.getFecha(), user.getCorreo(), user.getPassword());
        Assert.assertEquals(1, manager.numUsers());
        //Adding a second user to the GameManager
        manager.addUser("abc","Ramos","Hector",);
        Assert.assertEquals(2, manager.numUsers());
    }

    @Test
    public void addObjectTest(){
        //Setting up with 1 Test User
        manager.addUser(user.getId(),user.getName(),user.getSurname());
        //Test for the objects the test user has equals 0 as setUp method
        Assert.assertEquals(0, manager.getNumGameObjectsUser(user.getId()));
        //Adding an object to the User passing a id of the Object, Expects http 201 Ok
        Assert.assertEquals(201,manager.addUserGameObject(user.getId(), listGameObjects.get(0).getId()));
        //Test if the number of objects inside Test User has increased to 1
        Assert.assertEquals(1, manager.getNumGameObjectsUser(user.getId()));
    }

    //Metodo Teardown
    @After
    public void tearDown() {
        manager.liberateReserves();
    }
}