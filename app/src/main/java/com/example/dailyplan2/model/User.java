package com.example.dailyplan2.model;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;


public class User {
        private final UUID id;
        private final String firstname;
        private final String lastname;
        private final String email;
        private final String password;

        public User(UUID id, String firstname, String lastname, String email, String password) {
                this.id = id;
                System.out.println(firstname);
                this.firstname = firstname;
                System.out.println(this.firstname);
                this.lastname = lastname;
                this.email = email;
                this.password = password;
        }

        public User(String firstname, String lastname, String email, String password) {
                this.id = UUID.randomUUID();
                this.firstname = firstname;
                this.lastname = lastname;
                this.email = email;
                this.password = password;
        }

        public UUID getId() {
                return id;
        }

        public String getFirstname() {
                return firstname;
        }

        public String getLastname() {
                return lastname;
        }

        public String getEmail() {
                return email;
        }

        public String getPassword() {
                return password;
        }

        @Override
        public String toString() {
                return this.firstname + " " + this.lastname;
        }
}
