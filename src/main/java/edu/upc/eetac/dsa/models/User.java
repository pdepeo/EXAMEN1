package edu.upc.eetac.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    //Basic User Values
    private String id;
    private String name;
    private String surname;
    private String fecha;
    private String correo;
    private String password;

    //Game User Objects
    private List<Objeto> listGameObjects = null; //List of Objects
    //Public Constructor to initialize User
    public User(String id, String name, String surname,String fecha, String correo, String password )
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fecha = fecha;
        this.correo = correo;
        this.password = password;
        this.listGameObjects = new LinkedList<>();
    }
    public User(){
        //Empty Constructor Initialization for second cases
        //Objects list of User is always initialized empty
    }

    public int getNumGameObjects(){
        return this.listGameObjects.size();
    }

    //Returns an object from the list, else null for Out of bounds or Not initialized
    public Objeto getGameObject(int index){
        try {
            return this.listGameObjects.get(index);
        }
        catch (IndexOutOfBoundsException | ExceptionInInitializerError e ){
            return null;
        }
    }
    //Adds Object to the User list
    public int setGameObject(Objeto gameObject){
        try{
            this.listGameObjects.add(gameObject);
        }
        catch (ExceptionInInitializerError e)
        {
            return 400;//400 Bad Request
        }
        catch (IndexOutOfBoundsException e){
            return 507;//Insufficient storage
        }
        return 201;//201 Created
    }
    //Returns User Object List
    public List<Objeto> getListGameObjects(){
        return this.listGameObjects;
    }
    //Setter for Swagger API,DON'T USE IN MAIN CODE!
    public void setListGameObjects(List<Objeto> listGameObjects) {
            this.listGameObjects = (listGameObjects);
    }
    //Adds a List of Objects to User
    public int setListGameObjects_resCode(List<Objeto> listGameObjects) {
        try{
            this.listGameObjects.addAll(listGameObjects);
        }
        catch(NullPointerException e){
            return 204;//204 No Content
        }
        catch( IndexOutOfBoundsException e){
            return 400;//400 Bad Request
        }
        return 201;//201 Created
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

      public String getFecha() {
        return fecha;
    }
    public void setFecha(String surname) {
        this.fecha = fecha;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   @Override
    public String toString(){
        return "ID: " + this.getId() + " | Name: " + this.getName() + " | Surname: " + this.getSurname() +"| Fecha : " + this.getFecha() + " | Email: " + this.getCorreo() + " | Password: " + this.getPassword() ;
    }
}
