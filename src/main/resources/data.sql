DROP TABLE IF EXISTS coin;
 
CREATE TABLE coin (
  value DECIMAL  PRIMARY KEY,
  count DECIMAL NOT NULL
);
 
INSERT INTO coin (value, count) VALUES
  (0.01, 100),
  (0.05, 100),
  (0.1, 100),  
  (0.25, 100);