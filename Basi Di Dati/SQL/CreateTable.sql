--\Inizio File SQL DB E-learing Platform DB
/*
    ---------------------------
        !Creazione Tabelle!
    ---------------------------
*/
/*
    ---------------------------
          !Table-STUDENTE!
    ---------------------------
*/

CREATE TABLE STUDENTE 
(
	Matricola VARCHAR(9),
	Nome VARCHAR(50),
	Cognome VARCHAR(50),
	DatadiNascita DATE,
	CodFiscale VARCHAR(16),
	Sesso VARCHAR(1),
	CDL VARCHAR(50),
	email VARCHAR(50),
	Dipartimento VARCHAR(5),
	
	PRIMARY KEY(Matricola),
	FOREIGN KEY(Dipartimento) REFERENCES  DIPARTIMENTO(CodiceStruttura)
); 
/*
    ---------------------------
        !Table-DIPARTIMENTO!
    ---------------------------
*/
CREATE TABLE DIPARTIMENTO 
(
	CodiceStruttura VARCHAR(5),
	Nome VARCHAR(40),
	Direttore VARCHAR(40),
	Citta VARCHAR(40),
	provincia VARCHAR(40),
	Via VARCHAR(40),
	Cap INT,
	PRIMARY KEY (CodiceStruttura)
); 
/*
    ---------------------------
          !Table-DOCENTE!
    ---------------------------
*/
CREATE TABLE DOCENTE 
(
	IdDocente VARCHAR(5),
	Nome VARCHAR(50),
	Cognome VARCHAR(50),
	DataDiNascita DATE,
	CodFiscale VARCHAR(16),
	Sesso VARCHAR(1),
	Telefono INT,
	URL VARCHAR(50),
	Email VARCHAR(30),
	Dipartimento VARCHAR(5),
	
	PRIMARY KEY(IdDocente),
	FOREIGN KEY(Dipartimento) REFERENCES  DIPARTIMENTO(CodiceStruttura)
);
/*
    ---------------------------
        !Table-INSEGNAMENTO!
    ---------------------------
*/
CREATE TABLE INSEGNAMENTO 
(
	IdInsegnamento VARCHAR(5),
	denominazione VARCHAR(40),
	IdDocente VARCHAR(5),
	
	PRIMARY KEY(IdInsegnamento),
	FOREIGN KEY(IdDocente) REFERENCES  DOCENTE(IdDocente)
);

