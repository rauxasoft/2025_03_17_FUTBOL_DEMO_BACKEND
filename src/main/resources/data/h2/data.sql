
INSERT INTO EQUIPOS (ID, CUMPLE_FAIR_PLAY_FINANCIERO, RANKING_EUROPEO, NOMBRE, CIUDAD, FECHA_FUNDACION, PRESIDENTE, LINK_ESCUDO) VALUES
(100, TRUE, 1, 'Real Madrid', 'Madrid', '1910-10-23','Florencio Sánchez','http://elmiradorespagnol.free.fr/futbol/original/Real%20Madrid.png'),
(101, TRUE, 12, 'F.C. Barcelona', 'Barcelona', '1899-04-16','Joel Finestres','http://elmiradorespagnol.free.fr/futbol/original/Barcelona.png'),
(102, FALSE, 41,'Athletic Bilbao', 'Bilbao', '1898-02-01','Anna Losada','http://elmiradorespagnol.free.fr/futbol/original/Athletic%20Bilbao.gif'),
(103, TRUE, 78,'Real Sociedad', 'San Sebastián', '1912-09-11','Carloto Ruiz','http://elmiradorespagnol.free.fr/futbol/original/Real%20Sociedad.gif');

INSERT INTO ARBITROS(ID, NOMBRE, APELLIDO1, APELLIDO2, FECHA_NACIMIENTO, COLEGIO) VALUES
(1, 'Segundo', 'Melero', 'Molano', '1997-10-21', 'Andaluz'),
(2, 'José Ramón', 'Lentijo', 'Pereira', '2000-01-08', 'Extremeño'),
(3, 'Carmelo', 'Fuentes', 'Badalejo', '2000-01-08', 'Extremeño'),
(4, 'Juan José', 'Badimón', 'Altamira', '2000-01-08', 'Extremeño'),
(5, 'Carlos', 'Farriño', 'Buruanda', '2000-01-08', 'Extremeño'),
(6, 'Marcos', 'Cortés', 'Cantalejo', '2000-01-08', 'Extremeño');

INSERT INTO PARTIDOS (ID, ID_JORNADA, ID_ARBITRO, FECHA, ID_EQUIPO_LOCAL, ID_EQUIPO_VISITANTE, ESTADO, GOLES_LOCAL, GOLES_VISITANTE) VALUES
(1,  1, 1, TIMESTAMP '2024-11-03 17:00', 100, 101, 'FINALIZADO', 1, 4),
(2,  1, 2, TIMESTAMP '2024-11-03 17:00', 102, 103, 'FINALIZADO', 1, 1),
(3,  2, 3, TIMESTAMP '2024-11-10 17:00', 101, 102, 'FINALIZADO', 2, 2),
(4,  2, 4, TIMESTAMP '2024-11-10 21:00', 103, 100, 'FINALIZADO', 1, 4),
(5,  3, 1, TIMESTAMP '2024-11-16 18:00', 100, 102, 'PENDIENTE', NULL, NULL),
(6,  3, 5, TIMESTAMP '2024-11-17 18:30', 103, 101, 'PENDIENTE', NULL, NULL),
(7,  4, 6, TIMESTAMP '2024-11-24 19:00', 101, 100, 'PENDIENTE', NULL, NULL),
(8,  4, 1, TIMESTAMP '2024-11-24 21:00', 103, 102, 'PENDIENTE', NULL, NULL),
(9,  5, 3, TIMESTAMP '2024-12-01 17:00', 102, 101, 'PENDIENTE', NULL, NULL),
(10, 5, 4, TIMESTAMP '2024-12-01 17:00', 100, 103, 'PENDIENTE', NULL, NULL),
(11, 6, 6, TIMESTAMP '2024-12-08 17:00', 102, 100, 'PENDIENTE', NULL, NULL),
(12, 6, 5, TIMESTAMP '2024-12-08 17:00', 101, 103, 'PENDIENTE', NULL, NULL);

INSERT INTO TIPOS_LANCE (ID) VALUES
('INICIO_DEL_PARTIDO'),
('FIN_PRIMERA_PARTE'),
('INICIO_SEGUNDA_PARTE'),
('FINAL_DEL_PARTIDO'),
('GOL_LOCAL'),
('GOL_VISITANTE'),
('TARJETA_AMARILLA'),
('TARJETA_ROJA'),
('FALTA'),
('PENALTY'),
('COMENTARIO'),
('OCASION'),
('REVISION_VAR'),
('GOL_LOCAL_ANULADO'),
('GOL_VISITANTE_ANULADO');

