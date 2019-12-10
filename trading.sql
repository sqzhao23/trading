DROP DATABASE IF EXISTS trading;
CREATE DATABASE trading DEFAULT CHARACTER SET utf8;
USE trading;

CREATE TABLE user (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) DEFAULT NULL,
	password varchar(255) DEFAULT NULL,
	contact varchar(255) DEFAULT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE category (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) DEFAULT NULL,
	description varchar(255) DEFAULT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) DEFAULT NULL,
	originalPrice float(10,2) DEFAULT NULL,
	salePrice float(10,2) DEFAULT NULL,
	description varchar(255) DEFAULT NULL,
	status varchar(255) DEFAULT NULL,
	uid int(11) DEFAULT NULL,
	cid int(11) DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_item_category FOREIGN KEY (cid) REFERENCES category (id),
	CONSTRAINT fk_item_user FOREIGN KEY (uid) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE itemImage (
	id int(11) NOT NULL AUTO_INCREMENT,
	iid int(11) DEFAULT NULL,
	type varchar(255) DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_itemImage_item FOREIGN KEY (iid) REFERENCES item (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE order_ (
	id int(11) NOT NULL AUTO_INCREMENT,
	orderCode varchar(255) DEFAULT NULL,
	address varchar(255) DEFAULT NULL,
	receiver varchar(255) DEFAULT NULL,
	mobile varchar(255) DEFAULT NULL,
	createDate datetime DEFAULT NULL,
	status varchar(255) DEFAULT NULL,
	uid int(11) DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_order_user FOREIGN KEY (uid) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE orderitem (
	id int(11) NOT NULL AUTO_INCREMENT,
	iid int(11) DEFAULT NULL,
	oid int(11) DEFAULT NULL,
	uid int(11) DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_orderitem_item FOREIGN KEY (iid) REFERENCES item (id),
	CONSTRAINT fk_orderitem_user FOREIGN KEY (uid) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	
	