CREATE DATABASE  IF NOT EXISTS remote_banking;
USE remote_banking;

-- PERSON table
DROP TABLE IF EXISTS person;
CREATE TABLE person (
  idperson int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  date_of_birth date NOT NULL,
  gender varchar(45) NOT NULL,
  PRIMARY KEY (idperson)
);
INSERT INTO remote_banking.person
(first_name, last_name, date_of_birth, gender)
VALUES
('John', 'Doe', '2000-01-1', 'man'),
('Jane', 'Doe', '2000-01-1', 'woman'),
('Jack', 'Doe', '2000-01-1', 'man');

-- GOOGLE table (empty at its initial state
DROP TABLE IF EXISTS google_accounts;
CREATE TABLE google_accounts (
  idgoogle_accounts int(11) NOT NULL,
  family_name varchar(45) DEFAULT NULL,
  PRIMARY KEY (idgoogle_accounts)
);

-- EMAILS table
DROP TABLE IF EXISTS emails;
CREATE TABLE emails (
  idemails int(11) NOT NULL AUTO_INCREMENT,
  email varchar(45) NOT NULL,
  person_idperson int(11) NOT NULL,
  google_accounts_idgoogle_accounts int(11) DEFAULT NULL COMMENT 'email can be not a google',
  PRIMARY KEY (idemails),
  UNIQUE KEY email_UNIQUE (email),
  KEY fk_emails_person_idx (person_idperson),
  UNIQUE KEY fk_emails_google_accounts_idx (google_accounts_idgoogle_accounts),
  CONSTRAINT fk_emails_google_accounts FOREIGN KEY (google_accounts_idgoogle_accounts) REFERENCES google_accounts (idgoogle_accounts) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT fk_emails_person FOREIGN KEY (person_idperson) REFERENCES person (idperson) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO remote_banking.emails
(email, person_idperson)
VALUES
('John@Doe.com', 1),
('Jane@Doe.com', 2),
('Jack@Doe.com', 3);
