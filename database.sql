/* Comandos para la ejecucion del fichero
 mysql -h 127.0.0.1 -P 3306 -u antoniojoselp -p >>> antonio 
 use SIBW;
 */


/* Borramos las tablas en caso de estar ya creadas */
DROP TABLE IF EXISTS ShoppingCart;
DROP TABLE IF EXISTS Pedido;
DROP TABLE IF EXISTS Usuario;
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
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY(id_prod)
);

/* Tabla de Imagenes */
CREATE TABLE Imagenes(
  id_img INT AUTO_INCREMENT,
  ruta VARCHAR(100),
  id_prod INT NOT NULL,
  PRIMARY KEY(id_img),
  FOREIGN KEY(id_prod) REFERENCES Producto(id_prod) ON DELETE CASCADE
);






/* Tabla de usuarios */
CREATE TABLE Usuario(
  nick VARCHAR(50),
  passw VARCHAR (500) NOT NULL,
  email VARCHAR(100) NOT NULL,
  admin BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY (nick)
);


/* Tabla de pedidos */
CREATE TABLE Pedido(
  id_order INT AUTO_INCREMENT,
  date Timestamp NOT NULL,
  nick VARCHAR(50) NOT NULL,
  PRIMARY KEY(id_order),
  FOREIGN KEY(nick) REFERENCES Usuario(nick) ON DELETE CASCADE
);

/* Tabla de pedidos */
CREATE TABLE ShoppingCart(
  id INT AUTO_INCREMENT,
  nick VARCHAR(50) NOT NULL,
  id_prod INT NOT NULL,
  quantity INT NOT NULL CHECK (quantity>0),
  id_order INT,
  price FLOAT,
  PRIMARY KEY(id),
  FOREIGN KEY(nick) REFERENCES Usuario(nick) ON DELETE CASCADE,
  FOREIGN KEY(id_prod) REFERENCES Producto(id_prod) ON DELETE CASCADE,
  FOREIGN KEY(id_order) REFERENCES Pedido(id_order) ON DELETE CASCADE
  
);

/* Añadimos algunos elementos por defecto a cada tabla 

*/

INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, active) VALUES('Model S',"105970",
  '<p>
    La Tesla Model S è una berlina elettrica, nel segmento E, prodotta da Tesla dal 2012. Sebbene sia un prodotto veterano, ha subito lievi aggiornamenti nel corso degli anni. La Tesla Model S ha cinque posti distribuiti su due file di sedili, ma può opzionalmente essere equipaggiata con sette posti grazie a una terza fila con due seggiolini per bambini. La Tesla Model S è prodotta nello stabilimento di produzione Tesla a Fremont, California (Stati Uniti).
  </p>
  <p>
    È una berlina di lusso a cinque porte. Commercializzato dal 2012, ha il punteggio più alto in termini di sicurezza ed è un successo in termini di vendite all`interno e all`esterno degli Stati Uniti.
  </p>',
  './immagini/coche1-1.jpeg', 'https://www.tesla.com/es_es/models',true);


INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, active) VALUES('Model 3',"52970",
  '<p>
    La Model 3 include l`opzione di un doppio motore e trazione integrale, freni Performance e pneumatici Überturbine da 20" per un controllo totale in tutte le condizioni atmosferiche. Inoltre, il suo spoiler in fibra di carbonio migliora la stabilità alle alte velocità, consentendo alla Model 3 di accelerare da Da 0 a 100 km/h* in soli 3,3 secondi.
  </p>
  <p>
    Le funzioni avanzate di sicurezza e comfort dell`autopilota sono progettate per aiutarti nelle parti più faticose della guida.
  </p>
  <p>
    La Model 3 è completamente elettrica, quindi non dovrai più recarti a una stazione di servizio. Se lo ricarichi durante la notte a casa, ti ritroverai con una batteria completamente carica ogni mattina. E quando sei in viaggio, è facile connettersi a qualsiasi stazione pubblica o alla rete di ricarica di Tesla mentre sei in viaggio. Al momento disponiamo di oltre 30.000 Supercharger in tutto il mondo, con sei nuove sedi aperte ogni settimana.
  </p>', 
  './immagini/coche2-1.jpeg', 'https://www.tesla.com/es_es/model3', true);

INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, active) VALUES('Model X',"115870",
  '<p>
    Con la massima potenza e l`accelerazione più veloce di qualsiasi SUV, Model X Plaid è il SUV dalle prestazioni più elevate mai costruito. I propulsori della Model X, abbinati a un`architettura della batteria rinnovata, possono fornire una coppia istantanea a qualsiasi velocità.
  </p>
  <p>
    Le piattaforme Model X uniscono le tecnologie di propulsione e batteria per offrire livelli insuperabili di prestazioni, autonomia ed efficienza. Il nuovo modulo riprogettato e l`architettura termica consentono una ricarica più rapida, offrendo maggiore potenza e resistenza in tutte le condizioni.
  </p>
  <p>
    Con lo spazio di stivaggio e la capacità di traino più ampi di qualsiasi SUV elettrico e posti a sedere per un massimo di sette adulti, la Model X offre la massima utilità. Le porte anteriori si aprono e si chiudono automaticamente, le porte ad ala Falcon facilitano il carico e la sfera di traino standard ti consente di portare con te i tuoi effetti personali ovunque tu vada.
  </p>', 
  './immagini/coche3-1.jpeg', 'https://www.tesla.com/es_es/modelx', true);

INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, active) VALUES('Model Y',"66970",
  '<p>
    Come tutte le Tesla, la Model Y è progettata per essere l`auto più sicura del suo segmento. Il baricentro basso, la struttura rigida del telaio e le ampie zone di assorbimento degli urti offrono una protezione senza pari.
  </p>
  <p>
    La Model Y offre la massima versatilità: è in grado di trasportare 5 passeggeri e i loro bagagli. Ogni sedile della seconda fila si ripiega in modo indipendente, creando uno spazio flessibile per sci, mobili, bagagli e altro ancora. Il portellone posteriore dà accesso ad un bagagliaio a pianale ribassato con grande facilità e rapidità di carico e scarico.
  </p>
  <p>
    La trazione integrale di Tesla è dotata di due motori indipendenti eccezionalmente reattivi che controllano digitalmente la coppia alle ruote anteriori e posteriori per migliorare notevolmente la maneggevolezza, la trazione e il controllo della stabilità. La Model Y si comporta senza problemi sotto pioggia, neve, fango e tutti i tipi di terreno.
  </p>', 
  './immagini/coche4-1.jpeg', 'https://www.tesla.com/es_es/modely', true);


INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, active) VALUES('Caricabatterie portatile wireless 2.0',"99",
  '<p>
    Non rimanere senza energia in viaggio, in ufficio o nella vita di tutti i giorni con il caricabatterie portatile wireless 2.0. Posiziona semplicemente il tuo dispositivo abilitato Qi sulla base di ricarica per una ricarica rapida e sicura o un rapido aumento della batteria. Puoi anche ricaricare il tuo caricabatterie portatile wireless 2.0 utilizzando il dock di ricarica wireless del tuo veicolo Tesla o qualsiasi dispositivo di ricarica wireless. Offre fino a 10.000 mAh di carica della batteria da 37 Wh e include un cavo Lightning USB-C integrato per la ricarica cablata opzionale.
  </p>
  <p>
    Disponibile in Midnight Silver Metallic, Dark Blue Metallic, Red Multi-Coat, Pearl White Multi-Coat e Solid Black per abbinarsi a qualsiasi colore di vernice per veicoli Tesla.
  </p>', 
  './immagini/cargador_1.avif', 'https://shop.tesla.com/es_es/product/cargador-portatil-inalambrico-2_0?sku=1625886-00-A', true);



INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, active) VALUES('Portapacchi per tetto in vetro Model S',"460",
  '<p>
    Il portapacchi in vetro della Model S è stato progettato e realizzato da zero per massimizzare l`efficienza aerodinamica e ridurre al minimo il rumore interno e l`impatto sull`autonomia. L`intelligente meccanismo di fissaggio con staffe pressofuse e chiusure integrate ti consente di installarlo senza problemi nella tua casa.  
  </p>
  <p>
    Le barre del tetto in alluminio verniciato a polvere sono dotate di scanalature a T per fissare senza problemi accessori compatibili come portasci, portabici e box da tetto.
  </p>
  <p>
    Qualsiasi Model S ordinata prima del 7 febbraio 2019 può essere adattata per essere compatibile con il portapacchi. Scopri di più sul portapacchi in vetro Model S e su come allestire il tuo veicolo sulla nostra pagina di supporto.
  </p>', 
  './immagini/soporte_techo_1.avif', 'https://shop.tesla.com/es_es/product/soporte-de-techo-para-techo-de-cristal-del-model_s', true);



INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, active) VALUES('Connettore a parete di terza generazione',"549",
  '<p>
    Il Wall Connector è la soluzione di ricarica più conveniente per case, appartamenti, strutture ricettive e luoghi di lavoro.
  </p>
  <p>
    Con fino a 45 miglia di autonomia aggiuntiva all`ora di carica, più impostazioni di alimentazione, 25` di lunghezza e un design versatile per interni ed esterni, il connettore a parete offre una praticità senza pari.
  </p>
  <p>
    I Wall Connector possono condividere l`energia per massimizzare la capacità elettrica esistente, distribuendo automaticamente l`energia per ricaricare più auto contemporaneamente.
  </p>', 
  './immagini/conector_1.avif', 'https://shop.tesla.com/es_es/product/conector-de-pared-de-tercera-generacion', true);



/*
INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, active) VALUES('Ombrelli da porta illuminati del Model S',"250",
  '<p>
    Aggiungi un tocco premium alla tua Tesla per togliere il nome del modello del tuo veicolo con gli ombrelli delle porte illuminate del Model S. Questa elegante incorporazione non richiede cavi aggiuntivi al sistema elettrico del tuo veicolo. Ogni ombra si attiva mediante un interruttore magnetico e si alimenta con una batteria a lunga durata con LED per un`installazione senza fili.
  </p>
  <p>
    Include: 2 battitacco illuminati, 2 interruttori magnetici e modelli di installazione, 1 leva.
  </p>', 
  './immagini/umbrales_1.avif', 'https://shop.tesla.com/es_es/product/umbrales-de-puerta-iluminados-del-model-s', true);


INSERT INTO Producto(nombre, precio, descripcion, foto_portada, enlace, active) VALUES('Protezioni interne per tutte le stagioni della Model S',"290",
  '<p>
    Le fodere del bagagliaio posteriore Tesla Model S All Weather recentemente riprogettate sono realizzate su misura utilizzando la più recente tecnologia nelle misurazioni laser digitali per la tua Model S. Questa protezione del pavimento premium copre e protegge la tua Model S per mantenerla priva di sporco e fuoriuscite. I tappetini sono realizzati in materiale elastomerico termoplastico, che ha un`anima rigida per una resistenza senza pari e una facile pulizia.
  </p>
  <p>
    I miglioramenti al rivestimento interno includono l`aggiunta di toppe di fissaggio ai tappetini anteriori per facilitare l`installazione, nonché l`aumento dell`altezza delle pareti laterali per proteggere meglio i pavimenti anteriori del veicolo.
  </p>
  <p>
    Include: 1 protezione pavimento lato conducente, 1 protezione pavimento lato passeggero, 1 protezione pavimento 2a fila.
  </p>', 
  './static/img/protectores_1.avif', 'https://shop.tesla.com/es_es/product/protectores-interiores-para-cualquier-clima-del-model_s', true);
*/


INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/coche1-1.jpeg', 1);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/coche1-2.jpeg', 1);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/coche2-1.jpeg', 2);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/coche2-2.jpeg', 2);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/coche3-1.jpeg', 3);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/coche3-2.jpeg', 3);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/coche4-1.jpeg', 4);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/coche4-2.jpeg', 4);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/cargador_1.avif', 5);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/cargador_2.jpg', 5);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/soporte_techo_1.avif', 6);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/soporte_techo_2.avif', 6);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/conector_1.avif', 7);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./immagini/conector_2.avif', 7);
/*
INSERT INTO Imagenes(ruta, id_prod) VALUES('./static/img/conector_1.avif', 8);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./static/img/conector_2.avif', 8);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./static/img/protectores_1.avif', 9);
INSERT INTO Imagenes(ruta, id_prod) VALUES('./static/img/protectores_2.avif', 9);
*/





/* Contraseña: antonio */
INSERT INTO Usuario(nick, passw, email, admin) VALUES ('antonio', '6fc10570c810f24685e42b7db25ab335cd79e38f4f0a0ed41312a4dbfbe272d9ebafff097ca499d195e1466e4047fcec9a4a4fa287ac1a0866155f8e1d1871ae', 'antoniojoselp@correo.ugr.es', true);


