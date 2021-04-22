DROP TABLE IF EXISTS proizvodjac CASCADE;
DROP TABLE IF EXISTS proizvod CASCADE;
DROP TABLE IF EXISTS racun CASCADE;
DROP TABLE IF EXISTS stavka_racuna CASCADE;

DROP SEQUENCE IF EXISTS proizvodjac_seq;
DROP SEQUENCE IF EXISTS proizvod_seq;
DROP SEQUENCE IF EXISTS racun_seq;
DROP SEQUENCE IF EXISTS stavka_racuna_seq;

CREATE TABLE proizvodjac(
	id integer not null,
    naziv varchar(50) not null,
    adresa varchar(200),
	kontakt varchar(100)
);

CREATE TABLE proizvod(
	id integer not null,
    naziv varchar(50),
	proizvodjac integer not null
);

CREATE TABLE racun(
    id integer not null,
    datum date,
    nacin_placanja varchar(50)
);

CREATE TABLE stavka_racuna(
	id integer not null,
    redni_broj integer not null,
    kolicina numeric,
    jedinica_mere varchar(50),
    cena numeric,
    racun integer not null,
    proizvod integer not null
);

ALTER TABLE proizvodjac ADD CONSTRAINT PK_Proizvodjac PRIMARY KEY(id);
ALTER TABLE proizvod ADD CONSTRAINT PK_Proizvod PRIMARY KEY(id);
ALTER TABLE racun ADD CONSTRAINT PK_Racun PRIMARY KEY(id);
ALTER TABLE stavka_racuna ADD CONSTRAINT PK_Stavka_racuna PRIMARY KEY(id);

ALTER TABLE proizvod ADD CONSTRAINT 
FK_Proizvod_Proizvodjac FOREIGN KEY(proizvodjac) REFERENCES proizvodjac(id);
ALTER TABLE stavka_racuna ADD CONSTRAINT 
FK_Stavka_racuna_Proizvod FOREIGN KEY(proizvod) REFERENCES proizvod(id);
ALTER TABLE stavka_racuna ADD CONSTRAINT 
FK_stavka_racuna_Racun FOREIGN KEY(racun) REFERENCES racun(id);

CREATE INDEX IDPK_Proizvodjac ON proizvodjac(id);
CREATE INDEX IDPK_Proizvod ON proizvod(id);
CREATE INDEX IDPK_Racun ON racun(id);
CREATE INDEX IDPK_Stavka_racuna ON stavka_racuna(id);

CREATE INDEX IDXFK_Proizvod_Proizvodjac ON proizvod(proizvodjac);
CREATE INDEX IDXFK_Stavka_racuna_Proizvod ON stavka_racuna(proizvod);
CREATE INDEX IDXFK_Stavka_racuna_Racun ON stavka_racuna(racun);

CREATE SEQUENCE proizvodjac_seq INCREMENT 1;
CREATE SEQUENCE proizvod_seq INCREMENT 1;
CREATE SEQUENCE racun_seq INCREMENT 1;
CREATE SEQUENCE stavka_racuna_seq INCREMENT 1;