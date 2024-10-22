-- Index

DROP INDEX IF EXISTS idx_pirkejas_vardas_pavarde;
DROP INDEX IF EXISTS idx_telnr;

--Trig

DROP TRIGGER IF EXISTS MaxUzsakymuKiekis ON tagu8226.Uzsakymas;
DROP FUNCTION IF EXISTS UzsakymuLimitas();

DROP TRIGGER IF EXISTS TikrintiProdVarda ON tagu8226.Produktas;
DROP FUNCTION IF EXISTS TikrintiProduktoVarda();

--Views
DROP MATERIALIZED VIEW IF EXISTS UzsakymoInfo;
DROP VIEW IF EXISTS PirkejuUzsakymuSuma;;
DROP VIEW IF EXISTS BrangusKompiuteriai;

--Tables
DROP TABLE IF EXISTS Uzsakymas CASCADE;
DROP TABLE IF EXISTS Produktas CASCADE;
DROP TABLE IF EXISTS Gamyklos CASCADE;
DROP TABLE IF EXISTS Pirkejas CASCADE;
