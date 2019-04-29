
drop database if exists songstore;
create database songstore;
use songstore;   
CREATE TABLE Album (
    album_id int NOT NULL auto_increment,
    title varchar(256) NOT NULL,
    release_date date NOT NULL,
    recording_label varchar(256) NOT NULL,
    number_songs int NOT NULL,
    date_added date NOT NULL,
    cost double(4,2) NOT NULL,
    list_price double(4,2) NULL,
    sale_price double(4,2) NULL,
    removal_status bool NOT NULL,
    removal_date date NULL,
    image varchar(250) NULL,
    genre varchar(256) NOT NULL,
    total_sales int,
    CONSTRAINT Album_pk PRIMARY KEY (album_id)
);

-- Table: Artist
CREATE TABLE Artist (
    artist_id int NOT NULL auto_increment,
    name varchar(30) NOT NULL,
image varchar(250) NULL,
    CONSTRAINT Artist_pk PRIMARY KEY (artist_id)
);

-- Table: album_artist
CREATE TABLE album_artist (
    album_artist_id int NOT NULL auto_increment,
    artist_id int NOT NULL,
    album_id int NOT NULL,
    CONSTRAINT album_artist_pk PRIMARY KEY (album_artist_id)
);



-- Table: Invoice
CREATE TABLE Invoice (
    invoice_id int NOT NULL auto_increment,
    date date NOT NULL,
    total_cost double(5,2) NOT NULL,
    pst double(5,2) NOT NULL,
    gst double(5,2) NOT NULL,
    hst double(5,2) NOT NULL,
    total_gross double(5,2) NOT NULL,
    email varchar(50) NOT NULL,
    CONSTRAINT Invoice_pk PRIMARY KEY (invoice_id)
);
-- Table: Cart
CREATE TABLE Cart (
    cart_id int NOT NULL auto_increment,
    email varchar(50) NOT NULL,
    track_id int NULL,
    album_id int NULL,
    invoice_id int NULL,
    CONSTRAINT Cart_pk PRIMARY KEY (cart_id),
    FOREIGN KEY (invoice_id) REFERENCES Invoice(invoice_id)
);

-- Table: News
CREATE TABLE News (
    news_id int NOT NULL auto_increment,
    feed varchar(300) NOT NULL,
    used varchar(1) NOT NULL,
    CONSTRAINT News_pk PRIMARY KEY (news_id)
);

-- Table: Order
CREATE TABLE `Orders` (
    order_id int NOT NULL auto_increment,
    invoice_id int NOT NULL,
    price int NOT NULL,
    
    CONSTRAINT Order_pk PRIMARY KEY (order_id)
);

-- Table: Review
CREATE TABLE Review (
    review_id int NOT NULL auto_increment,
    date date NOT NULL,
    rating int NOT NULL,
    text text NULL,
    is_approved bool NULL,
    email varchar(50) NOT NULL,
    album_id int NULL,
    track_id int NULL,
    CONSTRAINT Review_pk PRIMARY KEY (review_id)
);

-- Table: Survey
CREATE TABLE Survey (
    survey_id int NOT NULL auto_increment,
    question varchar(100) NOT NULL,
    option1 varchar(100) NOT NULL,
    option2 varchar(100) NOT NULL,
    option3 varchar(100) NULL,
    option4 varchar(100) NULL,
    option5 varchar(100) NULL,
    date_created date NOT NULL,
    created_by varchar(50) NOT NULL,
    CONSTRAINT Survey_pk PRIMARY KEY (survey_id)
);

-- Table: Survey_Result
CREATE TABLE Survey_Result (
    survey_result_id int NOT NULL auto_increment,
    email varchar(50) NOT NULL,
    survey_id int NOT NULL,
    answer varchar(100) NOT NULL,
    date_submitted date NOT NULL,
    CONSTRAINT Survey_Result_pk PRIMARY KEY (survey_result_id)
);

-- Table: Track
CREATE TABLE Track (
    track_id int NOT NULL auto_increment,
    selection_number int NULL,
    title varchar(256) NOT NULL,
    songwriter varchar(256) NOT NULL,
    play_length varchar(256) NOT NULL,
    genre varchar(256) NOT NULL,
    album_id int NOT NULL,
    cost double(4,2) NOT NULL,
    list_price double(4,2) NULL,
    sale_price double(4,2) NULL,
    date_added date NOT NULL,
    individual bool NOT NULL,
    removal_status bool NOT NULL,
    removal_date date NULL,
    total_sales int,
    CONSTRAINT Track_pk PRIMARY KEY (track_id)
);


