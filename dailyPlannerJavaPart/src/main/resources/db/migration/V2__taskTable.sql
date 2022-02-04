CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE tasks(
                      idTask UUID NOT NULL PRIMARY KEY,
                      firstName VARCHAR(100) NOT NULL,
                      lastName VARCHAR(100) NOT NULL,
                      data VARCHAR(45) NOT NULL,
                      description VARCHAR(300) NOT NULL,
                      location VARCHAR(300) NOT NULL
);
