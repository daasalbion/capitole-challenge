create table PUBLIC.BRANDS
(
    ID         BIGINT auto_increment primary key,
    NAME       CHARACTER VARYING                not null,
    CREATED_AT TIMESTAMP default LOCALTIMESTAMP not null,
    UPDATED_AT TIMESTAMP on update LOCALTIMESTAMP
);

create table PUBLIC.PRODUCTS
(
    ID         BIGINT auto_increment primary key,
    NAME       CHARACTER VARYING                not null,
    PRICE      DOUBLE                           NOT NULL,
    CREATED_AT TIMESTAMP default LOCALTIMESTAMP not null,
    UPDATED_AT TIMESTAMP on update LOCALTIMESTAMP
);

create table PUBLIC.PRICE_LIST
(
    ID         BIGINT auto_increment primary key,
    PRICE      DOUBLE    NOT NULL,
    START_DATE TIMESTAMP not null,
    END_DATE   TIMESTAMP not null
);

create table PUBLIC.PRICES
(
    ID         INT auto_increment primary key,
    BRAND_ID   INT                  not null,
    START_DATE TIMESTAMP            not null,
    END_DATE   TIMESTAMP            not null,
    PRICE_LIST INT                  NOT NULL,
    PRODUCT_ID INT                  not null,
    PRIORITY   INT                  not null,
    PRICE      DOUBLE               NOT NULL,
    CURR       CHARACTER VARYING(5) NOT NULL,

    constraint FK_PRICES_BRANDS
        foreign key (BRAND_ID)
            references BRANDS (ID),
    constraint FK_PRICES_PRODUCTS
        foreign key (PRODUCT_ID)
            references PRODUCTS (ID)
);
INSERT INTO BRANDS (ID, NAME) VALUES (1, 'ZARA' );
INSERT INTO PRODUCTS (ID, NAME, PRICE) VALUES (35455, 'SHOES', 50.00);
INSERT INTO PUBLIC.PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1, TIMESTAMP '2020-06-14 00:00:00', TIMESTAMP '2020-12-31 23:59:59', 1, 35455, 0, 35.5, 'EUR'),
       (1, TIMESTAMP '2020-06-14 15:00:00', TIMESTAMP '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
       (1, TIMESTAMP '2020-06-15 00:00:00', TIMESTAMP '2020-06-15 11:00:00', 3, 35455, 1, 30.5, 'EUR'),
       (1, TIMESTAMP '2020-06-15 16:00:00', TIMESTAMP '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');