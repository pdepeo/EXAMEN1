package edu.upc.eetac.dsa.models;

public class Objeto {
    private final String name;
    private final String description;
    private double coin;

    //Can contain multiple constructors to initialize itself
    public Objeto(String name, String description, double coin ){
        this.name = name;
        this.description= name;
        this.coin=coin;
    }
    //Models must always contain empty constructor and setter and getters for API Rest Magic!
    public Objeto(){
    }
    public void setName(String name){this.name = name;}
    public void setId(String id){this.id =id;}
    public String getName() {
        return this.name;
    }
    public String getId() {return id;}

    public String getDescription() {return this.description}
    public void setDescription(String description){this.description = description;}

    public double getCoin () {return coin;}
    public void setCoin(Double coin){this.coin=coin;}
    }
    @Override
    public String toString(){
        return "Nombre: " + this.getId() + " | Name: " + this.getName() ;

         }

    @Override
    public String toString(){
        return "ID: " + this.getId() + " | Name: " + this.getName() + " | Surname: " + this.getSurname() +"| Fecha : " + this.getFecha() + " | Email: " + this.getCorreo() + " | Password: " + this.getPassword() ;
    }
}
