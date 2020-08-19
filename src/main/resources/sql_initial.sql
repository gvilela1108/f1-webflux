create table circuits (
	id integer not null
	,circuit_id				VARCHAR(13) NOT null
	,circuit_url			VARCHAR(42) NOT NULL
	,circuit_name			VARCHAR(13) NOT NULL
	,circuit_latitude		NUMERIC(7,4) NOT NULL
	,circuit_longitude		NUMERIC(7,4) NOT NULL
	,circuit_localization	VARCHAR(9) NOT NULL
	,circuit_country		VARCHAR(7) NOT null
    ,dat_creation 			TIMESTAMP not null
    ,dat_update 				TIMESTAMP
);

create table races (
	id integer not null
	,season		INTEGER  	NOT NULL
	,round		VARCHAR(5)	NOT NULL
	,url		VARCHAR(54)	NOT NULL
	,race_name	VARCHAR(19)	NOT null
	,circuit_id	varchar(13)	not null
	,date		DATE		NOT NULL
	,time		VARCHAR(9)	NOT null
    ,dat_creation 			TIMESTAMP not null
    ,dat_update 				TIMESTAMP

);

create table fatests_lap(
	id integer not null
	,season					INTEGER  	NOT NULL
	,round					VARCHAR(5)	NOT null
	,rank					INTEGER 	NOT null
	,lap					INTEGER
	,time_milis				VARCHAR(30)
	,time					VARCHAR(8)
	,average_speed_units	VARCHAR(3)
	,average_speed			NUMERIC(7,3)
    ,dat_creation 			TIMESTAMP not null
    ,dat_update 				TIMESTAMP
);

create table drivers (
  id integer not null
  ,driver_id			varchar(30)
  ,permanent_number     INTEGER		NOT NULL
  ,code					VARCHAR(3)	NOT NULL
  ,url					VARCHAR(44)	NOT NULL
  ,given_name			VARCHAR(8)	NOT NULL
  ,family_name			VARCHAR(6)	NOT NULL
  ,date_of_birth		DATE		NOT NULL
  ,nationality			VARCHAR(7)	NOT null
  ,dat_creation 			TIMESTAMP not null
  ,dat_update 				TIMESTAMP
);

create table constructors (
id integer not null
  ,constructor_id			VARCHAR(8) NOT NULL
  ,url			VARCHAR(57) NOT NULL
  ,name			VARCHAR(8) NOT NULL
  ,nationality	VARCHAR(6) NOT null
  ,dat_creation 			TIMESTAMP not null
  ,dat_update 				TIMESTAMP
);

create table results (
id integer not null
	,season		INTEGER  	NOT NULL
	,round		VARCHAR(5)	NOT NULL
	,numero			varchar(4)	not null
	,position		varchar(2)	not	null
	,position_text	varchar(2)	not null
	,points			INTEGER		not	null
	,driver_id		VARCHAR(30)	not null
	,constructor_id	varchar(8)	not null
	,grid			VARCHAR(3)	not null
	,laps			integer		not null
	,status			VARCHAR(8)	NOT null
	,time_millis	integer
	,time			varchar(11)	not null
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



