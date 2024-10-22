CREATE TABLE tagu8226.Pirkejas (
    PIRK_ID           INTEGER         NOT NULL,
    Vardas            VARCHAR(30)     NOT NULL,
    Pavarde           VARCHAR(30)     NOT NULL,
    Adresas           VARCHAR(20)     NOT NULL,
    Telefonas         BIGINT          NOT NULL,
    PRIMARY KEY (PIRK_ID),
    CONSTRAINT AdresoDidzRaide CHECK (Adresas ~ '^[A-Z].*$')
);
CREATE TABLE tagu8226.Gamyklos (
    GAM_ID           INTEGER       NOT NULL,
    Adresas          VARCHAR(20)   NOT NULL,
    Pavadinimas      VARCHAR(30)   NOT NULL,
    PRIMARY KEY (GAM_ID)
);
CREATE TABLE tagu8226.Produktas (
    PROD_ID          INTEGER       NOT NULL,    
    GAM_ID           INTEGER       NOT NULL,
    Pavadinimas      VARCHAR(30)   NOT NULL,
    Aprasymas        VARCHAR(120)  DEFAULT 'Aprasymo nera',
    Kaina            FLOAT         NOT NULL,
    PRIMARY KEY (PROD_ID),
    CONSTRAINT IGamykla FOREIGN KEY (GAM_ID) REFERENCES tagu8226.Gamyklos ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT TeigiamaKaina CHECK(Kaina >= 0)
);
CREATE TABLE tagu8226.Uzsakymas (
    U_ID           INTEGER    NOT NULL,
    PIRK_ID        INTEGER    NOT NULL,
    UzsakymoData   DATE       NOT NULL,
    PROD_ID        INTEGER    NOT NULL,
    Kiekis         INTEGER    DEFAULT 1,
    PRIMARY KEY (U_ID),
    CONSTRAINT IPirkeja  FOREIGN KEY (PIRK_ID) REFERENCES tagu8226.Pirkejas  ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT IProdukta FOREIGN KEY (PROD_ID) REFERENCES tagu8226.Produktas ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT TeisingaData CHECK(UzsakymoData > '1900-01-01' AND UzsakymoData < CURRENT_DATE + INTERVAL '1 day')
);
