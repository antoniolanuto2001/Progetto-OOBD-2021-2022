--\Inizio File SQL DB E-learing Platform DB

/*
    ---------------------------
        !Creazione Domini!
    ---------------------------
*/
--Vincolo Di Dominio : EmailCheckUnina
CREATE DOMAIN EMAIL_DOMINIO AS VARCHAR(60)
	CHECK ( VALUE LIKE '%@studenti.unina.it' OR VALUE LIKE '%@unina.it' );
--Vincolo Di Dominio : PasswordDomainCheck
CREATE DOMAIN PASSWORD_DOMINIO AS VARCHAR(40)
	CHECK (VALUE ~ '^.*(?=.*[@!#$^*%&])(?=.*[0-9])(?=.*[a-zA-Z]).*$'
		AND VALUE LIKE '________%');
--Vincolo Di Dominio : UrlDocenteUnina
CREATE DOMAIN URL AS VARCHAR(60)
	CHECK ( VALUE LIKE 'https://www.docenti.unina.it/%' );
--Vincolo Di Dominio : GeneraliCorrette
CREATE DOMAIN GENERALI AS VARCHAR(60)
	CHECK ( VALUE <> '' AND VALUE NOT SIMILAR TO '%[0-9]+%' AND VALUE NOT SIMILAR TO '%[@!#$^*%&]+%'  );
--Vincolo Di Dominio : CodFiscaleCheck
CREATE DOMAIN CODICEFISCALE AS VARCHAR(16)
	CHECK (VALUE <> '' AND VALUE ~ '^.*(?=.*[0-9])(?=.*[A-Z]).*$' AND VALUE NOT SIMILAR TO '%[a-z]+%' AND VALUE NOT SIMILAR TO '%[@!#$^*%&]+%');
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
	Provincia VARCHAR(40),
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
	Nome GENERALI,
	Cognome GENERALI,
	DatadiNascita DATE,
	CodFiscale CODICEFISCALE,
	Sesso VARCHAR(1),
	CDL VARCHAR(50),
	Email EMAIL_DOMINIO,
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
	Nome GENERALI,
	Cognome GENERALI,
	DataDiNascita DATE,
	CodFiscale CODICEFISCALE,
	Sesso VARCHAR(1),
	Telefono INT,
	URL URL,
	Email EMAIL_DOMINIO,
	Password PASSWORD_DOMINIO,
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
	Denominazione VARCHAR(80),
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
	Nome VARCHAR(80),
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
	IdTestRiferimento INT,

	PRIMARY KEY(IdQuizA),
	FOREIGN KEY(IdTestRiferimento) REFERENCES  TEST(IdTest)
	ON DELETE CASCADE
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
	LetteraCorretta VARCHAR(1),
	Domanda VARCHAR(800),
	PunteggioRispostaCorretta INT,
	PunteggioRispostaSbagliata INT,
	IdTestRiferimento INT,
	
	PRIMARY KEY(IdQuizM),
	FOREIGN KEY(IdTestRiferimento) REFERENCES TEST(IdTest)
	ON DELETE CASCADE
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
	Risposta VARCHAR(300),
	LetteraToken VARCHAR(1),
	IDQuizRiferimento INT,

	PRIMARY KEY(IDRisposta) ,
	FOREIGN KEY(IDQuizRiferimento) REFERENCES QUIZMULTIPLA(IdQuizM)
	ON DELETE CASCADE
);
CREATE SEQUENCE IdRiSpostaSeq
START 1
INCREMENT 1
MINVALUE 1
MAXVALUE 300000
OWNED BY RISPOSTA.IDRisposta;
/*
    -----------------------------------
    		!Table-RISULTATOTEST!
    -----------------------------------
*/
CREATE TABLE RISULTATOTEST
(
	IdRisultatoTest SERIAL,
	Matricola VARCHAR(9),
	PunteggioTotale INT,
	DataTest DATE,
	IdTest INT,
	NumeroQuiz INT,

	PRIMARY KEY (IdRisultatoTest),
	FOREIGN KEY(IdTest) REFERENCES TEST(IdTest),
	FOREIGN KEY(Matricola) REFERENCES STUDENTE(Matricola)
);
/*
    ---------------------------------
    !Table-VALUTAZIONERISPOSTAAPERTA!
    ---------------------------------
*/
CREATE TABLE VALUTAZIONERISPOSTAAPERTA
(
	IdValutazioneAperta SERIAL,
	RispostaInserita VARCHAR(2000),
	PunteggioAssegnato INT,
	Valutata BOOLEAN,
	Matricola VARCHAR(9),
	IdDocente VARCHAR(5),
	IdRisultatoTest INT,
	IdQuizA INT,

	PRIMARY KEY (IdValutazioneAperta),
	FOREIGN KEY(IdRisultatoTest) REFERENCES RISULTATOTEST(IdRisultatoTest)
	ON DELETE CASCADE,
	FOREIGN KEY(IdDocente) REFERENCES DOCENTE(IdDocente),
	FOREIGN KEY(Matricola) REFERENCES STUDENTE(Matricola),
	FOREIGN KEY(IdQuizA) REFERENCES QUIZAPERTA(IdQuizA)

);
CREATE SEQUENCE IdValutazioneApertaSeq
START 1
INCREMENT 1
MINVALUE 1
MAXVALUE 300000
OWNED BY VALUTAZIONERISPOSTAAPERTA.IdValutazioneAperta;
/*
    -----------------------------------
    !Table-VALUTAZIONERISPOSTAMULTIPLA!
    -----------------------------------
*/

