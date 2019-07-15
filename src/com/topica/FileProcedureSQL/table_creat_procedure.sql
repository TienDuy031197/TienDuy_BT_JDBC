CREATE DEFINER=`root`@`localhost` PROCEDURE `table_creat_procedure`()
BEGIN
	Create table IF NOT EXISTS employees(
		id int(10) NOT NULL auto_increment,
        		name varchar(50) NOT NULL,
        		address varchar(50) NOT NULL,
       		phone varchar(10) NOT NULL,
       		primary key(id)
   	 );
END