CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE users(
   id UUID NOT NULL PRIMARY KEY,
   firstName VARCHAR(100) NOT NULL,
   lastName VARCHAR(100) NOT NULL,
   email VARCHAR(100) NOT NULL,
   password VARCHAR(100) NOT NULL
);
INSERT INTO users(ID,firstName,lastName,email,password) VALUES (uuid_generate_v4(),'Stef','Paula','stef_paula@yahoo.ro', 'parola1');