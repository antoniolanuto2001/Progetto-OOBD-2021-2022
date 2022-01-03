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
	FOREIGN KEY(IDQuizRiferimento) REFERENCES QUIZMULTIPLA(IdQuizM)
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
INSERT INTO dipartimento VALUES
('40122','Dipartimento di Agraria', 'Prof. Danilo Ercolini','Portici','Napoli','Via Università 100',80055),
('40124','Dipartimento di Architettura','Prof. Michelangelo Russo','Napoli','Napoli','Via Monteoliveto 3',80134),
('40142','Dipartimento di Biologia','Prof.ssa Gionata De Vico','Napoli','Napoli','Via Cupa Nuova Cintia 21',80126),
('40125','Dipartimento di Economia e Management','Prof.ssa Adele Caldarelli','Napoli','Napoli','Via Cupa Nuova Cintia 21',80126),
('40127','Dipartimento di Farmacia','Prof.ssa Angela Zampella','Napoli','Napoli','Via Domenico Montesano 49',80131),
('40143','Dipartimento di Fisica "Ettore Pancini"','Prof. Gennaro Miele','Napoli','Napoli','Via Cupa Nuova Cintia 21',80126),
('40128','Dipartimento di Giurisprudenza','Prof. Sandro Staiano','Napoli','Napoli','Via Nuova Marina 33',80133),
('40133','Dipartimento di Ingegneria','Prof. Nicola Bianco','Napoli','Napoli','Piazzale Tecchio 80',80125),
('40137','Dipartimento di Medicina e Chirurgia','Prof. Fabrizio Pane','Napoli','Napoli','Via Pansini 5',80131),
('40147','Dipartimento di Scienze Politiche','Prof. Vittorio Amato','Napoli','Napoli','Via Leopoldo Rodinò 22',80138);
('40132','Dipartimento di  Ingegneria Elettrica e delle Tecnologie dell Informazione','Prof. Fabio Villone','Napoli','Napoli','Via Claudio 21',80125);
('40555','Dipartimento di Matematica','Prof.ssa Cristina Trombetti','Napoli','Napoli','Via Cupa Nuova Cintia 21','80126');
/*
  ---------------------------------
    !INSERT->TABLE->DOCENTE!
  ---------------------------------
*/
INSERT INTO docente VALUES
('D1432','Silvia','Rossi','04/12/1987','RSSSLV87D52F839Q','F','081679963','https://www.docenti.unina.it/silvia.rossi','silvia.rossi@unina.it','dOCx8821!','40132'),
('D2442','Daniele','Castorina','06/25/1982','CSTDNL82H25F839Q','M','081675694','https://www.docenti.unina.it/daniele.castorina','daniele.castorina@unina.it','Meticl1X00@','40555'),
('D2311','Francesco','Isgrò','02/13/1980','SGRFNC80B13F839Y','M','081679308','https://www.docenti.unina.it/francesco.isgro','francesco.isgro@unina.it','Levil1#Xp','40132'),
('D2422','Isabella','Sifola','03/11/1981','SFLSLL81C51F839C','F','0812539125','https://www.docenti.unina.it/mariaisabella.sifola','mariaisabella.sifola@unina.it','CriciDe02@','40122'),
('D4231','Marina','Albanese','09/28/1983','LBNMRN83P68G902K','F','0812538226','https://www.docenti.unina.it/marina.albanese','marina.albanese@unina.it','DanzixL3i!','40147'),
('D6242','Francesca','Manes','05/02/1978','MNSFNC78E42F839F','F','081676003','https://www.docenti.unina.it/manes','manes@unina.it','LeppPa1z@','40125'),
('D3321','Domenico','De Palma','03/09/1975','DPLDNC75C09F839R','M','0817462773','https://www.docenti.unina.it/giovannidomenico.depalma','giovannidomenico.depalma@unina.it','DoctorStr3ngerx!','40137'),
('D4715','Giovanni','Paternoster','06/10/1960','PTRGNN60H10F839V','M','08176138','https://www.docenti.unina.it/giovanni.paternoster','giovanni.paternoster@unina.it','MiliteX35x0@','40143'),
('D2732','Pasquale','Troncone','01/18/1975','TRNPQL75A18F839S','M','0816711111','https://www.docenti.unina.it/pasquale.troncone','pasquale.troncone@unina.it','ElSombrero99!@','40128'),
('D8513','Marco','La Commara','04/23/1973','LCMMRC73D23F839A','M','081676107','https://www.docenti.unina.it/marco.lacommara','marco.lacommara@unina.it','PasqualDenionadsw4x@','40127'),
('D9934','Anna','Capaldo','05/04/1970','CPLNNA70E44F839O','F','0812535048','https://www.docenti.unina.it/anna.capaldo','anna.capaldo@unina.it','dELTA@1Xd','40142'),
('D9442','Dario','Antonini','02/05/1980','NTNDRA80B05G902E','M','081679091','https://www.docenti.unina.it/dario.antonini','dario.antonini@unina.it','MERge2!@a','40142');

