# guava sample
	com.example.Config mysql접속 정보 설정

# 테이블 생성
	use test;

	CREATE TABLE `member` (
	`no` int(11) NOT NULL AUTO_INCREMENT,
	`id` varchar(50) NOT NULL,
	`name` varchar(50) NOT NULL,
	`password` varchar(50) NOT NULL,
	`age` int(11) NOT NULL,
	`r_dt` datetime NOT NULL,
	PRIMARY KEY (`no`)
	) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;

	CREATE TABLE `login_log` (
	`no` int(11) NOT NULL AUTO_INCREMENT,
	`id` varchar(50) NOT NULL,
	`name` varchar(50) NOT NULL,
	`age` int(11) NOT NULL,
	`r_dt` datetime NOT NULL,
	PRIMARY KEY (`no`)
	) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;

