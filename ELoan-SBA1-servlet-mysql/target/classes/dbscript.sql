DROP DATABASE IF EXISTS eloan;

CREATE DATABASE eloan;

USE eloan;

 CREATE TABLE users(
 	username varchar(20) primary key,
 	password varchar(20) not null
 );
 
 INSERT INTO users VALUES
 	('admin','admin');

 	
CREATE TABLE loan(
  applno VARCHAR(200) PRIMARY KEY,
  purpose TEXT NOT NULL,
  amtrequest DECIMAL NOT NULL,
  doa DATE NOT NULL,
  bstructure VARCHAR(20) NOT NULL,
  bindicator VARCHAR(20) NOT NULL,  
  tindicator VARCHAR(20) NOT NULL,  
  address TEXT NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  mobile CHAR(10) NOT NULL UNIQUE,
  status  VARCHAR(100) NOT NULL
);

CREATE TABLE approvedLoan(
  applno VARCHAR(200) REFERENCES loan (applno),
  amotsanctioned DECIMAL NOT NULL,
  loanterm DECIMAL NOT NULL,
  psd DATE NOT NULL,
  lcd DATE NOT NULL,
  emi DECIMAL NOT NULL
);

 commit;