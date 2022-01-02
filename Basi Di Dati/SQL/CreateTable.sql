--\Inizio File SQL DB E-learing Platform DB

/*
    ---------------------------
        !Creazione Domini!
    ---------------------------
*/
CREATE DOMAIN EMAIL_DOMINIO AS VARCHAR(50)
	CHECK ( VALUE LIKE '_%@_%._%' );

CREATE DOMAIN PASSWORD_DOMINIO AS VARCHAR(40)
	CHECK (VALUE ~ '^.*(?=.*[@!#$^*%&])(?=.*[0-9])(?=.*[a-zA-Z]).*$'
		AND VALUE LIKE '________%');

CREATE DOMAIN URL AS VARCHAR(50)
	CHECK ( VALUE LIKE 'https://www.%' );
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
	Nome VARCHAR(120),
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
	email EMAIL_DOMINIO,
	password PASSWORD_DOMINIO,
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
	URL URL,
	Email EMAIL_DOMINIO,
	password PASSWORD_DOMINIO,
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
/*
    ---------------------------------
      !VINCOLI->TABLE->DOCENTE!
    ---------------------------------
*/
ALTER TABLE DOCENTE
ADD CONSTRAINT EmailUniqueTableDocente UNIQUE (email);
/*
    ---------------------------------
      !VINCOLI->TABLE->STUDENTE!
    ---------------------------------
*/
ALTER TABLE STUDENTE
ADD CONSTRAINT EmailUniqueTableStudente UNIQUE (email);
/*
    ---------------------------------
      !INSERT->TABLE->DIPARTIMENTO!
    ---------------------------------
*/
insert into dipartimento values
('40122','Dipartimento di Agraria', 'Prof. Danilo Ercolini','Portici','Napoli','Via Università 100',80055),
('40124','Dipartimento di Architettura','Prof. Michelangelo Russo','Napoli','Napoli','Via Monteoliveto 3',80134),
('40142','Dipartimento di Biologia','Prof.ssa Gionata De Vico','Napoli','Napoli','Via Cupa Nuova Cintia 21',80126),
('40125','Dipartimento di Economia e Management','Prof.ssa Adele Caldarelli','Napoli','Napoli','Via Cupa Nuova Cintia 21',80126),
('40127','Dipartimento di Farmacia','Prof.ssa Angela Zampella','Napoli','Napoli','Via Domenico Montesano 49',80131),
('40143','Dipartimento di Fisica "Ettore Pancini"','Prof. Gennaro Miele','Napoli','Napoli','Via Cupa Nuova Cintia 21',80126),
('40128','Dipartimento di Giurisprudenza','Prof. Sandro Staiano','Napoli','Napoli','Via Nuova Marina 33',80133),
('40133','Dipartimento di Ingegneria Industriale','Prof. Nicola Bianco','Napoli','Napoli','Piazzale Tecchio 80',80125),
('40137','Dipartimento di Medicina e Chirurgia','Prof. Fabrizio Pane','Napoli','Napoli','Via Pansini 5',80131),
('40147','Dipartimento di Scienze Politiche','Prof. Vittorio Amato','Napoli','Napoli','Via Leopoldo Rodinò 22',80138);