CREATE TABLE VALUTAZIONERISPOSTAMULTIPLA
(
	IdValutazioneMultipla SERIAL,
	LetteraInserita VARCHAR(1),
	Matricola VARCHAR(9),
	PunteggioOttenuto INT,
	Valutata BOOLEAN,
	IdRisultatoTest INT,
	IdQuizM INT,

	PRIMARY KEY (IdValutazioneMultipla),
	FOREIGN KEY(IdRisultatoTest) REFERENCES RISULTATOTEST(IdRisultatoTest)
	ON DELETE CASCADE,
	FOREIGN KEY(Matricola) REFERENCES STUDENTE(Matricola),
	FOREIGN KEY(IdQuizM) REFERENCES QUIZMULTIPLA(IdQuizM)
);
CREATE SEQUENCE IdValutazioneMultiplaSeq
START 1
INCREMENT 1
MINVALUE 1
MAXVALUE 300000
OWNED BY VALUTAZIONERISPOSTAMULTIPLA.IdValutazioneMultipla;

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



--------------------------------- Funzione Di Correzione Automatica Delle Domande A Risposta Multipla --------------------------------

--Fase 1
-----Creo Come prima cosa la funzione che aggiorna il punteggio del risultato del test --- 
CREATE OR REPLACE FUNCTION CorreggiMultipla(ValidDaFunction valutazionerispostamultipla.idvalutazionemultipla%TYPE)
RETURNS void 

AS
$CorreggiMultipla$
DECLARE
letteraCorrettaDaquery quizmultipla.letteracorretta%TYPE; 
valutazioneMultipla valutazionerispostamultipla%ROWTYPE;
quizMultiplo quizmultipla%ROWTYPE;
idquizmDaquery quizmultipla.idquizm%TYPE;
punteggioOttenutoNuovo int;

BEGIN
	SELECT * FROM valutazionerispostamultipla
	INTO valutazioneMultipla WHERE idvalutazionemultipla=ValidDaFunction;
	
	idquizmDaquery:=valutazioneMultipla.idquizm;
	 
	
	SELECT * FROM quizmultipla INTO quizMultiplo WHERE idquizm=idquizmDaquery;
	
	letteraCorrettaDaquery:=quizMultiplo.letteracorretta;
	IF letteraCorrettaDaquery = valutazioneMultipla.letterainserita THEN
	 	punteggioOttenutoNuovo:=quizmultiplo.punteggiorispostacorretta; 	
		
	ELSE
	punteggioOttenutoNuovo:=quizmultiplo.punteggiorispostasbagliato; 
	END IF;
	UPDATE valutazionerispostamultipla
	SET punteggioottenuto=punteggioOttenutoNuovo
	WHERE idvalutazionemultipla=ValidDaFunction; 

	UPDATE risultatotest
	SET punteggiototale=punteggiototale+punteggioOttenutoNuovo
	WHERE idrisultatotest=valutazioneMultipla.idrisultatotest;
	
END;
$CorreggiMultipla$ LANGUAGE plpgsql;
--Fase 2
-----Creo il trigger addetto al controllo dell effettivo lancio della funzione --- 

CREATE OR REPLACE FUNCTION TriggerCorreggiMultipla()
  RETURNS TRIGGER 
  AS
$TriggerCorreggiMultipla$
BEGIN
	IF NEW.valutata=false THEN
	PERFORM correggimultipla(NEW.idvalutazionemultipla);
	UPDATE valutazionerispostamultipla
	SET valutata = true 
	WHERE idvalutazionemultipla=NEW.idvalutazionemultipla;
	END IF;
	RETURN NEW;
END;
$TriggerCorreggiMultipla$ LANGUAGE plpgsql;
--Fase 3
-----Creo il trigger vero e proprio lanciatore 
CREATE TRIGGER correggiMultipleTriggerLanciatore
AFTER INSERT
ON valutazionerispostamultipla
FOR EACH ROW 
EXECUTE PROCEDURE TriggerCorreggiMultipla();

--------------------------------- Funzione Di Aggiornamento Della Correzione Delle Domande A Risposta Aperta --------------------------------

--Fase 1
-----Creo Come prima cosa la funzione che aggiorna il punteggio del risultato del test --- 
CREATE OR REPLACE FUNCTION AggiornaPunteggioAperta(ValidDaFunction valutazionerispostaaperta.idvalutazioneaperta%TYPE)
RETURNS void 

