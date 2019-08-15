<-----------create table--------------------->
CREATE TABLE EMPTABLE(
	employee_id number(6) NOT NULL,
	first_name varchar2(20) NOT NULL,
	last_name varchar2(20) NOT NULL,
	date_of_joining DATE NOT NULL,
	date_of_birth DATE NOT NULL,
	DEPARTMENT_ID INTEGER NOT NULL,
	grade varchar2(2) NOT NULL,
	designation varchar2(50),
	gender varchar2(10) NOT NULL,
	base_pay integer NOT NULL,
	PRIMARY KEY(employee_id),
	FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENTS(DEPARTMENT_ID),
	CONSTRAINT first_name_check CHECK (regexp_like(first_name,'^[[:alpha:]]+$')),
	CONSTRAINT last_name_check CHECK (regexp_like(last_name,'^[[:alpha:]]+$')),
	CONSTRAINT employee_id_check CHECK (regexp_like(employee_id,'^[1-9]{1}[0-9]{5}$')),
	CONSTRAINT gender_check CHECK(gender IN ('male','female','Male','Female')),
	CONSTRAINT grade_check CHECK(grade IN ('G1','G2','G3','G4','G5','G6','G7'))
);

<-------trigger for age check-------->

CREATE OR REPLACE TRIGGER check_age BEFORE INSERT OR UPDATE ON EMPTABLE
FOR EACH ROW
BEGIN
	IF( floor(months_between(sysdate,:NEW.date_of_birth)/12) < 18  )
  THEN
    RAISE_APPLICATION_ERROR(
      -20001,
      'Employee should be atleast 18 year old' );
  END IF;

 if( floor(months_between(sysdate,:NEW.date_of_birth)/12) > 58)
 THEN
 	RAISE_APPLICATION_ERROR(
      -20001,
      'Employee should less than 58' );
     END IF;
END;

<----------------------------------------->


DELETE FROM EMPTABLE;


<--------------------------------------->

CREATE TABLE ADMIN(username varchar2(50),password varchar2(50));

INSERT INTO ADMIN VALUES('nikhil','nikhil');

INSERT INTO EMPTABLE values(
	111111,
	'NIKHIL',
	'SHINDE',
	TO_DATE('1990-11-09','YYYY-MM-DD'),
	TO_DATE('2000-08-09','YYYY-MM-DD'),
	10,
	'G1',
	'Programmer',
	'Male',
	10000
);

SELECT * FROM admin WHERE USERNAME = 'nikhil' AND PASSWORD = 'nikhil'