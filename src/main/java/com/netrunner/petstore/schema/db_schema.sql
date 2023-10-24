-- MySQL Workbench Synchronization
-- Generated: 2023-09-29 20:52
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Ajay

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER SCHEMA `petstore_schema`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Address`
DROP FOREIGN KEY `customer_address`,
DROP FOREIGN KEY `dealer_address`;

ALTER TABLE `petstore_schema`.`Seller`
DROP FOREIGN KEY `seller_type`;

ALTER TABLE `petstore_schema`.`Products_Animal`
DROP FOREIGN KEY `animal_ref`,
DROP FOREIGN KEY `seller_ref`;

ALTER TABLE `petstore_schema`.`Seller_Analytics`
DROP FOREIGN KEY `product_analytics_ref`,
DROP FOREIGN KEY `seller_analytics_ref`;

ALTER TABLE `petstore_schema`.`Orders`
DROP FOREIGN KEY `customer_ref`;

ALTER TABLE `petstore_schema`.`Order_Items`
DROP FOREIGN KEY `order_ref`,
DROP FOREIGN KEY `product_ref`;

ALTER TABLE `petstore_schema`.`Payment`
DROP FOREIGN KEY `order_payment_ref`;

ALTER TABLE `petstore_schema`.`Delivery`
DROP FOREIGN KEY `order_delivery_ref`;

ALTER TABLE `petstore_schema`.`Customer`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Address`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ,
CHANGE COLUMN `is_default` `is_default` VARCHAR(45) BINARY NULL DEFAULT NULL ;

ALTER TABLE `petstore_schema`.`Animals`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Seller`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`SellerType`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Products_Animal`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Seller_Analytics`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Orders`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Order_Items`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Payment`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Delivery`
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `petstore_schema`.`Address`
ADD CONSTRAINT `customer_address`
  FOREIGN KEY (`customer_id`)
  REFERENCES `petstore_schema`.`Customer` (`uuid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `dealer_address`
  FOREIGN KEY (`customer_id`)
  REFERENCES `petstore_schema`.`Seller` (`idSeller`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `petstore_schema`.`Seller`
ADD CONSTRAINT `seller_type`
  FOREIGN KEY (`seller_type`)
  REFERENCES `petstore_schema`.`SellerType` (`idSellerType`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `petstore_schema`.`Products_Animal`
ADD CONSTRAINT `animal_ref`
  FOREIGN KEY (`animal_id`)
  REFERENCES `petstore_schema`.`Animals` (`idAnimals`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `seller_ref`
  FOREIGN KEY (`seller_id`)
  REFERENCES `petstore_schema`.`Seller` (`idSeller`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `petstore_schema`.`Seller_Analytics`
ADD CONSTRAINT `product_analytics_ref`
  FOREIGN KEY (`product_id`)
  REFERENCES `petstore_schema`.`Products_Animal` (`idProducts_Animal`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `seller_analytics_ref`
  FOREIGN KEY (`seller_id`)
  REFERENCES `petstore_schema`.`Seller` (`idSeller`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `petstore_schema`.`Orders`
ADD CONSTRAINT `customer_ref`
  FOREIGN KEY (`customer_id`)
  REFERENCES `petstore_schema`.`Customer` (`uuid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `petstore_schema`.`Order_Items`
ADD CONSTRAINT `order_ref`
  FOREIGN KEY (`order_id`)
  REFERENCES `petstore_schema`.`Orders` (`idOrders`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `product_ref`
  FOREIGN KEY (`product_id`)
  REFERENCES `petstore_schema`.`Products_Animal` (`idProducts_Animal`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `petstore_schema`.`Payment`
ADD CONSTRAINT `order_payment_ref`
  FOREIGN KEY (`order_id`)
  REFERENCES `petstore_schema`.`Orders` (`idOrders`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `petstore_schema`.`Delivery`
ADD CONSTRAINT `order_delivery_ref`
  FOREIGN KEY (`order_id`)
  REFERENCES `petstore_schema`.`Orders` (`idOrders`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;