-- ********************************************************************************
--
--                                   Jornada 1 
--
-- ********************************************************************************

-- Partido 1

INSERT INTO LANCES (INDEX, ID_PARTIDO, INSTANTE_TIEMPO, MINUTO, ID_LANCE, COMENTARIO) VALUES
( 0, 1, TIMESTAMP '2024-11-03 17:00',  0, 'INICIO_DEL_PARTIDO'    , 'Rueda el balón'), 
( 1, 1, TIMESTAMP '2024-11-03 17:00',  5, 'GOL_VISITANTE'         , 'Goooolazo de Lamin Yamal!'),
( 2, 1, TIMESTAMP '2024-11-03 17:00',  9, 'COMENTARIO'            , 'Sin duda va a ser el gol de la jornada. Un pepinazo con rosca desde la frontal del area que entra quitando las telarañas de la escuadra. Imparable.'),
( 3, 1, TIMESTAMP '2024-11-03 17:00', 14, 'COMENTARIO'            , 'Gran dominio del Barça.'),
( 4, 1, TIMESTAMP '2024-11-03 17:00', 20, 'TARJETA_AMARILLA'      , 'Para Mbappe, fruto de la desesperación'),
( 5, 1, TIMESTAMP '2024-11-03 17:00', 23, 'GOL_VISITANTE'         , 'Gol de Fermín. Fuerte en la cepa del palo. Nada pudo hacer el cancerbero blanco.'),
( 6, 1, TIMESTAMP '2024-11-03 17:00', 27, 'COMENTARIO'            , 'El Barça quiere más'),
( 7, 1, TIMESTAMP '2024-11-03 17:00', 46, 'FIN_PRIMERA_PARTE'     , 'Fin de la primiera parte. Jugadores al tunel de vestuarios.'),
( 8, 1, TIMESTAMP '2024-11-03 17:00', 49, 'COMENTARIO'   	      , 'Ochenta mil asistentes esta tarde en el Barnabeu'),
( 9, 1, TIMESTAMP '2024-11-03 17:00', 52, 'COMENTARIO'   	      , 'El partido está resultando muy positivo para los intereses del Barça. Anceloti tendrá que arreglar muchas cosas en el vestuario si quiere optar a algo positivo. La liga se juega hoy en el Bernabeu'),
(10, 1, TIMESTAMP '2024-11-03 17:00', 60, 'INICIO_SEGUNDA_PARTE'  , 'No hay cambios en ningún equipo. ¿Podrá remontar el equipo merengue?'),
(11, 1, TIMESTAMP '2024-11-03 17:00', 86, 'COMENTARIO'            , 'Nico Williams se estará arrepintiendo de no formar parte de esta armada invencible que ha creado Flick'),
(12, 1, TIMESTAMP '2024-11-03 17:00', 73, 'OCASION'               , 'Ocasión fallada por Vinicius. Le faltó poco'),
(13, 1, TIMESTAMP '2024-11-03 17:00', 79, 'GOL_VISITANTE'         , 'Golazo de Dani Olmo. Chutazo desde 40 metros que entra por la escuadra'),
(14, 1, TIMESTAMP '2024-11-03 17:00', 84, 'GOL_VISITANTE'         , 'Sin tiempo a reaccionar vuelve a marcar el Barça. Esta vez Pedri.'),
(15, 1, TIMESTAMP '2024-11-03 17:00', 86, 'COMENTARIO'            , 'El público abandona el Bernabeu'),
(16, 1, TIMESTAMP '2024-11-03 17:00', 95, 'TARJETA_AMARILLA'      , 'A Vinicius por protestar'),
(17, 1, TIMESTAMP '2024-11-03 17:00',103, 'GOL_LOCAL'    	      , 'Recorta distancias Mbappe'),
(18, 1, TIMESTAMP '2024-11-03 17:00',104, 'COMENTARIO'    	      , 'Será divertido ver los comentarios de Roncero en el postpartido'),
(19, 1, TIMESTAMP '2024-11-03 17:00',105, 'FINAL_DEL_PARTIDO'     , 'Pita el final Honorio Mesquides. Debacle del equipo merengue. El Barça sale reforzado');

-- Partido 2

