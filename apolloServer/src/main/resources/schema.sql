DROP TABLE IF EXISTS trades;
DROP TABLE IF EXISTS strategies;
DROP TABLE IF EXISTS users;

create table users (
	id int primary key not null auto_increment,
  userName varchar(30) not null
);

create table strategies (
  id int primary key not null auto_increment,
  strategyName varchar(20) not null,
  onoff bit not null,
  userId int not null,
  startingVol int not null, -- our starting amount of stock
  stock varchar(4) not null, -- stock ticker symbol
  exitProfitPercent double not null, -- when the strategy exits given a certain profit or loss
  exitLossPercent double not null,

	stdDevs double, -- for bollinger bands

  shortTime int not null, -- for two moving averages, short time is used for all 3 strategies
--   longTime int,
--
-- 	highPrice double, -- for price breakout
-- 	lowPrice double,
-- 	openPrice double,
-- 	closePrice double,
-- 	longShort bit,

    FOREIGN KEY (userId) references users(id)
);

create table trades (
	id int primary key not null auto_increment,
	buy bit not null,
	price double not null,
	size int not null,
	stock varchar(4) not null, -- the stock ticker symbol
	tradeDate datetime not null,
	state varchar(10) not null, -- cancelled, executed, in process, etc
  strategyId int not null,
  userId int not null,

    FOREIGN KEY (strategyId) references strategies(id),
    FOREIGN KEY (userId) references users(id)
);