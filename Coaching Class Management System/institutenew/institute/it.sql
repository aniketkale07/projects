drop database institute
create database institute;
use institute;
DROP TABLE IF EXISTS `Admission`;

CREATE TABLE `Admission` (
  `Add_id` INTEGER, 
  `Date1` VARCHAR(255), 
  `stu_id` INTEGER, 
  `stu_name` VARCHAR(255),
  `Email_id`VARCHAR(255), 
  `stu_add` VARCHAR(255), 
  `stu_phno` VARCHAR(255),
  `stu_gender` VARCHAR(255), 
  `co_id` VARCHAR(255), 
  `b_id` VARCHAR(255),
  `feetype` VARCHAR(255),
  `fee` VARCHAR(255), 
  INDEX (`b_id`), 
  INDEX (`co_id`), 
  INDEX (`stu_id`)
) ENGINE=innodb DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'Admission'
#

INSERT INTO `Admission` (`Add_id`, `Date1`, `stu_id`, `stu_name`,`Email_id`, `stu_add`, `stu_gender`, `stu_phno`, `co_id`, `b_id`,`feetype`,`fee` ) VALUES (1,'4/6/2015', 1, 'Archana','Archana425@gmail.com', 'Pune', 'Female', 1234567890, 'Java', '8 to 9.30 am','Installation','7000');
INSERT INTO `Admission` (`Add_id`, `Date1`, `stu_id`, `stu_name`,`Email_id`, `stu_add`, `stu_gender`, `stu_phno`, `co_id`, `b_id`,`feetype`,`fee`) VALUES (2,'5/6/2015', 2, 'Kalpana','Kalpana24@gmail.com', 'Bhor', 'Female', 1452630710, 'Java', '8 to 9.30 am','Installation','7000');
INSERT INTO `Admission` (`Add_id`, `Date1`, `stu_id`, `stu_name`,`Email_id`, `stu_add`, `stu_gender`, `stu_phno`, `co_id`, `b_id`,`feetype`,`fee`) VALUES (3,'2/2/2015', 3, 'Sachin','sachin11@gmail.com', 'Pune', 'Male', 9854621758, ' VB', '1 to 2 pm','Installation','7500');
INSERT INTO `Admission` (`Add_id`, `Date1`, `stu_id`, `stu_name`,`Email_id`, `stu_add`, `stu_gender`, `stu_phno`, `co_id`, `b_id`,`feetype`,`fee`) VALUES (4,'2/8/2015',4,'KunalChavan','kunal24chavan@gmail.com', 'Pune', 'Male', 9874102369, ' Adv java', ' 10 to 11 am','One Time','7000');
INSERT INTO `Admission` (`Add_id`, `Date1`, `stu_id`, `stu_name`,`Email_id`, `stu_add`, `stu_gender`, `stu_phno`, `co_id`, `b_id`,`feetype`,`fee`) VALUES (5, '20/9/2015', 5,'Vishal Chavan','Visu43@gmail.com', 'Mumbai', 'Male', 9874124568, ' Adv java', ' 10 to 11 am','One Time','7000');

# 4 records

#
# Table structure for table 'Batch'
#

DROP TABLE IF EXISTS `Batch`;

CREATE TABLE `Batch` (
  `b_id` INTEGER, 
  `co_name` VARCHAR(255), 
  `date1` VARCHAR(255), 
  `time` VARCHAR(255)
) ENGINE=innodb DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'Batch'
#

INSERT INTO `Batch` (`b_id`, `co_name`, `date1`, `time`) VALUES (201, ' Adv java', ' 4/8/2015', ' 10 to 11 am');
INSERT INTO `Batch` (`b_id`, `co_name`, `date1`, `time`) VALUES (202, ' VB', '2/2/2015','1 to 2 pm');
INSERT INTO `Batch` (`b_id`, `co_name`, `date1`, `time`) VALUES (203, ' Java', '1/6/2015','8 to 9.30 am');
INSERT INTO `Batch` (`b_id`, `co_name`, `date1`, `time`) VALUES (204, ' Syspro', '5/7/2015','11 to 12 am');
INSERT INTO `Batch` (`b_id`, `co_name`, `date1`, `time`) VALUES (205, ' TCS', '8/7/2015','2 to 3 pm');
INSERT INTO `Batch` (`b_id`, `co_name`, `date1`, `time`) VALUES (206, ' .NET', '8/7/2015','4 to 6 pm');
INSERT INTO `Batch` (`b_id`, `co_name`, `date1`, `time`) VALUES (207, ' RDBMS', '2/7/2015','7 to 8 pm');
# 2 records

#
# Table structure for table 'bill'
#

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `b_no` INTEGER, 
  `date1` VARCHAR(255), 
  `stu_id` INTEGER, 
  `Add_id` INTEGER, 
  `stu_name` VARCHAR(255),
  `Email_id`VARCHAR(255), 
  `stu_add` VARCHAR(255), 
  `stu_gender` VARCHAR(255), 
  `stu_phno` VARCHAR(255), 
  `co_id` VARCHAR(255), 
  `Fee_t` VARCHAR(255), 
  `Paid_F`VARCHAR(255),
  INDEX (`stu_id`), 
  INDEX (`Add_id`), 
  INDEX (`Fee_t`), 
  INDEX (`Paid_F`)
) ENGINE=innodb DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'bill'
#