INSERT INTO LANCES (INDEX, ID_PARTIDO, INSTANTE_TIEMPO, MINUTO, ID_LANCE, COMENTARIO) VALUES
( 0, 2, TIMESTAMP '2024-11-03 17:00',  0, 'INICIO_DEL_PARTIDO'    , 'Empieza el encuentro'),
( 1, 2, TIMESTAMP '2024-11-03 17:32', 39, 'COMENTARIO'            , 'Fuera de juego de Lorenzo'),
( 2, 2, TIMESTAMP '2024-11-03 17:00', 45, 'FIN_PRIMERA_PARTE'     , 'Partido aburrido. Esto acabará en tablas.'),
( 3, 2, TIMESTAMP '2024-11-03 17:00', 45, 'INICIO_SEGUNDA_PARTE'  , 'Se reanuda el encuentro. Empieza a diluviar.'),
( 4, 2, TIMESTAMP '2024-11-03 17:00', 78, 'OCASION'               , 'Huuuuy! Alto! La tuvo Ivan Ferlosio.'),
( 5, 2, TIMESTAMP '2024-11-03 17:00', 81, 'GOL_VISITANTE'         , 'Gol de Fran Herández'),
( 6, 2, TIMESTAMP '2024-11-03 17:00', 85, 'GOL_LOCAL'    		  , 'Empata Sousa Badia'),
( 7, 2, TIMESTAMP '2024-11-03 17:00', 87, 'COMENTARIO'            , 'Muy mal Nico Williams. Nada que ver con el nivel mostrado en la Eurocopa. Su magia se apaga por momentos.'),
( 8, 2, TIMESTAMP '2024-11-03 17:00', 90, 'FINAL_DEL_PARTIDO'     , 'Fin del partido. Ni un minuto de prolongación. Tablas en el marcador y punto de oro para los visitantes');

-- ********************************************************************************
--
--                                   Jornada 2
--
-- ********************************************************************************

-- Partido 3

INSERT INTO LANCES (INDEX, ID_PARTIDO, INSTANTE_TIEMPO, MINUTO, ID_LANCE, COMENTARIO) VALUES
( 0, 3, TIMESTAMP '2024-11-03 17:00',  0, 'INICIO_DEL_PARTIDO'    , 'Inicia el encuentro el colegiado Marcelo Redondo'), 
( 1, 3, TIMESTAMP '2024-11-03 17:00', 15, 'GOL_LOCAL'             , 'GOL! A la salida de un corner. Se impone Julio Jordán a sus defensores y marca de cabeza'),
( 2, 3, TIMESTAMP '2024-11-03 17:00', 22, 'TARJETA_ROJA'          , 'Entrada salvaje de Guzmán! Se queda con 10 el equipo visitante'),
( 3, 3, TIMESTAMP '2024-11-03 17:00', 27, 'COMENTARIO'            , 'El partido se pone cuesta arriba para los de Perez Laso'),
( 4, 3, TIMESTAMP '2024-11-03 17:00', 47, 'FIN_PRIMERA_PARTE'     , 'Finaliza la primiera parte. Jugadores a la ducha.'),
( 5, 3, TIMESTAMP '2024-11-03 17:00', 47, 'INICIO_SEGUNDA_PARTE'  , '¿Podrá remontar el equipo merengue?'),
( 6, 3, TIMESTAMP '2024-11-03 17:00', 69, 'GOL_VISITANTE'         , 'Empata Casamaro'),
( 7, 3, TIMESTAMP '2024-11-03 17:00', 78, 'GOL_VISITANTE'         , 'Doblete de Casamaro. Nadie podía imaginar esta remontada!'),
( 8, 3, TIMESTAMP '2024-11-03 17:00', 88, 'TARJETA_AMARILLA'      , 'A Badimón por perder tiempo en un saque de banda'),
( 9, 3, TIMESTAMP '2024-11-03 17:00', 89, 'GOL_LOCAL'    		  , 'Empata Carlos Marín'),
(10, 3, TIMESTAMP '2024-11-03 17:00', 94, 'FINAL_DEL_PARTIDO'     , 'Empate justo en el marcador');

-- Partido 4

