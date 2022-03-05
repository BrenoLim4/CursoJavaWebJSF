/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  99039833
 * Created: 03/03/2022
 */
DROP TRIGGER IF EXISTS `modulo3_spring_mvc`.`endereco_aft_insert`;

DELIMITER $$
USE `modulo3_spring_mvc`$$
CREATE DEFINER = CURRENT_USER TRIGGER `modulo3_spring_mvc`.`endereco_aft_insert`
 AFTER INSERT ON `endereco` FOR EACH ROW
BEGIN
	update modulo3_spring_mvc.pessoa
    set id_endereco_atual = new.id
    where id = id_pessoa;
END$$
DELIMITER ;


DROP TRIGGER IF EXISTS `modulo3_spring_mvc`.`pessoa_AFTER_DELETE`;

DELIMITER $$
USE `modulo3_spring_mvc`$$
CREATE DEFINER = CURRENT_USER TRIGGER `modulo3_spring_mvc`.`pessoa_AFTER_DELETE` AFTER DELETE ON `pessoa` FOR EACH ROW
BEGIN
	delete from modulo3_spring_mvc.usuario
    where id_pessoa = old.id;
END$$
DELIMITER ;