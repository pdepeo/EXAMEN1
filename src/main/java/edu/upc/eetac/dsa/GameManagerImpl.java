package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.Objeto;
import edu.upc.eetac.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;

public class GameManagerImpl implements GameManager {

    private static GameManager instance;
    private HashMap<String , User> mapUser;
    private List<Objeto> listGameObjects;
    private static Logger log = Logger.getLogger(GameManagerImpl.class);
    //Private Constructor
    private GameManagerImpl(){
        //Singleton Private Constructor
        this.mapUser = new HashMap<>();
        this.listGameObjects = new LinkedList<>();
    }
    //Singleton implementation for the instance of the GameManager
    public static GameManager getInstance(){
        if(instance == null) {
            instance = new GameManagerImpl();
        }
        return instance;
    }
    //Listado de usuarios ordenado alfabéticamente
    @Override
    public List<User> getSortedUsersAlphabetical() {
        //Map of Users is not empty
        if(this.mapUser != null) {
            List<User> result = new LinkedList<>(mapUser.values());

            Collections.sort(result, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    //ToIgnoreCase: To not distinguish between Capital and LowerCase
                    return u1.getName().compareToIgnoreCase(u2.getName());
                }
            });
            log.info("List Ordered Alphabetically: " + result.toString());
            return result; //200 OK PETITION
        }
        else
            return null; //404 (Empty Table)
    }
    //Añadir usuario
    @Override
    public int addUser(String id, String name, String surname, String fecha, String correo, String password ) {
        User tmp_user = new User(id,name,surname,fecha,correo,password);
        try{
            mapUser.put(id,tmp_user);
            log.info("User Added: " +tmp_user );
            return 201; //OK CREATED
        }
        catch (IndexOutOfBoundsException e){
            log.error("UserMap Full Error");
            return 507; //INSUFFICIENT STORAGE
        }
        catch (IllegalArgumentException e){
            log.error("Incorrect format exception");
            return 400; //BAD REQUEST
        }
    }

    //Modificar Usuario
    @Override
    public int updateUser(String id, String name, String surname,String fecha, String correo, String password) {
        User upd_usr = this.mapUser.get(id);
        if (upd_usr != null) {
            try {
                upd_usr.setName(name);
                upd_usr.setSurname(surname);
                upd_usr.setFecha(fecha);
                upd_usr.setCorreo(correo);
                upd_usr.setPassword(password);
                log.info("Updated User parameters:" + upd_usr);
                return 201; //OK CREATED
            } catch (IllegalArgumentException e) {
                log.error("An incorrect format for the User");
                return 400; //BAD REQUEST
            }
        } else {
            return 404; //User Not Found
        }
    }
    //Consultar usuario
    @Override
    public User getUser(String id) {
        User upd_usr = this.mapUser.get(id);
        if(upd_usr!=null){
            log.info("User found: " + upd_usr);
        }else{
            log.error("User not found for ID: "+id);
        }
        return upd_usr;
    }
    //Consultar numeros de usuarios que hay en el sistema
    @Override
    public int numUsers() {
        return this.mapUser.size();
    }
    //Añadir Objeto sobre un usuario
    @Override
    public int addUserGameObject(String userId, String gameobjectId) {
        User temp_usr = mapUser.get(userId);
        //From Object Id to Object from the List of Objects
        Objeto temp_gameobj = getGameObject( gameobjectId);
        if(temp_usr != null && temp_gameobj!=null){
            int err = temp_usr.setGameObject(temp_gameobj);
            if(err == 201){
                log.info("Object added to user " + temp_usr.getName() + " : " + temp_gameobj.getName());
                return 201; //OK CREATED
            }
            else if(err == 400){
                log.error("Bad Format");
                return 400; //BAD REQUEST
            }
            else{
                log.error("No Inventory space for user: "+ temp_usr.getName());
                return 507; //INSUFFICIENT STORAGE
            }
        }
        else{
            log.error("User: "+userId +" &/or Object: " + gameobjectId +" NOT FOUND!");
            return 404; //USER NOT FOUND
        }
    }
    //Añadir Lista de Objetos sobre un usuario
    @Override
    public int addUserGameObjects(String id, List<Objeto> listGameObjects){
        User temp_usr = mapUser.get(id);
        if(temp_usr != null){
            int err = temp_usr.setListGameObjects_resCode(listGameObjects);
            if(err == 201){
                log.info("201: Object List added to user " + temp_usr.getName());
                return 201; //OK CREATED
            }
            else if(err == 400){
                log.error("400: Bad Format");
                return 400; //BAD REQUEST
            }
            else{
                log.error("204: No Object Content: "+ temp_usr.getName());
                return 204; //204 No Content
            }
        }
        else{
            log.error("User Not found");
            return 404; //USER NOT FOUND
        }
    }
    //Consultar el número de objetos de un sistema
    @Override
    public int getNumGameObjectsUser(String id) {
        User temp_usr = mapUser.get(id);
        if(temp_usr != null){
            log.info("User: "+temp_usr.getName() + " has NumObjects: "+temp_usr.getNumGameObjects());
            return temp_usr.getNumGameObjects();
        }
        else{
            log.error("User Not found");
            return 404; //USER NOT FOUND
        }
    }

    /*                          EXTRAS                  */
    @Override
    public int addGameObject(Objeto gameObject) {
        int result;
        try {
            this.listGameObjects.add(gameObject);
            log.info("201: Object Added: " + gameObject.getName());
            result = 201;//OK CREATED
        } catch (IllegalArgumentException e) {
            log.error("400: Bad Object parameters");
            result = 400;//BAD REQUEST
        } catch (IndexOutOfBoundsException e) {
            log.error("507: Insufficient Storage");
            result = 507;//INSUFFICIENT STORAGE
        }
        return result;
    }
    @Override
    public int addGameObjects(List<Objeto> listGameObjects) {
        int result;
        try {
            this.listGameObjects.addAll(listGameObjects);
            log.info("201: Objects Added: " + listGameObjects.toString());
            result = 200;//OK Added
        } catch (IllegalArgumentException e) {
            log.error("400: Bad Object parameters");
            result = 400;//BAD REQUEST
        } catch (IndexOutOfBoundsException e) {
            log.error("507: Insufficient Storage");
            result = 507;//INSUFFICIENT STORAGE
        }
        return result;
    }
    @Override
    public Objeto getGameObject(String gameObjectId) {
        Objeto result_Game_object = null;
        try{
            for(Objeto gameObject : this.listGameObjects){
                if (gameObject.getId().compareTo(gameObjectId) == 0){
                    result_Game_object = gameObject;
                    log.info("302: Object found: " + gameObject.getName());
                }
            }
        }catch(ExceptionInInitializerError e){
            log.error("400: Object list not initialized");
            return null; //400 ERROR List of Objects not initialized
        }
        return result_Game_object;
    }
    @Override
    public List<User> getUsersList() {
        List<User> result = null;
        if(this.mapUser.size() !=0){
            result = new LinkedList<>(this.mapUser.values());
            log.info("User List: "+result.toString());
        }
        return result; //Null: 404 Empty User HashMap
    }
    //Generate Id
    @Override
    public String generateId(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) { // length of the random generated ID
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
    //Liberar Recursos
    @Override
    public void liberateReserves() {
        this.listGameObjects.clear();
        this.mapUser.clear();
    }
}