INSERT INTO LANCES (INDEX, ID_PARTIDO, INSTANTE_TIEMPO, MINUTO, ID_LANCE, COMENTARIO) VALUES
( 0, 4, TIMESTAMP '2024-11-03 17:00',  0, 'INICIO_DEL_PARTIDO'    , 'Rueda el balón en el Reale Arena'), 
( 1, 4, TIMESTAMP '2024-11-03 17:00',  8, 'GOL_VISITANTE'         , 'Goooolazo de Vinicius JR!'),
( 2, 4, TIMESTAMP '2024-11-03 17:00', 14, 'COMENTARIO'            , 'No hay un dominio claro entre Real Sociedad y Real Madrid.'),
( 3, 4, TIMESTAMP '2024-11-03 17:00', 23, 'GOL_VISITANTE'         , 'Gol de Dani Carvajal'),
( 4, 4, TIMESTAMP '2024-11-03 17:00', 27, 'COMENTARIO'            , 'El Real Madrid un sin parar'),
( 5, 4, TIMESTAMP '2024-11-03 17:00', 46, 'FIN_PRIMERA_PARTE'     , 'Fin de la primera parte. Jugadores al tunel de vestuarios.'),
( 6, 4, TIMESTAMP '2024-11-03 17:00', 46, 'INICIO_SEGUNDA_PARTE'  , '¿Podrá remontar el equipo txuriurdin?'),
( 7, 4, TIMESTAMP '2024-11-03 17:00', 52, 'OCASION'               , 'Ocasión fallada local. Le faltó poco'),
( 8, 4, TIMESTAMP '2024-11-03 17:00', 67, 'GOL_VISITANTE'         , 'Golazo de Dani de nuevo. Chutazo desde 40 metros que entra por la escuadra'),
( 9, 4, TIMESTAMP '2024-11-03 17:00', 71, 'GOL_VISITANTE'         , 'Sin tiempo a reaccionar vuelve a marcar el Madrid.'),
(10, 4, TIMESTAMP '2024-11-03 17:00', 82, 'COMENTARIO'            , 'El público abandona el Reale Arena'),
(11, 4, TIMESTAMP '2024-11-03 17:00', 88, 'TARJETA_AMARILLA'      , 'A Oyarzabal por protestar'),
(12, 4, TIMESTAMP '2024-11-03 17:00', 89, 'GOL_LOCAL'    		  , 'Recorta distancias Oyarzabal'),
(13, 4, TIMESTAMP '2024-11-03 17:00', 94, 'FINAL_DEL_PARTIDO'     , 'Pita el final Rodríguez Rodríguez. Debacle del equipo txuriurdin. El Madrid sale reforzado');

-- ********************************************************************************
--
--                                   Jornada 3
--
-- ********************************************************************************

-- Partido 5

-- Partido 6

-- ********************************************************************************
--
--                                   Jornada 4
--
-- ********************************************************************************

-- Partido 7

-- Partido 8

-- ********************************************************************************
--
--                                   Jornada 5
--
-- ********************************************************************************

-- Partido 9

-- Partido 10

-- ********************************************************************************
--
--                                   Jornada 6 (EN SIMULADOR_LANCES)
--
-- ********************************************************************************

-- Partido 11

INSERT INTO SIMULADOR_LANCES (ID, ID_PARTIDO, INSTANTE_TIEMPO, MINUTO, ID_LANCE, COMENTARIO) VALUES
( 0, 11, TIMESTAMP '2024-11-03 17:00',  0, 'INICIO_DEL_PARTIDO'   , 'Rueda el balón'), 
( 1, 11, TIMESTAMP '2024-11-03 17:00',  1, 'TARJETA_ROJA'         , 'La lía parda Vinicius en el primer minuto de partido. ¡A la caseta!. No le hace falta ducharse.'),
( 2, 11, TIMESTAMP '2024-11-03 17:00', 14, 'COMENTARIO'           , 'Gran inicio de partido del Athletic, que domina cómodamente.'),
( 3, 11, TIMESTAMP '2024-11-03 17:00', 18, 'OCASION'              , 'Ocasión clara de Nico Williams. El balón se va por poco'),
( 4, 11, TIMESTAMP '2024-11-03 17:00', 25, 'GOL_VISITANTE'        , 'Gooooool de Carvajal!'),
( 5, 11, TIMESTAMP '2024-11-03 17:00', 27, 'GOL_VISITANTE_ANULADO', 'Gol anulado a Carvajal; claro fuera de juego, no hizo falta VAR'),
( 6, 11, TIMESTAMP '2024-11-03 17:00', 37, 'COMENTARIO'           , 'Partido soporífero del Madrid. Los espectadores aprovechan para comerse el bokata.'),
( 7, 11, TIMESTAMP '2024-11-03 17:00', 44, 'GOL_LOCAL'            , 'BACALO BACALAO BACALAO BACALAOOOOOOOOOOOOOOOOOOOOO!!! GOL de Iñaki Williams!!. Le pega mal, pero también vale!!'),
( 8, 11, TIMESTAMP '2024-11-03 17:00', 47, 'FIN_PRIMERA_PARTE'    , 'Fin de la primiera parte. Jugadores al tunel de vestuarios.'),
( 9, 11, TIMESTAMP '2024-11-03 17:00', 46, 'INICIO_SEGUNDA_PARTE' , '¿Podrá remontar el equipo merengue?'),
(10, 11, TIMESTAMP '2024-11-03 17:00', 51, 'OCASION'              , 'Ocasión fallada por Mbappe, que llevaba desaparecido todo el partido'),
(11, 11, TIMESTAMP '2024-11-03 17:00', 67, 'GOL_VISITANTE'        , 'Gol del Madrid. Gol de Luka Modric, que pasaba por ahí y se la encuentra.'),
(12, 11, TIMESTAMP '2024-11-03 17:00', 71, 'PENALTY'              , 'Penalty a favor del Athletic!!  Penalty claro e indiscutible. Lanza Iñaki Williams '),
(13, 11, TIMESTAMP '2024-11-03 17:00', 71, 'GOL_LOCAL'            , 'Golazo de Williams!! Disparo sin contamplaciones rasa y al palo largo.'),
(14, 11, TIMESTAMP '2024-11-03 17:00', 81, 'COMENTARIO'           , 'El público se lo está pasando en grande esta tarde en San Mamés'),
(15, 11, TIMESTAMP '2024-11-03 17:00', 88, 'TARJETA_AMARILLA'     , 'A Mbappe, por protestar'),
(16, 11, TIMESTAMP '2024-11-03 17:00', 89, 'GOL_LOCAL'     	      , 'BACALO BACALAO BACALAO BACALAOOOOOOOOOOOOOOOOOOOOO!!! Gol de Lekue!! Impresionante remate de cabeza'),
(17, 11, TIMESTAMP '2024-11-03 17:00', 93, 'FINAL_DEL_PARTIDO'    , 'Pita el final Sobrino Magan. Muy correcto el arbitraje del árbitro manchego');

