drop table if exists  `salaries` ;
drop table if exists  `dept_manager` ;
CREATE TABLE `salaries` (
                            `emp_no` int(11) NOT NULL,
                            `salary` int(11) NOT NULL,
                            `from_date` date NOT NULL,
                            `to_date` date NOT NULL,
                            PRIMARY KEY (`emp_no`,`from_date`));
CREATE TABLE `dept_manager` (
                                `dept_no` char(4) NOT NULL,
                                `emp_no` int(11) NOT NULL,
                                `to_date` date NOT NULL,
                                PRIMARY KEY (`emp_no`,`dept_no`));
INSERT INTO dept_manager VALUES('d001',10002,'9999-01-01');
INSERT INTO dept_manager VALUES('d002',10006,'9999-01-01');
INSERT INTO dept_manager VALUES('d003',10005,'9999-01-01');
INSERT INTO dept_manager VALUES('d004',10004,'9999-01-01');
INSERT INTO salaries VALUES(10001,88958,'2002-06-22','9999-01-01');
INSERT INTO salaries VALUES(10002,72527,'2001-08-02','9999-01-01');
INSERT INTO salaries VALUES(10003,43311,'2001-12-01','9999-01-01');
INSERT INTO salaries VALUES(10004,74057,'2001-11-27','9999-01-01');
INSERT INTO salaries VALUES(10005,94692,'2001-09-09','9999-01-01');
INSERT INTO salaries VALUES(10006,43311,'2001-08-02','9999-01-01');
INSERT INTO salaries VALUES(10007,88070,'2002-02-07','9999-01-01');


select sl.emp_no, sl.salary, sl.from_date, sl.to_date, dept_no
from dept_manager as dept, salaries as sl
where dept.emp_no = sl.emp_no
order by sl.emp_no