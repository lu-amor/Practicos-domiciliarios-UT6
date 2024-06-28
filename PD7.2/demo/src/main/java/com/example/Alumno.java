package com.example;

public class Alumno extends  Object{
    private int ID;
    private String fullName;
    private String email;

    public Alumno(int ID, String fullName, String email) {
        this.ID = ID;
        this.fullName = fullName;
        this.email = email;
    }

    public boolean setFullName(String fullName){
        if (!fullName.isEmpty()){
            this.fullName = fullName;
            return true;
        } else {
            return false; 
            // seria buena practica devolver un booleano en los setters?
            // o lo dejo void como siempre y no habria necesidad de un bloque else ?
        }
    }

    public String getFullName(){
        if (!fullName.isEmpty()){
            return fullName;
        } else {
            return "No name asigned";
        }
    }

    public boolean setEmail(String email){
        if (!email.isEmpty()){
            this.email = email;
            return true;
        } else {
            return  false;
        }
    }

    public String getEmail(){
        if (!email.isEmpty()){
            return email;
        } else {
            return "No email";
        }
    }

    public int getID(){
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return ID == alumno.ID &&
            fullName.equals(alumno.fullName) &&
            email.equals(alumno.email);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(ID);
        result = 31 * result + fullName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}

