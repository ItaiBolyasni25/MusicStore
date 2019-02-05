drop IF EXISTS database songstore;
create database songstore;
DROP USER IF EXISTS songstore@localhost;
CREATE USER songstore@'localhost' IDENTIFIED WITH mysql_native_password BY 'dawson123' REQUIRE NONE;
GRANT ALL ON songstore.* TO songstore@'localhost';
FLUSH PRIVILEGES;
Use songstore;
CREATE TABLE Album (
    album_id int NOT NULL auto_increment,
    title varchar(256) NOT NULL,
    release_date date NOT NULL,
    recording_label varchar(30) NOT NULL,
    number_songs int NOT NULL,
    date_added date NOT NULL,
    cost double(4,2) NOT NULL,
    list_price double(4,2) NULL,
    sale_price double(4,2) NULL,
    removal_status bool NOT NULL,
    removal_date date NULL,
    image blob NULL,
    CONSTRAINT Album_pk PRIMARY KEY (album_id)
);

-- Table: Album_Review
CREATE TABLE Album_Review (
    album_review_id int NOT NULL auto_increment,
    album_id int NOT NULL,
    review_id int NOT NULL,
    CONSTRAINT Album_Review_pk PRIMARY KEY (album_review_id)
);

-- Table: Artist
CREATE TABLE Artist (
    artist_id int NOT NULL auto_increment,
    name varchar(30) NOT NULL,
    CONSTRAINT Artist_pk PRIMARY KEY (artist_id)
);

-- Table: Artist_Album
CREATE TABLE Artist_Album (
    artist_album_id int NOT NULL auto_increment,
    artist_id int NOT NULL,
    album_id int NOT NULL,
    CONSTRAINT Artist_Album_pk PRIMARY KEY (artist_album_id)
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
    user_id int NOT NULL,
    CONSTRAINT Invoice_pk PRIMARY KEY (invoice_id)
);

-- Table: News
CREATE TABLE News (
    news_id int NOT NULL auto_increment,
    user_id int NOT NULL,
    headline varchar(50) NOT NULL,
    description text NOT NULL,
    link varchar(300) NOT NULL,
    date_created date NOT NULL,
    image_url varchar(300) NULL,
    CONSTRAINT News_pk PRIMARY KEY (news_id)
);

-- Table: Order
CREATE TABLE `Order` (
    order_id int NOT NULL auto_increment,
    invoice_id int NOT NULL,
    price int NOT NULL,
    track_id int NOT NULL,
    album_id int NOT NULL,
    CONSTRAINT Order_pk PRIMARY KEY (order_id)
);

-- Table: Review
CREATE TABLE Review (
    review_id int NOT NULL auto_increment,
    date date NOT NULL,
    rating int NOT NULL,
    text text NULL,
    is_approved bool NOT NULL,
    user_id int NOT NULL,
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
    created_by int NOT NULL,
    CONSTRAINT Survey_pk PRIMARY KEY (survey_id)
);

-- Table: Survey_Result
CREATE TABLE Survey_Result (
    survey_result_id int NOT NULL auto_increment,
    user_id int NOT NULL,
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
    genre varchar(30) NOT NULL,
    album_id int NOT NULL,
    cost double(4,2) NOT NULL,
    list_price double(4,2) NULL,
    sale_price double(4,2) NULL,
    date_added date NOT NULL,
    individual bool NOT NULL,
    removal_status bool NOT NULL,
    removal_date date NULL,
    CONSTRAINT Track_pk PRIMARY KEY (track_id)
);

-- Table: Track_Review
CREATE TABLE Track_Review (
    track_review_id int NOT NULL auto_increment,
    track_id int NOT NULL,
    review_id int NOT NULL,
    CONSTRAINT Track_Review_pk PRIMARY KEY (track_review_id)
);

