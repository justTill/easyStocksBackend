INSERT INTO public.sectors ("sector_id", "name_en", "name_de")
VALUES (10, 'Energy', 'Energie'),
       (15, 'Materials', 'Material: Roh- und Grundstoffe'),
       (20, 'Industrials', 'Industriegüter'),
       (25, 'Consumer Discretionary', 'Nicht-Basiskonsumgüter'),
       (30, 'Consumer Staples', 'Basiskonsumgüter'),
       (35, 'Health Care', 'Gesundheitswesen'),
       (40, 'Financials', 'Finanzen'),
       (45, 'Information Technology', 'Informationstechnology'),
       (50, 'Communication Services', 'Kommunikationsdienstleistungen'),
       (55, 'Utilities', 'Versorgungsunternehmen');
INSERT INTO public.sectors ("sector_id", "name_en", "name_de")
VALUES (60, 'Real Estate', 'Immobilien');


INSERT INTO public.stocks ("name", "symbol", "sector_id")
VALUES ('adidas', 'ADS.FRK', 25),
       ('Airbus', 'AIR.FRK', 20),
       ('Allianz', 'ALV.FRK', 40),
       ('BASF', 'BAS.FRK', 15),
       ('Bayer', 'BAYN.FRK', 35),
       ('Beiersdorf', 'BEI.FRK', 25),
       ('BMW', 'BMW.FRK', 25),
       ('Brenntag', 'BNR', 15),
       ('Continental', 'CON.FRK', 25),
       ('Covestro', '1COV.FRK', 15);
INSERT INTO public.stocks ("name", "symbol", "sector_id")
VALUES ('Daimler', 'DAI.FRK', 25),
       ('Delivery Hero', 'DHER.FRK', 30),
       ('Deutsche Bank', 'DBK.FRK', 40),
       ('Deutsche Börse', 'DB1.FRK', 40),
       ('Deutsche Post', 'DPW.FRK', 20),
       ('Deutsche Telekom', 'DTE.FRK', 50),
       ('E.ON', 'EOAN.FRK', 55),
       ('Fresenius', 'FRE.FRK', 35),
       ('Fresenius Medical Care', 'FME.FRK', 35),
       ('HeidelbergCement', 'HEI.FRK', 15);
INSERT INTO public.stocks ("name", "symbol", "sector_id")
VALUES ('HelloFresh', 'HFG.DEX', 30),
       ('Henkel vz.', 'HEN3.FRK', 25),
       ('Infineon', 'IFX.FRK', 45),
       ('Linde', 'LIN.FRK', 15),
       ('Merck', 'MRK.FRK', 15),
       ('MTU Aero Engines', 'MTX.FRK', 20),
       ('Münchener Rückversicherungs-Gesellschaft', 'MUV2.FRK', 40),
       ('Porsche', 'PAH3.FRK', 25),
       ('PUMA', 'PUM.FRK', 25),
       ('QIAGEN', 'QIA.FRK', 35);
INSERT INTO public.stocks ("name", "symbol", "sector_id")
VALUES ('RWE', 'RWE.FRK', 55),
       ('SAP', 'SAP.FRK', 45),
       ('Sartorius vz.', 'SRT3.FRK', 35),
       ('Siemens', 'SIE.FRK', 35),
       ('Siemens Energy', 'ENR.FRK', 45),
       ('Siemens Healthineers', 'SHL.FRK', 45),
       ('Symrise', 'SY1.FRK', 25),
       ('Volkswagen (VW) vz.', 'VOW3.FRK', 25),
       ('Vonovia', 'VNA.FRK', 60),
       ('Zalando', 'ZAL.FRK', 25);