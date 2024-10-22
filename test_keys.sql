--== isoriniu raktu ribojimu patikra ==
-- neegzistuojantis pirkejas
INSERT INTO tagu8226.Uzsakymas VALUES(600, 32607, '2023-01-01', 102, 1);
-- neegzistuojantis produktas
INSERT INTO tagu8226.Uzsakymas VALUES(601, 1, '2023-01-01', 32607, 1);
-- neegzistuojanti gamykla
INSERT INTO tagu8226.Produktas VALUES(150, 32607, 'ACER Extensa EX800', 'Naudotas', 1500.00);

-- update restrict'ai
UPDATE tagu8226.Gamyklos SET GAM_ID = 1250 WHERE GAM_ID = 1150;
UPDATE tagu8226.Pirkejas SET PIRK_ID = 2 WHERE PIRK_ID = 1;
UPDATE tagu8226.Produktas SET PROD_ID = 101 WHERE PROD_ID = 100;



-- delete cascade'ai
-- Gamyklos -> Produktas delete cascade'o patikra
---- Prepare

INSERT INTO tagu8226.Gamyklos VALUES(3000, 'Duobes g.', 'GOOGLE kompiuteris');
INSERT INTO tagu8226.Produktas VALUES(170, 3000, 'GOOGLE MACHINE 3000', 'Naujas', 3000.00);
SELECT * FROM tagu8226.Gamyklos WHERE GAM_ID = 3000;
SELECT * FROM tagu8226.Produktas WHERE GAM_ID = 3000;
-- Delete
DELETE FROM tagu8226.Produktas WHERE GAM_ID = 3000;
-- Check
SELECT * FROM tagu8226.Gamyklos WHERE GAM_ID = 3000;
SELECT * FROM tagu8226.Produktas WHERE GAM_ID = 3000;


-- Pirkejas -> Uzsakymas delete cascade'o patikra
---- Prepare
INSERT INTO tagu8226.Pirkejas VALUES (21, 'Jonas', 'Argentina', 'Uzsienio g.', 37061934578);
INSERT INTO tagu8226.Uzsakymas VALUES(701, 21, '2023-03-01', 107, 1);
SELECT * FROM tagu8226.Pirkejas WHERE PIRK_ID = 21;
SELECT * FROM tagu8226.Uzsakymas WHERE PIRK_ID = 21;
---- Delete
DELETE FROM tagu8226.Uzsakymas WHERE PIRK_ID = 21;
----- Check
SELECT * FROM tagu8226.Pirkejas WHERE PIRK_ID = 21;
SELECT * FROM tagu8226.Uzsakymas WHERE PIRK_ID = 21;

-- Produktas -> Uzsakymas delete cascade'o patikra
---- Prepare
INSERT INTO tagu8226.Produktas VALUES(140, 1250, 'ACER Extensa EX800', 'Naudotas', 1500.00);
INSERT INTO tagu8226.Uzsakymas VALUES(702, 1, '2023-04-14', 140, 1);
SELECT * FROM tagu8226.Produktas WHERE PROD_ID = 140;
SELECT * FROM tagu8226.Uzsakymas WHERE PROD_ID = 140;
---- Delete
DELETE FROM tagu8226.Uzsakymas WHERE PROD_ID = 140;
----- Check
SELECT * FROM tagu8226.Produktas WHERE PROD_ID = 140;
SELECT * FROM tagu8226.Uzsakymas WHERE PROD_ID = 140;
