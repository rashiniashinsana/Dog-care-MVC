DROP database IF EXISTS dogCare;
CREATE database IF NOT EXISTS dogCare;

USE dogCare;

CREATE TABLE user
(
    username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(6) NOT NULL
);


CREATE TABLE owner
(
    O_ID       VARCHAR(5) PRIMARY KEY,
    Owner_Name VARCHAR(25) NOT NULL,
    Contacts   VARCHAR(30)
);


CREATE TABLE dog
(
    D_ID           VARCHAR(5) PRIMARY KEY,
    D_Name         VARCHAR(25) NOT NULL,
    Gender         ENUM ('Male', 'Female', 'Other'),
    Breed          VARCHAR(255),
    Age            VARCHAR(2),
    O_ID           VARCHAR(255),
    FOREIGN KEY (O_ID) REFERENCES owner (O_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE employee
(
    Emp_ID   VARCHAR(5) PRIMARY KEY,
    Emp_Name VARCHAR(255) NOT NULL,
    NIC      VARCHAR(12)  NOT NULL,
    Sex      ENUM ('Male', 'Female', 'Other'),
    Contact  VARCHAR(15),
    JobRole  VARCHAR(15)


);


CREATE TABLE payment
(
    P_ID   VARCHAR(5) PRIMARY KEY,
    Amount DECIMAL(5, 2),
    Date DATE


);

CREATE TABLE appointment
(
    Appointment_ID VARCHAR(5) PRIMARY KEY,
    O_ID           VARCHAR(5),
    Emp_ID         VARCHAR(5),
    Date           DATE,
    Time           TIME,
    purpose        VARCHAR(255) NOT NULL,
    FOREIGN KEY (O_ID) REFERENCES owner (O_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Emp_ID) REFERENCES employee (Emp_ID) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE treatment
(
    T_ID       VARCHAR(5) PRIMARY KEY,
    Date       DATE,
    Medication VARCHAR(255),
    Diagnosis  VARCHAR(255),
    P_ID       VARCHAR(5),
    D_ID       VARCHAR(5),
    FOREIGN KEY (P_ID) REFERENCES payment (P_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (D_ID) REFERENCES dog (D_ID) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE record
(
    Record_ID      VARCHAR(5) PRIMARY KEY,
    Description    VARCHAR(255),
    Date            DATE
);


CREATE TABLE inventory
(
    Item_ID     VARCHAR(5) PRIMARY KEY,
    Item_Name   VARCHAR(15),
    Description VARCHAR(20),
    Unit_Price  DOUBLE(5, 2),
    Quantity    VARCHAR(5)
);

CREATE TABLE supplier
(
    Sup_ID       VARCHAR(5) PRIMARY KEY,
    Sup_Name     VARCHAR(15),
    Contact      VARCHAR(30),
    Supplierment VARCHAR(30)
);


CREATE TABLE community_project
(
    Project_ID   VARCHAR(5) PRIMARY KEY,
    Project_Name VARCHAR(15),
    Date         DATE,
    Location     VARCHAR(30)

);

CREATE TABLE inventory_details
(
    Item_ID VARCHAR(5),
    Unit_Price double(5,2),
    Quantity VARCHAR(5),
    FOREIGN KEY (Item_ID) REFERENCES inventory (Item_ID) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE login_details
(
    username VARCHAR(20) ,
    Emp_ID VARCHAR(5),
    FOREIGN KEY (username) REFERENCES user (username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Emp_ID) REFERENCES employee (Emp_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