AS
$AggiornaPunteggioAperta$
DECLARE 
valutazioneaperta valutazionerispostaaperta%ROWTYPE;
quizAperta quizaperta%ROWTYPE;
idquizaDaquery quizaperta.idquiza%TYPE;
punteggioOttenuto int;

BEGIN
	SELECT * FROM valutazionerispostaaperta
	INTO valutazioneaperta WHERE idvalutazioneaperta=ValidDaFunction;
	
	idquizaDaquery:=valutazioneaperta.idquiza;
	 
	
	SELECT * FROM quizaperta INTO quizAperta WHERE idquiza=idquizaDaquery;
	

	IF valutazioneaperta.PunteggioAssegnato<=quizAperta.PunteggioMassimo AND valutazioneaperta.PunteggioAssegnato>=quizAperta.PunteggioMinimo   THEN
	 	punteggioOttenuto:=valutazioneaperta.PunteggioAssegnato; 	
		
	ELSE
	raise notice 'Errore nell assegnazione del codice '; 
	END IF;
	UPDATE risultatotest
	SET punteggiototale=punteggiototale+punteggioOttenuto
	WHERE idrisultatotest=valutazioneaperta.idrisultatotest;
	
END;
$AggiornaPunteggioAperta$ LANGUAGE plpgsql;
--Fase 2
-----Creo il trigger addetto al controllo del effettivo lancio della funzione 
CREATE OR REPLACE FUNCTION TriggerCorreggiAperta()
  RETURNS TRIGGER 

  AS
$TriggerCorreggiAperta$
BEGIN
	IF NEW.valutata=true THEN
	PERFORM AggiornaPunteggioAperta(NEW.idvalutazioneaperta);
	END IF;
	RETURN NEW;
END;
$TriggerCorreggiAperta$   LANGUAGE PLPGSQL;
--Fase 3
-----Creo il trigger vero e proprio lanciatore ---
CREATE trigger CorreggiApertaLanciatore
AFTER UPDATE
ON valutazionerispostaaperta
FOR EACH ROW 
EXECUTE PROCEDURE TriggerCorreggiAperta();

---------------------------------Funzione Di Aggiornamento Automatico dei Quiz --------------------------------

CREATE OR REPLACE FUNCTION aggiornaNumeroQuiz()
  RETURNS TRIGGER 
AS
$TriggerAggiungiQuiz$
BEGIN
	UPDATE Test
	SET numeroquiz=numeroquiz+1
	where idtest=NEW.idtestriferimento;
	RETURN NEW;
