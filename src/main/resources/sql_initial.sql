--drop table circuits


drop table results;
drop table constructors;
drop table drivers;
drop table fatests_lap;
drop table races;
drop table circuits;

create table circuits (
	id BIGSERIAL not null
	,circuit_id				VARCHAR(200) NOT null
	,circuit_url			VARCHAR(2000) NOT NULL
	,circuit_name			VARCHAR(2000) NOT NULL
	,circuit_latitude		NUMERIC(7,4) NOT NULL
	,circuit_longitude		NUMERIC(7,4) NOT NULL
	,circuit_localization	VARCHAR(20) NOT NULL
	,circuit_country		VARCHAR(20) NOT null
    ,dat_creation 			TIMESTAMP not null
    ,dat_update 				TIMESTAMP
);

create table races (
	id BIGSERIAL not null
	,season		INTEGER  	NOT NULL
	,round		VARCHAR(30)	NOT NULL
	,url		VARCHAR(2000)	NOT NULL
	,race_name	VARCHAR(200)	NOT null
	,circuit_id	varchar(30)	not null
	,date		DATE		NOT NULL
	,time		VARCHAR(30)	NOT null
    ,dat_creation 			TIMESTAMP not null
    ,dat_update 				TIMESTAMP

);

create table fatests_lap(
	id BIGSERIAL not null
	,season					INTEGER  	NOT NULL
	,round					VARCHAR(30)	NOT null
	,rank					INTEGER 	NOT null
	,lap					INTEGER
	,time_milis				VARCHAR(30)
	,time					VARCHAR(30)
	,average_speed_units	VARCHAR(3)
	,average_speed			NUMERIC(7,3)
    ,dat_creation 			TIMESTAMP not null
    ,dat_update 				TIMESTAMP
);

create table drivers (
  id BIGSERIAL not null
  ,driver_id			varchar(30)
  ,permanent_number     INTEGER		NOT NULL
  ,code					VARCHAR(10)	NOT NULL
  ,url					VARCHAR(2000)	NOT NULL
  ,given_name			VARCHAR(20)	NOT NULL
  ,family_name			VARCHAR(20)	NOT NULL
  ,date_of_birth		DATE		NOT NULL
  ,nationality			VARCHAR(20)	NOT null
  ,dat_creation 			TIMESTAMP not null
  ,dat_update 				TIMESTAMP
);

create table constructors (
id BIGSERIAL not null
  ,constructor_id			VARCHAR(30) NOT NULL
  ,url			VARCHAR(200) NOT NULL
  ,name			VARCHAR(20) NOT NULL
  ,nationality	VARCHAR(20) NOT null
  ,dat_creation 			TIMESTAMP not null
  ,dat_update 				TIMESTAMP
);

create table results (
id BIGSERIAL not null
	,season		INTEGER  	NOT NULL
	,round		VARCHAR(30)	NOT NULL
	,numero			varchar(4)	not null
	,position		varchar(2)	not	null
	,position_text	varchar(20)	not null
	,points			INTEGER		not	null
	,driver_id		VARCHAR(30)	not null
	,constructor_id	varchar(30)	not null
	,grid			VARCHAR(30)	not null
	,laps			integer		not null
	,status			VARCHAR(20)	NOT null
	,time_millis	integer
	,time			varchar(20)	not null
    ,dat_creation 			TIMESTAMP not null
    ,dat_update 				TIMESTAMP
);







ALTER TABLE circuits ADD CONSTRAINT pk_circuit PRIMARY KEY (circuit_id);
ALTER TABLE races ADD CONSTRAINT pk_race PRIMARY KEY (season,round);
ALTER TABLE drivers ADD CONSTRAINT pk_driver PRIMARY KEY (driver_id);
ALTER TABLE constructors ADD CONSTRAINT pk_constructor PRIMARY KEY (constructor_id);


alter table races add constraint fk_circuit foreign key(circuit_id) REFERENCES	circuits(circuit_id);
alter table results add constraint fk_results_races foreign key(season,round) REFERENCES	races(season,round);
alter table results add constraint fk_results_driver foreign key(driver_id) REFERENCES	drivers(driver_id);
alter table results add constraint fk_results_constructors foreign key(constructor_id) REFERENCES	constructors(constructor_id);
alter table fatests_lap add constraint fk_race_flap foreign key(season,round) REFERENCES	races(season,round);



