# 创建数据库
create database study_db character set utf8;
use study_db;

# 输入
drop table if exists `employees`;
CREATE TABLE `employees`
(
    `emp_no`     int(11)     NOT NULL,
    `birth_date` date        NOT NULL,
    `first_name` varchar(14) NOT NULL,
    `last_name`  varchar(16) NOT NULL,
    `gender`     char(1)     NOT NULL,
    `hire_date`  date        NOT NULL,
    PRIMARY KEY (`emp_no`)
);
INSERT INTO employees
VALUES (10001, '1953-09-02', 'Georgi', 'Facello', 'M', '1986-06-26');
INSERT INTO employees
VALUES (10002, '1964-06-02', 'Bezalel', 'Simmel', 'F', '1985-11-21');
INSERT INTO employees
VALUES (10003, '1959-12-03', 'Parto', 'Bamford', 'M', '1986-08-28');
INSERT INTO employees
VALUES (10004, '1954-05-01', 'Chirstian', 'Koblick', 'M', '1986-12-01');
INSERT INTO employees
VALUES (10005, '1955-01-21', 'Kyoichi', 'Maliniak', 'M', '1989-09-12');
INSERT INTO employees
VALUES (10006, '1953-04-20', 'Anneke', 'Preusig', 'F', '1989-06-02');
INSERT INTO employees
VALUES (10007, '1957-05-23', 'Tzvetan', 'Zielinski', 'F', '1989-02-10');
INSERT INTO employees
VALUES (10008, '1958-02-19', 'Saniya', 'Kalloufi', 'M', '1994-09-15');
INSERT INTO employees
VALUES (10009, '1952-04-19', 'Sumant', 'Peac', 'F', '1985-02-18');
INSERT INTO employees
VALUES (10010, '1963-06-01', 'Duangkaew', 'Piveteau', 'F', '1989-08-24');
INSERT INTO employees
VALUES (10011, '1953-11-07', 'Mary', 'Sluis', 'F', '1990-01-22');

############################################################################
# 查找入职员工时间排名倒数第三的员工所有信息
############################################################################
select *
from employees;

## 方法一
select *
from employees
order by hire_date desc
limit 2,1;
### 或者
select *
from employees
order by hire_date desc
limit 1 offset 2;

## 方法二
### 若存在多人(如3人)同时在最晚的一天入职的情形，
### 必须要考虑去重(使用distinct或者group by) distinct和group by的执行顺序都在limit前面
select *
from employees
where hire_date =
      (select distinct hire_date
       from employees
       order by hire_date desc
       limit 1 offset 2);


