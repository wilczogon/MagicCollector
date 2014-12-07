connect 'jdbc:derby:magicdb';
connect 'jdbc:derby://localhost:1527/magicdb';
connect 'jdbc:derby:magicdb;create=true';

create table card(
	id INT PRIMARY KEY,
    name VARCHAR(256),
    set_id varchar(10)
);

create table card(
	user_id INT,
	card_id INT,
    foil BOOLEAN,
    PRIMARY KEY(user_id, card_id, foil),
    additional_info varchar(10),
    amount INT,
    amount_public INT
);

create table user_data(
	user_id INT PRIMARY KEY,
	nickname VARCHAR(32)
);

insert into user_data values (1, 'Nikifor'), (2, 'Santa Claus'), (3, 'Fairy');

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


