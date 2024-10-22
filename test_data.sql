
-- LENTELIU TESTAI

SELECT * FROM tagu8226.Uzsakymas;
SELECT * FROM tagu8226.Produktas;
SELECT * FROM tagu8226.Pirkejas;
SELECT * FROM tagu8226.Gamyklos;

-- VIEWS TESTAI

-- Brangus kompiuteriai yra tie, kurie kainuoja virs 1150
    SELECT * FROM BrangusKompiuteriai
     ORDER BY Kaina DESC;

-- Unikalus pirkejai ir ju perkamu prekiu kiekis

    SELECT * FROM PirkejuUzsakymuSuma
    ORDER BY Suma ASC;

-- Uzsakymo informacija
    SELECT * FROM UzsakymoInfo;