-- Partido 12

INSERT INTO SIMULADOR_LANCES (ID, ID_PARTIDO, INSTANTE_TIEMPO, MINUTO, ID_LANCE, COMENTARIO) VALUES
(18, 12, TIMESTAMP '2024-11-03 17:00',  0, 'INICIO_DEL_PARTIDO'   , 'Empieza el partido en el Camp Nou'),
(19, 12, TIMESTAMP '2024-11-03 17:00', 10, 'COMENTARIO'           , 'Fuera de juego de Lorenzo'),
(20, 12, TIMESTAMP '2024-11-03 17:00', 15, 'FALTA'                , 'Falta peligrosa de Oyarzabal al borde del área'),
(21, 12, TIMESTAMP '2024-11-03 17:00', 16, 'OCASION'              , 'Lanza la falta Lamine Yamal, que se va por poco'),
(22, 12, TIMESTAMP '2024-11-03 17:00', 25, 'TARJETA_AMARILLA'     , 'Tarjeta amarilla a Elustondo por falta clara a Pedri'),
(23, 12, TIMESTAMP '2024-11-03 17:00', 45, 'FIN_PRIMERA_PARTE'    , 'Partido aburrido. Esto acabará en tablas.'),
(24, 12, TIMESTAMP '2024-11-03 17:00', 45, 'INICIO_SEGUNDA_PARTE' , 'Se reanuda el encuentro. A ver si mejora la cosa.'),
(25, 12, TIMESTAMP '2024-11-03 17:00', 68, 'OCASION'              , 'Huuuuy! Alto! La tuvo Lewandowski.'),
(26, 12, TIMESTAMP '2024-11-03 17:00', 81, 'GOL_LOCAL'            , 'Gol de Ansu Fati!! Triple regate que deja sentados a los rivales y golazo por la escuadra!!'),
(27, 12, TIMESTAMP '2024-11-03 17:00', 85, 'GOL_VISITANTE'        , 'Empata Igor Zubeldia'),
(28, 12, TIMESTAMP '2024-11-03 17:00', 87, 'GOL_LOCAL'            , 'Gol de Ferran Torres'),
(29, 12, TIMESTAMP '2024-11-03 17:00', 87, 'COMENTARIO'           , 'Se ha vuelto loco el partido. Tres goles en 5 minutos.'),
(30, 12, TIMESTAMP '2024-11-03 17:00', 90, 'FINAL_DEL_PARTIDO'    , 'Fin del partido. Ni un minuto de prolongación. Victoria del Barça, que se acerca una jornada más a la victoria en la liga. Esto es todo por hoy');