CREATE TABLE User (
    title varchar(30) NOT NULL,
    lastname varchar(30) NOT NULL,
    firstname varchar(30) NOT NULL,
    company_name varchar(30) NULL,
    address1 varchar(30) NULL,
    address2 varchar(30) NULL,
    city varchar(30) NULL,
    province varchar(30) NULL,
    country varchar(30) NULL,
    postal_code varchar(7) NULL,
    home_telephone varchar(10) NULL,
    cellphone varchar(10) NULL,
    email varchar(50) NOT NULL,
    last_genre varchar(30) NULL,
    is_manager bool NULL,
    language varchar(30) NULL,
    hash varchar(300) NOT NULL,
    salt varchar(300) NULL,
    CONSTRAINT User_pk PRIMARY KEY (email)
);


CREATE TABLE Roles (
    email varchar(50) NOT NULL,
    roles varchar(50) NOT NULL,
    CONSTRAINT Groups_pk PRIMARY KEY (email)
);

CREATE TABLE Banner(
    banner_id int NOT NULL auto_increment,
    banner varchar(300) NOT NULL,
    used varchar(1) NOT NULL,
    CONSTRAINT Banner_pk PRIMARY KEY (banner_id) 
);

-- Reference: Cart_Album (table: Cart)
ALTER TABLE Cart ADD CONSTRAINT Cart_Album FOREIGN KEY Cart_Album (album_id)
    REFERENCES Album (album_id);

-- Reference: Cart_Track (table: Cart)
ALTER TABLE Cart ADD CONSTRAINT Cart_Track FOREIGN KEY Cart_Track (track_id)
    REFERENCES Track (track_id);

-- Reference: Cart_User (table: Cart)
ALTER TABLE Cart ADD CONSTRAINT Cart_User FOREIGN KEY Cart_User (email)
    REFERENCES User (email);

-- Reference: Invoice_User (table: Invoice)
ALTER TABLE Invoice ADD CONSTRAINT Invoice_User FOREIGN KEY Invoice_User (email)
    REFERENCES User (email);


-- Reference: Order_Invoice (table: Order)
ALTER TABLE `Orders` ADD CONSTRAINT Order_Invoice FOREIGN KEY Order_Invoice (invoice_id)
    REFERENCES Invoice (invoice_id);


-- Reference: Review_User (table: Review)
ALTER TABLE Review ADD CONSTRAINT Review_User FOREIGN KEY Review_User (email)
    REFERENCES User (email);

-- Reference: Review_Album (table: Review)
ALTER TABLE Review ADD CONSTRAINT Review_Album FOREIGN KEY Review_Album (album_id)
    REFERENCES Album (album_id);

-- Reference: Review_Track (table: Review)
ALTER TABLE Review ADD CONSTRAINT Review_Track FOREIGN KEY Review_Track (track_id)
    REFERENCES Track (track_id);

-- Reference: Survey_Result_Survey (table: Survey_Result)
ALTER TABLE Survey_Result ADD CONSTRAINT Survey_Result_Survey FOREIGN KEY Survey_Result_Survey (survey_id)
    REFERENCES Survey (survey_id);

-- Reference: Survey_Result_User (table: Survey_Result)
ALTER TABLE Survey_Result ADD CONSTRAINT Survey_Result_User FOREIGN KEY Survey_Result_User (email)
    REFERENCES User (email);

-- Reference: Survey_User (table: Survey)
ALTER TABLE Survey ADD CONSTRAINT Survey_User FOREIGN KEY Survey_User (created_by)
    REFERENCES User (email);

-- Reference: Track_Album (table: Track)
ALTER TABLE Track ADD CONSTRAINT Track_Album FOREIGN KEY Track_Album (album_id)
    REFERENCES Album (album_id);

-- Reference: album_artist_Album (table: album_artist)
ALTER TABLE album_artist ADD CONSTRAINT album_artist_Album FOREIGN KEY album_artist_Album (album_id)
    REFERENCES Album (album_id);

-- Reference: album_artist_Artist (table: album_artist)
ALTER TABLE album_artist ADD CONSTRAINT album_artist_Artist FOREIGN KEY album_artist_Artist (artist_id)
    REFERENCES Artist (artist_id);

-- Reference: groups_user_email (table: groups)
ALTER TABLE Roles ADD CONSTRAINT Groups_User FOREIGN KEY Roles (email)
    REFERENCES User (email);
-- End of file.