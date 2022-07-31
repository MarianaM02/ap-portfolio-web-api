-- Datos iniciales BD portfolio

-- Users -----
-- Hacer variables en Heroku!

-- Profile -----
INSERT INTO portfolio.profile (about,github_url,last_name,linkedin_url,location,name,picture_url,title,fk_user) VALUES
	 ('Soy estudiante de Sistemas y actualmente estoy formandome en Web Development con especialización en Back End. Soy una persona sumamente autodidacta y curiosa, siempre explorando nuevos desafíos. Estoy en búsqueda de  encontrar un ambiente laboral que promueva el aprendizaje y la mejora personal constante.','https://github.com/MarianaM02','Madeira','https://www.linkedin.com/in/marianasmadeira/','Merlo, Bs As.','Mariana','https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png','Java Backend',1);


-- Education -----
INSERT INTO portfolio.education (description,end_date,picture_url,place,start_date,title,fk_user) VALUES
	 ('Noviembre 2021 - Mayo 2022',NULL,'','Ministerio de Desarrollo Productivo',NULL,'Argentina Programa: #YoProgramo',1),
	 ('2021 - 2022',NULL,'','AdaITW',NULL,'Java Backend',1),
	 ('Marzo 2021 - Agosto 2021',NULL,'','PoloTIC Misiones',NULL,'Desarrollo Web Fullstack con Java con perfil Junior',1),
	 ('Octubre 2020 - Diciembre 2020',NULL,'','Ministerio de Desarrollo Productivo',NULL,'Argentina Programa: #SéProgramar',1),
	 ('2021 - Actualidad',NULL,'','Universidad Nacional del Oeste',NULL,'Licenciatura en Informática',1);


-- Job Experience -----
INSERT INTO portfolio.job_experience (description,end_date,picture_url,place,start_date,title,fk_user) VALUES
	 ('2015 - 2020',NULL,'','Autónoma',NULL,'Reparadora de Computadoras',1);


-- Proyects -----
INSERT INTO portfolio.project (description,picture_url,project_title,project_url,fk_user) VALUES
	 ('Sistema de Reservas - Trabajo Práctico Final Cursada PoloTIC Java 2021 COM1',NULL,'Sistema de Reservas','https://github.com/MarianaM02/Madeira_Mariana_TP_Final_Java',1),
	 ('Java Coach es un proyecto API REST con Spring Boot, Hibernate-JPA y MySQL. Su propósito es generar una base de datos con contenido de estudio para el exámen de certificación Java de Oracle',NULL,'Java Coach API','https://github.com/MarianaM02/java-coach-api',1),
	 ('Tienda con carrito utilizando framework Django',NULL,'Tienda Killari','https://github.com/MarianaM02/tp-final-django-polotic-2021',1);

	
-- Skills -----