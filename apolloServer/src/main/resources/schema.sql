#CREATE DATABASE IF NOT EXISTS apollo;
#use apollo;

DROP TABLE IF EXISTS trades;
DROP TABLE IF EXISTS twoMovingAvgs;
DROP TABLE IF EXISTS bollingerBands;
DROP TABLE IF EXISTS priceBreakout;
DROP TABLE IF EXISTS strategies;
DROP TABLE IF EXISTS users;

create table users (
	id int primary key not null auto_increment,
  userName varchar(30) not null
);

create table strategies (
  id int primary key not null auto_increment,
  strategyName varchar(20) not null,
  onoff bit not null
);

create table trades (
	id int primary key not null auto_increment,
	buysell bit not null,
	price double not null,
	size int not null,
	stock varchar(4) not null,
	tradeDate datetime not null,
	state varchar(10) not null,
  strategyId int not null,
  userId int not null,

    FOREIGN KEY (strategyId) references strategies(id),
    FOREIGN KEY (userId) references users(id)
);

create table twoMovingAvgs (
	id int primary key not null auto_increment,
	strategyId int not null,
  userId int not null,

    FOREIGN KEY (strategyId) references strategies(id),
    FOREIGN KEY (userId) references users(id)
);

create table bollingerBands (
	id int primary key not null auto_increment,
	timePeriod int not null,
	stdDevs int not null,
	strategyId int not null,
	userId int not null,

    FOREIGN KEY (strategyId) references strategies(id),
    FOREIGN KEY (userId) references users(id)
);

create table priceBreakout (
	id int primary key not null auto_increment,
	strategyId int not null,
  userId int not null,

    FOREIGN KEY (strategyId) references strategies(id),
    FOREIGN KEY (userId) references users(id)
);