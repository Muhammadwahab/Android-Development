package com.example.ajmalsyed.prizebond;

/**
 * Created by Ajmal Syed on 6/8/2017.
 */
public class UserInstance {



        String name, drawnumber;
        String number;

        public UserInstance(String name,String number,String email){
            this.name=name;
            this.number=number;
            this.drawnumber =email;
        }

        void setName(String name){
            this.name=name;
        }
        void setNumber(String number){
            this.number=number;
        }

        void setDrawnumber(String drawnumber){
            this.drawnumber = drawnumber;
        }

        String getName(){
            return name;
        }

        String getNumber(){
            return number;
        }

        String getDrawnumber(){
            return drawnumber;
        }
    }


