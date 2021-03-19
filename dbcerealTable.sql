//This is the Cereal table you will have.
//You are free to construct other tables any way you want.


CREATE TABLE IF NOT EXISTS `cereal` (
  `idcereal` INT NOT NULL AUTO_INCREMENT,
  `brand` VARCHAR(10) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `sugargram` INT NOT NULL,
  `fibergram` INT NOT NULL,
  `expiredate` DATE NOT NULL,
  `price` DECIMAL(4,2) NOT NULL,
  `productdescription` VARCHAR(100) NOT NULL,
  `inventory` INT NOT NULL,
  PRIMARY KEY (`idcereal`));

