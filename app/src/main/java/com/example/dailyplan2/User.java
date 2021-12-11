package com.example.dailyplan2;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;


public class User {
        private final UUID id;
        private String firstname;
        private String lastname;
        private String email;
        private String password;

        public User(String firstName, String lastName, String email, String password) {
                this.id = UUID.randomUUID();
                this.firstname = firstName;
                this.lastname = lastName;
                this.email = email;
                this.password = password;
        }

        public void setFirstName(String firstName) {
                this.firstname = firstName;
        }

        public void setLastName(String lastName) {
                this.lastname = lastName;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getFirstName() {
                return firstname;
        }

        public String getLastName() {
                return lastname;
        }

        public String getEmail() {
                return email;
        }

        public String getPassword() {
                return password;
        }

        public UUID getId() {
                return id;
        }
}
