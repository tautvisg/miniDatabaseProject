

-- Sujungiame varda pavarde
CREATE INDEX idx_pirkejas_vardas_pavarde 
ON Pirkejas (vardas, pavarde);



CREATE UNIQUE INDEX idx_telnr
ON Pirkejas (Telefonas);














------
