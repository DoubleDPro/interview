/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 15.2 		*/
/*  Created On : 04-авг-2024 20:29:17 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Tables */

DROP TABLE IF EXISTS text_tasks.text_tasks CASCADE
;

/* Create Tables */

CREATE TABLE text_tasks.text_tasks
(
    id uuid NOT NULL,
    content text NOT NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE text_tasks.text_tasks ADD CONSTRAINT "PK_text_task"
    PRIMARY KEY (id)
;