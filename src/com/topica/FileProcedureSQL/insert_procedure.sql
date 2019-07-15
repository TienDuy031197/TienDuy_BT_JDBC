CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_procedure`(IN _id INTEGER,	
							  IN _name VARCHAR(50),
							  IN _address VARCHAR(50),
                                                     		   		  IN _phone VARCHAR(10))
BEGIN
	INSERT INTO employees(id,name,address,phone)
   	VALUES(_id,_name,_address,_phone);
END