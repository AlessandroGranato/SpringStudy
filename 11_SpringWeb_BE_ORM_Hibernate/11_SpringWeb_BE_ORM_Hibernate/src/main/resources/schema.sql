DROP TABLE SPITTER;
DROP TABLE SPITTLE;

CREATE TABLE SPITTER(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR2(50) UNIQUE NOT NULL,
    password VARCHAR2(50) NOT NULL,
    first_name VARCHAR2(50) NOT NULL,
    last_name VARCHAR2(50) NOT NULL,
    email VARCHAR2(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE SPITTLE(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY,
    message VARCHAR2(140) NOT NULL,
    creation_date TIMESTAMP DEFAULT SYSDATE,
    latitude NUMBER(6,2) NOT NULL,
    longitude NUMBER(6,2) NOT NULL,
    PRIMARY KEY(id)
);