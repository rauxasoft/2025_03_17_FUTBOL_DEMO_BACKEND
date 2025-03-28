DROP TABLE LANCES IF EXISTS;
DROP TABLE SIMULADOR_LANCES IF EXISTS;
DROP TABLE TIPOS_LANCE IF EXISTS;
DROP TABLE PARTIDOS IF EXISTS;
DROP TABLE EQUIPOS IF EXISTS;
DROP TABLE ARBITROS IF EXISTS;

DROP SEQUENCE EQUIPOS_SEQ IF EXISTS;

CREATE SEQUENCE "EQUIPOS_SEQ" 
	MINVALUE 1 
	MAXVALUE 999999999 
	INCREMENT BY 50 
	START WITH 1000
	NOCACHE 
	NOCYCLE;
	
CREATE SEQUENCE "ARBITROS_SEQ" 
	MINVALUE 1 
	MAXVALUE 999999999 
	INCREMENT BY 50 
	START WITH 1000
	NOCACHE 
	NOCYCLE;
	
CREATE TABLE ARBITROS(
	ID							BIGINT			NOT NULL,
	NOMBRE						VARCHAR(50)		,
	APELLIDO1					VARCHAR(50)		,
	APELLIDO2					VARCHAR(50)		,
	FECHA_NACIMIENTO			DATE			,
	COLEGIO						VARCHAR(100) 	,
	PRIMARY KEY(ID)
);	

CREATE TABLE EQUIPOS(
	ID							BIGINT			NOT NULL,
	NOMBRE						VARCHAR(100)	,
	CIUDAD						VARCHAR(100)	,
	FECHA_FUNDACION				DATE			,
	CUMPLE_FAIR_PLAY_FINANCIERO	BOOLEAN			,
	RANKING_EUROPEO				INTEGER			,
	PRESIDENTE					VARCHAR(100)	,
	LINK_ESCUDO					VARCHAR(250)	,
	PRIMARY KEY (ID)
);

CREATE TABLE PARTIDOS(
	ID							BIGINT			NOT NULL,
	ID_JORNADA					INTEGER			,
	FECHA						TIMESTAMP		,
	ID_EQUIPO_LOCAL				BIGINT			,
	ID_EQUIPO_VISITANTE			BIGINT			,
	ESTADO						VARCHAR(100)	,
	GOLES_LOCAL					INTEGER			,
	GOLES_VISITANTE				INTEGER			,
	ID_ARBITRO					BIGINT			,
	PRIMARY KEY (ID),
	FOREIGN KEY (ID_EQUIPO_LOCAL) REFERENCES EQUIPOS (ID),
	FOREIGN KEY (ID_EQUIPO_VISITANTE) REFERENCES EQUIPOS (ID),
	FOREIGN KEY (ID_ARBITRO) REFERENCES ARBITROS (ID)
);

CREATE TABLE TIPOS_LANCE(
	ID							VARCHAR(100)	NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE LANCES(
	INDEX						BIGINT			NOT NULL,
	ID_PARTIDO					BIGINT			,
	INSTANTE_TIEMPO				TIMESTAMP		,
	MINUTO						INTEGER			,
	ID_LANCE					VARCHAR(100)	,
	COMENTARIO					VARCHAR(250)	,
	
	FOREIGN KEY (ID_PARTIDO) REFERENCES PARTIDOS (ID),
	FOREIGN KEY (ID_LANCE) REFERENCES TIPOS_LANCE (ID)
);

CREATE TABLE SIMULADOR_LANCES(
	ID							BIGINT			NOT NULL,
	ID_PARTIDO					BIGINT			,
	INSTANTE_TIEMPO				TIMESTAMP		,
	MINUTO						INTEGER			,
	ID_LANCE					VARCHAR(100)	,
	COMENTARIO					VARCHAR(250)	,
	
	PRIMARY KEY (ID),
	FOREIGN KEY (ID_PARTIDO) REFERENCES PARTIDOS (ID),
	FOREIGN KEY (ID_LANCE) REFERENCES TIPOS_LANCE (ID)
);


CREATE VIEW ULTIMA_JORNADA AS (

	SELECT P.ID	             AS ID_PARTIDO,
	       P.ID_JORNADA      AS JORNADA,
	       P.ESTADO          AS ESTADO_PARTIDO,   
	       EL.NOMBRE         AS EQUIPO_LOCAL,
	       P.GOLES_LOCAL     AS GOLES_LOCAL,
	       EV.NOMBRE         AS EQUIPO_VISITANTE,
	       P.GOLES_VISITANTE AS GOLES_VISITANTE,
	       L.INDEX           AS INDICE_LANCE,
	       L.MINUTO          AS MINUTO_LANCE,
	       L.ID_LANCE        AS TIPO_LANCE,
	       L.COMENTARIO      AS COMENTARIO
	               
	FROM PARTIDOS P LEFT JOIN LANCES L ON P.ID = L.ID_PARTIDO 
	                                           JOIN EQUIPOS EL ON P.ID_EQUIPO_LOCAL = EL.ID 
	                                           JOIN EQUIPOS EV ON P.ID_EQUIPO_VISITANTE = EV.ID
	WHERE P.ID IN (11, 12)

);