package com.example.dailyplan2.model;

import java.util.UUID;

public class Task {
        private final UUID idTask;
        private final UUID idUser;
        private final String date;
        private final String description;
        private final String location;

        public Task(UUID idUser, String date,  String description, String location) {
            this.idTask = UUID.randomUUID();
            this.idUser = idUser;
            this.date = date;
            this.description = description;
            this.location = location;
        }

        public Task(String date, String description, String location) {
            this.idTask= null;
            this.idUser= null;
            this.date = date;
            this.description = description;
            this.location = location;
    }

    public UUID getIdTask() {return idTask;}

        public UUID getIdUser() {return idUser;}

        public String getDate() {return date;}

        public String getDescription() {return description;}

        public String getLocation() {return location;}
}
