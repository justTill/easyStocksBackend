INSERT INTO public.sectors ("sectorId", "nameEn", "nameDe")
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
INSERT INTO public.sectors ("sectorId", "nameEn", "nameDe")
VALUES (60, 'Real Estate', 'Immobilien');


INSERT INTO public.stocks ("name", "symbol", "sectorId")
VALUES ('adidas', 'ADS', 25),
       ('Airbus', 'AIR', 20),
       ('Allianz', 'ALV', 40),
       ('BASF', 'BAS', 15),
       ('Bayer', 'BAYN', 35),
       ('Beiersdorf', 'BEI', 25),
       ('BMW', 'BMW', 25),
       ('Brenntag', 'BNR', 15),
       ('Continental', 'CON', 25),
       ('Covestro', '1COV', 15);
INSERT INTO public.stocks ("name", "symbol", "sectorId")
VALUES ('Daimler', 'DAI', 25),
       ('Delivery Hero', 'DHER', 30),
       ('Deutsche Bank', 'DBK', 40),
       ('Deutsche Börse', 'DB1', 40),
       ('Deutsche Post', 'DPW', 20),
       ('Deutsche Telekom', 'DTE', 50),
       ('E.ON', 'EOAN', 55),
       ('Fresenius', 'FRE', 35),
       ('Fresenius Medical Care', 'FME', 35),
       ('HeidelbergCement', 'HEI', 15);
INSERT INTO public.stocks ("name", "symbol", "sectorId")
VALUES ('HelloFresh', 'HFG', 30),
       ('Henkel vz.', 'HEN3', 25),
       ('Infineon', 'IFX', 45),
       ('Linde', 'LIN', 15),
       ('Merck', 'MRK', 15),
       ('MTU Aero Engines', 'MTX', 20),
       ('Münchener Rückversicherungs-Gesellschaft', 'MUV2', 40),
       ('Porsche', 'PAH3', 25),
       ('PUMA', 'PUM', 25),
       ('QIAGEN', 'QIA', 35);
INSERT INTO public.stocks ("name", "symbol", "sectorId")
VALUES ('RWE', 'RWE', 55),
       ('SAP', 'SAP', 45),
       ('Sartorius vz.', 'SRT3', 35),
       ('Siemens', 'SIE', 35),
       ('Siemens Energy', 'ENR', 45),
       ('Siemens Healthineers', 'SHL', 45),
       ('Symrise', 'SY1', 25),
       ('Volkswagen (VW) vz.', 'VOW3', 25),
       ('Vonovia', 'VNA', 60),
       ('Zalando', 'ZAL', 25);