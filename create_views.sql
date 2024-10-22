
CREATE VIEW BrangusKompiuteriai(Pavadinimas, Aprasymas, Kaina)
AS SELECT DISTINCT Pavadinimas, Aprasymas, Kaina 
FROM tagu8226.Produktas 
WHERE Kaina > 1150.00;

CREATE VIEW PirkejuUzsakymuSuma AS
SELECT PIRK_ID, SUM(Kiekis) AS Suma
FROM tagu8226.Uzsakymas
GROUP BY PIRK_ID;




