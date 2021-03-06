-- Replace the Scheme with your own Schema! --
SET SCHEMA "I045664";
DROP TABLE TWEETS;
CREATE COLUMN TABLE TWEETS(
	"ID" INTEGER NOT NULL,
	"USER_NAME" NVARCHAR(100),
	"CREATED_AT" DATE,
	"TEXT" NVARCHAR (140),
	"HASH_TAGS" NVARCHAR (100),
	PRIMARY KEY("ID")
);
CREATE SEQUENCE "I045664"."TWEET_SEQUENCE" INCREMENT BY 1 START WITH 1 NO CYCLE; 

