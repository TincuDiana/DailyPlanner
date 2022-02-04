CREATE TABLE taskEventRel(
   idTaskEvent UUID NOT NULL PRIMARY KEY,
   idTask UUID NOT NULL,
   idEvent UUID NOT NULL,
   CONSTRAINT fkEventId
   FOREIGN KEY(idEvent)
      REFERENCES event(idEvent),
   CONSTRAINT fkTaskId
   FOREIGN KEY(idTask)
      REFERENCES tasks(idTask)
);