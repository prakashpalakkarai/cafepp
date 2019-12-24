CREATE SCHEMA IF NOT EXISTS billpay;

USE billpay;

CREATE TABLE payment_methods (
  pmt_method_id char(3) NOT NULL,
  method_name varchar(40) NOT NULL,
  updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (pmt_method_id)
);

INSERT INTO payment_methods (pmt_method_id,method_name,updatedAt,createdAt) VALUES
('ACK','Automatic Payment from Checking',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
,('BIP','Pay using Bill Pay',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
,('BIS','Pay at Biller Site',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
;


CREATE TABLE bill_schedules (
  bill_sch_id char(3) NOT NULL,
  schedule_name varchar(40) NOT NULL,
  frequency varchar(40) DEFAULT NULL,
  updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (bill_sch_id)
);

INSERT INTO bill_schedules (bill_sch_id,schedule_name,frequency,updatedAt,createdAt) VALUES
('BLY','Bi-weekly','2 weeks',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
,('MLY','Monthly','1 month',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
,('QLY','Quarterly','3 months',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
,('SEM','Semi-montly','0.5 months',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
,('WLY','Weekly','1 week',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
;



CREATE TABLE payee (
  payee_id int(10) NOT NULL AUTO_INCREMENT,
  name varchar(40) NOT NULL,
  type varchar(40) NOT NULL,
  due_amount decimal(7,2) NOT NULL DEFAULT '0.00',
  total_balance decimal(7,2) NOT NULL DEFAULT '0.00',
  interest decimal(7,2) NOT NULL DEFAULT '0.00',
  promotion_expire_dt date DEFAULT NULL,
  account_num int(20) DEFAULT NULL,
  pmt_method_id char(3) DEFAULT NULL,
  bill_sch_id char(3) DEFAULT NULL,
  createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (payee_id),
  --KEY pmt_method_id (pmt_method_id),
  --KEY bill_sch_id (bill_sch_id),
  CONSTRAINT payee_ibfk_1 FOREIGN KEY (pmt_method_id) REFERENCES payment_methods (pmt_method_id),
  CONSTRAINT payee_ibfk_2 FOREIGN KEY (bill_sch_id) REFERENCES bill_schedules (bill_sch_id)
);

CREATE TABLE bill_payments (
  pmt_id int(10) NOT NULL AUTO_INCREMENT,
  pmt_schedule_dt date NOT NULL,
  payee_id int(10) NOT NULL,
  amount decimal(7,2) NOT NULL,
  pmt_status varchar(40) NOT NULL,
  createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (pmt_id),
  --KEY payee_id (payee_id),
  CONSTRAINT bill_payments_ibfk_1 FOREIGN KEY (payee_id) REFERENCES payee (payee_id)
);



