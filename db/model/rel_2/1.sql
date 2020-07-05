-- Generated by Oracle SQL Developer Data Modeler 19.4.0.350.1424
--   at:        2020-07-04 21:26:22 CEST
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



CREATE SEQUENCE category_id_sequence START WITH 1 INCREMENT BY 1 CACHE 100 ORDER;

CREATE SEQUENCE complaint_id_sequence START WITH 1 INCREMENT BY 1 CACHE 100 ORDER;

CREATE SEQUENCE delivery_id_sequence START WITH 1 INCREMENT BY 1 CACHE 100 ORDER;

CREATE SEQUENCE order_id_sequence START WITH 1 INCREMENT BY 1 CACHE 100 ORDER;

CREATE SEQUENCE product_id_sequence START WITH 1 INCREMENT BY 1 CACHE 100 ORDER;

CREATE SEQUENCE promotion_id_sequence START WITH 1 INCREMENT BY 1 CACHE 100 ORDER;

CREATE SEQUENCE delivery_company_id_sequence START WITH 1 INCREMENT BY 1 CACHE 100 ORDER;

CREATE SEQUENCE customer_id_sequence START WITH 1 INCREMENT BY 1 CACHE 100 ORDER;

CREATE SEQUENCE picture_id_sequence START WITH 1 INCREMENT BY 1 CACHE 100 ORDER;

CREATE TABLE category (
    category_id         NUMBER(3) NOT NULL,
    category_name       VARCHAR2(30 CHAR) NOT NULL,
    parent_category_id  NUMBER(3)
);

CREATE INDEX parent_category_id_idx ON
    category (
        parent_category_id
    ASC );

ALTER TABLE category ADD CONSTRAINT category_pk PRIMARY KEY ( category_id );

ALTER TABLE category ADD CONSTRAINT category_name_uk UNIQUE ( category_name );

CREATE TABLE complaint (
    complaint_id    NUMBER(5) NOT NULL,
    complaint_time  TIMESTAMP NOT NULL,
    handling_time   TIMESTAMP
);

ALTER TABLE complaint ADD CONSTRAINT complaint_pk PRIMARY KEY ( complaint_id );

CREATE TABLE complaints_for_orders (
    order_id      NUMBER(7) NOT NULL,
    complaint_id  NUMBER(5) NOT NULL
);

CREATE INDEX complaint_id_idx ON
    complaints_for_orders (
        complaint_id
    ASC );

ALTER TABLE complaints_for_orders ADD CONSTRAINT complaints_for_orders_pk PRIMARY KEY ( order_id,
                                                                                        complaint_id );

CREATE TABLE customer (
    customer_id   NUMBER(7) NOT NULL,
    email         VARCHAR2(50 CHAR) NOT NULL,
    surname       VARCHAR2(30 CHAR) NOT NULL,
    first_name    VARCHAR2(30 CHAR) NOT NULL,
    city          VARCHAR2(30 CHAR) NOT NULL,
    postal_code   VARCHAR2(6 CHAR) NOT NULL,
    house_number  NUMBER(5) NOT NULL,
    phone_number  VARCHAR2(15 CHAR) NOT NULL,
    street        VARCHAR2(30 CHAR) NOT NULL,
    flat_number   NUMBER(5),
    password      VARCHAR2(30 CHAR)
);

ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( customer_id );

ALTER TABLE customer ADD CONSTRAINT customer_email_uk UNIQUE ( email );

CREATE TABLE delivery (
    delivery_id          NUMBER(6) NOT NULL,
    price                NUMBER(5, 2) NOT NULL,
    delivery_time        DATE NOT NULL,
    delivery_company_id  NUMBER(2) NOT NULL
);

CREATE INDEX delivery_company_name_idx ON
    delivery (
        delivery_company_id
    ASC );

ALTER TABLE delivery ADD CONSTRAINT delivery_pk PRIMARY KEY ( delivery_id );

CREATE TABLE delivery_company (
    delivery_company_id    NUMBER(2) NOT NULL,
    delivery_company_name  VARCHAR2(15 CHAR) NOT NULL
);

ALTER TABLE delivery_company ADD CONSTRAINT delivery_company_pk PRIMARY KEY ( delivery_company_id );

ALTER TABLE delivery_company ADD CONSTRAINT delivery_company_name_uk UNIQUE ( delivery_company_name );

CREATE TABLE Orders (
    order_id     NUMBER(7) NOT NULL,
    delivery_id  NUMBER(6) NOT NULL,
    customer_id  NUMBER(7) NOT NULL,
    cost_of_products NUMBER(6,2) NOT NULL,
    cost_of_delivery NUMBER(6,2) NOT NULL,
    final_cost NUMBER(6,2) NOT NULL
);

CREATE INDEX order_delivery_id_idx ON
    Orders (
        delivery_id
    ASC );

CREATE INDEX order_customer_id_idx ON
    Orders (
        customer_id
    ASC );

ALTER TABLE Orders ADD CONSTRAINT order_pk PRIMARY KEY ( order_id );

CREATE TABLE picture (
    photo       BLOB NOT NULL,
    product_id  NUMBER(7) NOT NULL,
    picture_id  NUMBER(10) NOT NULL
);

ALTER TABLE picture ADD CONSTRAINT picture_pk PRIMARY KEY ( picture_id );

