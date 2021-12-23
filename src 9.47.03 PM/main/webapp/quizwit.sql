use quizwit;

CREATE TABLE quizzes (
	quizId INT NOT NULL AUTO_INCREMENT,
    quizName VARCHAR(1000) NOT NULL,
    quizDesc VARCHAR(2000) NOT NULL,
    quizKey VARCHAR(30) NOT NULL,
    difficultyLevel TINYINT NOT NULL,
    state TINYINT NOT NULL,
    public TINYINT NOT NULL,
    timeDuration INT NOT NULL,
    startTime TIMESTAMP,
    endTime TIMESTAMP,
    shuffle TINYINT NOT NULL,
    createdOn TIMESTAMP NOT NULL,
    adminId INT NOT NULL,
    PRIMARY KEY (quizId),
    FOREIGN KEY (adminId) REFERENCES admin(adminId)
);

CREATE TABLE sections (
	sectionId INT NOT NULL AUTO_INCREMENT,
    sectionDesc VARCHAR(1000) NOT NULL,
    passingScore DOUBLE NOT NULL,
    quizId INT NOT NULL,
    PRIMARY KEY (sectionId),
    FOREIGN KEY (quizId) REFERENCES quizzes(quizId)
);

CREATE TABLE question_bank (
	questionId INT NOT NULL AUTO_INCREMENT,
    quizDesc VARCHAR(6000) NOT NULL,
    answer VARCHAR(10),
    sectionId INT NOT NULL,
    marks DOUBLE NOT NULL,
    negativeMarking DOUBLE NOT NULL,
    questionImg VARCHAR(1000),
    PRIMARY KEY (questionId),
    FOREIGN KEY (sectionId) REFERENCES sections(sectionId)
);

CREATE TABLE mcq_options (
	optionId INT NOT NULL AUTO_INCREMENT, 
    optionDesc VARCHAR(2000), 
    optionImg VARCHAR(1000), 
    questionId INT NOT NULL,
	PRIMARY KEY (optionId),
    FOREIGN KEY (questionId) REFERENCES question_bank(questionId)
);