connect 'jdbc:derby:magicdb';
connect 'jdbc:derby://localhost:1527/magicdb';
connect 'jdbc:derby:magicdb;create=true';

SET IGNORECASE TRUE;

CREATE TABLE users (
   username VARCHAR(50) NOT NULL PRIMARY KEY,
   password VARCHAR(50) NOT NULL,
   enabled BIT NOT NULL
);

CREATE TABLE authorities (
   username VARCHAR(50) NOT NULL,
   authority VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

ALTER TABLE authorities ADD CONSTRAINT fk_authorities_users foreign key (username) REFERENCES users(username);

INSERT INTO users VALUES ('david', 'newyork', true);
INSERT INTO users VALUES ('alex', 'newjersey', true);
INSERT INTO users VALUES ('tim', 'illinois', true);

INSERT INTO authorities VALUES ('david', 'ROLE_USER');
INSERT INTO authorities VALUES ('alex', 'ROLE_USER');
INSERT INTO authorities VALUES ('tim', 'ROLE_USER');

create table card(
	id INT PRIMARY KEY,
    name VARCHAR(256),
    set_id varchar(10)
);

create table card_data(
	user_id VARCHAR(50),
	card_id INT,
    foil BOOLEAN,
    PRIMARY KEY(user_id, card_id, foil),
    additional_info varchar(10),
    amount INT,
    amount_public INT
);

select * from card_data
	join card on card_data.card_id = card.id
	join user_data on card_data.user_id = user_data.user_id
	where card_data.user_id = ? and card_data.amount_public > 0;

	
select * from card
	where card.name like '%s%' and not card.id in (
		select card.id from card
	 	join card_data on card_data.card_id = card.id
		where card.name like '%s%' and card_data.user_id = 1);

select * from card_data
	join card on card_data.card_id = card.id
	join user_data on card_data.user_id = user_data.user_id
	where card.name like '%s%' and card_data.amount_public > 0;


