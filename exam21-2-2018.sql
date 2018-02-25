-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2018 at 11:37 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `exam`
--
CREATE DATABASE IF NOT EXISTS `exam` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `exam`;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `Code` varchar(5) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Describtion` varchar(45) NOT NULL,
  `Exam time` datetime NOT NULL,
  `No of hours` double NOT NULL,
  `Semester` varchar(10) NOT NULL,
  `Level` varchar(45) NOT NULL,
  `Midterm` int(11) NOT NULL,
  `Oral` int(11) NOT NULL,
  `Final` int(11) NOT NULL,
  `Doctor_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `course_has_course`
--

DROP TABLE IF EXISTS `course_has_course`;
CREATE TABLE `course_has_course` (
  `Course_aquired` varchar(5) NOT NULL,
  `Course_required` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`ID`, `Name`) VALUES
(1, 'Math');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Phone` varchar(13) DEFAULT NULL,
  `Role` varchar(45) NOT NULL,
  `Department_ID` int(11) NOT NULL,
  `Password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation` (
  `ID` int(11) NOT NULL,
  `Course_Code` varchar(5) NOT NULL,
  `year` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `e_question`
--

DROP TABLE IF EXISTS `e_question`;
CREATE TABLE `e_question` (
  `ID` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `e_question_has_evaluation`
--

DROP TABLE IF EXISTS `e_question_has_evaluation`;
CREATE TABLE `e_question_has_evaluation` (
  `E_Question_ID` int(11) NOT NULL,
  `Evaluation_ID` int(11) NOT NULL,
  `Answer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `Id` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL COMMENT 'Computer Section is dept or program !',
  `No. Of Hours` double NOT NULL,
  `Department_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `program`
--

INSERT INTO `program` (`Id`, `Name`, `No. Of Hours`, `Department_ID`) VALUES
(1, 'Statistics and Computer', 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `question` varchar(70) NOT NULL,
  `first_choice` varchar(45) NOT NULL,
  `second_choice` varchar(45) NOT NULL,
  `third_choice` varchar(45) DEFAULT NULL,
  `forth_choice` varchar(45) DEFAULT NULL,
  `topic` varchar(45) NOT NULL,
  `level` varchar(45) NOT NULL,
  `correct_answer` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `question`, `first_choice`, `second_choice`, `third_choice`, `forth_choice`, `topic`, `level`, `correct_answer`) VALUES
(1, 'Your faculty', 'Science', 'Law', 'Commerce', 'engineering', 'A', 'easy', 'Science'),
(2, 'Your year', '1', '2', '3', '4', 'A', 'easy', '4'),
(3, 'DB Doctor', 'Ahmed', 'Wael', 'Zaky', 'Yasser', 'B', 'medium', 'Wael'),
(4, 'FS Doctor', 'Mona', 'Neven', 'Azza', 'Nesren', 'B', 'medium', 'Azza'),
(5, 'Year of Reg', '2013', '2014', '2015', '2016', 'C', 'hard', '2013'),
(6, 'Year of graduation', '2018', '2019', '2020', '2021', 'C', 'hard', '2018');

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
CREATE TABLE `quiz` (
  `id` int(11) NOT NULL,
  `total_deg` int(11) NOT NULL,
  `quiz_generator_id` int(11) NOT NULL,
  `Course_Code` varchar(5) NOT NULL,
  `Registeration_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `quiz_generator`
--

DROP TABLE IF EXISTS `quiz_generator`;
CREATE TABLE `quiz_generator` (
  `id` int(11) NOT NULL,
  `is_current` tinyint(4) NOT NULL,
  `start_topic` varchar(20) NOT NULL,
  `end_topic` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `no_level1` int(11) NOT NULL,
  `no_level2` int(11) NOT NULL,
  `no_level3` int(11) NOT NULL,
  `Doctor_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `quiz_has_questions`
--

DROP TABLE IF EXISTS `quiz_has_questions`;
CREATE TABLE `quiz_has_questions` (
  `quiz_id` int(11) NOT NULL,
  `questions_id` int(11) NOT NULL,
  `student_choice` varchar(45) NOT NULL,
  `is_correct` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `registeration`
--

DROP TABLE IF EXISTS `registeration`;
CREATE TABLE `registeration` (
  `ID` int(11) NOT NULL,
  `Oral` int(11) NOT NULL,
  `Grade` int(11) NOT NULL,
  `Quiz1` int(11) DEFAULT NULL,
  `Quiz2` int(11) DEFAULT NULL,
  `IS_Evaluate1` tinyint(4) DEFAULT NULL,
  `IS_Evaluate2` tinyint(4) DEFAULT NULL,
  `Attendence_Percentage` double DEFAULT NULL,
  `Course_Code` varchar(5) NOT NULL,
  `Student_ID` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `ID` varchar(14) NOT NULL,
  `StartDate` date NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Level` int(11) NOT NULL,
  `GPA` double DEFAULT NULL,
  `AverageMaxHours` double DEFAULT NULL,
  `Program_Id` int(11) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`ID`, `StartDate`, `Name`, `Level`, `GPA`, `AverageMaxHours`, `Program_Id`, `Email`, `Password`) VALUES