CREATE TABLE product (
    product_id       NUMBER(7) NOT NULL,
    product_name     VARCHAR2(50 CHAR) NOT NULL,
    product_price    NUMBER(8, 2) NOT NULL,
    amount_in_stock  NUMBER(6) NOT NULL,
    description      VARCHAR2(1000 CHAR) NOT NULL,
    sold_amount      NUMBER(6),
    weight           NUMBER(6, 2),
    vendor           VARCHAR2(30),
    production_year  NUMBER(4),
    "size"           VARCHAR2(50 CHAR),
    category_id      NUMBER(3) NOT NULL
);

CREATE INDEX product_category_id_idx ON
    product (
        category_id
    ASC );

ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( product_id );

CREATE TABLE product_order (
    amount_of_ordered_products  NUMBER(6) NOT NULL,
    product_id                  NUMBER(7) NOT NULL,
    order_id                    NUMBER(7) NOT NULL
);

CREATE INDEX product_order_order_id_idx ON
    product_order (
        order_id
    ASC );

ALTER TABLE product_order ADD CONSTRAINT product_order_pk PRIMARY KEY ( product_id,
                                                                        order_id );

CREATE TABLE promotion (
    promotion_id  NUMBER(4) NOT NULL,
    product_id    NUMBER(7),
    category_id   NUMBER(3),
    percentage    NUMBER(5, 2) NOT NULL,
    description   VARCHAR2(150 CHAR),
    deadline      TIMESTAMP,
    delivery_id   NUMBER(6)
);

ALTER TABLE promotion
    ADD CONSTRAINT one_promotion CHECK ( ( ( category_id IS NOT NULL )
                                           AND ( product_id IS NULL ) )
                                         OR ( ( product_id IS NOT NULL )
                                              AND ( category_id IS NULL ) )
                                         OR ( ( category_id IS NULL )
                                              AND ( product_id IS NULL ) ) );

CREATE INDEX product_id_idx ON
    promotion (
        product_id
    ASC );

CREATE INDEX delivery_id_idx ON
    promotion (
        delivery_id
    ASC );

CREATE INDEX category_id_idx ON
    promotion (
        category_id
    ASC );

ALTER TABLE promotion ADD CONSTRAINT promotion_pk PRIMARY KEY ( promotion_id );

ALTER TABLE category
    ADD CONSTRAINT category_category_fk FOREIGN KEY ( parent_category_id )
        REFERENCES category ( category_id );

ALTER TABLE complaints_for_orders
    ADD CONSTRAINT complaints_for_orders_order_fk FOREIGN KEY ( order_id )
        REFERENCES Orders ( order_id )
            ON DELETE CASCADE;

ALTER TABLE complaints_for_orders
    ADD CONSTRAINT complaintsfororderscomplaintfk FOREIGN KEY ( complaint_id )
        REFERENCES complaint ( complaint_id )
            ON DELETE CASCADE;

ALTER TABLE delivery
    ADD CONSTRAINT delivery_delivery_company_fk FOREIGN KEY ( delivery_company_id )
        REFERENCES delivery_company ( delivery_company_id );

ALTER TABLE Orders
    ADD CONSTRAINT order_customer_fk FOREIGN KEY ( customer_id )
        REFERENCES customer ( customer_id )
            ON DELETE CASCADE;

ALTER TABLE Orders
    ADD CONSTRAINT order_delivery_fk FOREIGN KEY ( delivery_id )
        REFERENCES delivery ( delivery_id );

ALTER TABLE picture
    ADD CONSTRAINT picture_product_fk FOREIGN KEY ( product_id )
        REFERENCES product ( product_id )
            ON DELETE CASCADE;

ALTER TABLE product
    ADD CONSTRAINT product_category_fk FOREIGN KEY ( category_id )
        REFERENCES category ( category_id );

ALTER TABLE product_order
    ADD CONSTRAINT product_order_order_fk FOREIGN KEY ( order_id )
        REFERENCES Orders ( order_id )
            ON DELETE CASCADE;

ALTER TABLE product_order
    ADD CONSTRAINT product_order_product_fk FOREIGN KEY ( product_id )
        REFERENCES product ( product_id )
            ON DELETE CASCADE;

ALTER TABLE promotion
    ADD CONSTRAINT promotion_category_fk FOREIGN KEY ( category_id )
        REFERENCES category ( category_id )
            ON DELETE CASCADE;

ALTER TABLE promotion
    ADD CONSTRAINT promotion_delivery_fk FOREIGN KEY ( delivery_id )
        REFERENCES delivery ( delivery_id )
            ON DELETE CASCADE;

ALTER TABLE promotion
    ADD CONSTRAINT promotion_product_fk FOREIGN KEY ( product_id )
        REFERENCES product ( product_id )
            ON DELETE CASCADE;
            
CREATE OR REPLACE TRIGGER fkntm_complaints_for_order 
    BEFORE UPDATE OF complaint_id, order_id ON complaints_for_orders 
    FOR EACH ROW 
    WHEN ( new.complaint_id <> old.complaint_id or new.order_id <> old.order_id ) 
BEGIN
    raise_application_error(-20225, 'Non Transferable FK constraint  on table Complaints_For_Orders is violated');
END; 
/

CREATE OR REPLACE TRIGGER fkntm_product_order 
    BEFORE UPDATE OF product_id, order_id ON product_order 
    FOR EACH ROW 
    WHEN ( new.product_id <> old.product_id or new.order_id <> old.order_id ) 
BEGIN
    raise_application_error(-20225, 'Non Transferable FK constraint  on table Product_Order is violated');
END; 
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            11
-- CREATE INDEX                            10
-- ALTER TABLE                             28
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           2
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          6
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0

