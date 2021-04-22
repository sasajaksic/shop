/*
DROP SEQUENCE IF EXISTS proizvodjac_seq;
DROP SEQUENCE IF EXISTS proizvod_seq;
DROP SEQUENCE IF EXISTS racun_seq;
DROP SEQUENCE IF EXISTS stavka_racuna_seq;

CREATE SEQUENCE proizvodjac_seq INCREMENT 1;
CREATE SEQUENCE proizvod_seq INCREMENT 1;
CREATE SEQUENCE racun_seq INCREMENT 1;
CREATE SEQUENCE stavka_racuna_seq INCREMENT 1;
*/

/*****************************/
--Test podaci
INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (-101, 'Test2', 'Test2', 'Test2');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (-101, to_date('02.02.2000.', 'dd.mm.yyyy.'), 'Test2');
INSERT INTO "proizvod" ("id", "naziv", "proizvodjac")
VALUES (-101, 'Test2', -101);

INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (-100, 'Test', 'Test', 'Test');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (-100, to_date('01.01.2000.', 'dd.mm.yyyy.'), 'Test');

INSERT INTO "proizvod" ("id", "naziv", "proizvodjac")
VALUES (-100, 'Test', -101);
INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (-100, -101, -101, 1, 1, 'Test', 1);
/*****************************/

INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'HP', 'Palo Alto, California', '+381111111');
INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'Dell', 'Round Rock, Texas', '+382222222');
INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'Logitech', 'Lausanne, Switzerland', '+383333333');
INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'Microsoft', 'Redmond, Washington', '+384444444');


INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('01.01.2020.', 'dd.mm.yyyy.'), 'Gotovina');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('02.02.2020.', 'dd.mm.yyyy.'), 'Kartica');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('03.03.2020.', 'dd.mm.yyyy.'), 'Cek');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('04.04.2020.', 'dd.mm.yyyy.'), 'Gotovina');


INSERT INTO "proizvod" ("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'SPECTRE 700', 1);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Z3700', 1);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Pavilion 800', 1);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'PREMIER WM527', 2);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'MS819', 2);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'KB813', 2);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'MX Anywhere 2S', 3);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'MX Master 2S', 3);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'K780', 3);

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Arc', 3);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Surface', 3);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Natural Ergonomic 4000', 3);


INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_racuna_seq'), 1, 1, 1, 20, 'komad', 4000);
INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_racuna_seq'), 1, 3, 2, 30, 'komad', 12000);
INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_racuna_seq'), 1, 6, 3, 40, 'komad', 10000);

INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_racuna_seq'), 2, 2, 1, 2, 'kutija', 6000);
INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_racuna_seq'), 2, 4, 2, 7, 'komad', 7000);
INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_racuna_seq'), 2, 9, 3, 14, 'komad', 11000);

INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_racuna_seq'), 3, 5, 1, 8, 'kutija', 4000);
INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_racuna_seq'), 3, 7, 2, 4, 'kutija', 4500);
INSERT INTO "stavka_racuna"("id", "racun", "proizvod" ,"redni_broj", "kolicina", "jedinica_mere", "cena")
VALUES (nextval('stavka_racuna_seq'), 3, 8, 3, 25, 'komad', 6600);