END;
$TriggerAggiungiQuiz$   LANGUAGE PLPGSQL;
--Trigger Aperta
CREATE trigger AggiugniQuizApertaLanciatore
AFTER INSERT
ON quizaperta
FOR EACH ROW 
EXECUTE PROCEDURE aggiornaNumeroQuiz();
--Trigger Multipla
CREATE trigger AggiugniQuizApertaLanciatore
AFTER INSERT
ON quizmultipla
FOR EACH ROW 
EXECUTE PROCEDURE aggiornaNumeroQuiz();

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
('40147','Dipartimento di Scienze Politiche','Prof. Vittorio Amato','Napoli','Napoli','Via Leopoldo Rodinò 22',80138),
('40132','Dipartimento di  Ingegneria Elettrica e delle Tecnologie dell Informazione','Prof. Fabio Villone','Napoli','Napoli','Via Claudio 21',80125),
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
(default,'Prova di Analisi I -Funzioni',120,4,'05/01/2022','D2442'),
(default,'Prova di Analisi I -Teoria',60,4,'05/09/2022','D2442'),
(default,'Prova di Analisi I -Serie',120,4,'05/13/2022','D2442'),
(default,'Diritto Privato',120,4,'06/15/2022','D2732'),
(default,'Diritto Penale',120,3,'06/19/2022','D2732'),
(default,'Filosofia del Diritto',100,4,'06/18/2022','D2732'),
(default,'Architettura degli Elaboratori -Prima parte',120,3,'06/20/2022','D1432'),
(default,'Architettura degli Elaboratori -Seconda parte',120,5,'06/25/2022','D1432'),
(default,'Architettura degli Elaboratori -Terza parte',100,3,'06/24/2022','D1432'),
(default,'CITOLOGIA ED ISTOLOGIA E LABORATORIO',200,4,'06/22/2022','D9934'),
(default,'ENDOCRINOLOGIA APP. SOSTANZE STUPEFACENTI',120,4,'06/15/2022','D9934'),
(default,'APPLICAZIONI IN BIOINFORMATICA',100,5,'06/20/2022','D9442'),
(default,'BIOLOGIA MOLECOLARE FORENSE',200,6,'06/22/2022','D9442'),
(default,'Laboratorio di Programmazione',120,4,'06/15/2022','D2311'),
(default,'Test studenti 2019',100,3,'06/19/2022','D2311'),
(default,'ANALISI DEL RISCHIO CREDITIZIO',100,3,'06/10/2022','D4231'),
(default,'EVOLUTION OF FINANCIAL MARKETS',120,4,'06/12/2022','D4231'),
(default,'FONDAMENTI DI AGRONOMIA',100,3,'06/13/2022','D2422'),
(default,'LABORATORIO DI COLTIVAZIONE DELLE PIANTE',60,3,'06/10/2022','D2422'),
(default,'PERFORMANCE MANAGEMENT SYSTEMS',120,5,'06/24/2022','D6242');

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
(default,'C','A cosa serve una Memoria Cache?',1,0,7),
(default,'A','Come si ottengono prestazioni migliori con le Cache?',1,0,7),
(default,'B','La differenza tra Architettura RISC e CISC',1,0,7),
(default,'B','Quale tra le seguenti affermazioni è corretta?',1,0,1),
(default,'A','Quale tra queste è la definizione corretta di funzione?',1,0,1),
(default,'B','Una funzione monotona è...',1,0,1),
(default,'A','Quale tra le seguenti affermazioni è corretta:',3,-1,2),
(default,'A','Quale  tra queste risposte richiama il teorema di Weierstrass?',3,-1,2),
(default,'B','Quali sono gli elementi essenziali del contratto?',2,0,4),
(default,'D','Quale  tra queste risposte è corretta?',2,0,4),
(default,'A','Cosa sono le norme imperative?',2,0,4),
(default,'A','Quale  tra queste risposte è corretta?',4,0,4),
(default,'C','Definizione di principio di tassatività',4,0,5),
(default,'C','Definizione di principio di tipicità',4,0,5),
(default,'A','Definizione di recidiva',2,0,5),
(default,'C','Differenza tra cooperazione e coordinazione',3,0,6),
(default,'B','Ermeneutica:',3,0,6),
(default,'B','Diritti umani sono un modello di legge naturale?',3,0,6),
(default,'A','Quale tra le seguenti affermazioni è corretta?',2,0,8),
(default,'A','Quale tra le seguenti è la definizione di BIOS?',2,0,8),
(default,'B','Quanti Bit contiene un Byte?',2,0,8),
(default,'A','Una operazione di Push...',2,0,9),
(default,'D','Una operazione di Pop...',2,0,9),
(default,'D','Da cosa riconosci immediatamente un valore esadecimale?',4,0,9),
(default,'C','Ormone controregolatore che causa iperglicemia:',3,0,11),
(default,'C','Definizione di adenoma ipofisario',2,0,11),
(default,'C','Definizione di T-Test',2,-1,12),
(default,'A','Definizione di geni omologhi',2,-1,12),
(default,'A','Definizione di nucleosoma',2,-1,12),
(default,'B','Cosa studia la BioInformatica?',2,-1,12),
(default,'B','Definizione di fenotipo',2,-1,12),
(default,'A','Una Multiplex PCR è...',2,0,13),
(default,'D','Il Combur test è...',2,0,13),
(default,'A','La frequenza allelica è...',2,0,13),
(default,'B','Quale tra le seguenti tracce biologiche è visibile alla luce bianca?',2,0,13),
(default,'B','Un IDE è...',4,0,15),
(default,'C','A cosa serve la JDK?',4,0,15),
(default,'B','Cosa sono i  Design Pattern?',2,0,15),
(default,'A','Cosa sono i titoli di debito',3,0,16),
(default,'C','Quali sono le posizioni fuori bilancio',3,0,16),
(default,'C','In quali categorie si possono distinguere gli strumenti finanziari soggetti al rischio di credito?',3,0,16),
(default,'C','Stabilire quale tra le seguenti è la giusta definizione per sistema colturale:',4,-1,18),
(default,'A','Cosa significa monosuccessione?',4,-1,18),
(default,'B','Una intensa retrogradazione del fosforo avviene:',2,-1,18),
(default,'C','Quanti sono i principali tipi di piante?',3,0,19),
(default,'A','Da cosa sono particolarmente distinguibili i vari tipi di piante?',3,0,19),
(default,'D','Quante sono le specie di piante sulla Terra? Indica un valore circa:',2,0,19),
(default,'D','Dai una definizione di valutazione Multirater',2,0,20),
(default,'A','La "Bibbia" dei libri di Performance Management è...',2,0,20);

/*
  ---------------------------------
    !INSERT->TABLE->RISPOSTA!
  ---------------------------------
*/
INSERT INTO RISPOSTA values