/*
  ---------------------------------
    !INSERT->TABLE->STUDENTE!
  ---------------------------------
*/
INSERT INTO studente VALUES
('N8600124','Filippo','Marziani','07/12/2000','FLPMZN00L12F839E','M','Architettura','f.marziani@studenti.unina.it','Melin01@','40124'),
('N8600125','Gianluca','Ricci','10/10/2001','GNLRCC01R10F839M','M','Architettura','g.ricci@studenti.unina.it','Forest92!','40124'),
('N8600134','Laura','Rossi','02/24/1999','RSSLRA99B64F839U','F','Giurisprudenza','l.rossi@studenti.unina.it','SSxGR23!@Dd1','40128'),
('N8600114','Marco','Grani','04/05/2000','GRNMRC00D05F839N','M','Scienze Enologiche','m.grani@studenti.unina.it','gfGERWU83!z','40122'),
('N8600332','Chiara','Mastri','10/03/2000','CHRMTR00C50G902D','F','Economia Aziendale','c.mastri@studenti.unina.it','Chia992_l!','40125'),
('N86003762','Antonio','Lanuto','09/29/2001','LNTNTN01P29F839R','M','Informatica','an.lanuto@studenti.unina.it','ToMMASO664##X','40132'),
('N86004411','Sandra','Sarni','06/19/1998','SRNSDR98R46I862D','F','Scienze Erboristiche','sa.sarni@studenti.unina.it','IGGRHOW3H51@','40127'),
('N86004322','Elena','Mino','04/10/2002','MNILNE02D50G902D','F','Chimica e tecnologia farmaceutiche','ele.mino@studenti.unina.it','IXXsad31AZ@','40127'),
('N86008711','Emi','Perrone','02/12/1996','PRRMEI06B52L219I','F','Dietistica','emi.perrone@studenti.unina.it','Hxk351!!dsl','40137'),
('N86008113','Alicia','Sica','06/01/2002','SCILCA02H41D612T','F','International Relations','al4.sica@studenti.unina.it','Minus44!X','40147'),
('N86008513','Miro','Ferrari','02/18/2002','FRRMRI02B18A662L','M','Scienze Statistiche per le Decisioni','mir.ferrari@studenti.unina.it','Kxl11ivrASo214@A','40147'),
('N86004200','Lorenzo','Lari','10/09/2000','LRALNZ00R09G902K','M','Autonomous vehicle engineering','lor3.lari@studenti.unina.it','UncondiTi0nAll!!','40133'),
('N86003313','Veronica','Innalzi','07/23/2001','NNLVNC01L63F839R','F','Ingegneria Biomedica','vero.innalzi@studenti.unina.it','Vero.Inn!221','40133');

