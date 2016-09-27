-- dev_chat database version 4

CREATE DATABASE dev_chat;

CREATE TABLE users
(
username			varchar(50)		PRIMARY KEY,
password			varchar(50)		NOT NULL, 	-- password constraints are checked by client
email				varchar(50)		NOT NULL,
firstname			varchar(50),
lastname			varchar(50),
languages			varchar(100),
location			varchar(50),
company				varchar(50),
job_title			varchar(50),
website				varchar(50),
personal_info		varchar(250)
);


CREATE TABLE teams
(
team_id				serial			PRIMARY KEY,
team_name			varchar(50)		NOT NULL	UNIQUE
);

CREATE TABLE team_members
(
team_id				integer			REFERENCES teams (team_id),
username			varchar(50)		REFERENCES users (username),
delivered			boolean			DEFAULT false, -- not used
PRIMARY KEY 		(team_id, username)
);

CREATE TABLE private_chat
(
chat_id				serial			PRIMARY KEY,
user1				varchar(50)		NOT NULL	REFERENCES users (username),
delivered1			boolean			DEFAULT false,	-- not used	
user2				varchar(50)		NOT NULL	REFERENCES users (username),
delivered2			boolean			DEFAULT false	-- not used
);

CREATE TABLE private_messages
(
message_id			serial,
chat_id				integer			REFERENCES private_chat (chat_id),
message_sent		timestamptz		NOT NULL,	-- converted from calendar object
user_posted			varchar(50)		NOT NULL REFERENCES users (username),
text				text,				-- allows blank messages
PRIMARY KEY (chat_id, message_id) 				
);

CREATE TABLE team_messages
(
message_id			serial,
chat_id				integer			REFERENCES teams (team_id),
message_sent		timestamptz		NOT NULL,
user_posted			varchar(50)		NOT NULL REFERENCES users (username),
text				text,			
PRIMARY KEY (chat_id, message_id) 				
);
