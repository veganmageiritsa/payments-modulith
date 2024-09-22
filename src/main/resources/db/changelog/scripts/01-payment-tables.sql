CREATE TABLE if not exists  inventory (
	id           BIGINT  NOT NULL,
	name         VARCHAR(50)  NOT NULL,
	description  VARCHAR(120) ,
	price NUMERIC(20,4) NOT NULL,
	CONSTRAINT pk_inventory PRIMARY KEY (id)
	);

CREATE SEQUENCE if not exists inventory_seq
	MINVALUE 1
	START WITH 20
OWNED BY inventory.id;
;

CREATE INDEX idx_name ON inventory (name);


CREATE TABLE if not exists  orders (
	id           BIGINT  NOT NULL,
	order_identifier  VARCHAR(100) ,
	customer_name  VARCHAR(50),
	customer_email  VARCHAR(50),
	order_status  VARCHAR(50),
	created_at DATE ,
	CONSTRAINT pk_orders PRIMARY KEY (id)
	);

CREATE SEQUENCE if not exists orders_seq
	MINVALUE 1
	START WITH 20 OWNED BY orders.id;
;

CREATE INDEX idx_id ON orders (id);
CREATE INDEX idx_identifier ON orders (order_identifier);


CREATE TABLE if not exists  order_inventory (
	order_id           BIGINT  NOT NULL,
	inventory_id  BIGINT  NOT NULL,
	quantity  INT  NOT NULL,
	total_price  NUMERIC(20,4) NOT NULL,
	CONSTRAINT pk_order_inventory
   PRIMARY KEY (
	order_id,
	inventory_id
   )
	);

CREATE INDEX ord_idx ON order_inventory (order_id);
CREATE INDEX inv_idx ON order_inventory (inventory_id);

CREATE TABLE if not exists  payments (
	id           BIGINT  NOT NULL,
	order_id  BIGINT  NOT NULL ,
	amount  INT,
	status  VARCHAR(50),
	payment_date DATE ,
	CONSTRAINT pk_payments PRIMARY KEY (id)
	);

CREATE SEQUENCE if not exists payments_seq
	MINVALUE 1
	START WITH 20 OWNED BY payments.id;

CREATE INDEX pay_idx ON payments (order_id);

CREATE TABLE IF NOT EXISTS event_publication
(
	id               UUID NOT NULL,
	listener_id      TEXT NOT NULL,
	event_type       TEXT NOT NULL,
	serialized_event TEXT NOT NULL,
	publication_date TIMESTAMP WITH TIME ZONE NOT NULL,
	completion_date  TIMESTAMP WITH TIME ZONE,
	PRIMARY KEY (id)
	);
CREATE INDEX IF NOT EXISTS event_publication_serialized_event_hash_idx ON event_publication USING hash(serialized_event);
CREATE INDEX IF NOT EXISTS event_publication_by_completion_date_idx ON event_publication (completion_date);
-- CREATE TABLE if not exists inventory (
-- 	id         INT AUTO_INCREMENT NOT NULL,
-- 	username   VARCHAR(50)  NOT NULL,
-- 	email      VARCHAR(100) NOT NULL,
-- 	password   VARCHAR(120) NOT NULL,
-- 	first_name  VARCHAR(150) NULL,
-- 	last_name   VARCHAR(200) NULL,
-- 	created_at datetime     NULL,
-- 	updated_at datetime     NULL,
-- 	UNIQUE (username),
-- 	UNIQUE (email),
-- 	CONSTRAINT pk_app_users PRIMARY KEY (id)
-- );
--
-- CREATE INDEX  idx_username ON app_users (username);
--
-- CREATE INDEX  idx_email ON app_users (email);
--
-- CREATE TABLE if not exists  app_roles (
-- 	id INT AUTO_INCREMENT NOT NULL,
-- 	name enum('ROLE_ADMIN', 'ROLE_USER') NOT NULL,
-- 	UNIQUE (name),
-- 	CONSTRAINT pk_app_roles PRIMARY KEY (id)
-- );
--
-- CREATE TABLE if not exists  app_users_roles (
-- 	app_user_id INT NOT NULL,
-- 	app_role_id INT NOT NULL,
-- 	CONSTRAINT pk_app_roles PRIMARY KEY (app_user_id, app_role_id)
-- );
--
-- insert into app_users(username, email, password, first_name, last_name, created_at, updated_at)
-- values ('nikleontiou@gmail.com', 'nikleontiou@gmail.com', '$2a$10$7Dq6QwfNvb0FGgvydAybPulXIZRhyXSidnAhs4EZVUuvXFdJrYy4K', 'Nikolas', 'Leontiou', '2024-05-15', '2024-05-15');
--
-- insert into app_users(username, email, password, first_name, last_name, created_at, updated_at)
-- values ('alibaba@gmail.com', 'alibaba@gmail.com', '$2a$05$r/74Zj2d7wrmDEB4eOiPEedQ7ec3BSr5JCBtf6pXmCWj45VsO3UUe', 'Ali', 'Baba', '2024-05-15', '2024-05-15');
-- insert into app_roles(name)
-- values ('ROLE_ADMIN');
-- insert into app_roles(name)
-- values ('ROLE_USER');
--
-- insert into app_users_roles(app_user_id, app_role_id)
-- values (1, 1);
-- insert into app_users_roles(app_user_id, app_role_id)
-- values (2, 2);