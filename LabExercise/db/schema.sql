CREATE TABLE Persons (
  PersonID int PRIMARY KEY,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255)
);

INSERT INTO Persons VALUES (10, "Wan", "Jason", "11A Alley", "Selangor")