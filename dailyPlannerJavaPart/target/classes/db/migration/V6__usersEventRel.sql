CREATE TABLE usersEventRel(
   idUserEvent UUID NOT NULL PRIMARY KEY,
   idUser UUID NOT NULL,
   idEvent UUID NOT NULL,
   CONSTRAINT fkEventId
   FOREIGN KEY(idEvent)
      REFERENCES event(idEvent),
   CONSTRAINT fkTaskId
   FOREIGN KEY(idUser)
      REFERENCES users(id)
);