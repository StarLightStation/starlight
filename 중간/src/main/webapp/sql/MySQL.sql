-- starlight.board definition
-- [게시글 테이블]

CREATE TABLE `board` (
  `bNum` int NOT NULL AUTO_INCREMENT,
  `mID` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pNum` int NOT NULL,
  `bContent` varchar(500) NOT NULL,
  `bStar` double NOT NULL,
  `bDate` date NOT NULL DEFAULT (now()),
  `mName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`bNum`),
  KEY `fk_board_mid` (`mID`),
  CONSTRAINT `fk_board_mid` FOREIGN KEY (`mID`) REFERENCES `member` (`mID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- starlight.`member` definition
-- [멤버 테이블]

CREATE TABLE `member` (
  `mID` varchar(30) NOT NULL,
  `mPW` varchar(30) NOT NULL,
  `mName` varchar(20) NOT NULL,
  `subscription` int NOT NULL DEFAULT '0',
  `isAdmin` int NOT NULL DEFAULT '0',
  `mPhone` varchar(30) NOT NULL,
  `signUpKind` varchar(20) NOT NULL,
  PRIMARY KEY (`mID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- starlight.order_ definition
-- [주문 테이블]

CREATE TABLE `order_` (
  `oNum` int NOT NULL AUTO_INCREMENT,
  `mID` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `oDate` date NOT NULL DEFAULT (now()),
  `oPrice` int NOT NULL,
  `oState` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '결제대기',
  `oAddress` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`oNum`),
  KEY `order__FK` (`mID`),
  CONSTRAINT `order__FK` FOREIGN KEY (`mID`) REFERENCES `member` (`mID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- starlight.orderdetail definition
-- [주문 상세 테이블]

CREATE TABLE `orderdetail` (
  `odNum` int NOT NULL AUTO_INCREMENT,
  `pNum` int NOT NULL,
  `oNum` int NOT NULL,
  `odCnt` int NOT NULL,
  `isWrite` varchar(20) NOT NULL DEFAULT '작성 전',
  PRIMARY KEY (`odNum`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- starlight.product definition
-- [상품 (주류) 테이블]

-- starlight.product definition

CREATE TABLE `product` (
  `pNum` int NOT NULL AUTO_INCREMENT,
  `pName` varchar(30) NOT NULL,
  `pPrice` int NOT NULL,
  `pImage` varchar(50) NOT NULL,
  `pCnt` int NOT NULL,
  `pCategory` varchar(10) NOT NULL,
  `pAlcohol` double NOT NULL,
  `pSweet` varchar(10) DEFAULT NULL,
  `pSour` varchar(10) DEFAULT NULL,
  `pSparkle` varchar(10) DEFAULT NULL,
  `pImageDetail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`pNum`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- starlight.subscription definition
-- [구독 정보 테이블]

CREATE TABLE `subscription` (
  `subNum` int NOT NULL,
  `subName` varchar(30) NOT NULL,
  `subPrice` int NOT NULL,
  PRIMARY KEY (`subNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- starlight.subsinfo definition
-- [구독 상세 테이블]

CREATE TABLE `subsinfo` (
  `sInfoNum` int NOT NULL AUTO_INCREMENT,
  `mID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `subNum` int NOT NULL,
  `sInfoPeriod` date NOT NULL DEFAULT ((now() + interval 1 month)),
  PRIMARY KEY (`sInfoNum`),
  KEY `subsinfo_FK` (`mID`),
  CONSTRAINT `subsinfo_FK` FOREIGN KEY (`mID`) REFERENCES `member` (`mID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;