(default,'Aumentare il Clock della RAM','A',1),
(default,'Aumentare la capienza del disco','B',1),
(default,'Velocizzare gli accessi alla memoria principale','C',1),
(default,'Con Cache piccole','A',2),
(default,'Con Cache grandi','B',2),
(default,'Senza Cache','C',2),
(default,'Set di istruzioni complesso per la RISC e Set di istruzioni ridotto per la CISC','A',3),
(default,'Set di istruzioni ridotto per la RISC e Set di istruzioni complesso per la CISC','B',3),
(default,'Reduced Implementation Set Computing e Complex Instruction Set Computing','C',3),
(default,'Informatica deriva dall unione di Informazione e Automatica ed è la scienza che si occupa del trattamento e della trasmissione delle informazioni','A',4),
(default,'Informatica deriva dall unione di Informazione e Matematica ed è la scienza che si occupa della promozione della scienza e della tecnologia','B',4),
(default,'Nessuna tra quelle elencate','C',4),
(default,'Relazione tra due insiemi che associa a ogni elemento del dominio uno e un solo elemento del codominio','A',5),
(default,'Relazione tra due insiemi che associa a ogni elemento del codominio uno e un solo elemento del dominio','B',5),
(default,'Relazione tra due insiemi che associa ad uno ed un solo elemento del dominio, uno ed un solo elemento del codominio','C',5),
(default,'Una funzione monotona e una funzione che ha una sola nota ','A',6),
(default,'Una funzione che da un certo punto in poi chiamato Xo seguire un andamento costante discendente o crescente','B',6),
(default,'Una funzione che solamente cresce sempre di valore in valore ','C',6),
(default,'Il teorema dei due carabinieri permette di calcolare il limite di una successione o funzione confrontando questa con altri due oggetti analoghi','A',7),
(default,'Il teorema dei due carabinieri afferma che una funzione ammette limite in un punto reale se e soltanto se accade che il punto e di accumulazione per il dominio della funzione','B',7),
(default,'Il teorema dei due carabinieri afferma che se una funzione in un punto p è dotata di limite diverso da 0, allora esiste almeno un intorno I di p tale che per tutti i punti di I i valori della funzione hanno lo stesso segno del limite','C',7),
(default,'Nessuna delle risposte elencate','D',7),
(default,'Una funzione continua in un intervallo chiuso e limitato è dotata di un massimo e minimo assoluti','A',8),
(default,'Nessuna delle risposte elencate','B',8),
(default,'Una funzione continua in un intervallo chiuso e limitato assume tutti i valori compresi tra il minimo m ed il suo massimo M','C',8),
(default,'Il teorema indicato non è un teorema matematico','D',8),
(default,'Lo stato, la causa,la forma','A',9),
(default,'Accordo delle parti, la causa, l oggetto, la forma','B',9),
(default,'La convivenza,la firma, la causa, l oggetto, la forma','C',9),
(default,'Nessuna delle risposte elencate','D',9),
(default,'Il diritto privato è suddiviso in diritto agrario e del lavoro','A',10),
(default,'Il diritto privato è suddiviso in diritto civile, penale e commerciale','B',10),
(default,'Il diritto privato è suddiviso in diritto dell autonomia privata e diritto commerciale','C',10),
(default,'Nessuna delle risposte elencate','D',10),
(default,'Norme che non possono essere derogate','A',11),
(default,'Norme derogabili','B',11),
(default,'Norme derogabili solo a figure professionali certificate','C',11),
(default,'In diritto la rappresentanza è l istituto per cui ad un soggetto è attribuito un apposito potere di sostituirsi ad un altro soggetto nel compimento di una o più attività giuridiche','A',12),
(default,'In diritto la rappresentanza è la persona incaricata di agire per conto altrui','B',12),
(default,'Nessuna delle risposte elencate','C',12),
(default,'Nessuna delle risposte elencate','A',13),
(default,'Afferma che tutti gli organi dello stato sono tenuti ad agire secondo la legge','B',13),
(default,'Principio che impone al legislatore penale di uniformarsi a una tecnica di formulazione della norma atta ad assicurare una precisa determinazione della fattispecie legale','C',13),
(default,'Afferma che l autorità amministrativa ha il potere di emanare solo atti disciplinati nel contenuto','A',14),
(default,'Nesuna delle risposte elencate','B',14),
(default,'Indica il fatto che la norma penale si applica unicamente agli accadimenti reali che effettivamente si riconnettono alla fattispecie astratta','C',14),
(default,'Ripetizione di un reato da parte di chi è stato in precedenza condannato con sentenza irrevocabile','A',15),
(default,'Ripetizione di una condanna per uno stesso soggetto imputato','B',15),
(default,'Ripetizione di una condanna nell ambito della stessa seduta amministrativa','C',15),
(default,'Nessuna delle risposte elencate','A',16),
(default,'Ogni scelta sociale si riduce alla sola decisione di cooperare','B',16),
(default,'La cooperazione si fonda sulla scelta razionale della collaborazione','C',16),
(default,'Nessuna delle risposte elencate','A',17),
(default,'Arte, tecnica e attività di interpretare il senso di testi antichi','B',17),
(default,'Disciplina per la codifica di manoscritti filosofici','C',17),
(default,'No','A',18),
(default,'Si','B',18),
(default,'Nessuna delle risposte elencate','C',18),
(default,'La pipeline dati in informatica è una tecnologia utilizzata nell architettura hardware dei microprocessori per incrementare il throughoput','A',19),
(default,'La pipeline in informatica è una applicazione di gestione delle risorse di sistema','B',19),
(default,'Nessuna delle risposte elencate','C',19),
(default,'La pipeline in informatica è una rete di connessione di tipo AdvancedProtocol utilizzata per la protezione dei dati','D',19),
(default,'Modulo software eseguito al momento dell accensione del computer per configurare il funzionamento dei dispositivi di ingresso','A',20),
(default,'Modulo software di scanning problematiche all avvio del computer','B',20),
(default,'Modulo hardware di controllo sicurezza sulla motherboard','C',20),
(default,'Nessuna delle risposte elencate','D',20),
(default,'4 bit','A',21),
(default,'8 bit','B',21),
(default,'12 bit','C',21),
(default,'Aggiunge nello stack','A',22),
(default,'Rimuove dallo stack','B',22),
(default,'Modifica lo stack','C',22),
(default,'Nessuna tra le risposte elencate','A',23),
(default,'Modifica lo stack','B',23),
(default,'Aggiunge nello stack','C',23),
(default,'Rimuove dallo stack','D',23),
(default,'Nessuna tra le risposte elencate','A',24),
(default,'Dal numero di cifre','B',24),
(default,'Dalla sua fine: #FF','C',24),
(default,'Dal suo inizio: 0x','D',24),
(default,'Ormone eicosanoide','A',25),
(default,'Ormone tiroxina','B',25),
(default,'Ormone insulina','C',25),
(default,'Un disturbo psicofisico','A',26),
(default,'Una contrattura','B',26),
(default,'Un tumore benigno','C',26),
(default,'Test orofaringeo','A',27),
(default,'Test sanguigno','B',27),
(default,'Test statistico di tipo parametrico','C',27),
(default,'due geni sono omologhi se hanno un gene ancestrale da cui sono originati entrambi','A',28),
(default,'geni di specie diverse che si sono evoluti da un gene ancestrale comune mediante speciazione','B',28),
(default,'Nessuna tra le risposte elencate','C',28),
(default,'Unità fondamentale della cromatina','A',29),
(default,'Nucleo degli octoplasti','B',29),
(default,'Nessuna tra le risposte elencate','C',29),
(default,'Studia i metodi per la coltivazione artificiale','A',30),
(default,'Studia algoritmi, metodologie e strumenti software per l analisi di dati biologici','B',30),
(default,'Nessuna tra le risposte elencate','C',30),
(default,'Insieme dei geni che compongono il DNA','A',31),
(default,'Insieme delle caratteristiche morfologiche','B',31),
(default,'Nessuna tra le risposte elencate','C',31),
(default,'Sistema multifunzione per scopi di sicurezza, guidabilità e confort','A',32),
(default,'Sistema telematico di connessione a componenti hardware','B',32),
(default,'Nessuna tra le risposte elencate','C',32),
(default,'Nessuna tra le risposte elencate','A',33),
(default,'Il test non esiste','B',33),
(default,'Test SkinConsult per la valutazione della pelle','C',33),
(default,'Test delle urine','D',33),
(default,'La frazione di loci che l allele occupa nella popolazione','A',34),
(default,'Nessuna tra le risposte elencate','B',34),
(default,'La proporzione di individui in una popolazione che presenta un particolare gene','C',34),
(default,'Sudore','A',35),
(default,'Sangue','B',35),
(default,'Nessuna tra le risposte elencate','C',35),
(default,'Un software per la gestione dei processi','A',36),
(default,'Un ambiente di sviluppo','B',36),
(default,'Un editor di testo','C',36),
(default,'Il Kernel di un sistema operativo','A',37),
(default,'Applicazione per il monitoraggio della banda di rete','B',37),
(default,'Insieme di strumenti necessari per la compilazione, l esecuzione e il testing di applicazioni scritte in Java','C',37),
(default,'Metodi per la modifica di colori e font','A',38),
(default,'Soluzione progettuale standard ad un problema ricorrente','B',38),
(default,'Applicazione di disegno progettuale','C',38),
(default,'Titoli che vengono emessi da soggetti che hanno bisogno di finanziamenti','A',39),
(default,'Titoli dello stato','B',39),
(default,'Titoli a saldo compiuto','C',39),
(default,'Nessuna tra le risposte elencate','A',40),
(default,'Banche e Governi','B',40),
(default,'Banche, Governi, Clientela e Società finanziarie','C',40),
(default,'Nessuna tra le risposte elencate','A',41),
(default,'Titoli azionari e di NT','B',41),
(default,'Titoli di debito e posizioni fuori bilancio','C',41),
(default,'Nessuna tra le risposte elencate','A',42),
(default,'Tecniche di manutenzione','B',42),
(default,'Associazione di produzioni e tecniche in interazione dinamica','C',42),
(default,'Tecnica di avvicendamento dei seminativi ','A',43),
(default,'Tecnica di pulizia e manutenzione','B',43),
(default,'Tecnica di condizionalità','C',43),
(default,'In terreni con poco potere assorbente','A',44),
(default,'In terreni molto calcarei','B',44),
(default,'In terreni poco calcarei','C',44),
(default,'480 000 circa','A',45),
(default,'350 000 circa','B',45),
(default,'400 000 circa','C',45),
(default,'Dalle foglie','A',46),
(default,'Dal colore dello stelo','B',46),
(default,'Nessuna tra le risposte elencate','C',46),
(default,'80.100 specie','A',47),
(default,'45.050 specie','B',47),
(default,'Non conosciamo questo valore','C',47),
(default,'60.065 specie','D',47),
(default,'Nessuna tra le risposte elencate','A',48),
(default,'Valutazione specifica della fattibilità','B',48),
(default,'Valutazione specifica della manovrabilità','C',48),
(default,'Feedback a 360 gradi','D',48),
(default,'Coaching for Performance','A',49),
(default,'Manage the Performance','B',49),
(default,'Another level: The Performance Management Book','C',49);