INSERT INTO `bill` (`b_no`, `date1`, `stu_id`, `Add_id`, `stu_name`,`Email_id`, `stu_add`, `stu_gender`, `stu_phno`, `co_id`, `Fee_t`,`Paid_F`) VALUES (1, '4/6/2015', 1, 1, 'Archana','Archana425@gmail.com', 'Pune', 'Female', 1234567890, 'Java', 'Installation','7000');
INSERT INTO `bill` (`b_no`, `date1`, `stu_id`, `Add_id`, `stu_name`,`Email_id`, `stu_add`, `stu_gender`, `stu_phno`, `co_id`, `Fee_t`,`Paid_F`) VALUES (2, '2/8/2015', 4, 4, 'Kunal Chavan','kunal24chavan@gmail.com', 'Pune', 'Male', 9874102369, ' Adv java', 'One Time','70000');
# 2 records

#
# Table structure for table 'Course'
#

DROP TABLE IF EXISTS `Course`;

CREATE TABLE `Course` (
  `co_id` INTEGER, 
  `co_name` VARCHAR(255), 
  `co_duration` VARCHAR(255), 
  `co_fee1` VARCHAR(255),
  `co_fee` VARCHAR(255)
) ENGINE=innodb DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'Course'
#

INSERT INTO `Course` (`co_id`, `co_name`, `co_duration`, `co_fee1`,`co_fee`) VALUES (102, 'Java', ' 3 Months', ' 6000','7000');
INSERT INTO `Course` (`co_id`, `co_name`, `co_duration`, `co_fee1`,`co_fee`) VALUES (103, 'Adv java', ' 3 Months', '7000','7500');
INSERT INTO `Course` (`co_id`, `co_name`, `co_duration`, `co_fee1`,`co_fee`) VALUES (104, 'VB', ' 2 Months', ' 7500','8000');
INSERT INTO `Course` (`co_id`, `co_name`, `co_duration`, `co_fee1`,`co_fee`) VALUES (105, 'Syspro', ' 2 Months', '6000','7000');
INSERT INTO `Course` (`co_id`, `co_name`, `co_duration`, `co_fee1`,`co_fee`) VALUES (106, 'TCS', ' 3 Months', '6500','7000');
INSERT INTO `Course` (`co_id`, `co_name`, `co_duration`, `co_fee1`,`co_fee`) VALUES (107, '.NET', ' 3 Months', '5900','6500');
INSERT INTO `Course` (`co_id`, `co_name`, `co_duration`, `co_fee1`,`co_fee`) VALUES (107, 'RDBMS', ' 2 Months', '5000','6000');
# 4 records

#
# Table structure for table 'Enquiry'
#

DROP TABLE IF EXISTS `Enquiry`;

CREATE TABLE `Enquiry` (
  `Enq_id` INTEGER, 
  `Date1` VARCHAR(255), 
  `stu_id` VARCHAR(255), 
  `stu_name` VARCHAR(255), 
  `stu_add` VARCHAR(255), 
  `stu_gender` VARCHAR(255), 
  `stu_phno` VARCHAR(255), 
  `co_id` VARCHAR(255), 
  `b_id` VARCHAR(255)
) ENGINE=innodb DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'Enquiry'
#

INSERT INTO `Enquiry` () VALUES (1, '10/2/2015', ' Vicky', 'vicky23@gmail.com', 'Ambegao', 'Male', 4563458702, 'C++,JAVA', '8 to 9 Am');
INSERT INTO `Enquiry` () VALUES (2, '11/2/2015', ' Kunal', 'kunalchavan24@gmail.com', 'Ambegao Pathar', 'Male', 7276291158, 'C++,.NET,JAVA', '8 to 10 Am');
INSERT INTO `Enquiry` () VALUES (3, '13/2/2015', ' Omkar', 'Omkar_p23@gmail.com', 'Ambegao Pathar', 'Male', 8975894123, 'Syspro', '7 to 8 Am');
# 1 records

#
# Table structure for table 'Student'
#

DROP TABLE IF EXISTS `Student`;

CREATE TABLE `Student` (
  `ID` INTEGER NOT NULL AUTO_INCREMENT, 
  PRIMARY KEY (`ID`)
) ENGINE=innodb DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'Student'
#

# 0 records

#
# Table structure for table 'Teacher'
#

DROP TABLE IF EXISTS `Teacher`;

CREATE TABLE `Teacher` (
  `t_id` INTEGER, 
  `t_name` VARCHAR(255), 
  `t_add` VARCHAR(255), 
  `Email_id`VARCHAR(255),
  `t_phno` VARCHAR(255), 
  `t_gender` VARCHAR(255), 
  `co_id` VARCHAR(255), 
  `b_id` VARCHAR(255)
) ENGINE=innodb DEFAULT CHARSET=utf8;

SET autocommit=1;

DROP TABLE IF EXISTS `Exam`;


CREATE TABLE `Result` (
  `s_id` INTEGER, 
  `s_name` VARCHAR(255), 
  `E_name` VARCHAR(255), 
  `Result`VARCHAR(255)
  
) ENGINE=innodb DEFAULT CHARSET=utf8;



SET autocommit=1;


#
# Dumping data for table 'Teacher'
#

# 0 records

