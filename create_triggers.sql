
-- Patikrinti produkto varda 
CREATE OR REPLACE FUNCTION TikrintiProduktoVarda() RETURNS TRIGGER AS $$
DECLARE prodpavKiekis INT;
BEGIN
  -- Ar produktas jau turi du pavadinimus
  SELECT COUNT(*) INTO prodpavKiekis
  FROM Produktas
  WHERE Pavadinimas = NEW.Pavadinimas;
  
  -- Jeigu yra bent penki ar daugiau tada klaida
  IF prodpavKiekis >= 5 THEN
    RAISE EXCEPTION 'Gali buti max penki produktai tokiu vardu';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER TikrintiProdVarda
BEFORE INSERT OR UPDATE ON Produktas
FOR EACH ROW
EXECUTE FUNCTION TikrintiProduktoVarda();

----------------------------------------------------------------------

-- Negalima vienu metu uzsakyti daugiau negu 10 kompiuteriu

CREATE OR REPLACE FUNCTION UzsakymuLimitas() RETURNS TRIGGER AS $$
BEGIN

  IF NEW.Kiekis > 10 THEN 
  RAISE EXCEPTION 'Negalima vienu metu uzsakyti daugiau negu 10 kompiuteriu';
  END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER MaxUzsakymuKiekis
BEFORE INSERT OR UPDATE ON Uzsakymas
FOR EACH ROW
EXECUTE PROCEDURE UzsakymuLimitas();

------------------------------------------------------


-- Minimalus produkto uzsakymo kiekis turi buti 1

--CREATE OR REPLACE FUNCTION MinimalusUzsakymoKiekis() RETURNS TRIGGER AS $$
--BEGIN
--    IF NEW.Kiekis < 1 THEN
--        RAISE EXCEPTION 'Minimalus uzsakymo kiekis yra 1';
--    END IF;
--    RETURN NEW;
--END;
--$$ LANGUAGE plpgsql;

--CREATE TRIGGER MinUzsakymoKiekis
--BEFORE INSERT OR UPDATE ON Uzsakymas
--FOR EACH ROW
--EXECUTE FUNCTION MinimalusUzsakymoKiekis();



-----
-- Update pirkeju uzsakymu suma

--CREATE FUNCTION UpdateUzsakymuSuma() RETURNS TRIGGER AS $$
--BEGIN
--  UPDATE PirkejuUzsakymuSuma
--  SET Suma = (
--    SELECT SUM(Kiekis)
--    FROM tagu8226.Uzsakymas
--    WHERE PIRK_ID = NEW.PIRK_ID
--    GROUP BY PIRK_ID
--  )
--  WHERE PIRK_ID = NEW.PIRK_ID;
--  RETURN NEW;
--END;
--$$ LANGUAGE plpgsql;

--CREATE TRIGGER trg_Uzs_Suma
--AFTER INSERT OR UPDATE ON tagu8226.Uzsakymas
--FOR EACH ROW
--EXECUTE FUNCTION UpdateUzsakymuSuma();