('20140918224532', '2012-09-15', 'Medhat Mohamed Hassan Gad', 4, 3, 19, 1, 'medhat.mmh5@gmail.com', '$2y$10$9SCciExenWD8tbH/mn4x3.7cffJpK29MT3C.h7blvApjKesyfliaK');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`Code`),
  ADD KEY `fk_Course_Doctor1_idx` (`Doctor_ID`);

--
-- Indexes for table `course_has_course`
--
ALTER TABLE `course_has_course`
  ADD PRIMARY KEY (`Course_aquired`,`Course_required`),
  ADD KEY `fk_Course_has_Course_Course2_idx` (`Course_required`),
  ADD KEY `fk_Course_has_Course_Course1_idx` (`Course_aquired`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_Doctor_Department1_idx` (`Department_ID`);

--
-- Indexes for table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_Evaluation_Course1_idx` (`Course_Code`);

--
-- Indexes for table `e_question`
--
ALTER TABLE `e_question`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `e_question_has_evaluation`
--
ALTER TABLE `e_question_has_evaluation`
  ADD PRIMARY KEY (`E_Question_ID`,`Evaluation_ID`),
  ADD KEY `fk_E_Question_has_Evaluation_Evaluation1_idx` (`Evaluation_ID`),
  ADD KEY `fk_E_Question_has_Evaluation_E_Question1_idx` (`E_Question_ID`);

--
-- Indexes for table `program`
--
ALTER TABLE `program`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Program_Department1_idx` (`Department_ID`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`id`,`quiz_generator_id`),
  ADD KEY `fk_quiz_quiz_generator1_idx` (`quiz_generator_id`),
  ADD KEY `fk_quiz_Course1_idx` (`Course_Code`),
  ADD KEY `fk_quiz_Registeration1_idx` (`Registeration_ID`);

--
-- Indexes for table `quiz_generator`
--
ALTER TABLE `quiz_generator`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_quiz_generator_Doctor1_idx` (`Doctor_ID`);

--
-- Indexes for table `quiz_has_questions`
--
ALTER TABLE `quiz_has_questions`
  ADD PRIMARY KEY (`quiz_id`,`questions_id`),
  ADD KEY `fk_quiz_has_questions_questions1_idx` (`questions_id`),
  ADD KEY `fk_quiz_has_questions_quiz1_idx` (`quiz_id`);

--
-- Indexes for table `registeration`
--
ALTER TABLE `registeration`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_Registeration_Course1_idx` (`Course_Code`),
  ADD KEY `fk_Registeration_Student1_idx` (`Student_ID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_UNIQUE` (`ID`),
  ADD KEY `fk_Student_Program1_idx` (`Program_Id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `fk_Course_Doctor1` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctor` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `course_has_course`
--
ALTER TABLE `course_has_course`
  ADD CONSTRAINT `fk_Course_has_Course_Course1` FOREIGN KEY (`Course_aquired`) REFERENCES `course` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Course_has_Course_Course2` FOREIGN KEY (`Course_required`) REFERENCES `course` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `fk_Doctor_Department1` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `fk_Evaluation_Course1` FOREIGN KEY (`Course_Code`) REFERENCES `course` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `e_question_has_evaluation`
--
ALTER TABLE `e_question_has_evaluation`
  ADD CONSTRAINT `fk_E_Question_has_Evaluation_E_Question1` FOREIGN KEY (`E_Question_ID`) REFERENCES `e_question` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_E_Question_has_Evaluation_Evaluation1` FOREIGN KEY (`Evaluation_ID`) REFERENCES `evaluation` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `program`
--
ALTER TABLE `program`
  ADD CONSTRAINT `fk_Program_Department1` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `fk_quiz_Course1` FOREIGN KEY (`Course_Code`) REFERENCES `course` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_quiz_Registeration1` FOREIGN KEY (`Registeration_ID`) REFERENCES `registeration` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_quiz_quiz_generator1` FOREIGN KEY (`quiz_generator_id`) REFERENCES `quiz_generator` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `quiz_generator`
--
ALTER TABLE `quiz_generator`
  ADD CONSTRAINT `fk_quiz_generator_Doctor1` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctor` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `quiz_has_questions`
--
ALTER TABLE `quiz_has_questions`
  ADD CONSTRAINT `fk_quiz_has_questions_questions1` FOREIGN KEY (`questions_id`) REFERENCES `questions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_quiz_has_questions_quiz1` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `registeration`
--
ALTER TABLE `registeration`
  ADD CONSTRAINT `fk_Registeration_Course1` FOREIGN KEY (`Course_Code`) REFERENCES `course` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Registeration_Student1` FOREIGN KEY (`Student_ID`) REFERENCES `student` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `fk_Student_Program1` FOREIGN KEY (`Program_Id`) REFERENCES `program` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
