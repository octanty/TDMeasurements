
-- insert producers
INSERT INTO PA165.PRODUCER (ADDRESS, ICO, NAME) VALUES ('Palírenská 641/2, 326 00, Plzeň - Božkov Česká republika', 27904636, 'STOCK Plzeň – Božkov s.r.o.');
INSERT INTO PA165.PRODUCER (ADDRESS, ICO, NAME) VALUES ('Na Kůtku 14, 768 72 Chvalčov', 26304465, 'Likérka Drak s.r.o.');
INSERT INTO PA165.PRODUCER (ADDRESS, ICO, NAME) VALUES ('Praha 6, Dejvice, Velvarská 1626/45', 27250555, 'PRAŽSKÁ VODKA & DESTILÁTY, spol. s r.o.');
INSERT INTO PA165.PRODUCER (ADDRESS, ICO, NAME) VALUES ('Razov 472 763 12 Vizovice', 49971361, 'RUDOLF JELÍNEK a.s.');
INSERT INTO PA165.PRODUCER (ADDRESS, ICO, NAME) VALUES ('687 63 Boršice u Blatnice 57', 26224496, 'ŽUSY s.r.o.');


-- IDs
-- "STOCK Plzeň – Božkov s.r.o.": select id from producer where ico =  27904636 LIMIT 1
-- "Likérka Drak s.r.o.": select id from producer where ico =  26304465
-- "PRAŽSKÁ VODKA & DESTILÁTY, spol. s r.o.": select id from producer where ico =  27250555  LIMIT 1
-- "RUDOLF JELÍNEK a.s.": select id from producer where ico =  49971361 LIMIT 1
-- "ŽUSY s.r.o.": select id from producer where ico =  26224496 LIMIT 1



-- insert stores
INSERT INTO PA165.STORE (ADDRESS, ICO, NAME) VALUES ('Obecní dům, U Prašné brány 1090/2, 111 21 Praha 1', 25928082, 'Kubík a.s.');
INSERT INTO PA165.STORE (ADDRESS, ICO, NAME) VALUES ('Slavíčkova 1a, 638 00, Brno', 44012373, 'AHOLD Czech Republic, a.s');
INSERT INTO PA165.STORE (ADDRESS, ICO, NAME) VALUES ('Purkyňova 45, Brno, 612 00', 60736135, 'Maloobchodní síť BRNĚNKA, spol. s r.o.');

-- IDs
-- "Kubík a.s." : select id from store where ico=25928082
-- "AHOLD Czech Republic, a.s" : select id from store where ico=44012373
-- "Maloobchodní síť BRNĚNKA, spol. s r.o." : select id from store where ico=60736135



-- insert liquors (selecting producer by ico)
INSERT INTO PA165.LIQUOR(ALCOHOLPERCENTAGE, EAN, NAME, VOLUME, PRODUCER_ID) VALUES (27, '8594005015524', 'Fernet Stock Z Generation 1l', 1, (select id from producer where ico=27904636));
INSERT INTO PA165.LIQUOR(ALCOHOLPERCENTAGE, EAN, NAME, VOLUME, PRODUCER_ID) VALUES (30, '8594005011465', 'FERNET STOCK CITRUS MINI 0,05 L', 0.05, (select id from producer where ico=27904636));
INSERT INTO PA165.LIQUOR(ALCOHOLPERCENTAGE, EAN, NAME, VOLUME, PRODUCER_ID) VALUES (40, '8594005013711', 'FERNET STOCK 0.197 L', 0.197, (select id from producer where ico=27904636));
INSERT INTO PA165.LIQUOR(ALCOHOLPERCENTAGE, EAN, NAME, VOLUME, PRODUCER_ID) VALUES (50, '8595198801239', 'SLIVOVICE KOSHER MINI BÍLÁ JELÍNEK 0,05L', 0.05, (select id from producer where ico=49971361));
INSERT INTO PA165.LIQUOR(ALCOHOLPERCENTAGE, EAN, NAME, VOLUME, PRODUCER_ID) VALUES (40, '8594002710156', 'VODKA PRAŽSKÁ 0.2L 40%', 0.2, (select id from producer where ico=27250555));
INSERT INTO PA165.LIQUOR(ALCOHOLPERCENTAGE, EAN, NAME, VOLUME, PRODUCER_ID) VALUES (45, '999998888', 'Hruškovica', 1, (select id from producer where ico=26224496));

-- insert stamps
INSERT INTO PA165.STAMP (ISSUEDDATE, NUMBEROFSTAMP) VALUES ('2012-09-21 12:46:00', '608524KOE');
INSERT INTO PA165.STAMP (ISSUEDDATE, NUMBEROFSTAMP) VALUES ('2013-07-22 22:22:22', '998524KOE');
INSERT INTO PA165.STAMP (ISSUEDDATE, NUMBEROFSTAMP) VALUES ('2013-11-22 17:17:17', '558524FFF');


-- insert bottles of      "Fernet Stock Z Generation 1l"
INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8594005015524'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '608524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 25928082));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8594005015524'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '608524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 25928082));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', TRUE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8594005015524'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '608524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 25928082));
INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8594005015524'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '608524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 25928082));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8594005015524'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '608524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 25928082));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', TRUE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8594005015524'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '608524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 25928082));
INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8594005015524'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '608524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 25928082));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('222', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8594005015524'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '608524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 25928082));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('222', '2013-07-22 22:22:22', TRUE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8594005015524'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '608524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 25928082));


-- jelinek slivovice
INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8595198801239'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '998524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 44012373));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8595198801239'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '998524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 44012373));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', TRUE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8595198801239'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '998524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 44012373));
INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8595198801239'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '998524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 44012373));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8595198801239'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '998524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 44012373));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', TRUE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8595198801239'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '998524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 44012373));
INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8595198801239'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '998524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 44012373));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', FALSE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8595198801239'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '998524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 44012373));


INSERT INTO PA165.BOTTLE (LOTCODE, PRODUCEDDATE, SOLD, LIQUOR_ID, STAMP_ID, STORE_ID)
  VALUES ('111', '2013-07-22 22:22:22', TRUE,
          (SELECT
             id
           FROM LIQUOR
           WHERE ean = '8595198801239'),
          (SELECT
             id
           FROM STAMP
           WHERE NUMBEROFSTAMP = '998524KOE'),
          (SELECT
             id
           FROM store
           WHERE ico = 44012373));