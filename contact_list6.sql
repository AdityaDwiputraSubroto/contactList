CREATE TABLE `users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `password` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
);
INSERT INTO `users` VALUES (1,'admin','admin','admin'),(2,'janjan','janjan','janjan'),(3,'ada','ada','ada'),(5,'jojo','jojo','jojo'),(6,'ede','ede','ede'),(7,'ate','ate','ate'),(8,'adad','adad','adad'),(9,'qe','qe','qe');


CREATE TABLE `contacts` (
  `id_contact` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `image` varchar(45) DEFAULT NULL,
  `address` varchar(120) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_contact`),
  KEY `id_user_idx` (`id_user`),
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`)
) ;
INSERT INTO `contacts` VALUES (2,'Janjan','0849374928','jana@gmail.com','image2.jpeg','Jalan bangau 5','Female',2),(6,'asdasd','3613623','adad','image4.jpeg','asdad','Male',1),(9,'Tonoada','0257820567346','ton@gmail.com','trash-bin.png','Jalan ajad 7 236asdadsadasdasdadasdasdasd asdasdasdadasdasdada','Female',1),(16,'afafae','5684845457','asfafsa','image3.jpeg','asfasfadasda','Male',1),(22,'Tonoada','0257820567346','ton@gmail.com','renshu#extra15.pngcopy.png','Jalan ajad 7 236asdadsadasdasdadasdasdasd asdasdasdadasdasdada','Male',1),(23,'ada','0846462587','ada@gmail.com','renshu#extra18.png','adadadadadd','Male',1),(28,'ada','ada','ada','renshu#extra18.png','ada','Male',3),(29,'adate','0813914','adate@gmail.com','renshu#extra17 copy.png','adate','Male',3),(32,'ada','086885','ada@gm.com','renshu#extra18.png','adaad','Female',3),(33,'ada','08685474','jojo@gmail.com','trash-bin.png','adadad','Male',5),(34,'ttataa','087957373689','tata@gmail.com','renshu#extra15.pngcopy.png','adasdadsafa','Male',5),(35,'ttata','0879573736','tata@gmail.com','renshu#extra18.png','adasdads','Male',5),(36,'eqee','08895846','qe@gmail.com','renshu#extra15.pngcopy.png','qeqeqe','Female',9),(38,'adada','0965848','ad@gmail.com','1684904029929_renshu#extra15.pngcopy.png','adsasgfqawgags','Male',3);

