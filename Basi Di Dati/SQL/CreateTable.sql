--\Inizio File SQL DB E-learing Platform DB
/*
    ---------------------------
        !Creazione Tabelle!
    ---------------------------
*/
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
/*
    ---------------------------
           !Table-TEST!
    ---------------------------
*/
CREATE TABLE TEST
(
	IdTest SERIAL,
	Nome VARCHAR(50),
	LimiteTempo INT,
	NumeroQuiz INT,
	ScadenzaData DATE,
	PropietarioTest VARCHAR(5),
	
	PRIMARY KEY(IdTest),
	FOREIGN KEY(PropietarioTest) REFERENCES  DOCENTE(IdDocente)
);
CREATE SEQUENCE TestId
START 1
INCREMENT 1
MINVALUE 1
MAXVALUE 99999
OWNED BY TEST.IdTest;
/*
    ---------------------------
          !Table-GESTIONE!
    ---------------------------
*/
CREATE TABLE GESTIONE 
(
	IdOperazione SERIAL,
	Operazione VARCHAR(30),
	DataOperazione TIMESTAMP,
	IdDocente VARCHAR(5),
	IDTest INT,
	
	PRIMARY KEY(IdOperazione),
	FOREIGN KEY(IdDocente) REFERENCES  DOCENTE(IdDocente),
	FOREIGN KEY(IdTest) REFERENCES  TEST(IdTest)
);
CREATE SEQUENCE GestioneId
START 1
INCREMENT 1
MINVALUE 1
MAXVALUE 99999
OWNED BY GESTIONE.IdOperazione;
/*
    ---------------------------
        !Table-QUIZAPERTA!
    ---------------------------
*/
CREATE TABLE QUIZAPERTA
(
	IdQuizA SERIAL,
	Domanda VARCHAR(800),
	Risposta VARCHAR(2000),
	MaXLunghezzaRisposta INT,
	PunteggioMinimo INT,
	PunteggioMassimo INT,
	IdtestRiferimento INT,
	
	PRIMARY KEY(IdQuizA),
	FOREIGN KEY(IdtestRiferimento) REFERENCES  TEST(IdTest)
);
CREATE SEQUENCE IdQuizSeq
START 1
INCREMENT 1
MINVALUE 1
MAXVALUE 300000
OWNED BY QUIZAPERTA.IdQuizA;
/*
    ---------------------------
        !Table-QUIZMULTIPLA!
    ---------------------------
*/
CREATE TABLE QUIZMULTIPLA
(
	IdQuizM SERIAL,
	Domanda VARCHAR(800),
	PunteggioRispostaCorretta INT,
	PunteggioRispostaSbagliato INT,
	IdtestRiferimento INT,
	
	PRIMARY KEY(IdQuizM),
	FOREIGN KEY(IdtestRiferimento) REFERENCES TEST(IdTest)
);
CREATE SEQUENCE IdQuizMSeq
START 1
INCREMENT 1
MINVALUE 1
MAXVALUE 300000
OWNED BY QUIZMULTIPLA.IdQuizM;
/*
    ---------------------------
        !Table-RISPOSTA!
    ---------------------------
*/
CREATE TABLE RISPOSTA
(
	IDRisposta SERIAL,
	Risposta VARCHAR(200),
	IDQuizRiferimento INT,
	
	PRIMARY KEY(IDRisposta) ,
	FOREIGN KEY(IDQuizRiferimento) REFERENCES QUIZAPERTA(IdQuizA)
);
CREATE SEQUENCE IdRiSpostaSeq
START 1
INCREMENT 1
MINVALUE 1
MAXVALUE 300000
OWNED BY RISPOSTA.IDRisposta;
/*
    ---------------------------
    !Table-RISULTATITEST!
    ---------------------------
*/
CREATE TABLE RISULTATITEST
(
	IdRisultato SERIAL,
	NumeroQuiz INT,
	IdTest INT,
	Matricola VARCHAR(9),
	PuteggioTotale INT,
	
	PRIMARY KEY(IdRisultato),
	FOREIGN KEY(Matricola) REFERENCES STUDENTE(Matricola)
);
CREATE SEQUENCE IdRisulatatoSeq
START 1
INCREMENT 1
MINVALUE 1
MAXVALUE 300000
OWNED BY RISULTATITEST.IdRisultato;
/*
    ---------------------------------
    !Table-VALUTAZIONERISPOSTEAPERTE!
    ---------------------------------
*/
CREATE TABLE VALUTAZIONERISPOSTEAPERTE
(
	IdQuizAperto INT,
	IdDocente VARCHAR(5), 
	IdTest INT,
	IdRisultato INT,
	PunteggioOttenuto INT,
	DataValutazione TIMESTAMP,
	
	FOREIGN KEY (IdQuizAperto) REFERENCES QUIZAPERTA(IdQuizA),
	FOREIGN KEY (IdDocente) REFERENCES DOCENTE(IdDocente),
	FOREIGN KEY (IdTest) REFERENCES TEST(IdTest),
	FOREIGN KEY (IdRisultato) REFERENCES RISULTATITEST(IdRisultato)
);