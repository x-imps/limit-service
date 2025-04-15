CREATE TABLE if not exists limit_verification_log (
	id varchar(250),
	payment_id varchar(250),
	transaction_id varchar(250),
	payer_customer_id varchar(250),
	payer_account_id varchar(250),
	amount int,
	limit_status varchar(255),
	created_on timestamp,
	PRIMARY KEY (id)
);