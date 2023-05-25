/* Comandos para la ejecucion del fichero
 mysql -h 127.0.0.1 -P 3306 -u antoniojoselp -p >>> antonio 
 use SIBW;
 */


/* Borramos las tablas en caso de estar ya creadas */
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS PalabrasProhibidas;
DROP TABLE IF EXISTS Comentario;
DROP TABLE IF EXISTS Imagenes;
DROP TABLE IF EXISTS Producto;

/* Creamos las tablas */

/* Tabla de Producto */
CREATE TABLE Producto(
  id_prod INT AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  precio FLOAT NOT NULL,
  descripcion TEXT NOT NULL,
  foto_portada VARCHAR(100) NOT NULL,
  enlace VARCHAR(100),
  etiquetas VARCHAR(100),
  publicado BOOLEAN NOT NULL,
  PRIMARY KEY(id_prod)
);

/* Tabla de Imagenes */
CREATE TABLE Imagenes(
  id_img INT AUTO_INCREMENT,
  ruta VARCHAR(100),
  id_prod INT NOT NULL,
  pie VARCHAR(100),
  PRIMARY KEY(id_img),
  FOREIGN KEY(id_prod) REFERENCES Producto(id_prod) ON DELETE CASCADE
);



/* Tabla de comentarios */
CREATE TABLE Comentario(
  id_coment INT AUTO_INCREMENT,
  id_prod INT NOT NULL,
  autor VARCHAR(100) NOT NULL,
  comentario VARCHAR(300),
  editado BOOLEAN DEFAULT false,
  fecha DATETIME,
  PRIMARY KEY(id_coment),
  FOREIGN KEY(id_prod) REFERENCES Producto(id_prod) ON DELETE CASCADE
);

/* Tabla de palabras prohibidas */
CREATE TABLE PalabrasProhibidas(
  palabra VARCHAR(50),
  PRIMARY KEY(palabra)
);


/* Tabla de usuarios */
CREATE TABLE Usuario(
  nick VARCHAR(50),
  passw VARCHAR (100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  tipo_usuario ENUM('registrado', 'moderador', 'gestor', 'superusuario') NOT NULL,
  PRIMARY KEY (nick)
);

/* Añadimos algunos elementos por defecto a cada tabla 

*/

INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, publicado) VALUES('Model S',"105970",
  '<p>
    El Tesla Model S es una berlina eléctrica, del segmento E, fabricada por Tesla desde 2012. Aunque se trata de un producto veterano, ha sufrido ligeras actualizaciones con el paso de los años. El Tesla Model S tiene cinco plazas distribuidas en dos filas de asientos, pero opcionalmente se puede equipar con siete plazas gracias a una tercera fila con dos asientos para niños. El Tesla Model S se produce en la planta de fabricación que Tesla tiene en Fremont, California (Estados Unidos).
  </p>
  <p>
    Es una berlina de lujo de cinco puertas. Comercializado desde 2012, cuenta con la máxima calificación en materia de seguridad y, es todo un éxito en materia de ventas dentro y fuera de los Estados Unidos.
  </p>   		
  <p>
    Equipado con un paquete de baterías de 60, 75, 90 o 100 kWh, supera en autonomía al Tesla Roadster, siendo capaz de recorrer más de 400 kilómetros entre carga y carga. El motor va en el eje trasero y las baterías van tumbadas en el suelo. ¿Resultado? Un centro de gravedad más bajo para que la berlina vaya a la misma distancia del asfalto que un deportivo. El Tesla Model S está disponible en dos configuraciones diferentes de tracción: trasera y tracción total con motor dual. Esta última configuración equipa un motor en ambos ejes, monitorizados y controlados digitalmente, que permite una tracción óptima en cualquier situación. El Tesla Model S maximiza la capacidad del bloque de baterias con un diseño aerodinámico de lineas fluidas permitiendo una resistencia menor en el flujo de aire. En el interior llama la atención la pantalla táctil de 17 pulgadas, en ángulo hacia el conductor e incluye tanto los modos de día y de noche para una visibilidad sin distracciones. Cada superficie, tapizado y costura equilibra una óptima sensación táctil y visual, así como el respeto al medio ambiente. Los tiradores de las puertas están hechos de zinc pulido a mano, la tapicería de cuero utiliza cuero napa de alta calidad y las molduras decorativas están ejecutadas para preservar su belleza natural. Los materiales se obtienen cerca de la fábrica en California para reducir el impacto ambiental de su transporte a largas distancias.
  </p>',
  './static/img/coche1-1.jpeg', 'https://www.tesla.com/es_es/models',true);


INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, publicado) VALUES('Model 3',"52970",
  '<p>
    El Model 3 incluye la opción de motor dual y tracción integral, frenos Performance y llantas Überturbine de 20" para disfrutar de un control total en todas las condiciones climáticas. Además, su alerón de fibra de carbono mejora la estabilidad a velocidades altas, lo que permite que el Model 3 acelere de 0 a 100 km/h* en tan solo 3,3 segundos.
  </p>
  <p>
    Las características avanzadas de seguridad y comodidad del Piloto automático se han diseñado para ayudarle con las partes más agotadoras de la conducción.
  </p>
  <p>
    El Model 3 es completamente eléctrico, por lo que nunca tendrá que volver a ir a una estación de servicio. Si lo carga durante la noche en su hogar, se encontrará cada mañana con una batería completamente cargada. Y cuando esté en la carretera, es muy sencillo conectarlo durante el recorrido a cualquier estación pública o a la red de carga de Tesla. Actualmente disponemos de más de 30.000 Supercargadores en todo el mundo, y se inauguran seis ubicaciones nuevas cada semana.
  </p>
  ', 
  "./static/img/coche2-1.jpeg", 'https://www.tesla.com/es_es/model3', true);

INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, publicado) VALUES('Model X',"115870",
  '<p>
    Con la mayor potencia y la aceleración más rápida que cualquier otro SUV, el Model X Plaid es el SUV de mayor rendimiento jamás construido. Los trenes motrices del Model X, junto con la arquitectura de baterías renovada, pueden proporcionar un par motor instantáneo a cualquier velocidad.
  </p>
  <p>
    Las plataformas del Model X aúnan las tecnologías de tren motriz y batería para ofrecer unos niveles de rendimiento, autonomía y eficiencia insuperables. El nuevo módulo rediseñado y la arquitectura térmica permiten una carga más rápida, proporcionándole más potencia y resistencia en todas las condiciones.
  </p>
  <p>
    Con el mayor espacio de almacenamiento y capacidad de remolque de cualquier SUV eléctrico, y asientos para hasta siete adultos, el Model X ofrece la máxima utilidad. Las puertas delanteras se abren y cierran automáticamente, las Puertas de ala de halcón facilitan el cargamento y la bola de remolque de serie le permite llevar sus pertenencias allá dónde vaya.
  </p>', 
  './static/img/coche3-1.jpeg', 'https://www.tesla.com/es_es/modelx', true);

INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, publicado) VALUES('Model Y',"66970",
  '<p>
    Como todos los Tesla, el Model Y está diseñado para ser el coche más seguro de su segmento. El centro de gravedad bajo, la estructura rígida del chasis y las grandes zonas de absorción de impactos ofrecen una protección sin igual.
  </p>
  <p>
    El Model Y ofrece máxima versatilidad: es capaz de transportar 5 pasajeros y su equipaje. Cada asiento de la segunda fila se abate independientemente, creando espacio flexible para esquíes, muebles, equipaje y mucho más. El portón trasero da acceso a un maletero de suelo bajo con una gran facilidad y rapidez de carga y descarga.
  </p>
  <p>
    La tracción a las cuatro ruedas de Tesla dispone de dos motores independientes con una capacidad de respuesta excepcional que controlan digitalmente el par motor de las ruedas delanteras y traseras para una conducción, una tracción y un control de la estabilidad mucho mejores. El Model Y se desenvuelve sin problema en condiciones de lluvia, nieve, barro y todo tipo de terreno.
  </p>', 
  './static/img/coche4-1.jpeg', 'https://www.tesla.com/es_es/modely', true);


INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, publicado) VALUES('Cargardor portátil inalámbrico 2.0',"99",
  '<p>
    No se quede sin carga en la carretera, en su oficina o en su vida diaria con el cargador portátil inalámbrico 2.0. Simplemente coloque su dispositivo con Qi en la base de carga para una carga rápida y segura o un aumento rápido del nivel de la batería. También puede recargar su cargador portátil inalámbrico 2.0 utilizando la base de carga inalámbrica de su vehículo Tesla o cualquier dispositivo de carga inalámbrico. Ofrece hasta 10 000 mAh de potencia de la batería a 37 Wh e incluye un cable Lightning USB-C integrado para una carga con cable opcional.
  </p>
  <p>
    Disponible en Plata medianoche metalizado, Azul oscuro metalizado, Multicapas roja, Blanco perla Multicapas y Negro sólido para combinar con cualquier color de pintura de vehículo Tesla.
  </p>', 
  './static/img/cargador_1.avif', 'https://shop.tesla.com/es_es/product/cargador-portatil-inalambrico-2_0?sku=1625886-00-A', true);



INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, publicado) VALUES('Soporte de techo para techo de cristal del Model S',"460",
  '<p>
    El soporte de techo de cristal del Model S se ha diseñado y fabricado desde cero para maximizar la eficiencia aerodinámica y minimizar el ruido interior y el impacto en la autonomía. El logrado mecanismo de fijación con soportes fundidos y cierres integrados le permite instalarlo sin inconvenientes en su hogar.  
  </p>
  <p>
    Las barras de techo de aluminio recubierto con polvo cuentan con ranuras en T para fijar sin inconvenientes los accesorios compatibles, como portaesquís, portabicicletas y cofres de techo.
  </p>
  <p>
    Se puede adaptar cualquier Model S que se haya pedido antes del 7 de febrero de 2019 para que sea compatible con el soporte de techo. Obtenga más información sobre el soporte de techo de cristal del Model S y sobre cómo equipar su vehículo en nuestra  página de soporte.
  </p>', 
  './static/img/soporte_techo_1.avif', 'https://shop.tesla.com/es_es/product/soporte-de-techo-para-techo-de-cristal-del-model_s', true);



INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, publicado) VALUES('Conector de pared de tercera generación',"549",
  '<p>
    El conector de pared es la solución de carga más conveniente para casas, apartamentos, hostelería y lugares de trabajo.
  </p>
  <p>
    Con hasta 71 km de autonomía añadida por hora de carga, múltiples ajustes de potencia, 7,3 m de longitud y un diseño versátil en interiores y exteriores, el conector de pared ofrece una comodidad sin igual.
  </p>
  <p>
    Los conectores de pared pueden compartir la energía para maximizar la capacidad eléctrica existente, distribuyendo automáticamente la energía para cargar varios coches simultáneamente.
  </p>', 
  './static/img/conector_1.avif', 'https://shop.tesla.com/es_es/product/conector-de-pared-de-tercera-generacion', true);



INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, publicado) VALUES('Umbrales de puerta iluminados del Model S',"250",
  '<p>
    Añada un toque premium a su Tesla al destacar el nombre del modelo de su vehículo con los umbrales de las puertas iluminados del Model S. Esta elegante incorporación no requiere cableado adicional al sistema eléctrico de su vehículo. Cada umbral se activa mediante un interruptor magnético y se alimenta con una batería de larga duración con LED para una instalación sencilla.
  </p>
  <p>
    Incluye: 2x umbrales de puerta iluminados, 2x interruptores magnéticos y plantillas de instalación, 1x herramienta de palanca.
  </p>', 
  './static/img/umbrales_1.avif', 'https://shop.tesla.com/es_es/product/umbrales-de-puerta-iluminados-del-model-s', true);


INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, publicado) VALUES('Protectores interiores para cualquier clima del Model S',"290",
  '<p>
    Los protectores del maletero trasero para cualquier clima del Model S de Tesla, rediseñados recientemente, están hechos a medida usando la última tecnología en mediciones láser digitales para su Model S. Esta excelente protección del suelo cubre y protege su Model S para mantenerlo libre de suciedad y derrames. Las alfombrillas están fabricadas de material elastómero termoplástico, que cuenta con un núcleo rígido para ofrecer una resistencia inigualable y una limpieza sencilla.
  </p>
  <p>
    Entre las mejoras de los protectores interiores se incluyen la adición de parches de retención en las alfombrillas delanteras para facilitar su instalación, así como el aumento de la altura de las paredes para proteger mejor los suelos delanteros del vehículo.
  </p>
  <p>
    Incluye: 1 protector de suelo para el lado del conductor, 1 protector de suelo para el lado del pasajero, 1 protector de suelo de la segunda fila.
  </p>', 
  './static/img/protectores_1.avif', 'https://shop.tesla.com/es_es/product/protectores-interiores-para-cualquier-clima-del-model_s', true);



INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/coche1-1.jpeg', 1, 'Model S Rojo con paisaje.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/coche1-2.jpeg', 1, 'Model S Azul en movimiento.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/coche2-1.jpeg', 2, 'Model 3 Rojo desde arriba.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/coche2-2.jpeg', 2, 'Model 3 Salpicadero.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/coche3-1.jpeg', 3, 'Model X Blanco en movimiento.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/coche3-2.jpeg', 3, 'Model X Blanco con paisaje.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/coche4-1.jpeg', 4, 'Model Y Azul.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/coche4-2.jpeg', 4, 'Model Y Rojo en movimiento.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/cargador_1.avif', 5, 'Cargador en rojo dentro de caja.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/cargador_2.jpg', 5, 'Cargador en negro.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/soporte_techo_1.avif', 6, 'Soporte en model S.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/soporte_techo_2.avif', 6, 'Soporte siendo utilizado para bici.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/conector_1.avif', 7, 'Conector de pared blanco.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/conector_2.avif', 7, 'Conector de pared blanco desde lejos.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/conector_1.avif', 8, 'Umbrales iluminados para model S');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/conector_2.avif', 8, 'Umbrales iluminados para model S en distintta posición.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/protectores_1.avif', 9, 'Protectores para model S.');
INSERT INTO Imagenes(ruta, id_prod, pie) VALUES('./static/img/protectores_2.avif', 9, 'Protector del conductor.');



INSERT INTO Comentario(id_prod, autor, comentario, fecha) VALUES (1, 'Pepito Grillo', 'Aqui pongo mi comentario.', NOW());
INSERT INTO Comentario(id_prod, autor, comentario, fecha) VALUES (2, 'Antonio José', 'Aqui pongo mi comentario.', NOW());
INSERT INTO Comentario(id_prod, autor, comentario, fecha) VALUES (2, 'Naranjito', 'Aqui pongo mi comentario.', NOW());
INSERT INTO Comentario(id_prod, autor, comentario, fecha) VALUES (3, 'Pinocho', 'Aqui pongo mi comentario.', NOW());


INSERT INTO PalabrasProhibidas(palabra) VALUES('culo');
INSERT INTO PalabrasProhibidas(palabra) VALUES('caca');
INSERT INTO PalabrasProhibidas(palabra) VALUES('tonto');
INSERT INTO PalabrasProhibidas(palabra) VALUES('mierda');
INSERT INTO PalabrasProhibidas(palabra) VALUES('puta');
INSERT INTO PalabrasProhibidas(palabra) VALUES('gilipollas');


/* Contraseña: antoniojoselp */
INSERT INTO Usuario(nick, passw, email, tipo_usuario) VALUES ('antoniojoselp', '$2y$10$bSjCCkNJUrgg8YgJKc.SEObdjphM9iWsx9/uzZEYAo6hy4VQl.hj6', 'antoniojoselp@correo.ugr.es', 'superusuario');


