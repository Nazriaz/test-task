create table ASSESSMENT (
id IDENTITY primary key,
  assessed_value DEC(20),
  date DATE
);
create table CAR (
  id IDENTITY primary key,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  power DOUBLE,
  year_of_issue YEAR,
);

create table AIRPLANE (
  id IDENTITY primary key,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  manufacturer VARCHAR2(500),
  year_of_issue YEAR,
  fuel_capacity INT,
  seats INT
);
CREATE table CAR_ASSESSMENT(
car_id int ,
foreign key (car_id) references CAR(id),
assessment_id int ,
foreign key (assessment_id) references ASSESSMENT(id),
primary key (car_id,assessment_id)
);
CREATE table AIRPLANE_ASSESSMENT(
airplane_id int,
 foreign key (airplane_id) references AIRPLANE(id),
assessment_id int,
 foreign key (assessment_id) references ASSESSMENT(id),
primary key (airplane_id,assessment_id)
);