/*
  ---------------------------------
    !INSERT->TABLE->TEST!
  ---------------------------------
*/
INSERT INTO test VALUES
(default,'Prova di Analisi I -Funzioni',120,10,'05/01/2022','D2442'),
(default,'Prova di Analisi I -Teoria',60,6,'05/09/2022','D2442'),
(default,'Prova di Analisi I -Serie',120,12,'05/13/2022','D2442'),
(default,'Diritto Privato',120,15,'06/15/2022','D2732'),
(default,'Diritto Penale',120,10,'06/19/2022','D2732'),
(default,'Filosofia del Diritto',100,10,'06/18/2022','D2732'),
(default,'Architettura degli Elaboratori -La Cache',120,15,'06/20/2022','D1432'),
(default,'Architettura degli Elaboratori -La Ram',120,15,'06/25/2022','D1432'),
(default,'Architettura degli Elaboratori -Conversioni',100,6,'06/24/2022','D1432'),
(default,'CITOLOGIA ED ISTOLOGIA E LABORATORIO',200,20,'06/22/2022','D9934'),
(default,'ENDOCRINOLOGIA APP. SOSTANZE STUPEFACENTI',120,15,'06/15/2022','D9934'),
(default,'APPLICAZIONI IN BIOINFORMATICA',100,15,'06/20/2022','D9442'),
(default,'BIOLOGIA MOLECOLARE FORENSE',200,15,'06/22/2022','D9442'),
(default,'Laboratorio di Programmazione',120,5,'06/15/2022','D2311'),
(default,'Test studenti 2019',100,3,'06/19/2022','D2311'),
(default,'ANALISI DEL RISCHIO CREDITIZIO',100,10,'06/10/2022','D4231'),
(default,'EVOLUTION OF FINANCIAL MARKETS',120,20,'06/12/2022','D4231'),
(default,'FONDAMENTI DI AGRONOMIA',100,10,'06/13/2022','D2422'),
(default,'LABORATORIO DI COLTIVAZIONE DELLE PIANTE',60,6,'06/10/2022','D2422'),
(default,'PERFORMANCE MANAGEMENT SYSTEMS',120,13,'06/24/2022','D6242'),
(default,'ECONOMIA -Prima Parte',100,10,'06/23/2022','D6242'),
(default,'ECONOMIA -Seconda Parte',100,10,'01/22/2023','D6242'),
(default,'Chirurgia Generale',120,12,'02/24/2022','D3321'),
(default,'Chirurgia di Urgenza',200,15,'02/26/2022','D3321'),
(default,'Prima prova General Surgery',120,10,'02/10/2022','D3321'),
(default,'Seconda prova General Surgery',120,10,'02/10/2022','D3321'),
(default,'Fisica Nucleare -Uno',120,13,'02/11/2022','D4715'),
(default,'Fisica Nucleare -Due',140,15,'02/14/2022','D4715'),
(default,'Metodologie Nucleari in RadioFarmacia I',160,20,'01/17/2022','D8513'),
(default,'Metodologie Nucleari in RadioFarmacia II',200,20,'01/22/2022','D8513'),
(default,'Biologia Generale Primo Test',60,10,'02/15/2022','D9934');

/*
  ---------------------------------
    !INSERT->TABLE->INSEGNAMENTO!
  ---------------------------------
*/
INSERT INTO insegnamento VALUES
('24583','Citologia ed Istologia e Laboratorio','D9934'),
('31442','Endocrinologia applicata alle sostanze Stupefacenti','D9934'),
('U3536','Human-Robot Interaction','D1432'),
('U2322','Architettura degli Elaboratori','D1432'),
('00102','Analisi Matematica I','D2442'),
('U2321','Laboratorio di Programmazione','D2311'),
('U3523','Computer Vision','D2311'),
('U0016','Fondamenti di Agronomia','D2422'),
('U0017','Laboratorio di Coltivazione delle piante','D2422'),
('U0921','Analisi del rischio creditizio e finanziario','D4231'),
('U1913','Evolution of Financial Markets','D4231'),
('U2287','Analisi Impatto delle politiche pubbliche','D4231'),
('34750','Economia delle aziende e delle Amministrazioni Pubbliche','D6242'),
('U2620','Performance Management Systems','D6242'),
('U3311','Economia Aziendale per Impresa Sociale e Fundraising','D6242'),
('U4607','Misurazione delle Performance nelle Aziende Pubbliche','D6242'),
('02404','Chirurgia Di Urgenza','D3321'),
('02412','Chirurgia Generale','D3321'),
('28278','Attività Formative Professionalizzanti','D3321'),
('U1463','General Surgery','D3321'),
('26221','Fisica Nucleare per i Beni Culturali ed Ambientali','D4715'),
('03721','Diritto Penale della Economia','D2732'),
('03730','Diritto Penitenziario','D2732'),
('U2808','Fisica con Elementi di Matematica','D8513'),
('U4450','Metodologie Nucleari in RadioFarmacia','D8513'),
('26410','Applicazioni in BioInformatica','D9442'),
('U4385','Biologia Molecolare Forense','D9442');


/*
  ---------------------------------
    !INSERT->TABLE->QUIZMULTIPLA!
  ---------------------------------
*/
INSERT INTO quizmultipla VALUES
(default,'A cosa serve una Memoria Cache?',1,0,7),
(default,'Come si ottengono prestazioni migliori con le Cache?',1,0,7);