-- Table: User
CREATE TABLE User (
    user_id int NOT NULL auto_increment,
    title varchar(30) NOT NULL,
    lastname varchar(30) NOT NULL,
    firstname varchar(30) NOT NULL,
    company_name varchar(30) NOT NULL,
    address1 varchar(30) NOT NULL,
    address2 varchar(30) NULL,
    city varchar(30) NOT NULL,
    province varchar(30) NOT NULL,
    country varchar(30) NOT NULL,
    postal_code varchar(7) NOT NULL,
    home_telephone varchar(10) NULL,
    cellphone varchar(10) NULL,
    email varchar(50) NOT NULL,
    last_genre varchar(30) NOT NULL,
    is_manager bool NOT NULL,
    language varchar(30) NULL,
    hash varchar(300) NOT NULL,
    salt varchar(300) NULL,
    CONSTRAINT User_pk PRIMARY KEY (user_id)
);

-- foreign keys
-- Reference: Album_Review_Album (table: Album_Review)
ALTER TABLE Album_Review ADD CONSTRAINT Album_Review_Album FOREIGN KEY Album_Review_Album (album_id)
    REFERENCES Album (album_id);

-- Reference: Album_Review_Review (table: Album_Review)
ALTER TABLE Album_Review ADD CONSTRAINT Album_Review_Review FOREIGN KEY Album_Review_Review (review_id)
    REFERENCES Review (review_id);

-- Reference: Invoice_User (table: Invoice)
ALTER TABLE Invoice ADD CONSTRAINT Invoice_User FOREIGN KEY Invoice_User (user_id)
    REFERENCES User (user_id);

-- Reference: News_User (table: News)
ALTER TABLE News ADD CONSTRAINT News_User FOREIGN KEY News_User (user_id)
    REFERENCES User (user_id);

-- Reference: Order_Album (table: Order)
ALTER TABLE `Order` ADD CONSTRAINT Order_Album FOREIGN KEY Order_Album (album_id)
    REFERENCES Album (album_id);

-- Reference: Order_Invoice (table: Order)
ALTER TABLE `Order` ADD CONSTRAINT Order_Invoice FOREIGN KEY Order_Invoice (invoice_id)
    REFERENCES Invoice (invoice_id);

-- Reference: Order_Track (table: Order)
ALTER TABLE `Order` ADD CONSTRAINT Order_Track FOREIGN KEY Order_Track (track_id)
    REFERENCES Track (track_id);

-- Reference: Review_User (table: Review)
ALTER TABLE Review ADD CONSTRAINT Review_User FOREIGN KEY Review_User (user_id)
    REFERENCES User (user_id);

-- Reference: Survey_Result_Survey (table: Survey_Result)
ALTER TABLE Survey_Result ADD CONSTRAINT Survey_Result_Survey FOREIGN KEY Survey_Result_Survey (survey_id)
    REFERENCES Survey (survey_id);

-- Reference: Survey_Result_User (table: Survey_Result)
ALTER TABLE Survey_Result ADD CONSTRAINT Survey_Result_User FOREIGN KEY Survey_Result_User (user_id)
    REFERENCES User (user_id);

-- Reference: Survey_User (table: Survey)
ALTER TABLE Survey ADD CONSTRAINT Survey_User FOREIGN KEY Survey_User (created_by)
    REFERENCES User (user_id);

-- Reference: Track_Album (table: Track)
ALTER TABLE Track ADD CONSTRAINT Track_Album FOREIGN KEY Track_Album (album_id)
    REFERENCES Album (album_id);

-- Reference: Track_Review_Review (table: Track_Review)
ALTER TABLE Track_Review ADD CONSTRAINT Track_Review_Review FOREIGN KEY Track_Review_Review (review_id)
    REFERENCES Review (review_id);

-- Reference: Track_Review_Track (table: Track_Review)
ALTER TABLE Track_Review ADD CONSTRAINT Track_Review_Track FOREIGN KEY Track_Review_Track (track_id)
    REFERENCES Track (track_id);

-- Reference: artist_album_Album (table: Artist_Album)
ALTER TABLE Artist_Album ADD CONSTRAINT artist_album_Album FOREIGN KEY artist_album_Album (album_id)
    REFERENCES Album (album_id);

-- Reference: artist_album_Artist (table: Artist_Album)
ALTER TABLE Artist_Album ADD CONSTRAINT artist_album_Artist FOREIGN KEY artist_album_Artist (artist_id)
    REFERENCES Artist (artist_id);

-- End of file.
