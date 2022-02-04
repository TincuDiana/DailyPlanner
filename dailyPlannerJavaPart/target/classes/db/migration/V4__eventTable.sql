CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE event(
                      idEvent UUID NOT NULL PRIMARY KEY,
                      eventName VARCHAR(100) NOT NULL,
                      description VARCHAR(100) NOT NULL,
                      data VARCHAR(45),
                      location VARCHAR(100) NOT NULL
);