/*
  ---------------------------------
    !INSERT->TABLE->QUIZAPERTA!
  ---------------------------------
*/
INSERT INTO  quizaperta VALUES
(default,'Come si chiama quella congettura per cui tutti i numeri pari maggiori di 4 sono la somma di 2 numeri primi?','Si chiama congettura di Goldbach',100,0,3,2),
(default,'Scrivi un teorema a piacere riguardante le successioni','Se una successione crescente (strettamente o debolmente) o definitivamente crescente (strettamente o debolmente) è limitata superiormente, allora essa è convergente, cioè ammette limite.',350,-1,3,2),
(default,'Quanti tipi di serie conosci?','Serie Geometrica, Serie Numerica e Serie di Taylor',200,-1,3,3),
(default,'Dai una definizione di Serie Geometrica','Serie Geometrica, Serie Numerica e Serie di Taylor',200,-1,3,3),
(default,'Dai una definizione di Serie Numerica','Serie Geometrica, Serie Numerica e Serie di Taylor',200,-1,3,3),
(default,'Dai una definizione di Serie a termini positivi','Serie Geometrica, Serie Numerica e Serie di Taylor',200,-1,3,3),
(default,'E possibile individuare valori fondamentali comuni a tutti gli uomini? Quali dovrebbero essere?','Tutti i cittadini hanno pari dignità sociale e sono eguali davanti alla legge, senza distinzione di sesso, di razza, di lingua, di religione, di opinioni politiche, di condizioni personali e sociali. Inoltre La Repubblica riconosce a tutti i cittadini il diritto al lavoro e promuove le condizioni che rendano effettivo questo diritto.
',350,0,1,6),
(default,'Quali sono i principali flip flop che abbiamo studiato?','Set-Reset , JK, TIPO T, TIPO D',100,0,3,7),
(default,'Dai una definizione di Data Path','Insieme di unità di calcolo (registri,ALU,ecc.) necessari per eseguire istruzioni nella CPU',200,0,2,8),
(default,'Indica la differenza in una assegnazione C rispetto ad una Assembly','int var = 5,MOV R1,5',30,0,2,8),
(default,'Indica brevemente una definizione di studio di funzione','Indica una applicazione pratica dei teoremi e delle tecniche del calcolo infinitesimale nello specifico caso di una funzione di cui è nota una espressione analitica',300,0,2,1),
(default,'Dove è contenuta l informazione genetica delle cellule di tutti gli organismi viventi?','DNA',50,0,4,10),
(default,'Definizione di Mitosi','Processo di divisione cellulare',50,0,4,10),
(default,'Cosa sono gli alleli?','Gli alleli sono diverse forme di un gene',100,0,2,10),
(default,'Cosa si intende per struttura primaria,secondaria,terziaria e quaternaria?','Conformazione delle proteine',100,0,2,10),
(default,'La malattia di Graves è...','Una forma di ipertiroidismo',100,0,3,11),
(default,'La sindrome di Cushing è...','Una condizione da ipercortisolismo che insorge quando nell organismo sono in circolo eccessive dosi di ormoni glucocorticoidi',200,0,2,11),
(default,'Definizione di real Time PCR','Metodo che simultaneamente amplifica e quantifica il DNA',100,0,3,13),
(default,'Definizione di Random Match Probability','La frequenza del profilo genetico nella popolazione di riferimento',100,0,2,13),
(default,'Scrivi una funzione che ritorni la radice quadrata di un valore x dato in input','int returnradice (int x){return rad(x)}',100,0,3,14),
(default,'Scrivi una funzione che ritorni la potenza al quadrato di un valore x dato in input','int returnpotenza (int x){return pow(x,2)}',100,0,3,14),
(default,'Crea un nodo con una typedef','typedef struct Nodo{int x; struct Nodo *next;}Nodo;',100,0,3,14),
(default,'Dai una definizione sintetica di ecompilatore','Un compilatore è un programma che traduce una serie di istruzioni scritte in un linguaggio di programmazione in codice oggetto',100,0,3,14),
(default,'Cosa indica il rischio di credito?','Indica la possibilità che un soggetto non adempia agli obblichi di pagamento o rimbborso verso il creditore',100,0,2,16),
(default,'Quali fattori determinano il merito di credito di un cliente?','I bilanci e il loro andamento annuale, gli eventi negativi, l evoluzione aziendale e gli aspetti relativi alla posizione geografica',200,0,2,16),
(default,'Quali sono le 7 domande del mondo del marketing?','Chi, Come, Che cosa, Come, Dove, Quando, Perchè',40,0,2,17),
(default,'Che domande proporresti per una efficace strategia di marketing aziendale?','I soggetti che rientrano nel macroambiente, la strategia di posizionamento strategico, la finalità della segmentazione del mercato e eventuali progetti nel campo della pubblicità off e on line',200,0,4,17),
(default,'Chi è l unico giudice del mercato?','Il cliente',20,0,2,17),
(default,'Quali sono gli aspetti e le ipotessi di marketing che si possono testare online?','Il mercado più profittevole, il posizionamento del prodotto, il target clientela, il funnel, ovvero i canali di acquisizione e conversione',200,0,2,17),
(default,'Un sistema di gestione della performance è...','Insieme dei processi gestionali finalizzati ad indirizzare e condurre i comportamenti individuali e collettivi alla realizzazione degli obiettivi aziendali',300,0,4,20),
(default,'Cosa consente di fare un PMS efficace?','Definire, allineare ed assegnare gli obiettivi aziendali nell organizzazione',200,0,4,20),
(default,'Cosa permette di innescare percorsi di sviluppo efficaci e partecipati?','Dare una visione chiara di tutto il processo. Restituire una risposta mirata ai piani di sviluppo individuale. Fornire una guida all uso degli strumenti operativi.',300,0,4,20);

/*
  ---------------------------------
    !INSERT->TABLE->GESTIONE!
  ---------------------------------
*/
INSERT INTO Gestione VALUES
(default,'CREAZIONE','2022-01-01 09:00:00','D1432',7),
(default,'CREAZIONE','2022-01-01 09:20:40','D1432',8),
(default,'CREAZIONE','2022-01-01 09:30:32','D1432',9),
(default,'CREAZIONE','2022-01-03 10:00:11','D2442',1),
(default,'CREAZIONE','2022-01-03 10:10:13','D2442',2),
(default,'CREAZIONE','2022-01-03 10:20:05','D2442',3),
(default,'CREAZIONE','2022-01-14 10:00:02','D2311',14),
(default,'CREAZIONE','2022-01-14 11:20:01','D2311',15),
(default,'CREAZIONE','2022-01-10 07:00:24','D2732',4),
(default,'CREAZIONE','2022-01-10 07:20:56','D2732',5),
(default,'CREAZIONE','2022-01-10 10:30:36','D2732',6),
(default,'CREAZIONE','2022-01-12 09:20:15','D9934',10),
(default,'CREAZIONE','2022-01-12 09:30:12','D9934',11),
(default,'CREAZIONE','2022-01-18 08:20:51','D9442',12),
(default,'CREAZIONE','2022-01-18 08:30:23','D9442',13),
(default,'CREAZIONE','2022-02-01 10:40:51','D4231',16),
(default,'CREAZIONE','2022-02-01 11:20:38','D4231',17),
(default,'CREAZIONE','2022-02-02 10:10:11','D2422',18),
(default,'CREAZIONE','2022-02-02 11:40:42','D2422',19),
(default,'CREAZIONE','2022-02-04 09:15:27','D6242',20),
(default,'MODIFICA','2022-01-02 07:20:10','D1432',7),
(default,'MODIFICA','2022-01-01 15:32:20','D1432',8),
(default,'MODIFICA','2022-01-01 19:20:33','D1432',9),
(default,'MODIFICA','2022-01-10 13:00:50','D2442',1),
(default,'MODIFICA','2022-01-03 16:20:53','D2442',2),
(default,'MODIFICA','2022-01-09 08:30:35','D2442',3),
(default,'MODIFICA','2022-01-14 09:30:22','D2311',14),
(default,'MODIFICA','2022-01-15 16:30:01','D2311',15),
(default,'MODIFICA','2022-01-10 09:00:34','D2732',4),
(default,'MODIFICA','2022-01-13 10:21:36','D2732',5),
(default,'MODIFICA','2022-01-10 17:24:16','D2732',6),
(default,'MODIFICA','2022-02-05 09:13:11','D4231',16),
(default,'MODIFICA','2022-02-03 09:40:48','D4231',17);



CREATE USER silvio_barra PASSWORD 'Basedidati01!'; -- Creazione Nuovo Utente 
GRANT ALL ON ALL TABLES IN SCHEMA public TO Silvio_Barra; --Assegnazione Privileggi

/* ----------------------------------- */


